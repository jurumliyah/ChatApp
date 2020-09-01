package Messages;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class UpdateMessage extends Message {

	public String text;
	//String line;
	public UpdateMessage(String text ){
		this.text = text;
	}
	public UpdateMessage(){}
}
