package pck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	int port = 33100;
	ServerSocket server;
	Socket client;
	ChatServer(){
		try {
			server = new ServerSocket(port);
			while(true) {
				Socket client = server.accept();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Connection not established");
		}
	}
	
	private class Stream {
		   ObjectOutputStream write;
		   ObjectInputStream read;
		   
		   
		
	}

}
