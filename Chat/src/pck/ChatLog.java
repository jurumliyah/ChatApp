package pck;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatLog implements Serializable{
	static Text text;
	static String line;
	
	ChatLog(String str){
		text = new Text();
		text.setText(this.line);
		}

	void addText(String string) {
		this.line = this.line + string +"\n";
		this.text.setText(this.line);
	}
		

}
