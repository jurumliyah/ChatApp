package Messages;

public class ChangeRoomMessage extends Message {
	public String userName;
	public String newRoom;
	public String oldRoom;
	
	public ChangeRoomMessage(String userName, String newRoom, String oldRoom){
		this.userName = userName;
		this.newRoom = newRoom;
		this.oldRoom = oldRoom;
	}
}
