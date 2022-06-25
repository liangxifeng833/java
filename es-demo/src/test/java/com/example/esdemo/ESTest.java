package com.example.esdemo;


import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.example.esdemo.config.ElasticSearchClientConnect;
import com.example.esdemo.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * 使用es 官方推荐的 java api 方式操作 es
 */
public class ESTest extends ApplicationTests{
    @Autowired
    private ElasticSearchClientConnect elasticSearchClientConfig;

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexResponse createIndexResponse = elasticSearchClientConfig.restClient().getElasticsearchClient().indices().create(c -> c.index("test-lxf"));
        // 打印结果
        System.out.println(createIndexResponse.acknowledged());
        // 关闭连接

        elasticSearchClientConfig.restClient().getElasticsearchTransport().close();
        elasticSearchClientConfig.restClient().getRestClient().close();
        System.out.println(createIndexResponse.acknowledged());
    }

    @Test
    public void testGetIndex() throws IOException {
        GetIndexResponse getIndexResponse = elasticSearchClientConfig.restClient().getElasticsearchClient().indices().get(e -> e.index("test-lxf"));

        // 打印结果
        System.out.println("getIndexResponse.result() = " + getIndexResponse.result());
        System.out.println("getIndexResponse.result().keySet() = " + getIndexResponse.result().keySet());

        // 关闭连接
        elasticSearchClientConfig.restClient().getElasticsearchTransport().close();
        elasticSearchClientConfig.restClient().getRestClient().close();
    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void testDelIndex() throws IOException {
        DeleteIndexResponse deleteIndexResponse = elasticSearchClientConfig.restClient().getElasticsearchClient().indices().delete(e -> e.index("jing_index"));
        System.out.println("删除操作 = " + deleteIndexResponse.acknowledged());
        // 关闭连接
        elasticSearchClientConfig.restClient().getElasticsearchTransport().close();
        elasticSearchClientConfig.restClient().getRestClient().close();
        System.out.println(deleteIndexResponse.acknowledged());
    }

    /**
     * 新增文档
     */
    @Test
    public void testAddDoc() throws IOException {
        // 向user对象中添加数据
        User user = new User();
        user.setId(1);
        user.setName("haha");
        // 向索引中添加数据
        CreateResponse createResponse = elasticSearchClientConfig.restClient().getElasticsearchClient().create(e -> e.index("test-lxf").id("1001").document(user));
        System.out.println("createResponse.result() = " + createResponse.result());

        elasticSearchClientConfig.restClient().getElasticsearchTransport().close();
        elasticSearchClientConfig.restClient().getRestClient().close();
    }

    /**
     * 查询文档 通过主键
     */
    @Test
    public void testGetDocById() throws IOException {
        // 构建请求
        GetResponse<User> getResponse = elasticSearchClientConfig.restClient().getElasticsearchClient().get(e -> e.index("test-lxf").id("1001"), User.class);
        System.out.println("getResponse.source().toString() = " + getResponse.source().toString());

        elasticSearchClientConfig.restClient().getElasticsearchTransport().close();
        elasticSearchClientConfig.restClient().getRestClient().close();
    }

    /**
     * 分页查询文档
     */
    @Test
    public void testGetDocByPage() throws IOException {
        // 组合查询
        SearchResponse<User> searchResponse = elasticSearchClientConfig.restClient().getElasticsearchClient().search(
                s -> s.index("test-lxf").query(q -> q.bool(b -> b
                        .must(m -> m.match(u -> u.field("age").query(18)))
                        .must(m -> m.match(u -> u.field("name").query("客户")))
                        //.mustNot(m -> m.match(u -> u.field("sex").query("女")))
                ))
                .from(0)
                .size(10)
                , User.class);

        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));
        elasticSearchClientConfig.restClient().getElasticsearchTransport().close();
        elasticSearchClientConfig.restClient().getRestClient().close();
    }
}
