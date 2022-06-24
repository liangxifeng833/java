package com.example.esdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "user", shards = 1, replicas = 0)
public class User {
    private String name;
    private String sex;
    private Integer age;
}
