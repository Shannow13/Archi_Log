package com.esiea.logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Main {
	
	/*Méthode de connectio à la base de donnée. Elle prend en paramètre le "nom" du plugin JDBC associé à la base de donnée souhaitée 
	et l'url de connexion.*/
	
	public static void connection (String nameJDBC, String url){
		  Connection c = null;
		    try {
		      Class.forName(nameJDBC);
		      c = DriverManager.getConnection(url);
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Opened database successfully");
		  }

	
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
	
		try{
		Properties prop = load("config.properties");
		
		System.out.println("filepath : " + prop.getProperty("filepath"));
		System.out.println("State : "+ prop.getProperty("state"));
		
		//Connexion à la base
		connection(prop.getProperty("nameJDBC"), prop.getProperty("url"));
		
		LoggerFactory.geLogger(Main.class);
		LoggerFactory.geLogger(Main.class, prop.getProperty("state"));
		
		
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	
	}
	



}
