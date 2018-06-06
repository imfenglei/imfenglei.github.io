package com.im.fenglei.servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.im.fenglei.servicezuul.filter.MyFilter;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SericeZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SericeZuulApplication.class, args);
	}
	
	@Bean
	public MyFilter accessMyFilter(){
		return new MyFilter();
	}
}
