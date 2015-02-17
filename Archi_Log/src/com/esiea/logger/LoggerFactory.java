package com.esiea.logger;

import static com.esiea.logger.State.DEBUG;
import static com.esiea.logger.State.ERROR;
import static com.esiea.logger.State.INFO;

public class LoggerFactory {
	
	
	public static Logger geLogger(Class<?> MyClass){
		return(new Logger(MyClass));
	}
	
	public static Logger geLogger(Class<?> MyClass, String level){
		State state = null;
		
		switch (level){
			case "DEBUG" :
				state = DEBUG;
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
		
		return(new Logger(MyClass, state));
	}
}
