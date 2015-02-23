package com.esiea.logger;

import static com.esiea.logger.State.DEBUG;
import static com.esiea.logger.State.ERROR;
import static com.esiea.logger.State.INFO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class LoggerFactory {
	
	
	
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
	public static Logger geLogger(Class<?> MyClass){
		
		State state = getState();
		
		return(new Logger(MyClass, state));
	}
	
	
	// On cr�er l'instance du Logger avec le niveau de priorit� indiqu� par l'utilisateur et on met � jour le fichier properties
	public static Logger geLogger(Class<?> MyClass, String string){
		
		State state = getState(string);
		
		return(new Logger(MyClass, state));
	}
	
	
	
	// On extrait le niveau de priorit� du fichier properties
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
					System.out.println("Erreur dans le fichier properties. V�rifier la valeur du niveau de priorit�s de l'affichage des log.");
					return null;
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return state;
	}
	
	
	// On extrait le niveau de priorit� en fonction de ce que nous indique l'utilisateur et on M�J les properties
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
					System.out.println("La priorit� indiqu� n'est pas conforme, utilisation de la priorit� indiqu� dans le fichier properties");
					state = getState();
					break;
			
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return state;
	}
}
