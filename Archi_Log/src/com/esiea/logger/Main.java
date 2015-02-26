package com.esiea.logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Main {
	

	
/*	private static void insertLog(String nameJDBC, String url, String date, String level, String message){
		
		 Connection c = null;
		    try {
		      Class.forName(nameJDBC);
		      c = DriverManager.getConnection(url);
		      System.out.println("Opened database successfully");
		     
		      String sql = "INSERT INTO LOG " +
		                   "VALUES (?, ?, ?)"; 
		      PreparedStatement stmnt = c.prepareStatement(sql);
		      stmnt.setString(1, date);
		      stmnt.setString(2, level);
		      stmnt.setString(3, message);
		      System.out.println(sql);
		      stmnt.executeUpdate();
		      stmnt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Data Inserted Succesfully");
		
	}
	*/
	/*M�thode de connection � la base de donn�e. Elle prend en param�tre le "nom" du plugin JDBC associ� � la base de donn�e souhait�e 
	et l'url de connexion.*/
	
	/*public static void connection (String nameJDBC, String url){
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
	*/
	/*public static void createTable(String nameJDBC, String url){
		
		 Connection c = null;
		 Statement stmt = null;
		    try {
		      Class.forName(nameJDBC);
		      c = DriverManager.getConnection(url);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      String sql = "CREATE TABLE IF NOT EXISTS LOG " +
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
*/
	
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
	
		
		Logger log = LoggerFactory.getLogger(Main.class);
		
			log.debug("debug");
			log.info("info");
			log.error("error");
		
		
	
	}
	

}
