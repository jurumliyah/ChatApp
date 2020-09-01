package pck;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

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
	static boolean firstTime;
	static Label label;
	static Label label2;
	static VBox center;
	static VBox bottom;
	static ChatServer server;
	
    public static void main(String[] args) {

    	try {
			connected = server.isOnline();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	/*
    	 * Checks if server is already online, if so, this line stops creating new Server Window
    	 */
    	if (connected) {
    		return ;
    		}
    	
    	root = new BorderPane();
		connect = new Button("Connect");
		disconnect = new Button ("Disconnect");
		
		label = connected ? new Label("Connected") : new Label("Disconnected");
		label2 = new Label("User: ");
		connect.setStyle("-fx-background-color: #196b2f; -fx-text-fill: white; -fx-background-radius: 0px;");
		disconnect.setStyle("-fx-background-color: #960000; -fx-text-fill: white; -fx-background-radius: 0px;");
		connect.setMinSize(100, 15);
		disconnect.setMinSize(100, 15);
		connect.setMaxWidth(Double.MAX_VALUE);
		disconnect.setMaxWidth(Double.MAX_VALUE);

		
		connect.setAlignment(Pos.CENTER);
		disconnect.setAlignment(Pos.CENTER);
		label.setAlignment(Pos.CENTER);
		label2.setAlignment(Pos.TOP_CENTER);
		label2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


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
			handleConnect();
		});
		
		disconnect.setOnAction(e -> { 
			handleDisconnect();
		});
		
		root.setStyle(Window.nofocus + "-fx-background-color: #252525; -fx-background: #252525;  -fx-background-radius: 0px;");
		root.setCenter(center);
		root.setBottom(label2);
		//root.setTop(view);
    	launch(args);
    }
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(root, 500,500));
		primaryStage.setTitle("Chat Server");
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
			handleDisconnect();
			});
	}
	
	public static void handleConnect() {

		if (!connected) {
			server = new ChatServer();
			for(Room r : server.rooms) {System.out.println("''" + r.roomName);}
			server.openServer();
			label.setText("connected");
			connected = true;
			//for(Room r : server.rooms) {System.out.println("''" + r.roomName);}
		}
		else 
			label.setText("Already connected");
	}
	
	public static void handleDisconnect() {

		if (connected) {
			server.closeServer();
			label.setText("disconnected");
			connected = false;
		}
		else {
			label.setText("Already diconnected");
		}
	}
}
