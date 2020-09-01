package Messages;

public class PrivateMessage extends Message {

	public String user1;
	public String user2;
	public String sendingUser;
	public String message;
	
	public PrivateMessage (String user1, String user2, String sendingUser, String message){
		this.user1 = user1;
		this.user2 = user2;
		this.sendingUser =  sendingUser;
		this.message = message;
	}
}
