package pck;

public class UserLeftMessage extends Message {
	String roomName;
	String userName;
	String text;
	
	UserLeftMessage(String userName, String roomName){
		this.userName = userName;
		this.roomName = roomName;
	}
	UserLeftMessage(String userName, String roomName, String text){
		this.userName = userName;
		this.roomName = roomName;
		this.text = text;
	}

}
