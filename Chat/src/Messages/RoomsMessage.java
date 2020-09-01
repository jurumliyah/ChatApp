package Messages;

import java.util.ArrayList;

import pck.Room;

public class RoomsMessage extends Message{
	public ArrayList<Room> rooms;
	
	public RoomsMessage(ArrayList<Room> rooms){
		this.rooms = rooms;
	}

}
