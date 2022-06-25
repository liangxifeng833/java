package com.example.esdemo.Utils;

import com.example.esdemo.entity.EsSearchParams;
import com.example.esdemo.entity.EsSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * es 工具类
 * @author liangxifeng
 * @date 2022-06-25
 */
@Slf4j
@Component
public class EsUtils {
    /**
     * 索引的setting
     */
    @Value("classpath:json/es-setting.json")
    private Resource esSetting;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 判断索引是否存在
     * @param indexName 索引名称
     * @return boolean
     */
    public boolean indexExist(String indexName){
        if(indexName.isEmpty()){
            return false;
        }
        IndexCoordinates indexCoordinates = IndexCoordinates.of(indexName);
        return elasticsearchRestTemplate.indexOps(indexCoordinates).exists();
    }

    /**
     * 判断index是否存在 不存在创建index
     * 注意：该方式目前无法使用
     * @param index 索引实体
     * @param indexName 创建索引的名称
     */
    public void indexCreate(Class index,String indexName){
        if(null == index || indexName.isEmpty()){
            return;
        }
        IndexCoordinates indexCoordinates = IndexCoordinates.of(indexName);
        if(!elasticsearchRestTemplate.indexOps(indexCoordinates).exists()){
            // 根据索引实体，获取mapping字段
            Document mapping = elasticsearchRestTemplate.indexOps(indexCoordinates).createMapping(index);
            // 创建索引
            String esSettingStr = null;
            try {
                // 读取setting配置文件
                esSettingStr = IOUtils.toString(esSetting.getInputStream(), Charset.forName("utf-8"));
            } catch (IOException e) {
                //log.error("读取setting配置文件错误", e);
            }
            // setting
            Document setting = Document.parse(esSettingStr);
            elasticsearchRestTemplate.indexOps(indexCoordinates).create(setting);
            // 创建索引mapping
            elasticsearchRestTemplate.indexOps(indexCoordinates).putMapping(mapping);
        }
    }

    /**
     * 根据bean ID 查询
     *
     * @param id 主键
     * @param clazz 具体映射实体
     * @param <T>
     */
    public <T> T getById(Integer id, Class<T> clazz) {
        return elasticsearchRestTemplate.queryForObject(GetQuery.getById(String.valueOf(id)), clazz);
    }

    /**
     * 根据索引名称，删除索引
     * @param index 索引类
     */
    public void indexDelete(String index){
        elasticsearchRestTemplate.indexOps(IndexCoordinates.of(index)).delete();
    }


    /**
     * 索引添加别名
     * @param indexName 索引名
     * @param aliasName 别名
     */
    public boolean indexAddAlias(String indexName,String aliasName){
        if(indexName.isEmpty() || aliasName.isEmpty()){
            return false;
        }
        // 索引封装类
        IndexCoordinates indexCoordinates = IndexCoordinates.of(indexName);
        // 判断索引是否存在
        if(elasticsearchRestTemplate.indexOps(indexCoordinates).exists()){
            // 索引别名
            AliasQuery query = new AliasQuery(aliasName);
            // 添加索引别名
            boolean bool = elasticsearchRestTemplate.indexOps(indexCoordinates).addAlias(query);
            return bool;
        }
        return false;
    }

    /**
     * 索引别名删除
     * @param indexName 索引名
     * @param aliasName 别名
     */
    public boolean indexRemoveAlias(String indexName,String aliasName){
        if(indexName.isEmpty() || aliasName.isEmpty()){
            return false;
        }
        // 索引封装类
        IndexCoordinates indexCoordinates = IndexCoordinates.of(indexName);
        // 判断索引是否存在
        if(elasticsearchRestTemplate.indexOps(indexCoordinates).exists()){
            // 索引别名
            AliasQuery query = new AliasQuery(aliasName);
            // 删除索引别名
            boolean bool = elasticsearchRestTemplate.indexOps(indexCoordinates).removeAlias(query);
            return bool;
        }
        return false;
    }

    /**
     * 索引新增数据
     * @param <T> 索引类
     * @param t 索引类
     * @return
     */
    public <T> T add(T t){
        // 根据索引实体名新增数据
        T res = elasticsearchRestTemplate.save(t);
        return res;
    }

    /**
     * 批量插入数据
     * @param queries 数据
     * @param index 索引名称
     */
    public List<String> bulkIndex(List<IndexQuery> queries, String index){
        // 索引封装类
        IndexCoordinates indexCoordinates = IndexCoordinates.of(index);
        // 批量新增数据，此处数据，不要超过100m，100m是es批量新增的筏值，修改可能会影响性能
        return elasticsearchRestTemplate.bulkIndex(queries,indexCoordinates);
    }

    /**
     * 根据条件删除对应索引名称的数据
     * @param clazz 索引类对象
     * @param filedName 索引中字段
     * @param val 删除条件
     */
    public <T> void delete(Class<T> clazz,String filedName,Object val){
        //获取document 注解信息
        org.springframework.data.elasticsearch.annotations.Document annotation = (org.springframework.data.elasticsearch.annotations.Document) clazz.getAnnotation(org.springframework.data.elasticsearch.annotations.Document.class);
        //获取注解上的索引名称
        String indexName = annotation.indexName();
        // 匹配文件查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(filedName, val);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(termQueryBuilder);
        // 删除索引数据
        elasticsearchRestTemplate.delete(nativeSearchQuery,clazz, IndexCoordinates.of(indexName));
    }

    /**
     * 根据数据id删除索引
     * @param t 删除实体对象，该对象只需要赋值主键就可以
     * @param <T>
     * @throws IllegalAccessException
     */
    public <T> void deleteById(T t) throws IllegalAccessException {
        Class clazz = t.getClass();
        java.lang.reflect.Field[] Fields = clazz.getDeclaredFields();
        String beanId = null; //主键值
        Document document = Document.create();
        for (Field f : Fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(org.springframework.data.annotation.Id.class)) {
                beanId = String.valueOf(f.get(t)); //主键值
                break;
            }
        }
        //获取document 注解信息
        org.springframework.data.elasticsearch.annotations.Document annotation = (org.springframework.data.elasticsearch.annotations.Document) clazz.getAnnotation(org.springframework.data.elasticsearch.annotations.Document.class);
        //获取注解上的索引名称
        String indexName = annotation.indexName();
        if(null != beanId && !beanId.isEmpty()){
            // 根据索引删除索引id数据, 返回 res = 主键，没有任何意义
            String res = elasticsearchRestTemplate.delete(beanId,IndexCoordinates.of(indexName));
            log.info("del res="+res);
        }
    }


    /**
     * 通过主键局部更新
     * @param t 修改数据对象，ID不能为空
     */
    public <T> boolean updateByBean(T t) throws IllegalAccessException {
        Class clazz = t.getClass();
        java.lang.reflect.Field[] Fields = clazz.getDeclaredFields();
        String beanId = null;
        String beanIdName = null;
        Document document = Document.create();
        for (Field f : Fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(org.springframework.data.annotation.Id.class)) {
                beanId = String.valueOf(f.get(t)); //主键值
                beanIdName = f.getName(); //主键名
                continue;
            }
            //如果注解类型是 spring data es 的 Field 且 属性值非空,则获取该注解的 name,作为最终修改字段
            if(f.isAnnotationPresent(org.springframework.data.elasticsearch.annotations.Field.class) && f.get(t) != null) {
                org.springframework.data.elasticsearch.annotations.Field fieldAnnotation = (org.springframework.data.elasticsearch.annotations.Field) f.getAnnotation(org.springframework.data.elasticsearch.annotations.Field.class);
                //如果没有配置@Field.name则使用对象属性名作为修改字段名
                String fieldName = fieldAnnotation.name() != null ? fieldAnnotation.name() : f.getName();
                document.put(fieldName,f.get(t));
            }
        }
        log.info("update id value = "+beanId);
        //如果没有任何值要修改，则返回成功
        if( document.size() == 0 ) {
            return true;
        }
        log.info("update document data = " +document.toString());
        if (StringUtils.isBlank(beanId)) {
            log.warn("id不能为空");
            return false;
        }
        if (Objects.isNull(elasticsearchRestTemplate.queryForObject(GetQuery.getById(beanId), clazz))) {
            log.warn("该文档不存在");
            return false;
        }

        //获取document 注解信息
        org.springframework.data.elasticsearch.annotations.Document annotation = (org.springframework.data.elasticsearch.annotations.Document) clazz.getAnnotation(org.springframework.data.elasticsearch.annotations.Document.class);
        //获取注解上的索引名称
        String indexName = annotation.indexName();
        log.info("update document index name = " +indexName);
        //Document document = Document.create();
        // 将id为1023539082200的name列的值更为update by wfd
        //document.put("product_name", "update by wfd");
        //Document document = Document.parse(JSON.toJSONString(t));
        UpdateResponse response = elasticsearchRestTemplate.update( UpdateQuery.builder(beanId) //通过主键修改
                        .withDocument(document).build(), IndexCoordinates.of(indexName)
        );
        //结果 NOOP:代表要更新的字段与库中的值完全一直，UPDATED:代表不一致已更新
        log.info("update res = "+ response.getResult());
        return true;
    }

    /**
     * 无分页简单查询
     * @param c
     * @param search
     * @param index
     * @param <T>
     * @return
     */
    public <T> List<T> getList(Class<T> c,String search,String index){
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("product_name", search);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryBuilder);
        nativeSearchQuery.addSort(Sort.by(Sort.Direction.DESC,"_score"));
        nativeSearchQuery.setTrackTotalHits(true);
        SearchHits<T> tax_knowledge_matter = elasticsearchRestTemplate.search(nativeSearchQuery, c, IndexCoordinates.of(index));
        List<SearchHit<T>> searchHits = tax_knowledge_matter.getSearchHits();
        List<T> returnResult = new ArrayList<>();
        searchHits.forEach(item -> {
            T content = item.getContent();
            returnResult.add(content);
        });
        return returnResult;
    }

    /**
     * 组合带分页查询
     * @param clazz 需要返回的class
     * @param esSearchParams
     * @param <T>
     * @return
     */
    public <T> EsSearchResult<T> getList(Class<T> clazz, EsSearchParams esSearchParams){
        //获取document 注解信息
        org.springframework.data.elasticsearch.annotations.Document annotation = (org.springframework.data.elasticsearch.annotations.Document) clazz.getAnnotation(org.springframework.data.elasticsearch.annotations.Document.class);
        //获取注解上的索引名称
        String indexName = annotation.indexName();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //组合多条件 类似mysql
        // WHERE product_merchantid = 512 AND product_status = 1
        // AND (product_name like or product_model like ) order by product_price desc limit 0,20
        boolQuery.filter(QueryBuilders.termQuery("product_merchantid",esSearchParams.getProductMerchantId()));
        boolQuery.filter(QueryBuilders.termQuery("product_status",esSearchParams.getProductStatus()));
        boolQuery.should(QueryBuilders.multiMatchQuery( esSearchParams.getSearch(),"product_name","product_model")) .minimumShouldMatch(1);

        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(boolQuery);
        //排序, 如果又排序则使用指定排序规则，否则使用评分倒叙
        if(esSearchParams.getSortField() != null && esSearchParams.getSortValue() != null) {
            if(esSearchParams.getSortValue().equals("asc")) {
                nativeSearchQuery.addSort(Sort.by(Sort.Direction.ASC,esSearchParams.getSortField()));
            }else if(esSearchParams.getSortValue().equals("desc")) {
                nativeSearchQuery.addSort(Sort.by(Sort.Direction.DESC,esSearchParams.getSortField()));
            }else {
                nativeSearchQuery.addSort(Sort.by(Sort.Direction.DESC,"_score"));
            }
        }else {
            nativeSearchQuery.addSort(Sort.by(Sort.Direction.DESC,"_score"));
        }
        //分页
        nativeSearchQuery.setPageable(PageRequest.of(esSearchParams.getPage(),esSearchParams.getSize()));
        //获取超过1w条数据 需要加上  "track_total_hits":true ，不然只能显示出9999条
        nativeSearchQuery.setTrackTotalHits(true);
        SearchHits<T> tax_knowledge_matter = elasticsearchRestTemplate.search(nativeSearchQuery, clazz, IndexCoordinates.of(indexName));
        System.out.println(tax_knowledge_matter);
        tax_knowledge_matter.getSearchHits().forEach(System.out::println);
        List<SearchHit<T>> searchHits = tax_knowledge_matter.getSearchHits();
        List<T> returnResult = new ArrayList<>();
        searchHits.forEach(item -> {
            T content = item.getContent();
            returnResult.add(content);
        });
        return new EsSearchResult<T>((int) tax_knowledge_matter.getTotalHits(),returnResult);
    }
}
