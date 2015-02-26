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
	
	//M�thode qui cr�e la table LOG dans la BDD
	private static void createTable(String nameJDBC, String url){
		
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
	
	//M�thode qui charge le fichier de config. On peut la bouger ailleurs suivant l'architecture que l'on fait
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
		
	
	
	// On cr�er l'instance du Logger avec le niveau de priorit� indiqu� dans les properties
	public static Logger getLogger(Class<?> MyClass){
		
		State state = getState();
		String fileName = null;
		
		
		try {
			fileName = getFileName();
		} catch (IOException e) {
			System.out.println("Probleme avec le filepath dans properties");
			e.printStackTrace();
			return null;
		}
		
		return(new Logger(MyClass, state, fileName));
	}

	// On cr�er l'instance du Logger avec le niveau de priorit� indiqu� par l'utilisateur et on met � jour le fichier properties
	public static Logger getLogger(Class<?> MyClass, String string){
		

		State state = getState(string);
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
		
		return(new Logger(MyClass, state,fileName));
	}
	
	
	
	// On extrait le niveau de priorit� du fichier properties
	private static State getState(){
		State state = null;
		
		try{
			Properties prop = pload("config.properties");
			switch (prop.getProperty("state")){
				case "DEBUG" :
					state = DEBUG;
					//System.out.println(state);
					break;
					
				case "INFO" :
					state = INFO;
					break;
					
				case "ERROR" :
					state = ERROR;
					break;
					
				default :
					System.out.println("Erreur dans le fichier properties. V�rifier la valeur du niveau de priorit�s de l'affichage des log.");
					return null;
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		//System.out.println("State received:"+state.toString());
		
		return state;
	}
	
	
	// On extrait le niveau de priorit� en fonction de ce que nous indique l'utilisateur et on M�J les properties
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
					System.out.println("La priorit� indiqu� n'est pas conforme, utilisation de la priorit� indiqu� dans le fichier properties");
					state = getState();
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
