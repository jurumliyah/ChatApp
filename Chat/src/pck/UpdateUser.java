package pck;

public class UpdateUser extends Message{
	String userName;
	String roomName;
	UpdateUser(String roomName, String userName){
		this.roomName = roomName;
		this.userName = userName;
	}
}