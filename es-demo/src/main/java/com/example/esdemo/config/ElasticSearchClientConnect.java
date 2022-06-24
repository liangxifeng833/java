package com.example.esdemo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

/*
 * 使用 java api 必须要配置的, 连接 es 配置类
 */
@Component
public class ElasticSearchClientConnect {

    public ElasticSearchResult restClient(){
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200,"http")).build();
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);

        ElasticSearchResult elasticSearchResult=new ElasticSearchResult(restClient,transport,client);
        return elasticSearchResult;
    }

}
