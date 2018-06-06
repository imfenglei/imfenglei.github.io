package com.im.fenglei.eurekaclient.user;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5121994968902221481L;
	
	private String name;
	private int id;
	@Value("${server.port}")
	private int port;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
