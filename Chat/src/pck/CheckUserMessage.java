package pck;

class CheckUserMessage implements Message{
	String userName;
	CheckUserMessage(String userName){
		this.userName = userName;
	}
}