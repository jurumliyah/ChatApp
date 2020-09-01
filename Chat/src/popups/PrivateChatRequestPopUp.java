package popups;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrivateChatRequestPopUp  extends Thread{
	String user1;
	String introduce;
	public Stage stage;
	public Button button;
	public Button button2;
	String info;

	public PrivateChatRequestPopUp(String user1, String introduce){
		this.user1 = user1;
		this.introduce = introduce;
		this.info = "User " + user1 + " has requested private chat with you.";
		
		stage = new Stage();
		Scene scene;
		BorderPane root = new BorderPane();
		button = new Button("YES");
		button2 = new Button ("NO");
		VBox vbox = new VBox();
		HBox hbox = new HBox();

		Label label = new Label();
		Label label2 = new Label();
		label.setText(info);
		label2.setText(this.introduce);

		label.setAlignment(Pos.CENTER);
		label2.setAlignment(Pos.CENTER);
		label2.setAlignment(Pos.CENTER);

		button.setPadding(new Insets(0, 20, 0, 20));
		button.setAlignment(Pos.CENTER_LEFT);

		button2.setPadding(new Insets(0, 20, 0, 20));
		button.setAlignment(Pos.CENTER_RIGHT);


		hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        vbox.setSpacing(20);
        

        //label.setStyle("-fx-padding: 20,20,20,20");
        
        hbox.getChildren().addAll(button, button2);
        vbox.getChildren().addAll(label, label2, hbox);
        vbox.setAlignment(Pos.CENTER);
        
        root.setCenter(vbox);
        scene = new Scene(root, 300,120);
        stage.setScene(scene);
        stage.show();
		
		
	}
}
