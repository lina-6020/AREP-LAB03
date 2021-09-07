package edu.escuelaing.arep.nanoSpring;

import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Controller {
	@RequestMapping(value = "/sentence")
	static public String getSentence() {
		System.out.println("ejecutando:"+Controller.class);
		return "Greetings from Micro Spring Boot!";
	}
	@RequestMapping("/sum")
	public static Double sum() {
		return 2.0 + 2.0;
	}



}