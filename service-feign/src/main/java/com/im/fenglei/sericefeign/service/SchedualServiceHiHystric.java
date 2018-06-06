package com.im.fenglei.sericefeign.service;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

	@Override
	public String sayHiFromClientOne(String name, int id) {
		return "sorry " + name + ", service unavaliable";
	}
}
