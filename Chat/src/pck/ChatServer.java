package pck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

	static int i = 0;
    static int port = 4909;
	static ArrayList<Room> rooms = new ArrayList<Room>();

	static ObjectOutputStream out;
	static ObjectInputStream   in;
	static ServerSocket server;
	static Socket socket;
	
	public static void main(String[] args) {
		rooms.add(new Room("room1"));
		rooms.add(new Room("room2"));
		rooms.add(new Room("room3"));
		rooms.add(new Room("room4"));
		rooms.add(new Room("room5"));
		rooms.add(new Room("room6"));
		rooms.add(new Room("room7"));
		rooms.add(new Room("room8"));
		rooms.add(new Room("room9"));
		rooms.add(new Room("room10"));
		rooms.add(new Room("room11"));


		createServer();

	}
	
	static void createServer(){
		try {
			server = new ServerSocket(port);
			while(true) {
				socket = server.accept();
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
	
				try {
					out.writeObject(rooms);
					out.reset();
					Message msg;
					while ( (msg = (Message) in.readObject()) != null) {
					if (msg instanceof CheckUserMessage) {
						String name = ((CheckUserMessage) msg).userName;
						out.writeObject(checkUser(name));
						out.reset();
					}
					if (msg instanceof AddMeMessage ) {
						addUser((AddMeMessage)msg);
						updateUser(((AddMeMessage) msg).userName);
					}
					}
					
				} catch (ClassNotFoundException e) {e.printStackTrace();}			
			}
						
		} catch (IOException e) {e.printStackTrace(); System.out.println("odbijeno");
		}
		
	}
	
	static boolean checkUser(String name) {
		boolean test = false;
		for (Room room : rooms) {
			if (room.searchUser(name)) {test = true; System.out.println("aTkav user vec postoji: "  + name);}
		}
		if(test == false) System.out.println("Tkav user ne postoji " + name);

		return test;
	}
	
	static String getRoomName (String userName) {
		String name = null;
		for (Room room : rooms) {
			for (String user : room.userslist) {
				if (user.equals(userName)) {
					name = room.roomName;
				}
			}
		}
		return name;
	}
	
	static void addUser(AddMeMessage msg) {
		for (Room room : rooms) {
			if (room.roomName.equals(msg.roomName)) {
				room.addUser(msg.userName);
			}
		}
	}
	
	static void updateUser(String userName) {
		UpdateUser msg = new UpdateUser(getRoomName(userName), userName) ;
		try {
			out.writeObject(msg);
			out.reset();
		} catch (IOException e) {e.printStackTrace();
		}
	}


}
