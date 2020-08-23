package pck;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatServerGUI extends Application{
	
	static BorderPane root;
	static Button connect;
	static Button disconnect;
	static boolean connected;
	static Label label;
	static VBox center;
	static ChatServer server;
	
    public static void main(String[] args) {
    	root = new BorderPane();
		connect = new Button("Connect");
		disconnect = new Button ("Disconnect");
		label = new Label("disconnected");
		connected = false;
		
		connect.setStyle("-fx-background-color: #196b2f; -fx-text-fill: white; -fx-background-radius: 0px;");
		disconnect.setStyle("-fx-background-color: #960000; -fx-text-fill: white; -fx-background-radius: 0px;");
		connect.setMinSize(100, 15);
		disconnect.setMinSize(100, 15);
		connect.setMaxWidth(Double.MAX_VALUE);
		disconnect.setMaxWidth(Double.MAX_VALUE);

		
		connect.setAlignment(Pos.CENTER);
		disconnect.setAlignment(Pos.CENTER);
		label.setAlignment(Pos.CENTER);

		Image image = new Image("resources/logo3.png");
		ImageView view = new ImageView();
		view.setImage(image);
		
		center = new VBox();
		center.setSpacing(15);
		center.getChildren().addAll(view, connect, disconnect, label);
		center.setMinSize(300, 300);
		center.setAlignment(Pos.BOTTOM_CENTER);
		center.setMaxSize(300, 300);
		center.setStyle("-fx-border-color: white; -fx-border-width: 1" );
		
		connect.setOnAction(e -> { 
			if (!connected) {
				server = new ChatServer();
				server.openServer();
				connected = true;
				label.setText("connected");
			}
			else 
				label.setText("Already connected");
		});
		
		disconnect.setOnAction(e -> { 
			if (connected) {
				server.closeServer();
				connected = false;
				label.setText("disconnected");
			}
			else
				label.setText("Already diconnected");

		});
		

		
		
		root.setStyle(Window.nofocus + "-fx-background-color: #252525; -fx-background: #252525;  -fx-background-radius: 0px;");
		root.setCenter(center);
		//root.setTop(view);
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setScene(new Scene(root, 500,500));
		primaryStage.setTitle("Chat Server");
		primaryStage.show();
		
	}

}
