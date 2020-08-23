package pck;

public class StartPrivateChatMessage extends Message {
	String user1;
	String user2;
	
	StartPrivateChatMessage(String user1, String user2){
		this.user1 = user1;
		this.user2 = user2;
	}
	
}
