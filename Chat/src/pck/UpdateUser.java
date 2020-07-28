package pck;

public class UpdateUser implements Message{
	String userName;
	String roomName;
	UpdateUser(String roomName, String userName){
		this.roomName = roomName;
		this.userName = userName;
	}
}