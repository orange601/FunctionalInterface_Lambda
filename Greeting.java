package com.date.methodreferences;

public class Greeting {
	private String name;
	public Greeting() {}
	
	public Greeting(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String hello(String name) {
		return "name " + name;
	}
	
	public static String hi(String name) {
		return "hi " + name;
	}

}
