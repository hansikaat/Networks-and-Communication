import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class ConcHTTPAsk{
	public static void main(String[] args) {	

		try{

			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));

			while(true){
				Socket clientSocket = serverSocket.accept();
				new Thread(new MyRunnable(clientSocket)).start();
			}

		}
		catch(Exception e){
			System.out.println("Error");
		}

	}

}
