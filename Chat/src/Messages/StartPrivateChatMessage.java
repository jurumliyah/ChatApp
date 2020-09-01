package Messages;

public class StartPrivateChatMessage extends Message {
	public String user1;
	public String user2;
	
	public StartPrivateChatMessage(String user1, String user2){
		this.user1 = user1;
		this.user2 = user2;
	}
	
}
