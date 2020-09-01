package popups;

import java.awt.Insets;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NamePopUp {

	
	public NamePopUp(){
		Stage primaryStage = new Stage();
		Scene scene;
		BorderPane root = new BorderPane();
		Button button = new Button("  OK  ");
		VBox vbox = new VBox();
		
		String info = "User name You entered already exists.\n Choose another name.";
		Label label = new Label();
		label.setText(info);
		label.setAlignment(Pos.CENTER);
		button.setAlignment(Pos.CENTER);
		//button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //button.setStyle("-fx-padding: 20,20,20,20");
        label.setStyle("-fx-padding: 20,20,20,20");
        
        button.setOnAction(e -> {primaryStage.close();});
        vbox.getChildren().addAll(label,button);
        vbox.setAlignment(Pos.CENTER);
        
        root.setCenter(vbox);
        scene = new Scene(root, 250,120);
        primaryStage.setScene(scene);
        primaryStage.show();


	}
}
