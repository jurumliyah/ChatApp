package Messages;

public class UserLeftMessage extends Message {
	public String roomName;
	public String userName;
	public String text;
	
	public UserLeftMessage(String userName, String roomName){
		this.userName = userName;
		this.roomName = roomName;
	}
	public UserLeftMessage(String userName, String roomName, String text){
		this.userName = userName;
		this.roomName = roomName;
		this.text = text;
	}

}
