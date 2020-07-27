package pck;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Control;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
 
	/**
	 * Sample application that shows how the sized of controls can be managed.
	 * Sample is for demonstration purposes only, most controls are inactive.
	 */
	public class Main extends Application {
		
		static TextField namefield = new TextField();
        static TextField roomfield = new TextField();
        static Button button =  new Button("Connect");
        static ChoiceBox choicebox = new ChoiceBox();
        static ArrayList<Room> rooms = new ArrayList();
        static GridPane root;
        static Scene scene;
        
        
        
	    public static void main(String[] args) {
	    	createRooms(5);
	    	root = createWindow();
	    	scene = new Scene(root, 400,400);
	 
	        Application.launch(Main.class, args);
	    }
	    
	    public void start(Stage primaryStage) {
	        
	        button.setOnAction( e -> {handle();});
	        
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    
	    }
	    
	    static GridPane createWindow() {
	    	GridPane root = new GridPane();
	        Label label = new Label("W E L L C O M E");
	        Label namelabel = new Label("Name: ");
	        Label roomlabel = new Label("Room: ");
	        choicebox = new ChoiceBox();
	        
	        for (Room room : rooms) {
	        	choicebox.getItems().add(room.roomName);
	        }
	        choicebox.setValue(rooms.get(0).roomName);
	        choicebox.setTooltip(new Tooltip("Select the language"));

	        
	        root.setAlignment(Pos.CENTER);
	        root.setHgap(10);
	        root.setVgap(30);
	        namefield.setPromptText("enter name");
	        roomfield.setPromptText("choose room");
	        button.setAlignment(Pos.CENTER);
	        label.setAlignment(Pos.CENTER);
	        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	        button.setStyle("-fx-padding: 0,20,0,20");
	        choicebox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	        choicebox.setMaxHeight(ChoiceBox.USE_PREF_SIZE- 10);


	        
	        HBox h1 = new HBox();
	        HBox h2 = new HBox();
	        VBox vbox = new VBox();
	        vbox.setSpacing(10);
	        //h1.setFillHeight(true);
	        //h1.setPadding(new Insets(30,30,30,30));
	        //h2.setPadding(new Insets(30,30,30,30));
	        
	        h1.getChildren().addAll(namelabel, namefield);
	        h2.getChildren().addAll(roomlabel, choicebox);
	        vbox.getChildren().addAll(h1,h2);
	        
	        root.add(label, 0, 0);
	        root.add(vbox, 0, 1);
	        root.add(button, 0, 2);
	        root.setAlignment(Pos.CENTER);	        
	        root.setStyle("-fx-background-color: #f8f7ff");
	        root.setPadding(new Insets(20,20,20,20));

	        GridPane pane = new GridPane();
	        pane.add(root, 0, 0);
	        pane.setAlignment(Pos.CENTER);
	        pane.setStyle("-fx-background-color: #565959");
	        
	        return pane;
	    	
	    }
	    
	    void handle() {
        	String newuser = namefield.getText();
        	String roomName = (String) choicebox.getValue();
        	if (newuser.length() == 0) {
        		String name = getRandomName();
        		addUserToRoom(roomName, newuser);
        		System.out.println("new user name: " + name);
    			//button.getScene().getWindow().hide();
    	        //createChatWindow();       		
        		}
        	else {
        		boolean test = searchUser(rooms, newuser);
            	if (!test) {
            		addUserToRoom(roomName, newuser);
        			//button.getScene().getWindow().hide();
            		//createChatWindow();
    	        	
    	        	}
            	else System.out.println("Enter Another name");
        		}
        	}
	    
	    String getRandomName() {
	    	String name;
	    	for (int i = 0; ; i++)
	    		if(!compare((name="user" + i),rooms))
					break;
	    	return name;
	    }
	    
	    boolean compare(String randomname, ArrayList<Room> rooms) {
	    	boolean test = false;
	    	for(Room room : rooms) {
	    		for (String username : room.userslist)
	    			if(randomname.equals(username))  {test = true;}
	    	}
	    	return test;
	    }
	    void createChatWindow() {
	    	Stage stage = new Stage();
			stage.setScene(new Scene(new Window().createWindow(),400,400));
			stage.show();
	    }
	    
	   static void createRooms(int size){
		   for (int i = 0 ; i<size; i++)
			   rooms.add(new Room("Room" + i));
	    }
	   
	   void addUserToRoom(String roomName, String userName) {
		   for (Room room : rooms) {
			   if (room.roomName.contentEquals(roomName))
				   room.addUser(userName);
		   }
	   }
	   
	   boolean searchUser(ArrayList<Room> rooms , String newUser) {
		   boolean test = false;
		   for (Room room : rooms) {
			   if (room.searchUser(newUser)) { test = true; }
		   }
		   return test;
	   }
	   int getRoomForName(String roomName, ArrayList<Room> rooms) {
		   int k=0;
		   for (int i = 0; i <rooms.size(); i++) {
			   if (rooms.get(i).roomName.equals(roomName))
				   k = i;
		   }
		   return k;
	   }
	 

	}