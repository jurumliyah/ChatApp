package Messages;

public class AddMeMessage extends Message{
	public String userName;
	public String roomName;
	public AddMeMessage(String roomName, String userName){
		this.roomName = roomName;
		this.userName = userName;
	}
}