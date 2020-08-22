package pck;

public class ChangeRoomMessage extends Message {
	String userName;
	String newRoom;
	String oldRoom;
	
	ChangeRoomMessage(String userName, String newRoom, String oldRoom){
		this.userName = userName;
		this.newRoom = newRoom;
		this.oldRoom = oldRoom;
	}
}
