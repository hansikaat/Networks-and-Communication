package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {
    
    public static String askServer(String hostname, int port, String ToServer) throws  IOException {       
    	
    	if(ToServer!= null){
    		String modifiedSentence=null;    	    	  	
        	
        	
        	Socket clientSocket = new Socket(hostname, port);     	
        	
        	clientSocket.setSoTimeout(2000);
        	
        	DataOutputStream outToServer =
        			new DataOutputStream(clientSocket.getOutputStream());        	
        	
        	BufferedReader inFromServer =
        			new BufferedReader(new
        			InputStreamReader(clientSocket.getInputStream()));    	
        	        	
        	outToServer.writeBytes(ToServer+'\n');
        	
        	String s ;
        	StringBuilder sb = new StringBuilder("");
        	try{
        		while( (s = inFromServer.readLine()) != "\n" && s != null){
        			sb.append(s+'\n');
        		}
        		clientSocket.close();
        		modifiedSentence = sb.toString();
        		return modifiedSentence;
        	}
        	catch( IOException e){
        		clientSocket.close();
        		modifiedSentence = sb.toString();
        		return modifiedSentence;
        	}
        	     		
    	}
    	
    	else {    	
    		return askServer(hostname, port);
    	}
    	
    	
    }  		
    		
    		

    public static String askServer(String hostname, int port) throws  IOException {
    	
    	String modifiedSentence=null; 
    	
    	Socket clientSocket = new Socket(hostname, port);    
    	
    	clientSocket.setSoTimeout(10000);
    	
    	BufferedReader inFromServer =
    			new BufferedReader(new
    			InputStreamReader(clientSocket.getInputStream()));      	
    	
    	String s ;
    	StringBuilder sb = new StringBuilder("");
    	final int MAX_LINES = 1024;
        int counter = 0;
    	
    	try{
    		while( (s = inFromServer.readLine()) != "\n" && s != null){
    			sb.append(s+'\n');
                counter++;
                if(counter >= MAX_LINES)
                    return sb.toString();
    		}
    		clientSocket.close();
    		modifiedSentence = sb.toString();
    		return modifiedSentence;
    	}
    	catch( IOException e){
    		clientSocket.close();
    		modifiedSentence = sb.toString();
    		return modifiedSentence;
    	}
    	     	 
		
    }
}

