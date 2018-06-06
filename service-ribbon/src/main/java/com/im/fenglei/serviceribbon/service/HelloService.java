package com.im.fenglei.serviceribbon.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${eureka.service.hello.url}")
	private String HELLO_SERVICE;

	@HystrixCommand(fallbackMethod = "hiError")
	public String hiService(String name, int id) {
		String url = HELLO_SERVICE + "?name=" + name + "&id=" + id;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String body = responseEntity.getBody();
		HttpStatus statusCode = responseEntity.getStatusCode();
		int statusCodeValue = responseEntity.getStatusCodeValue();
		HttpHeaders headers = responseEntity.getHeaders();
		StringBuffer result = new StringBuffer();
		result.append("responseEntity.getBody()：").append(body).append("<hr>")
				.append("responseEntity.getStatusCode()：").append(statusCode)
				.append("<hr>").append("responseEntity.getStatusCodeValue()：")
				.append(statusCodeValue).append("<hr>")
				.append("responseEntity.getHeaders()：").append(headers)
				.append("<hr>");
		return result.toString();
	}

	/**
	 * 可以用一个数字做占位符，最后是一个可变长度的参数，来一一替换前面的占位符
	 */
	@HystrixCommand(fallbackMethod = "hiError")
	public String hiService1(String name, int id) {
		String url = HELLO_SERVICE + "?name={1}&id={2}";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, name, id);
		String body = responseEntity.getBody();
		HttpStatus statusCode = responseEntity.getStatusCode();
		int statusCodeValue = responseEntity.getStatusCodeValue();
		HttpHeaders headers = responseEntity.getHeaders();
		StringBuffer result = new StringBuffer();
		result.append("responseEntity.getBody()：").append(body).append("<hr>")
				.append("responseEntity.getStatusCode()：").append(statusCode)
				.append("<hr>").append("responseEntity.getStatusCodeValue()：")
				.append(statusCodeValue).append("<hr>")
				.append("responseEntity.getHeaders()：").append(headers)
				.append("<hr>");
		return result.toString();
	}

	/**
	 * 也可以前面使用name={name}这种形式，最后一个参数是一个map，map的key即为前边占位符的名字，map的value为参数值
	 */
	@HystrixCommand(fallbackMethod = "hiError")
	public String hiService2(String name, int id) {
		String url = HELLO_SERVICE + "?name={name}&id={id}";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("id", id);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);
		String body = responseEntity.getBody();
		HttpStatus statusCode = responseEntity.getStatusCode();
		int statusCodeValue = responseEntity.getStatusCodeValue();
		HttpHeaders headers = responseEntity.getHeaders();
		StringBuffer result = new StringBuffer();
		result.append("responseEntity.getBody()：").append(body).append("<hr>")
				.append("responseEntity.getStatusCode()：").append(statusCode)
				.append("<hr>").append("responseEntity.getStatusCodeValue()：")
				.append(statusCodeValue).append("<hr>")
				.append("responseEntity.getHeaders()：").append(headers)
				.append("<hr>");
		return result.toString();
	
	}

	/**
	 * 第一个调用地址也可以是一个URI而不是字符串，这个时候我们构建一个URI即可，参数神马的都包含在URI中了
	 */
	@HystrixCommand(fallbackMethod = "hiError")
	public String hiService3(String name, int id) {
		String url = HELLO_SERVICE + "?name={name}&id={id}";
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build().expand(name, id).encode();
		URI uri = uriComponents.toUri();

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		String body = responseEntity.getBody();
		HttpStatus statusCode = responseEntity.getStatusCode();
		int statusCodeValue = responseEntity.getStatusCodeValue();
		HttpHeaders headers = responseEntity.getHeaders();
		StringBuffer result = new StringBuffer();
		result.append("responseEntity.getBody()：").append(body).append("<hr>")
				.append("responseEntity.getStatusCode()：").append(statusCode)
				.append("<hr>").append("responseEntity.getStatusCodeValue()：")
				.append(statusCodeValue).append("<hr>")
				.append("responseEntity.getHeaders()：").append(headers)
				.append("<hr>");
		return result.toString();
	}

	public String hiError(String name, int id) {
		return "sorry " + name + ", service unavaliable";
	}
}
