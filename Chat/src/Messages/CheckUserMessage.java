package Messages;

public class CheckUserMessage extends Message{
	public String userName;
	public CheckUserMessage(String userName){
		this.userName = userName;
	}
}