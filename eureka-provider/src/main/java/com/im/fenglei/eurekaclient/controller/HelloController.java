package com.im.fenglei.eurekaclient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.im.fenglei.eurekaclient.user.User;

@RestController
public class HelloController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private DiscoveryClient client;

	@RequestMapping("/hi")
	public User hi(@RequestParam String name, @RequestParam int id) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
//		return "hi " + name + ",i am from port:" + port;
	}
	
	@RequestMapping("/hello")
	public String index(){
		List<ServiceInstance> instances = client.getInstances("hello-service");
		for(int i = 0; i < instances.size(); i++) {
			LOGGER.info("/hello,host:" + instances.get(i).getHost() + ",service_id:" + instances.get(i).getServiceId());
		}
		return "Hello World, from ribbon";
	}
}
