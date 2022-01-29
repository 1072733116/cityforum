package com.city.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.city.forum.mapper")
public class CityforumApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityforumApplication.class, args);
	}

}
