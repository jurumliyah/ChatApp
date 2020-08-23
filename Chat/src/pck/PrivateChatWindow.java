package pck;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrivateChatWindow {
	Stage primaryStage;
	Button button;
	Button button2;
	String user;
	PrivateChatWindow(String user){
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
		label.setText(info);
		label.setAlignment(Pos.CENTER);
		//button.setAlignment(Pos.CENTER);
		//button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 0px;");
		button.setPadding(new Insets(0, 20, 0, 20));
		button.setAlignment(Pos.CENTER_LEFT);


		//button2.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 0px;");
		button2.setPadding(new Insets(0, 20, 0, 20));
		button.setAlignment(Pos.CENTER_RIGHT);


		hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        

		

		//button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //button.setStyle("-fx-padding: 20,20,20,20");
        label.setStyle("-fx-padding: 20,20,20,20");
        
        hbox.getChildren().addAll(button, button2);
        vbox.getChildren().addAll(label, hbox);
        vbox.setAlignment(Pos.CENTER);
        
        root.setCenter(vbox);
        scene = new Scene(root, 300,120);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
