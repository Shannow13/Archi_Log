package com.esiea.logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Main {
	
	//Méthode qui charge le fichier de config. On peut la bouger ailleurs suivant l'architecture que l'on fait
	 public static Properties load(String filename) throws IOException, FileNotFoundException{
	      Properties properties = new Properties();

	      FileInputStream input = new FileInputStream(filename);
	      try{

	         properties.load(input);
	         return properties;

	      }

	              finally{

	         input.close();

	      } 
	 }
		
	public static void main(String[] args) {
	
		
		Logger log = LoggerFactory.getLogger(Main.class, true);
		
			log.debug("debug");
			log.info("info");
			log.error("error");
		
		
	
	}
	

}
