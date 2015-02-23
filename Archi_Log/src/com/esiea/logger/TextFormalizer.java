package com.esiea.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TextFormalizer {

	public static String formalized(String string, State state, Class<?> myClass) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		//Formattage de la string
		String formattedString = new String("[Logger."+state+"] ["+dateFormat.format(date)+"] ["+ myClass.getSimpleName() +"] "+string);
		
		//System.out.println(formattedString);
		
		return formattedString;
	}

}
