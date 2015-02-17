package com.esiea.logger;


public class Logger {
	


	private Class<?> MyClass;
	private String fileName;
	private State level;
	
	
	
	public Logger(Class<?> MyClass){
		this.setMyClass(MyClass);
	}
	
	public Logger(Class<?> MyClass, State level){
		this.setMyClass(MyClass);
		this.setLevel(level);
	}
	

	public void info(String string){
		String toPrint = TextFormalizer.formalized(string);
		// Je ne l'ai pas appelé Writer direct parce qu'il existe déjà dans le java.io et ne correspond pas à ce qu'on veut faire
		LogWriter.write(toPrint);
	}
	
	
	// Getter et setter pour tous les attributs, fait en automatique par eclipse
	public State getLevel() {
		return level;
	}

	public void setLevel(State level) {
		this.level = level;
	}

	public Class<?> getMyClass() {
		return MyClass;
	}

	public void setMyClass(Class<?> myClass) {
		MyClass = myClass;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
