package com.example.esdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(indexName = "user", shards = 1, replicas = 0)
public class User {
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;
    private String name;
    //JsonFormat (作用于读) 代表SpringBoot 返给给前端的时候才会具体起作用，也就是通过定义controllerc的方式，单元测试中查询出来的时间类型依然会多一个T
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm:ss",timezone = "GMT+8")
    //Field （作用于写）注解是es创建索引使用的
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
