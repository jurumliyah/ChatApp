package pck;

public class ChangeRoomMessage extends Message {
	String userName;
	String newRoom;
	
	ChangeRoomMessage(String userName, String newRoom){
		this.userName = userName;
		this.newRoom = newRoom;
	}
}
