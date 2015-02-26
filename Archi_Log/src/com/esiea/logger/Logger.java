package com.esiea.logger;

import static com.esiea.logger.State.DEBUG;
import static com.esiea.logger.State.ERROR;
import static com.esiea.logger.State.INFO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class Logger {
	

	// Attributs dont on a besoin
	// La class d'appel
	private Class<?> MyClass;
	// Le chemin de fichier dans lequel on doit ecrire
	private String fileName;
	// Le  niveau de priorité pour les messages a afficher
	private State level;
	// Boolean pour savoir si on enregistre ou non dans un fichier
	boolean fileOk;


	public Logger(Class<?> MyClass, State level, String fileName, boolean fileOk){
		this.setMyClass(MyClass);
		this.setLevel(level);
		this.setFileName(fileName);
		this.setFileWritting(fileOk);
	}
	

	public void debug(String string){
		if(level.ordinal() <= DEBUG.ordinal()){
			print(string, DEBUG);
		}	
	}
	
	
	public void info(String string){
		if(level.ordinal() <= INFO.ordinal()){
			print(string, INFO);
		}	
	}
	
	
	public void error(String string){
		if(level.ordinal() <= ERROR.ordinal()){
			//System.out.println("Ordinal ok");
			print(string, ERROR);
		}	
	}
	
	
	
	private void print(String string, State level){
		
		String state = level.toString();
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
	    String date = ft.format(dNow);
		Properties prop;
		try {
			prop = LoggerFactory.pload("config.properties");
		
		String toPrint = TextFormalizer.formalized(string, level, MyClass);
		insertLog(prop.getProperty("nameJDBC"), prop.getProperty("url"),date, state,string);

		// Je ne l'ai pas appelé Writer direct parce qu'il existe déjà dans le java.io et ne correspond pas à ce qu'on veut faire
		if(this.isFileWritting()){
			LogWriter.write(toPrint, fileName);
		}else{
			LogWriter.write(toPrint);
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
	private static void insertLog(String nameJDBC, String url, String date, String level, String message){
		
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
	
	// Getter et setter pour tous les attributs, fait en automatique par eclipse
	public State getLevel() {
		return level;
	}

	public void setLevel(State level) {
		this.level = level;
	}

	public Class<?> getMyClass() {
		return MyClass;
	}

	public void setMyClass(Class<?> myClass) {
		MyClass = myClass;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setFileWritting(boolean fileOk) {
		this.fileOk = fileOk;
	}
	public boolean isFileWritting() {
		return this.fileOk;
	}
	
}
