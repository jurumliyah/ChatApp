package Messages;

public class UserJoinedMessage extends Message {
	public String text;
	
	public UserJoinedMessage(String text) {
		this.text = text;
	}
	
}
