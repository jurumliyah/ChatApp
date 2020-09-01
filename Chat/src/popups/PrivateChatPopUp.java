package popups;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrivateChatPopUp {
	public Stage primaryStage;
	public Button button;
	public Button button2;
	String user;
	public TextField input;
	
	public PrivateChatPopUp(String user){
		input = new TextField();
		input.setMinSize(200, 80);
		input.setMinSize(200, 80);
		input.setText("Hey, do you want to chat with me?");
		input.setAlignment(Pos.CENTER);

		this.user = user;
		primaryStage = new Stage();
		Scene scene;
		BorderPane root = new BorderPane();
		button = new Button("YES");
		button2 = new Button ("NO");
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		
		String info = "Do you want to chat privately with user: " + this.user + " ?";
		Label label = new Label();
		Label label2 = new Label();
		label.setText(info);
		label2.setText("Type request message");
		label.setAlignment(Pos.CENTER);
		label2.setAlignment(Pos.CENTER);
        label.setStyle("-fx-padding: 10,10,10,10");



		button.setPadding(new Insets(0, 20, 0, 20));
		button.setAlignment(Pos.CENTER_LEFT);


		button2.setPadding(new Insets(0, 20, 0, 20));
		button.setAlignment(Pos.CENTER_RIGHT);

		hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);

        hbox.getChildren().addAll(button, button2);
        hbox.setStyle("-fx-padding: 20,0,0,0");
        vbox.getChildren().addAll(label, label2, input, hbox);
        vbox.setAlignment(Pos.CENTER);
        
        root.setCenter(vbox);
        scene = new Scene(root, 300,180);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
