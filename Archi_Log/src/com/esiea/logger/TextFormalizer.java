package com.esiea.logger;

public class TextFormalizer {

	public static String formalized(String string, State state) {
		
		
		String formattedString = new String("[Logger."+state+"] [ "+string);
		
		System.out.println(formattedString);
		
		return formattedString;
	}

}
