package com.esiea.logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {

	public static void write(String toPrint, String fileName) throws IOException {
		
		System.out.println(toPrint);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		int numberOfFile = 1;
		File file = null;
		
		String fileToWrite = fileName.concat("_"+dateFormat.format(date)+"_"+ Integer.toString(numberOfFile) +".txt");
		
		System.out.println(fileName);
		
		boolean write = false;
		
		while(!write){
		
			file = new File(fileToWrite);
			if(!file.exists()){
				file.createNewFile();
			}
			
			write = true;			
			
				FileReader fr = new FileReader(file);
				
				
				if(lineNumber(fr) >= 200){
					write = false;
					numberOfFile++;
					fileToWrite = fileName.concat("_"+dateFormat.format(date)+"_"+ Integer.toString(numberOfFile) +".txt");
				}
				
				fr.close();
		
		}
		
		
			FileWriter fw = new FileWriter(file,true);
			
			fw.write(toPrint+"\n");
			
			fw.close();
		
		// TODO Ajouter pour la BDD
		
	}

	private static int lineNumber(FileReader fr) throws IOException {
		int count = 0;
		
		LineNumberReader lnr = new LineNumberReader(fr);
		
		while(lnr.readLine() != null){
			count ++;
		}
		
		lnr.close();
		
		return count;
	}

}
