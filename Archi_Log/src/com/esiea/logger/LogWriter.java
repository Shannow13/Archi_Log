package com.esiea.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
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
		
		// Affichage du message dans le terminal
		System.out.println(toPrint);
		
		// Formatage du nom de fichier
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		int numberOfFile = 1;
		File file = null;
		String fileToWrite = fileName.concat("_"+dateFormat.format(date)+"_"+ Integer.toString(numberOfFile) +".txt");


		boolean write = false;

		 while(!write){
		
			 file = new File(fileToWrite);
			 if(!file.exists()){
					 try {
						file.createNewFile();
					} catch (IOException e) {
						System.out.println("Impossible de créer le fichier");
						e.printStackTrace();
						return;
					}
			 }
			
			 write = true;
			
			 FileReader fr;
			try {
				fr = new FileReader(file);
			
				 if(lineNumber(fr) >= 200){
					 write = false;
					 numberOfFile++;
					 fileToWrite = fileName.concat("_"+dateFormat.format(date)+"_"+ Integer.toString(numberOfFile) +".txt");
					 }
				
				 fr.close();
			
			 } catch (FileNotFoundException e) {
				 System.out.println("Impossible de lire le fichier");
					e.printStackTrace();
					return;
				} catch (IOException e) {
					System.out.println("Probleme avec la lecture de fichier");
					e.printStackTrace();
					return;
			}

		 }
		 
		 
		 try {
			FileWriter fw = new FileWriter(file,true);
			
			fw.write(toPrint+"\n");
			
			fw.close();
			
		} catch (IOException e) {
			System.out.println("Erreur avec le File name");
			e.printStackTrace();
		}
		
	}

	private static int lineNumber(FileReader fr) {
		int count = 0;
		
		 LineNumberReader lnr = new LineNumberReader(fr);
		
		 try {
			while(lnr.readLine() != null){
			 count ++;
			 }
			
			lnr.close();
		} catch (IOException e) {
			System.out.println("Impossible de lire ligne a ligne");
			e.printStackTrace();
			return -1;
		}
		
		
		 return count;
	}

}
