package pck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ChatServer extends Thread {

	static ChatLog chatLog;
	static int i = 0;
    static int port = 4909;
	static ArrayList<Room> rooms = new ArrayList<Room>();
	static ArrayList<ClientThread> clientThreads = new ArrayList<ClientThread>();
	static ServerSocket server;
	static Socket socket;
	
	public static void main(String[] args) {
		chatLog = new ChatLog("");
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
		
		Thread serverThread = new ChatServer();
		serverThread.start();

	}
	@Override
	public void run() {
		
		try {
			server = new ServerSocket(port);
			while(true) {
				socket = server.accept();
				Thread clientThread = new ClientThread();
				clientThreads.add((ClientThread) clientThread);
				clientThread.start();								
			}					
		} catch (IOException e) {e.printStackTrace(); System.out.println("odbijeno");}
	}
	
	static class ClientThread extends Thread{
		String myName;
		String myRoomName;
		static ObjectOutputStream out;
		static ObjectInputStream   in;

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
				//if (getRoomName(msg.userName).equals(msg.roomName))
				if (room.roomName.equals(msg.roomName)) {
					room.addUser(msg.userName);
					System.out.println("Server: test: addUser to Room " + getRoomName(msg.userName));

				}
			}
		}
		
		/*
		static void updateUser(String userName) {
			UpdateUser msg = new UpdateUser(getRoomName(userName), userName) ;
			
			try {
				out.writeObject(msg);
				out.reset();
			} catch (IOException e) {e.printStackTrace();
			}
		}
		*/
	 static void updateUser(String userName) {

		UpdateUser msg = new UpdateUser(getRoomName(userName), userName) ; 
		for (ClientThread ct : clientThreads) { 				
			if(ct.myRoomName.equals(getRoomName(userName))) { 	
				System.out.println("Server: test updateUser method: Room Name: " + getRoomName(userName));
				try { 						
					ct.out.writeObject(msg); 						
					ct.out.reset(); }
				catch (IOException e) {e.printStackTrace();}
				}
			}
			}
		
		
		static void broadcastNewUser(String userName) {
			UserJoinedMessage msg = new UserJoinedMessage(getTime() + " : " + userName + "joined Chat"); ;
			for (ClientThread ct : clientThreads) { 				
				if(ct.myRoomName.equals(getRoomName(userName))) { 					
					try { 						
						ct.out.writeObject(msg); 						
						ct.out.reset(); }
					catch (IOException e) {e.printStackTrace();}
					}
				}
		}
		static String getTime() {
			String str = null;
			LocalDateTime time = LocalDateTime.now();
		    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		    str = format.format(time);
		    return str;		
		}
		
		
		public void run() {
			try {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());

				out.writeObject(rooms);
				out.reset();
				
				
				Message msg;
				while (( (msg = (Message) in.readObject()) != null)&& (!Thread.currentThread().isInterrupted())){
				if (msg instanceof CheckUserMessage) {
					String name = ((CheckUserMessage) msg).userName;
					out.writeObject(checkUser(name));
					out.reset();
				}
				if (msg instanceof AddMeMessage ) {
					
					myName = ((AddMeMessage)msg).userName;
					System.out.println("(myName)  :  " + myName);
					addUser((AddMeMessage)msg);
					myRoomName = getRoomName(myName);
					System.out.println("getRoomName(myName)  :  " + getRoomName(myName));
					updateUser(((AddMeMessage) msg).userName);
					broadcastNewUser(((AddMeMessage) msg).userName);
				}
				if (msg instanceof UpdateMessage ) {
					UpdateMessage help = (UpdateMessage)msg;
					System.out.println("Server: instance of Update Message: " + help.text);
					out.writeObject(help);
					out.reset();
					//addUser((AddMeMessage)msg);
					//updateUser(((AddMeMessage) msg).userName);
				}

				}
				
			} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
			
		}
	}
}
