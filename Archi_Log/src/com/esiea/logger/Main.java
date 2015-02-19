package com.esiea.logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
	
	/*M�thode de connectio � la base de donn�e. Elle prend en param�tre le "nom" du plugin JDBC associ� � la base de donn�e souhait�e 
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
	
	public static void createTable(String nameJDBC, String url){
		
		 Connection c = null;
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      String sql = "CREATE TABLE LOG " +
		                   "(DATE			TEXT     NOT NULL," +
		                   " TYPE           TEXT    NOT NULL, " + 
		                   "MESSAGE			TEXT 	NOT NULL)"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Table created successfully");
	 }

	
	//M�thode qui charge le fichier de config. On peut la bouger ailleurs suivant l'architecture que l'on fait
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
		
		//Connexion � la base
		connection(prop.getProperty("nameJDBC"), prop.getProperty("url"));
		//Cr�ation de la Table LOG
		createTable(prop.getProperty("nameJDBC"), prop.getProperty("url"));
		
		LoggerFactory.geLogger(Main.class);
		LoggerFactory.geLogger(Main.class, prop.getProperty("state"));
		
		
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	
	}
	



}
