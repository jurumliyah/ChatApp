package pck;

import java.util.ArrayList;

public class RoomsMessage extends Message{
	ArrayList<Room> rooms;
	RoomsMessage(ArrayList<Room> rooms){
		this.rooms = rooms;
	}

}
