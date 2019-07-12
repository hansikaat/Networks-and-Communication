import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class HTTPAsk {
    public static void main( String[] args) throws IOException {
        
    	int port = Integer.parseInt(args[0]);
        ServerSocket welcomeSocket = new ServerSocket(port);
        String hostname;
        String hostport;
        String serverInput;

    	try{
        	while(true){
	        	Socket connectionSocket = welcomeSocket.accept();
	        	BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	        	DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

	        	hostname = null;
	        	hostport = null;
	        	serverInput = null;

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
	        	else{	outToClient.writeBytes("HTTP/1.1 400 Bad request\r\n"); }

	     		connectionSocket.close();
		        inFromClient.close();
		        outToClient.close();
	        	System.out.println("Closed");
        		
        	}
        }
    catch( IOException e){
    System.out.println("exception");
    System.exit(1);
    }

    }
}