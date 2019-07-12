import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class MyRunnable implements Runnable {

	Socket clientSocket;
		
	public MyRunnable(Socket c){	
			clientSocket = c;	
	}	
	
	public void run(){	
			
		try{
			String hostname=null;
	        String hostport=null;
	        String serverInput=null;				
	        
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());	
        	
		    String clientSentence = inFromClient.readLine();
        	String[] s = clientSentence.split("[ =&?/]");		 		
		 		
		 	if(s[2].equals("ask")){
	        	for(int i = 0; i<s.length;i++){
    				if(s[i].equals("hostname")){
    					hostname = s[i+1];
    					i++;
    				}
    				else if(s[i].equals("port")){
    					hostport = s[i+1];
    					i++;
    				}
    				else if(s[i].equals("string")){
    					serverInput = s[i+1];
    					i++;
    				}
        		}

        		String sAnswer = null;
        		try{	
        			sAnswer = TCPClient.askServer(hostname,Integer.parseInt(hostport),serverInput);
	        		String header  = "HTTP/1.1 200 OK\r\n\r\n";
	        		outToClient.writeBytes(header + sAnswer +"\r\n");
	        	}
	        	catch( Exception e){
	        			outToClient.writeBytes("HTTP/1.1 404 Not found\r\n");
	        	}
	        }
	        else{	
	        	outToClient.writeBytes("HTTP/1.1 400 Bad request\r\n"); 
	        }		    	
	
	    	clientSocket.close();	
		    inFromClient.close();	
		    outToClient.close();	
	      	System.out.println("Closed");
	
		}
	
		catch(IOException e){
			System.out.println("Error:run");
		}
	
	}

}