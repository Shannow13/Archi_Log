package com.esiea.logger;

import static com.esiea.logger.State.DEBUG;
import static com.esiea.logger.State.ERROR;
import static com.esiea.logger.State.INFO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class LoggerFactory {
	
	
	
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
	public static Logger geLogger(Class<?> MyClass){
		
		State state = getState();
		
		return(new Logger(MyClass, state));
	}
	
	
	// On créer l'instance du Logger avec le niveau de priorité indiqué par l'utilisateur et on met à jour le fichier properties
	public static Logger geLogger(Class<?> MyClass, String string){
		
		State state = getState(string);
		
		return(new Logger(MyClass, state));
	}
	
	
	
	// On extrait le niveau de priorité du fichier properties
	private static State getState(){
		State state = null;
		
		try{
			Properties prop = pload("config.properties");
			switch (prop.getProperty("state")){
				case "DEBUG" :
					state = DEBUG;
					System.out.println(state);
					break;
					
				case "INFO" :
					state = INFO;
					break;
					
				case "ERROR" :
					state = ERROR;
					break;
					
				default :
					System.out.println("Erreur dans le fichier properties. Vérifier la valeur du niveau de priorités de l'affichage des log.");
					return null;
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return state;
	}
	
	
	// On extrait le niveau de priorité en fonction de ce que nous indique l'utilisateur et on MàJ les properties
	private static State getState(String string){
		State state = null;
		
		try{
			Properties prop = pload("config.properties");
			switch (string){
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
					System.out.println("La priorité indiqué n'est pas conforme, utilisation de la priorité indiqué dans le fichier properties");
					state = getState();
					break;
			
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return state;
	}
}
