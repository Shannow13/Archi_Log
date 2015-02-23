package com.esiea.logger;

import static com.esiea.logger.State.DEBUG;
import static com.esiea.logger.State.ERROR;
import static com.esiea.logger.State.INFO;


public class Logger {
	

// Attributs dont on a besoin
	// La class d'appel
	private Class<?> MyClass;
	// Le chemin de fichier dans lequel on doit ecrire
	private String fileName;
	// Le  niveau de priorité pour les messages a afficher
	private State level;
	
	
	// 
	public Logger(Class<?> MyClass){
		this.setMyClass(MyClass);
	}
	
	public Logger(Class<?> MyClass, State level){
		this.setMyClass(MyClass);
		this.setLevel(level);
	}
	

	public void debug(String string){
		if(level.ordinal() >= DEBUG.ordinal()){
			print("logger.debug : "+string);
		}	
	}
	
	
	public void info(String string){
		if(level.ordinal() >= INFO.ordinal()){
			print("logger.info : "+string);
		}	
	}
	
	
	public void error(String string){
		if(level.ordinal() >= ERROR.ordinal()){
			print("logger.error : "+string);
		}	
	}
	
	
	
	private void print(String string){
		
		//TODO rajouter les infos de prio et de filename et mettre à jour les prototype dans TextFormalizer et LogWriter
		
		String toPrint = TextFormalizer.formalized(string, level, MyClass);
		// Je ne l'ai pas appelé Writer direct parce qu'il existe déjà dans le java.io et ne correspond pas à ce qu'on veut faire
		LogWriter.write(toPrint);
	}
	
	// Getter et setter pour tous les attributs, fait en automatique par eclipse
	public State getLevel() {
		return level;
	}

	public void setLevel(State level) {
		// TODO MàJ les properties
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
