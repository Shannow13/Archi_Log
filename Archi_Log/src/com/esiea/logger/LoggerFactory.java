package com.esiea.logger;

import static com.esiea.logger.State.DEBUG;
import static com.esiea.logger.State.ERROR;
import static com.esiea.logger.State.INFO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;


public class LoggerFactory {
	
	//Méthode qui crée la table LOG dans la BDD
	private static void createTable(String nameJDBC, String url){
		
		 Connection c = null;
		 Statement stmt = null;
		    try {
		      Class.forName(nameJDBC);
		      c = DriverManager.getConnection(url);

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
	 }
	
	//Méthode qui charge le fichier de config. On peut la bouger ailleurs suivant l'architecture que l'on fait
	 public static Properties pload(String filename) throws IOException, FileNotFoundException{
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
		
	
	
	// On créer l'instance du Logger avec le niveau de priorité indiqué dans les properties
	public static Logger getLogger(Class<?> MyClass, String level, String fileName, boolean dbOk){
		
		State state = getState(level);

		try {
			Properties prop = pload("config.properties");
			prop.setProperty("filepath", fileName);
			createTable(prop.getProperty("nameJDBC"), prop.getProperty("url"));
		} catch (IOException e) {
			System.out.println("Probleme avec le filepath dans properties");
			e.printStackTrace();
			return null;
		}
		
		return(new Logger(MyClass, state, fileName, true, dbOk));
	}

	// On créer l'instance du Logger avec le niveau de priorité indiqué par l'utilisateur et on met à jour le fichier properties
	public static Logger getLogger(Class<?> MyClass, String level, boolean fileOk , boolean dbOk){
		

		State state = getState(level);
		String fileName = null;
		
		try {
			fileName = getFileName();
			Properties prop = pload("config.properties");
			createTable(prop.getProperty("nameJDBC"), prop.getProperty("url"));
		} catch (IOException e) {
			System.out.println("Probleme avec le filepath dans properties");
			e.printStackTrace();
			return null;
		}
		
		return(new Logger(MyClass, state,fileName, true, dbOk));
	}
	
	
	
	// On extrait le niveau de priorité en fonction de ce que nous indique l'utilisateur et on MàJ les properties
	private static State getState(String string){
		State state = null;
		
		try{
			Properties prop = pload("config.properties");
			switch (string.toUpperCase()){
				case "DEBUG" :
					state = DEBUG;
					prop.setProperty("state", "DEBUG");
					break;
					
				case "INFO" :
					state = INFO;
					prop.setProperty("state", "INFO");
					break;
					
				case "ERROR" :
					state = ERROR;
					prop.setProperty("state", "ERROR");
					break;
				
				default :
					System.out.println("La priorité indiqué n'est pas conforme");
					break;
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		//System.out.println("State : " + state);
		
		return state;
	}
	
	
	
	private static String getFileName() throws IOException {
		
		String fileName = null;
		
		Properties prop = pload("config.properties");
		fileName = prop.getProperty("filepath");
		
		return fileName;
	}
	
	
	
}
