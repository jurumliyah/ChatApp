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
	
	 static void updateUser(String roomName, String userName) {

		UpdateUser msg = new UpdateUser(roomName, userName) ; 
		for (ClientThread ct : clientThreads) { 				
			if(roomName.equals(ct.myRoomName)) { 	
				try { 				
					System.out.println("SERVER: broadcast UPDATE USER METHOD: CT THREAD: " + ct.getName()  + " :  ROOM NAME / USERNAME: " + ct.myRoomName + " / " + ct.myName);
					ct.stream.out.writeObject(msg); 						
					ct.stream.out.reset(); }
				catch (IOException e) {e.printStackTrace();}
				}
			}
			}
		
		static void getThreadRooms() {
			for (ClientThread ct : clientThreads) {
				System.out.println("Client THREAD: " + ct.getName() + "; ROOM NAME: " + ct.myRoomName);
			}
		}
		static void broadcastNewUser(String roomName, String userName) {
			UserJoinedMessage msg = new UserJoinedMessage(getTime() + " : " + userName + " joined Chat"); ;
			for (ClientThread ct : clientThreads) { 				
				if(roomName.equals(ct.myRoomName)) { 	
					try { 	
						System.out.println("SERVER: broadcast NEW USER METHOD: ClientTHREAD NAME:" + ct.getName() + ": THIS THREAD ROOM NAME / USERNAME: " + ct.myRoomName + " / " + ct.myName);
						ct.stream.out.writeObject(msg); 						
						ct.stream.out.reset(); }
					catch (IOException e) {e.printStackTrace();}
					}
				}
		}
		
		static void broadcastMessage(UpdateMessage msg, String roomName) {
			for (ClientThread ct : clientThreads) { 				
				if(roomName.equals(ct.myRoomName)) { 	
					try { 		
						System.out.println("SERVER : MSG : FROM METHOD ROOMNAME: " + roomName);
						System.out.println("SERVER: MSG : ClientTHREAD NAME:" + ct.getName() + " ROOMNAME / USERNAME: "  + ct.myRoomName + " " + ct.myName);
						ct.stream.out.writeObject(msg); 						
						ct.stream.out.reset(); }
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
	
	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e1) {e1.printStackTrace();}

		while(true) {
			try {
				socket = server.accept();
				Thread clientThread = new ClientThread(socket);
				clientThreads.add((ClientThread) clientThread);
				clientThread.start();
				} catch (IOException e) {e.printStackTrace();}
			
					
			}					
	}
	
	static class ClientThread extends Thread{
	    String myName;
		String myRoomName;
		Stream stream;
		
		ClientThread (Socket socket){
			stream = new Stream(socket);
		}
		
		public String getmyName() {
			return this.myName;
		}
		public String getmyRoomName() {
			return this.myRoomName;
		}

		static boolean checkUser(String name) {
			boolean test = false;
			for (Room room : rooms) {
				if (room.searchUser(name)) {test = true; System.out.println("aTkav user vec postoji: "  + name);}
			}
			if(test == false) System.out.println("Tkav user ne postoji " + name);

			return test;
		}
		
		
		 void addUser(AddMeMessage msg) {
			{synchronized(this){
				for (Room room : rooms) {
					//if (getRoomName(msg.userName).equals(msg.roomName))
					if (room.roomName.equals(msg.roomName)) {
						room.addUser(msg.userName);
						System.out.println("SERVER: ADDING USER to Room " + msg.roomName);


					}
				}
			}}

		}
			
		public void run() {
			try {
				stream.out.writeObject(rooms);
				stream.out.reset();
				
				Message msg;
				while (( (msg = (Message) stream.in.readObject()) != null)&& (!Thread.currentThread().isInterrupted())){
				if (msg instanceof CheckUserMessage) {
					System.out.println("SERVER: check USER: ClientTHREAD NAME: " + Thread.currentThread().getName() + "///////////////////////////");
					String name = ((CheckUserMessage) msg).userName;
					stream.out.writeObject(checkUser(name));
					stream.out.reset();
				}
				if (msg instanceof AddMeMessage ) {
					System.out.println("SERVER: instance ADD ME: ClientTHREAD NAME: " + Thread.currentThread().getName() + "///////////////////////////");
					myName = ((AddMeMessage)msg).userName;
					myRoomName = ((AddMeMessage)msg).roomName;
					addUser((AddMeMessage)msg);
					updateUser(((AddMeMessage) msg).roomName, ((AddMeMessage)msg).userName);
					broadcastNewUser(((AddMeMessage) msg).roomName, ((AddMeMessage)msg).userName);
				}
				if (msg instanceof UpdateMessage ) {
					UpdateMessage help = (UpdateMessage)msg;
					System.out.println("SERVER: instance UPDATE MSG: ClientTHREAD NAME: " + Thread.currentThread().getName() + "BROADCASTING: " + help.text + "///////////////////////////");
					broadcastMessage(help, myRoomName);					
					//addUser((AddMeMessage)msg);
					//updateUser(((AddMeMessage) msg).userName);
				}

				}
				
			} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
			
		}
	}
}
