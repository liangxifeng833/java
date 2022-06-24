package com.example.esdemo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.ElasticsearchTransport;
import lombok.Data;
import org.elasticsearch.client.RestClient;

/**
 * 使用 java api 必须要配置的, 连接 es 配置类
 */
@Data
public class ElasticSearchResult {

    private RestClient restClient;

    private ElasticsearchTransport elasticsearchTransport;

    private ElasticsearchClient elasticsearchClient;

    public ElasticSearchResult(RestClient restClient, ElasticsearchTransport elasticsearchTransport, ElasticsearchClient elasticsearchClient) {
        this.restClient = restClient;
        this.elasticsearchTransport = elasticsearchTransport;
        this.elasticsearchClient = elasticsearchClient;
    }
}
