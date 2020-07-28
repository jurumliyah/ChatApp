package uebung2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class servertest {
	static int i = 0;
	static int port = 4999;
	static ArrayList<String> userList = new ArrayList<String>();
	static ObjectOutputStream out;
	static ObjectInputStream   in;
	
	public static void main(String[] args) {
		createServer();
		//System.out.println("ej dje si");	
	}
	
	static void createServer(){
		try {
			ServerSocket server = new ServerSocket(port);
			while(true) {
				Socket socket = server.accept();
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
				
				String str = "primljen" + i++;
				out.writeObject(str);
				out.reset();
				try {
					String newUser = (String)in.readObject();
					if (searchUser(newUser)) {
						System.err.println("Server: User exists, change user name!!!");
						out.writeObject(false);
						out.reset();
					}
					else {
						System.out.println("Server: User allowed");
						userList.add(newUser);
						out.writeObject(true);
						out.reset();
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				//out.close();
				//in.close();
				//server.close();
				//socket.close();
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("odbijeno");
		}
		
	}
	
	static boolean searchUser(String newUser) {
		boolean test = false;
		for (String user : userList ) {
			if (user.equals(newUser)) { test = true; }
		}
		return test;
	}
	
	static Object readObject() {
		Object obj = null;
		try {
			obj = in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	static void writeObject(Object obj) {
		try {
			out.writeObject(obj);
			out.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
