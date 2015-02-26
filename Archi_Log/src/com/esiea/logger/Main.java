package com.esiea.logger;


public class Main {
	

	public static void main(String[] args) {
	
		
		Logger log = LoggerFactory.getLogger(Main.class, "debug", true, true);
		
			log.debug("debug");
			log.info("info");
			log.error("error");
		
		
	
	}
	

}
