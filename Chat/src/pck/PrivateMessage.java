package pck;

public class PrivateMessage extends Message {

	String user1;
	String user2;
	String sendingUser;
	String message;
	
	PrivateMessage (String user1, String user2, String sendingUser, String message){
		this.user1 = user1;
		this.user2 = user2;
		this.sendingUser =  sendingUser;
		this.message = message;
	}
}
