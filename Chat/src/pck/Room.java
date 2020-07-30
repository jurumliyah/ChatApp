package pck;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable{
	
	String roomName;
	ArrayList<String> userslist = new ArrayList<String>();
	
	Room(String roomName){
		this.roomName = roomName;
	}
	
	void clone(ArrayList<String> userslist){
		this.userslist = userslist;
	}
	void addUser(String userName) {
		this.userslist.add(userName);
		System.out.println("Adding user: " + userName + " to Room: " + roomName);
		for (String s : userslist) {
			System.out.println("Users: " + s + " in Room: " + roomName);

		}
	}
	
	void addAllUsers(String[] users) {
		for (String u:users) {
			this.userslist.add(u);
		}
	}
	
	void addAllUsers(ArrayList<String> users) {
		for (String u:users) {
			this.userslist.add(u);
		}
	}
	
	boolean searchUser(String userName) {
		boolean test = false;
		for (String u : userslist) {
			if (u.equals(userName)) {
				test=true;
			}
		}
		return test;
	}

}
