package edu.escuelaing.arep.httpserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
public interface handler {
	 void get(String value) throws IOException;
}
