package com.esiea.logger;

import java.util.Calendar;


public class TextFormalizer {

	public static String formalized(String string, State state) {
		
		//récupération de la date
		Calendar cal = Calendar.getInstance();		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		String date = new String(year+"/"+month+"/"+day+"-"+hour+":"+minute);
		
		//Formattage de la string
		String formattedString = new String("[Logger."+state+"] ["+date+"] "+string);
		
		//System.out.println(formattedString);
		
		return formattedString;
	}

}
