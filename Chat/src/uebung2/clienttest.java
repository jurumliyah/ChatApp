package uebung2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class clienttest {
	static int port = 4999;
	static String userName;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	
	clienttest(String userName){
		this.userName = userName;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket clientsocket = new Socket("localhost", port);
			//PrintWriter pr = new PrintWriter(clientsocket.getOutputStream());
			
			in = new ObjectInputStream(clientsocket.getInputStream());
			out = new ObjectOutputStream(clientsocket.getOutputStream());
			try {
				String read =(String) in.readObject();
				System.out.println(read);
				String user="jasir";
				out.writeObject(user);
				out.reset();
				Boolean test = (Boolean)in.readObject();
				if (test) System.out.println("Awesome, I am accepted");
				else System.out.println("I am rejected");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//in.close();
			//out.close();
			clientsocket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
