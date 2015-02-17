package com.esiea.logger;



public class Logger {
	


	Class<?> MyClass;
	String level; 
	
	
	
	public Logger(Class<?> MyClass){
		this.MyClass = MyClass;
	}
	
	public Logger(Class<?> MyClass, String level){
		this.MyClass = MyClass;
		this.level = level;
	}
	
	
	
}
