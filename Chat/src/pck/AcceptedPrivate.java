package pck;

import Messages.Message;

public class AcceptedPrivate extends Message{
	String user1;
	String user2;
	AcceptedPrivate(String user1, String user2){
		this.user2 = user2;
		this.user1 = user1;

	}
}
