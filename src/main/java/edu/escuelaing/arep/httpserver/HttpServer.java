/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.httpserver;

/**
 *
 * @author Lina Buitrago
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.escuelaing.arep.nanoSpring.NanoSpring;
import edu.escuelaing.arep.webserver.tools.getFile;
import edu.escuelaing.arep.webserver.tools.getFiles;
import edu.escuelaing.arep.webserver.tools.getImage;


public class HttpServer implements Runnable  {
    private final Socket clientSock;
    private final NanoSpring nano;
    /**
     * Crea un nuevo hilo ClientSock para el socket proporcionado
     *
     * @param clientSocket el socket para el cliente.
     */
    public HttpServer (final Socket clientSocket) {
        this.clientSock = clientSocket;
        this.nano = new NanoSpring();
    }
    @Override
    public void run() {
        try{	
				nano.loadComponents();
                PrintWriter out = new PrintWriter(clientSock.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
                String inputLine, outputLine, path = null;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.matches("(GET)+.*")) {
                        path = inputLine.split(" ")[1];
                    }
                    if (!in.ready()) {
                        break;
                    }
                }
                System.out.println(path);
                if(path.startsWith("/nspapp")) {
                	String fileRequested = path.substring(7);
                	System.out.println(nano.getRouter().toString());
                	System.out.println(fileRequested);
                	if(nano.getRouter().containsKey(fileRequested)) {
                		//System.out.println(fileRequested);
                		//System.out.println(nano.invoke(fileRequested));
                		out.println(getHeader());
                		//out.println(outputLine);
                		out.println(nano.invoke(fileRequested));
                	}
                }
                else{getFiles(path,clientSock);}
                out.close();
                in.close();
        } catch (IOException |SecurityException | ClassNotFoundException e) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    public String getHeader() {
    	return  "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";
    }
    public void getFiles(String path,Socket clientSocket) throws IOException {
    	getFiles file;
    	String outputLine;
    	if(path.contains(".html")||path.contains(".css")||path.contains(".js")) {
    		file = new getFile();
    		file.getFiles(clientSocket, path);
    	}else if(path.contains(".png")) {
    		file = new getImage();
    		file.getFiles(clientSocket, path);
    	}else {
    		PrintWriter out = new PrintWriter(
	                clientSocket.getOutputStream(), true);
    		outputLine = getHeader()
	                 + "<!DOCTYPE html>\n"
	                 + "<html>\n"
	                 + "<head>\n"
	                 + "<meta charset=\"UTF-8\">\n"
	                 + "<title>Pagina ERROR</title>\n"
	                 + "</head>\n"
	                 + "<body>\n"
	                 + "<h1>Archivo no encontrado</h1>\n"
	                 + "</body>\n"
	                 + "</html>\n";
    		out.println(outputLine);
	        out.close();
    	}


    }
}
