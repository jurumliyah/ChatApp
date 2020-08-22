package pck;

public class UpdateUser extends Message{
	String userName;
	String roomName;
	//String method;
	UpdateUser(String roomName, String userName){
		this.roomName = roomName;
		this.userName = userName;
		//this.method = method;
	}
}