package com.im.fenglei.serviceribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.im.fenglei.serviceribbon.service.HelloService;

@RestController
public class HelloController {
	
	@Autowired
	HelloService helloService;
	
	@RequestMapping("/hello")
	public String hello(@RequestParam String name, @RequestParam int id) {
		return helloService.hiService(name, id)+"-ribbon";
	}
	
	@RequestMapping("/hello1")
	public String hello1(@RequestParam String name, @RequestParam int id) {
		return helloService.hiService1(name, id)+"-ribbon";
	}
	
	@RequestMapping("/hello2")
	public String hello2(@RequestParam String name, @RequestParam int id) {
		return helloService.hiService2(name, id)+"-ribbon";
	}
	
	@RequestMapping("/hello3")
	public String hello3(@RequestParam String name, @RequestParam int id) {
		return helloService.hiService3(name, id)+"-ribbon";
	}
}
