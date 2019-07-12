import java.net.*;
import java.io.*;

public class HTTPEcho {
    
	public static void main(String[] args) throws IOException {
    	String clientSentence; 
    	int port = Integer.parseInt(args[0]);
    	ServerSocket welcomeSocket = new ServerSocket(port);
    	String HTTPHeader = "HTTP/1.1 200 OK \r\n\r\n";
    	
    	
		while(true) {
			
			StringBuilder toClient = new StringBuilder();			
			Socket connectionSocket = welcomeSocket.accept();			
	    		
			BufferedReader inFromClient =
	    				new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	    		
	    	DataOutputStream outToClient =
	    				new DataOutputStream(connectionSocket.getOutputStream());
	    		
	    	toClient.append(HTTPHeader);
	    	
	    	while((clientSentence = inFromClient.readLine()) != null &&  clientSentence.length() != 0){
	             toClient.append(clientSentence + '\n');	             
	        }
	    	
	    			    				
	    	outToClient.writeBytes(toClient.toString()); 	    		
	    		
	    	connectionSocket.close(); 
	    	inFromClient.close();
	        outToClient.close();
	        welcomeSocket.close();
	    	
		} 
		
    	
		
		 
		
    }
}


