package pck;

public class AddMeMessage implements Message{
	String userName;
	String roomName;
	AddMeMessage(String roomName, String userName){
		this.roomName = roomName;
		this.userName = userName;
	}
}