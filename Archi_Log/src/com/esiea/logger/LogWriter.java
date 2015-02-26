package com.esiea.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {

	//Affichage dans la console
	public static void write(String toPrint) {
		
		System.out.println(toPrint);
	}
	
	//Ecriture dans un fichier texte
	public static void write(String toPrint, String fileName) {
		
		//System.out.println(toPrint);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		fileName = fileName.concat("_"+dateFormat.format(date)+".txt");
		
		System.out.println(fileName);
		
		File file = new File(fileName);
		try {
			FileWriter fw = new FileWriter(file,true);
			
			fw.write(toPrint+"\n");
			
			fw.close();
			
		} catch (IOException e) {
			System.out.println("Erreur avec le File name");
			e.printStackTrace();
		}
		
		// TODO Constructeur pour la methode 'write' pour la BDD
		
	}

}
