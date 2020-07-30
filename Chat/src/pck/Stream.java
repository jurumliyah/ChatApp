package pck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Stream {
Socket socket;
ObjectOutputStream out;
ObjectInputStream in;

Stream (String host, int port){
	try {
		socket = new Socket(host, port);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	} catch (IOException e) {e.printStackTrace();}
	
}
}
