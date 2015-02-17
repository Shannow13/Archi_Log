package com.esiea.logger;



public class Logger {
	


	Class<?> MyClass;
	int level; 
	
	
	
	public Logger(Class<?> MyClass){
		this.MyClass = MyClass;
	}
	
	public Logger(Class<?> MyClass, int level){
		this.MyClass = MyClass;
		this.level = level;
	}
	
	
	
}
