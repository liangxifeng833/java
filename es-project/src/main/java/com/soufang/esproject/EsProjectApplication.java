package com.soufang.esproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EntityScan("com.soufang.esproject.entity")
//开启异步请求,使@Async注解生效
@EnableAsync
//开启定时任务注解
@EnableScheduling
public class EsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsProjectApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
}

