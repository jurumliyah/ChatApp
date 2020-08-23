package pck;

public class PrivateChatRequest extends Message {
	String user1;
	String user2;
	String introduce;
	
	PrivateChatRequest(String user1, String user2, String introduce){
		this.user1 = user1;
		this.user2 = user2;
		this.introduce = introduce;
	}
}
