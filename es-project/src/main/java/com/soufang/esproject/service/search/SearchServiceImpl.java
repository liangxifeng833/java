package com.soufang.esproject.service.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import com.soufang.esproject.base.HouseSort;
import com.soufang.esproject.base.RentValueBlock;
import com.soufang.esproject.entity.House;
import com.soufang.esproject.entity.HouseDetail;
import com.soufang.esproject.entity.HouseTag;
import com.soufang.esproject.entity.SupportAddress;
import com.soufang.esproject.repository.HouseDetailResposity;
import com.soufang.esproject.repository.HouseReposity;
import com.soufang.esproject.repository.HouseTagRepository;
import com.soufang.esproject.repository.SupportAddressRepository;
import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.service.house.IAddressService;
import com.soufang.esproject.web.form.MapSearch;
import com.soufang.esproject.web.form.RentSearch;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



/**
 * Created by 瓦力.
 */
@Service
public class SearchServiceImpl implements ISearchService {
    private static final Logger logger = LoggerFactory.getLogger(ISearchService.class);
    private static final String INDEX_NAME = "xunwu";

    private static final String INDEX_TYPE = "house";

    //kafka主题
    private static final String INDEX_TOPIC = "house_build";


    @Autowired
    private HouseReposity houseRepository;

    @Autowired
    private HouseDetailResposity houseDetailRepository;

    @Autowired
    private HouseTagRepository tagRepository;

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private IAddressService addressService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private TransportClient esClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    //kafka监听主题
    @KafkaListener(topics = INDEX_TOPIC)
    private void handleMessage(String content) {
        try {
            //将字符串content转换为HouseIndexMessage类型
            HouseIndexMessage message = objectMapper.readValue(content,HouseIndexMessage.class);
            switch (message.getOperation()) {
                case HouseIndexMessage.INDEX:
                    this.createOrUpdateIndex(message); //创建或修改es中的索引
                    break;
                case HouseIndexMessage.REMOVE:
                    this.removeIndex(message); //删除es中的索引
                    break;
                default:
                    logger.warn("Not support message content" +content);
                    break;
            }
        } catch (IOException e) {
            logger.error("Cannot parse json for " + content,e);
        }
    }

    /**
     * 通过kafka消费者取出的消息对es索引进行创建或修改
     * @param message
     */
    private void createOrUpdateIndex(HouseIndexMessage message) {
        long houseId = message.getHouseId();
        House house = houseRepository.findOne(houseId);
        //如果数据库中没有查询到房源信息,则调用index()方法向kafka中生产消息,最多尝试3次,否则终止操作
        if(house == null){
            logger.error("Index house {} does not exist!",houseId);
            this.index(houseId,message.getRetry()+1);
            return;
        }
        HouseIndexTemplate indexTemplate = new HouseIndexTemplate();
        modelMapper.map(house,indexTemplate);
        HouseDetail detail = houseDetailRepository.findByHouseId(houseId);
        if (detail == null) {
            // TODO 异常情况
        }
        modelMapper.map(detail, indexTemplate);


        /**
         * 通过百度地图接口查询经纬度

        //查询城市
        SupportAddress city = supportAddressRepository.findByEnNameAndLevel(house.getCityEnName(), SupportAddress.Level.CITY.getValue());
        //查询地区
        SupportAddress region = supportAddressRepository.findByEnNameAndLevel(house.getRegionEnName(), SupportAddress.Level.REGION.getValue());
        //拼接具体地址
        String address = city.getCnName() + region.getCnName() + house.getStreet() + house.getDistrict() + detail.getDetailAddress();
        ServiceResult<BaiduMapLocation> location = addressService.getBaiduMapLocation(city.getCnName(), address);
        if (!location.isSuccess()) {
            this.index(message.getHouseId(), message.getRetry() + 1);
            return;
        }
        indexTemplate.setLocation(location.getResult());*/

        List<HouseTag> tags = tagRepository.findAllByHouseId(houseId);
        if (tags != null && !tags.isEmpty()) {
            List<String> tagStrings = new ArrayList<>();
            tags.forEach(houseTag -> { tagStrings.add(houseTag.getName());});
            indexTemplate.setTags(tagStrings);
        }

        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE)
                .setQuery(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseId));
        System.out.println("====houseId = " + houseId);
        System.out.println("=====requestBuilder = "+requestBuilder.toString());
        SearchResponse searchResponse = requestBuilder.get();
        System.out.println("=====requestBuilder = "+searchResponse.toString());
        long totalHit = searchResponse.getHits().getTotalHits();
        System.out.println("命中结果 = " + totalHit);
        //return true;

        boolean success;
        //如果查询结果数量=0，则创建es索引
        if (totalHit == 0) {
            success = create(indexTemplate);
            //如果查询结果数量=1，则根据houseId修改索引
        } else if (totalHit == 1) {
            String esId = searchResponse.getHits().getAt(0).getId();
            success = update(esId,indexTemplate);
            //否则如果查询结果多于1个，将多于的索引全部删除，然后在创建一个信息的索引，因为通过houseId查询结果准确的说应该只有一条
        } else {
            success = deleteAndCreate(totalHit,indexTemplate);
        }
        //上传百度麻点lbs数据
        /*
        addressService.lbsUpload(location.getResult(),house.getStreet() + house.getDistrict(), house.getCityEnName
                ()+house.getStreet(), message.getHouseId(), house.getPrice(), house.getArea());*/

        if (success) {
            logger.debug("Index success with house " +houseId);
        }
    }

    /**
     * 通过kafka消费者取出的消息对es索引进行删除
     * @param message
     */
    private void removeIndex(HouseIndexMessage message) {
        Long houseId = message.getHouseId();
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseId))
                .source(INDEX_NAME);

        logger.debug("Delete by query for house: " + builder);

        // 删除百度云麻点 lbs 数据
        //addressService.removeLbs(houseId);


        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        logger.debug("Delete total " + deleted);

        if (deleted <= 0) {
            this.remove(houseId,message.getRetry()+1);
        }
    }

    /**
     * 用户调用该接口,将houseId参数传入到kafka消息队列中,handleMessage函数监听消息中的数据并创建es索引.
     * @param houseId
     */
    @Override
    public void index(Long houseId) {
        this.index(houseId, 0);
    }

    /**
     * 向kafka发送新增索引消息
     * @param houseId
     * @param retry
     */
    private void index(Long houseId, int retry) {
        //如果超过最大消费次数则终止程序
        if (retry > HouseIndexMessage.MAX_RETRY) {
            logger.error("Retry index times over 3 for house: " + houseId + " Please check it!");
            return;
        }

        HouseIndexMessage message = new HouseIndexMessage(houseId, HouseIndexMessage.INDEX, retry);
        try {
            //将消息发送给INDEX_TOPIC主题的kafka中
            kafkaTemplate.send(INDEX_TOPIC, objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            logger.error("Json encode error for " + message);
        }

    }

    /**
     * 创建es索引
     * @param indexTemplate
     * @return
     */
    private boolean create(HouseIndexTemplate indexTemplate) {
        //处理自动补全索引结构
        if (!updateSuggest(indexTemplate)) {
            return false;
        }

        try {
            IndexResponse response = this.esClient.prepareIndex(INDEX_NAME, INDEX_TYPE)
                    .setSource(objectMapper.writeValueAsBytes(indexTemplate), XContentType.JSON).get();

            logger.debug("Create index with house: " + indexTemplate.getHouseId());
            if (response.status() == RestStatus.CREATED) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            logger.error("Error to index house " + indexTemplate.getHouseId(), e);
            return false;
        }
    }

    /**
     * 通过es索引主键修改es中索引
     * @param esId
     * @param indexTemplate
     * @return
     */
    private boolean update(String esId, HouseIndexTemplate indexTemplate) {
        //处理自动补全索引结构
        if (!updateSuggest(indexTemplate)) {
            return false;
        }

        try {
            UpdateResponse response = this.esClient.prepareUpdate(INDEX_NAME, INDEX_TYPE, esId).setDoc(objectMapper.writeValueAsBytes(indexTemplate), XContentType.JSON).get();

            logger.debug("Update index with house: " + indexTemplate.getHouseId());
            if (response.status() == RestStatus.OK) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            logger.error("Error to index house " + indexTemplate.getHouseId(), e);
            return false;
        }
    }

    /**
     * 从es中输出指定的查询索引,删除后,新建立一个信息的索引
     * @param totalHit
     * @param indexTemplate
     * @return
     */
    private boolean deleteAndCreate(long totalHit, HouseIndexTemplate indexTemplate) {
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, indexTemplate.getHouseId()))
                .source(INDEX_NAME);

        logger.debug("Delete by query for house: " + builder);

        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        if (deleted != totalHit) {
            logger.warn("Need delete {}, but {} was deleted!", totalHit, deleted);
            return false;
        } else {
            return create(indexTemplate);
        }
    }

    /**
     * 用户调用该接口,将houseId参数传入到kafka消息队列中,handleMessage函数监听消息中的数据并删除es索引.
     * @param houseId
     */
    @Override
    public void remove(Long houseId) {
        this.remove(houseId,0);
    }

    /**
     * 将删除索引的消息,发送给kafka
     * @param houseId
     * @param retry
     */
    private void remove(Long houseId, int retry) {
        if (retry > HouseIndexMessage.MAX_RETRY) {
            logger.error("Retry remove times over 3 for house:" + houseId + "Please check it!");
            return;
        }
        HouseIndexMessage message = new HouseIndexMessage(houseId, HouseIndexMessage.REMOVE, retry);
        try {
            //将消息发送给INDEX_TOPIC主题的kafka中
            this.kafkaTemplate.send(INDEX_TOPIC, objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            logger.error("Cannot encode Json for " + message,e);
        }
    }

    /**
     * 到es搜索引擎中做查询
     * @param rentSearch
     * @return
     */
    @Override
    public ServiceMultiResult<Long> query(RentSearch rentSearch) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        //通过城市英文名搜
        /*
        if (rentSearch.getCityEnName() != null && !rentSearch.getCityEnName().isEmpty()) {

            boolQuery.filter(
                    QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, rentSearch.getCityEnName()));
        }*/
        //通过地区英文名搜索
        if (rentSearch.getRegionEnName() != null && !"*".equals(rentSearch.getRegionEnName())) {
            boolQuery.filter(
                    QueryBuilders.termQuery(HouseIndexKey.REGION_EN_NAME, rentSearch.getRegionEnName())
            );
        }
        RentValueBlock area = RentValueBlock.matchArea(rentSearch.getAreaBlock());
        //如果用户搜索区间搜索
        if (!RentValueBlock.ALL.equals(area)) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(HouseIndexKey.AREA);
            if (area.getMax() > 0) {
                rangeQueryBuilder.lte(area.getMax());
            }
            if (area.getMin() > 0) {
                rangeQueryBuilder.gte(area.getMin());
            }
            boolQuery.filter(rangeQueryBuilder);
        }
        //如果用户搜索区间价格
        RentValueBlock price = RentValueBlock.matchPrice(rentSearch.getPriceBlock());
        if (!RentValueBlock.ALL.equals(price)) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(HouseIndexKey.PRICE);
            if (price.getMax() > 0) {
                rangeQuery.lte(price.getMax());
            }
            if (price.getMin() > 0) {
                rangeQuery.gte(price.getMin());
            }
            boolQuery.filter(rangeQuery);
        }
        //搜索朝向
        if (rentSearch.getDirection() > 0) {
            boolQuery.filter(
                    QueryBuilders.termQuery(HouseIndexKey.DIRECTION, rentSearch.getDirection())
            );
        }
        //搜索租赁方式,整租或合租
        if (rentSearch.getRentWay() > -1) {
            boolQuery.filter(
                    QueryBuilders.termQuery(HouseIndexKey.RENT_WAY, rentSearch.getRentWay())
            );
        }
        //单独对标题进行搜索并指定查询权重=2.0, 默认=1.0, must代表查询条件中如果有多个则必须多个条件全部满足
        boolQuery.must(
                QueryBuilders.matchQuery(HouseIndexKey.TITLE, rentSearch.getKeywords()).boost(2.0f)
        );

        //多条件搜索关键词,should代表以下多个条件下有一个条件满足就可以查询到结果
        boolQuery.should(
                QueryBuilders.multiMatchQuery(rentSearch.getKeywords(),
                        //HouseIndexKey.TITLE,
                        HouseIndexKey.TRAFFIC,
                        HouseIndexKey.DISTRICT,
                        HouseIndexKey.ROUND_SERVICE,
                        HouseIndexKey.SUBWAY_LINE_NAME,
                        HouseIndexKey.SUBWAY_STATION_NAME
                ));


        //es创建搜索
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addSort(
                        HouseSort.getSortKey(rentSearch.getOrderBy()),
                        SortOrder.fromString(rentSearch.getOrderDirection())
                )
                .setFrom(rentSearch.getStart())
                .setSize(rentSearch.getSize())
                //指定只查询文档中的house_id,如果不指定以下一行,则查询所有文档内容, 性能就会降低
                .setFetchSource(HouseIndexKey.HOUSE_ID, null);
        logger.debug(requestBuilder.toString());
        List<Long> houseIds = new ArrayList<>();
        //es搜索操作
        SearchResponse response = requestBuilder.get();
        logger.info("===sousuo response = " + response.toString());
        if (response.status() != RestStatus.OK) {
            logger.warn("Search status is no ok for " + requestBuilder);
            return new ServiceMultiResult(0,houseIds);
        }
        //循环命中次数,组合房屋主键list
        for (SearchHit hit : response.getHits()) {
            houseIds.add(
                    Longs.tryParse(String.valueOf(hit.getSource().get(HouseIndexKey.HOUSE_ID)))
            );
        }
        return new ServiceMultiResult<>(response.getHits().totalHits,houseIds);
        /*
        RentValueBlock area = RentValueBlock.matchArea(rentSearch.getAreaBlock());
        if (!RentValueBlock.ALL.equals(area)) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(HouseIndexKey.AREA);
            if (area.getMax() > 0) {
                rangeQueryBuilder.lte(area.getMax());
            }
            if (area.getMin() > 0) {
                rangeQueryBuilder.gte(area.getMin());
            }
            boolQuery.filter(rangeQueryBuilder);
        }*/
    }

    /**
     * 前台搜索框自动补全
     * @param prefix
     * @return
     */
    @Override
    public ServiceResult<List<String>> suggest(String prefix) {
        //自动补全构造器,该行中的 suggest对应穿件es索引类HouseIndexTemplate中的属性suggest, 自动最多补全5个词汇
        CompletionSuggestionBuilder suggestion = SuggestBuilders.completionSuggestion("suggest").prefix(prefix).size(5);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("autocomplete", suggestion);

        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .suggest(suggestBuilder);
        logger.debug(requestBuilder.toString());

        SearchResponse response = requestBuilder.get();
        Suggest suggest = response.getSuggest();
        if (suggest == null) {
            return ServiceResult.of(new ArrayList<>());
        }
        Suggest.Suggestion result = suggest.getSuggestion("autocomplete");

        int maxSuggest = 0;
        Set<String> suggestSet = new HashSet<>();
        //排重操作
        for (Object term : result.getEntries()) {
            if (term instanceof CompletionSuggestion.Entry) {
                CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;

                if (item.getOptions().isEmpty()) {
                    continue;
                }

                for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
                    String tip = option.getText().string();
                    //如果重复字段则终止本次循环
                    if (suggestSet.contains(tip)) {
                        continue;
                    }
                    suggestSet.add(tip);
                    maxSuggest++;
                }
            }

            if (maxSuggest > 5) {
                break;
            }
        }
        List<String> suggests = Lists.newArrayList(suggestSet.toArray(new String[]{}));
        return ServiceResult.of(suggests);
    }

    /**
     * 每次es在create或update索引的时候用
     * @param indexTemplate
     * @return
     */
    private boolean updateSuggest(HouseIndexTemplate indexTemplate) {
        //构造es索引分析器, 对需要分词的字段进行操作,目的是获取对应字段分词后的结果
        AnalyzeRequestBuilder requestBuilder = new AnalyzeRequestBuilder(
                this.esClient, AnalyzeAction.INSTANCE, INDEX_NAME, indexTemplate.getTitle(),
                indexTemplate.getLayoutDesc(), indexTemplate.getRoundService(),
                indexTemplate.getDescription(), indexTemplate.getSubwayLineName(),
                indexTemplate.getSubwayStationName());
        //使用ik_smart分词器
        requestBuilder.setAnalyzer("ik_smart");
        //获取对应字段的分词结果
        AnalyzeResponse response = requestBuilder.get();
        //获取每一个词
        List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
        if (tokens == null) {
            logger.warn("Can not analyze token for house: " + indexTemplate.getHouseId());
            return false;
        }

        List<HouseSuggest> suggests = new ArrayList<>();
        for (AnalyzeResponse.AnalyzeToken token : tokens) {
            // 排序数字类型 & 小于2个字符的分词结果
            if ("<NUM>".equals(token.getType()) || token.getTerm().length() < 2) {
                continue;
            }

            HouseSuggest suggest = new HouseSuggest();
            //将分词结果赋值到HouseSuggest的input属性中
            suggest.setInput(token.getTerm());
            suggests.add(suggest);
        }

        // 定制化小区自动补全
        HouseSuggest suggest = new HouseSuggest();
        suggest.setInput(indexTemplate.getDistrict());
        suggests.add(suggest);

        indexTemplate.setSuggest(suggests);
        return true;
    }

    /**
     * 聚合特定小区的房间数
     * @param cityEnName
     * @param regionEnName
     * @param district
     * @return
     */
    @Override
    public ServiceResult<Long> aggregateDistrictHouse(String cityEnName, String regionEnName, String district) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, cityEnName))
                .filter(QueryBuilders.termQuery(HouseIndexKey.REGION_EN_NAME, regionEnName))
                .filter(QueryBuilders.termQuery(HouseIndexKey.DISTRICT, district));

        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addAggregation(
                        AggregationBuilders.terms(HouseIndexKey.AGG_DISTRICT)
                                .field(HouseIndexKey.DISTRICT) //对小区名字段进行聚合
                ).setSize(0);//不需要原始数据, 只需要聚合数据

        logger.debug("小区聚合requestBuilder = " + requestBuilder.toString());

        SearchResponse response = requestBuilder.get();
        if (response.status() == RestStatus.OK) {
            Terms terms = response.getAggregations().get(HouseIndexKey.AGG_DISTRICT); //获取聚合
            if (terms.getBuckets() != null && !terms.getBuckets().isEmpty()) {
                return ServiceResult.of(terms.getBucketByKey(district).getDocCount()); //分档数量
            }
        } else {
            logger.warn("小区聚合 = Failed to Aggregate for " + HouseIndexKey.AGG_DISTRICT);

        }
        return ServiceResult.of(0L);
    }

    /**
     * 聚合城市数据
     * @param cityEnName
     * @return
     */
    @Override
    public ServiceMultiResult<HouseBucketDTO> mapAggregate(String cityEnName) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, cityEnName));

        //创建根据区域进行分组聚合
        AggregationBuilder aggBuilder = AggregationBuilders.terms(HouseIndexKey.AGG_REGION)
                .field(HouseIndexKey.REGION_EN_NAME);
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addAggregation(aggBuilder);

        logger.debug(requestBuilder.toString());

        SearchResponse response = requestBuilder.get();
        List<HouseBucketDTO> buckets = new ArrayList<>();
        if (response.status() != RestStatus.OK) {
            logger.warn("Aggregate status is not ok for " + requestBuilder);
            return new ServiceMultiResult<>(0, buckets);
        }

        Terms terms = response.getAggregations().get(HouseIndexKey.AGG_REGION);
        for (Terms.Bucket bucket : terms.getBuckets()) {
            buckets.add(new HouseBucketDTO(bucket.getKeyAsString(), bucket.getDocCount()));
        }

        return new ServiceMultiResult<>(response.getHits().getTotalHits(), buckets);
    }

    /**
     * 从es根据城市名称查询数据 带分页的 百度地图使用
     * @param cityEnName
     * @param orderBy
     * @param orderDirection
     * @param start
     * @param size
     * @return
     */
    @Override
    public ServiceMultiResult<Long> mapQuery(String cityEnName, String orderBy,
                                             String orderDirection,
                                             int start,
                                             int size) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, cityEnName));

        SearchRequestBuilder searchRequestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addSort(HouseSort.getSortKey(orderBy), SortOrder.fromString(orderDirection))
                .setFrom(start)
                .setSize(size);

        List<Long> houseIds = new ArrayList<>();
        SearchResponse response = searchRequestBuilder.get();
        if (response.status() != RestStatus.OK) {
            logger.warn("Search status is not ok for " + searchRequestBuilder);
            return new ServiceMultiResult<>(0, houseIds);
        }

        for (SearchHit hit : response.getHits()) {
            houseIds.add(Longs.tryParse(String.valueOf(hit.getSource().get(HouseIndexKey.HOUSE_ID))));
        }
        return new ServiceMultiResult<>(response.getHits().getTotalHits(), houseIds);
    }

    /**
     * 精确地图查询(小地图查询)有经纬度的查询
     * @param mapSearch
     * @return
     */
    @Override
    public ServiceMultiResult<Long> mapQuery(MapSearch mapSearch) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, mapSearch.getCityEnName()));

        //查询经纬度, 与es创建字段location的类型"type": "geo_point" 对应
        boolQuery.filter(
                QueryBuilders.geoBoundingBoxQuery("location")
                        .setCorners( //设置边角
                                new GeoPoint(mapSearch.getLeftLatitude(), mapSearch.getLeftLongitude()), //做上角
                                new GeoPoint(mapSearch.getRightLatitude(), mapSearch.getRightLongitude()) //右下角
                        ));

        SearchRequestBuilder builder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addSort(HouseSort.getSortKey(mapSearch.getOrderBy()),
                        SortOrder.fromString(mapSearch.getOrderDirection()))
                .setFrom(mapSearch.getStart())
                .setSize(mapSearch.getSize());

        List<Long> houseIds = new ArrayList<>();
        SearchResponse response = builder.get();
        if (RestStatus.OK != response.status()) {
            logger.warn("Search status is not ok for " + builder);
            return new ServiceMultiResult<>(0, houseIds);
        }

        for (SearchHit hit : response.getHits()) {
            houseIds.add(Longs.tryParse(String.valueOf(hit.getSource().get(HouseIndexKey.HOUSE_ID))));
        }
        return new ServiceMultiResult<>(response.getHits().getTotalHits(), houseIds);
    }
}
