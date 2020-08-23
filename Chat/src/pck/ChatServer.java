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
	static boolean online = false;
	
	ChatServer (){
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
		
	}
	public static void openServer(){
		online = true;
		Thread serverThread = new ChatServer();
		serverThread.start();
	}
	public static void closeServer(){
	
		Thread.currentThread().interrupt();
		online = false;
		for (ClientThread ct : clientThreads) {
			try {
				ct.stream.out.close();
				ct.stream.in.close();
				ct.stream.socket.close();
				System.out.println("Closing Client Threads");
				ct.interrupt();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
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
			UserJoinedMessage msg = new UserJoinedMessage(getTime() + " : " + userName + " joined Room " + roomName);
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
		static void broadcastUserLeft(String userName, String oldRoom, String newRoom) {
			UserLeftMessage msg = new UserLeftMessage (userName, oldRoom, getTime() + " : " + userName + " left Room " + oldRoom);
			for (ClientThread ct : clientThreads) { 				
				if(oldRoom.equals(ct.myRoomName)) {
					try {
						ct.stream.out.writeObject(msg);
					} catch (IOException e) {e.printStackTrace();}
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
		public static void sendRooms() {
			for (ClientThread ct : clientThreads) { 
				if(ct.myRoomName != null) {
					try {
						ct.stream.out.writeObject(new RoomsMessage(rooms));
						ct.stream.out.reset();
					} catch (IOException e) {e.printStackTrace();}
				}
			}
		}
		public static void sendPrivateRequest(PrivateChatRequest msg) {
			for (ClientThread ct : clientThreads) {
				if (ct.myName.equals(msg.user2)) {
					try {
						System.out.println("SERVER : SENDING REQUEST TO USER: " + msg.user2);

						ct.stream.out.writeObject(msg);
						ct.stream.out.reset();
					} catch (IOException e) {e.printStackTrace();}
				}
			}
		}
		public static void sendStartPrivateChat(StartPrivateChatMessage msg) {
			for (ClientThread ct : clientThreads) {
				if (ct.myName.equals(msg.user1) || ct.myName.equals(msg.user2)) {
					try {
						ct.stream.out.writeObject(msg);
						ct.stream.out.reset();
					} catch (IOException e) {e.printStackTrace();}
				}
			}
		}
		public static void broadcastPrivateMessage (PrivateMessage msg) {
			for (ClientThread ct : clientThreads) {
				if (ct.myName.equals(msg.user1) || ct.myName.equals(msg.user2)) {
					try {
						ct.stream.out.writeObject(msg);
						ct.stream.out.reset();
					} catch (IOException e) {e.printStackTrace();}
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

		while(!Thread.currentThread().isInterrupted() && online) {
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
		
		
		 void addUser( String userName, String roomName) {
			{synchronized(rooms){
				for (Room room : rooms) {
					//if (getRoomName(msg.userName).equals(msg.roomName))
					if (room.roomName.equals(roomName)) {
						room.addUser(userName);
						System.out.println("SERVER: ADDING USER to Room " + roomName);
					}
				}
			}}

		}
		 void removeUser(String userName, String roomName) {
			 {synchronized(rooms){
				 for (Room room : rooms) {
					 if (room.roomName.equals(roomName)){
						 room.removeUser(userName);
					 }
				 }
			 }}		 
		 }
			
		public void run() {
			try {
				//sendRooms();
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
					addUser(myName, myRoomName);
					sendRooms();
					updateUser(myRoomName, myName);
					broadcastNewUser(((AddMeMessage) msg).roomName, ((AddMeMessage)msg).userName);
				}
				if (msg instanceof UpdateMessage ) {
					UpdateMessage help = (UpdateMessage)msg;
					System.out.println("SERVER: instance UPDATE MSG: ClientTHREAD NAME: " + Thread.currentThread().getName() + "BROADCASTING: " + help.text + "///////////////////////////");
					broadcastMessage(help, myRoomName);					
					//addUser((AddMeMessage)msg);
					//updateUser(((AddMeMessage) msg).userName);
				}
				
				if (msg instanceof ChangeRoomMessage ) {
					System.out.println("SERVER: instance CHANGE ROOM: ClientTHREAD NAME: " + Thread.currentThread().getName() + "///////////////////////////");
					String newRoom = ((ChangeRoomMessage)msg).newRoom;
					String oldRoom = ((ChangeRoomMessage)msg).oldRoom;
					String userName = ((ChangeRoomMessage)msg).userName;
					
					removeUser(userName, oldRoom);
					myRoomName = newRoom;
					addUser(userName, myRoomName);
					sendRooms();
					broadcastUserLeft(userName, oldRoom, newRoom);
					//sendRooms();
					//addUser(userName, myRoomName);
					//sendRooms();
					updateUser(myRoomName, myName);
					broadcastNewUser(myRoomName, myName);
				}
				
				if (msg instanceof PrivateChatRequest ) {
					System.out.println("SERVER: instance PRIVATE REQUEST: ClientTHREAD NAME: " + Thread.currentThread().getName() + "///////////////////////////");
					PrivateChatRequest help = (PrivateChatRequest)msg;
					sendPrivateRequest(help);
				}
				if (msg instanceof AcceptedPrivate ) {
					System.out.println("SERVER: instance PRIVATE REQUEST: ClientTHREAD NAME: " + Thread.currentThread().getName() + "///////////////////////////");
					AcceptedPrivate help = (AcceptedPrivate)msg;
					StartPrivateChatMessage m = new StartPrivateChatMessage(help.user1, help.user2);
					sendStartPrivateChat(m);
				}
				
				if (msg instanceof PrivateMessage ) {
					System.out.println("SERVER: instance PrivateMessage: ClientTHREAD NAME: " + Thread.currentThread().getName() + "///////////////////////////");
					PrivateMessage help = (PrivateMessage)msg;
					broadcastPrivateMessage(help);
				}


				}
				
			} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
			
		}
	}
}
