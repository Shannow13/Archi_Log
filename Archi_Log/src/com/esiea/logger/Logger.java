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
	
	
	
	public Logger(Class<?> MyClass, State level, String fileName){
		this.setMyClass(MyClass);
		this.setLevel(level);
		this.setFileName(fileName);
		//System.out.println("Logger done " + MyClass.getSimpleName()+ " " + level);
	}
	

	public void debug(String string){
		if(level.ordinal() <= DEBUG.ordinal()){
			print(string, DEBUG);
		}	
	}
	
	
	public void info(String string){
		if(level.ordinal() <= INFO.ordinal()){
			print(string, INFO);
		}	
	}
	
	
	public void error(String string){
		if(level.ordinal() <= ERROR.ordinal()){
			//System.out.println("Ordinal ok");
			print(string, ERROR);
		}	
	}
	
	
	
	private void print(String string, State level){
		
		String toPrint = TextFormalizer.formalized(string, level, MyClass);
		// Je ne l'ai pas appelé Writer direct parce qu'il existe déjà dans le java.io et ne correspond pas à ce qu'on veut faire
		LogWriter.write(toPrint, fileName);
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
