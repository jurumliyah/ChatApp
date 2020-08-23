package pck;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PrivateChat {

	// id is the name of another client, with whom current client is communicating
	public static String id;
	public static Stage stage;
	ScrollPane scrollpane;
	GridPane center;
	GridPane bottom;
	BorderPane root;
	TextFlow textflow;
	Button button;
	public static TextField input;
	
	PrivateChat(String user) {
		this.id = user;
		stage = new Stage();
		textflow = new TextFlow();
		scrollpane = new ScrollPane();
		center = new GridPane();
		bottom = new GridPane();
		input = new TextField();
		button = new Button("Send");
		root = new BorderPane();
		
		bottom.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		//bottom.setPadding(new Insets(0,200,0,0));
		center.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		input.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 0px;");
		String backgroundcolor = Window.getBackgroundColorCode("blue");
		scrollpane.setStyle(Window.nofocus + "-fx-background-color: " + backgroundcolor + "; -fx-background: " + backgroundcolor + "; -fx-background-radius: 0px;");		
		
		bottom.getRowConstraints().add(new GridRow(1));
		bottom.getColumnConstraints().add(new GridCol(0,80));
		bottom.getColumnConstraints().add(new GridCol(1,20));
		bottom.add(input, 0, 0);
		bottom.add(button, 1, 0);
		bottom.setMinWidth(350);
		
		scrollpane.setContent(textflow);
		center.getColumnConstraints().add(new GridCol(1));
		center.getRowConstraints().add(new GridRow(1));
		center.add(scrollpane, 0, 0);
		center.setMinSize(150, 150);
		
		root.setCenter(center);
		root.setBottom(bottom);
		
		stage.setScene(new Scene(root, 500,500));
		stage.setTitle("Chatting with: "+ user);
		stage.show();
	}
	public void addPrivateMessage(String message, String user){
		Text text = new Text();
		text.setText(Main.getTime() + " " + user + ": " + message);
		text.setFill(Color.WHITE);
		text.setFont(Font.font ("Verdana", 10));
		if (user.equals(this.id)) {text.setFill(Color.ORANGE);}
		textflow.getChildren().add(text);
		textflow.getChildren().add(new Text(System.lineSeparator()));
		input.setText("");
	}
		
}

