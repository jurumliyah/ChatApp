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
		
		TextField namefield = new TextField();
        TextField roomfield = new TextField();
        Button button =  new Button("Connect");
        ChoiceBox choicebox = new ChoiceBox();
        static ArrayList<String> rooms = new ArrayList();
        static ArrayList<String> users = new ArrayList();
        GridPane root = createWindow();
        Scene scene = new Scene(root, 400,400);

        
	    public static void main(String[] args) {
	        rooms.add("room1");
	        rooms.add("room2");
	        rooms.add("room3");
	        rooms.add("room4");
	        Application.launch(Main.class, args);
	    }
	    
	    public void start(Stage primaryStage) {
	        
	        button.setOnAction( e -> {handle();});
	        
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    
	    }
	    
	    GridPane createWindow() {
	    	GridPane root = new GridPane();
	        Label label = new Label("W E L L C O M E");
	        Label namelabel = new Label("Name: ");
	        Label roomlabel = new Label("Room: ");
	        choicebox = new ChoiceBox();
	        
	        for (String item : rooms)
	        	choicebox.getItems().add(item);
	        choicebox.setValue(rooms.get(0));
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
        	if (newuser.length() == 0) {
        		String name = getRandomName();
        		users.add(name);
        		System.out.println("new user name: " + name);
    			button.getScene().getWindow().hide();
    	        createChatWindow();       		
        		}
        	else {
        		boolean test = true;
            	for (String user : users) if (newuser.equals(user)) {test = false;}
            	if (test) {
            		users.add(newuser);
            		System.out.println("testing button event: txt field value: \n" +namefield.getText());
    	        	System.out.println((String) choicebox.getValue());
            		createChatWindow();
    	        	
    	        	}
            	else System.out.println("Enter Another name");
        		}
        	}
	    
	    String getRandomName() {
	    	String name;
	    	for (int i = 0; ; i++)
	    		if(!compare((name="user" + i),users))
					break;
	    	return name;
	    }
	    
	    boolean compare(String user1, ArrayList<String> users) {
	    	boolean test = false;
	    	for (String u : users)
	    		if (u.equals(user1))
	    			test = true;
	    	return test;
	    }
	    void createChatWindow() {
	    	Stage stage = new Stage();
			stage.setScene(new Scene(new Window().createWindow(),400,400));
			stage.show();
	    }
	 

	}