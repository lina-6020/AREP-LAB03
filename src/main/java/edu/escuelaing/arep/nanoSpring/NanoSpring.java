package edu.escuelaing.arep.nanoSpring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Handler;

public class NanoSpring {
	private static HashMap<String, Method> routes= new HashMap<>();
	
	public void loadComponents() throws SecurityException, ClassNotFoundException {
	    String[] components = {"edu.escuelaing.arep.nanoSpring.Controller"};
		for(String c: components) {
			for(Method m: Class.forName(c).getMethods()) {
				if(m.isAnnotationPresent(RequestMapping.class)) {
					routes.put(m.getAnnotation(RequestMapping.class).value(),m);
				}
			}
		}
	}
	public String invoke(String StaticMethod) {
		String res="";
		try {
			res= routes.get(StaticMethod).invoke(null).toString();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public HashMap getRouter() {
		return routes;
	}
}
