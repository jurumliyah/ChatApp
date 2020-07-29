package pck;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class UpdateMessage extends Message {

	String text;
	//String line;
	UpdateMessage(String text ){
		this.text = text;
	}
	UpdateMessage(){}
}
