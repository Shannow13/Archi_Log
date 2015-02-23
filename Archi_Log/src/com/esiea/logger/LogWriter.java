package com.esiea.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

	public static void write(String toPrint, String fileName) {
		
		System.out.println(toPrint);
		
		File file = new File(fileName);
		try {
			FileWriter fw = new FileWriter(file,true);
			
			fw.write(toPrint+"\n");
			
			fw.close();
			
		} catch (IOException e) {
			System.out.println("Erreur avec le File name");
			e.printStackTrace();
		}
		
		// TODO Ajouter pour la BDD
		
	}

}
