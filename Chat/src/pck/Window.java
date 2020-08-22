package pck;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Window {
	String currentroom;
	String currentuser;
	String currentcolor;
	static String redcode;
	static String greencode;
	static String bluecode;
	static String defaultcolorcode; 
	ArrayList <Room> rooms;
	BorderPane window;
	GridPane colors;
	GridPane right;
	GridPane bottom;
	GridPane center;
	Text text;
	ScrollPane scrollpane;
	VBox scrollpane2;
	VBox scrollpane3;
	ListView<String> listViewRooms;
	ListView<String> listViewUsers;
	ChatLog chatLog = new ChatLog("");
	Button button;
	TextField type;
	TextFlow textflow;
	Label usersLabel;
	Label roomsLabel;
	String colormode;
	String nofocus = "-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; -fx-background-radius: 5, 4, 3;";

	
	Window(){
		 redcode = "#ff6363;";
		 greencode = "#75ff66;";
		 bluecode = "#63b9ff;";
		 defaultcolorcode = "#F4F4F4;"; 
		 rooms = new ArrayList<Room>();
		 window = new BorderPane();
		 colors = new GridPane();
		 right = new GridPane();
		 bottom = new GridPane();
		 center = new GridPane();
		 textflow = new TextFlow();
		 text = new Text();
		 scrollpane = new ScrollPane();
		 scrollpane2 = new VBox();
		 scrollpane3 = new VBox();
         listViewRooms = new ListView<String>();
         listViewRooms.setOrientation(Orientation.VERTICAL);
         listViewUsers = new ListView<String>();
         listViewUsers.setOrientation(Orientation.VERTICAL);
         button = new Button("Send");
 		 type = new TextField();
 		 roomsLabel = new Label("Rooms:");
 		 usersLabel = new Label("Users:");
 		 colormode = "light";
 		 currentcolor = "defaultcolor";
 		 
 		 
         }
	
	BorderPane createWindow() {
		
		scrollpane.setContent(textflow);
		scrollpane.setStyle(nofocus);


		center.getColumnConstraints().add(new GridCol(1));
		center.getRowConstraints().add(new GridRow(1));
		center.add(scrollpane, 0, 0);
		center.setMinWidth(300);
		
		
		scrollpane2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		scrollpane3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);    	
		
		right.getRowConstraints().add(new GridRow(0,35));
		right.getRowConstraints().add(new GridRow(1,65));
		right.getColumnConstraints().add(new GridCol(1));
		right.add(scrollpane2, 0, 0);
		right.add(scrollpane3, 0, 1);
		//right.setMinWidth(10);
		right.setMaxWidth(200);
		right.setPrefWidth(200);
	
		//CREATING  BOTTOM PANE WITH SEND BUTTON AND TEXTFIELD//
		//Button button = new Button("Send");
		type.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		//button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 16px;");
		button.setStyle("-fx-background-color: " + getColorCode(currentcolor) + ";");
		button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 0px;");
		bottom.getRowConstraints().add(new GridRow(1));
		bottom.getColumnConstraints().add(new GridCol(0,80));
		bottom.getColumnConstraints().add(new GridCol(1,20));
		bottom.add(type, 0, 0);
		bottom.add(button, 1, 0);
		//bottom.setAlignment(Pos.CENTER);
		bottom.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bottom.setPadding(new Insets(0,200,0,0));
        
		Button red = new Button();
		Button blue = new Button();
		Button green = new Button();
		Button defaultcolor = new Button();
		red.setStyle  ("-fx-background-color :" + redcode + "; -fx-background-radius: 0px;");
		blue.setStyle("-fx-background-color :" + bluecode + "; -fx-background-radius: 0px;");
		green.setStyle("-fx-background-color :" + greencode + "; -fx-background-radius: 0px;");
		defaultcolor.setStyle("-fx-background-color :" + defaultcolorcode + "; -fx-background-radius: 0px;");

        red.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        blue.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        green.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        defaultcolor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        
        red.setOnAction(e -> {scrollpane.setStyle(nofocus + "-fx-background-color:" + redcode + "-fx-background:" + redcode);
        text.setStyle("-fx-fill:white;"); currentcolor = "red";setListViewRoomsCellColor(); setListViewUsersCellColor();
        });
        blue.setOnAction(e -> {scrollpane.setStyle(nofocus + "-fx-background-color:" + bluecode + "-fx-background:" + bluecode);
        text.setStyle("-fx-fill:white;"); currentcolor = "blue";setListViewRoomsCellColor();setListViewUsersCellColor();
        colormode = "dark";
        });
        green.setOnAction(e -> {scrollpane.setStyle(nofocus + "-fx-background-color:" + greencode + "-fx-background:" + greencode);
        text.setStyle("-fx-fill:white;"); currentcolor = "green";setListViewRoomsCellColor();setListViewUsersCellColor();
        });
        defaultcolor.setOnAction(e -> {scrollpane.setStyle(nofocus + "-fx-background-color:" + defaultcolorcode + "-fx-background:" + defaultcolorcode);
        text.setStyle("-fx-fill:black;"); currentcolor = "defaultcolor";setListViewRoomsCellColor();setListViewUsersCellColor();
        });
        
        for (int i = 0; i<4; i++) {colors.getColumnConstraints().add(new GridCol(4));}
		colors.getRowConstraints().add(new GridRow(1));
		colors.add(defaultcolor, 0, 0);
		colors.add(blue, 1, 0);
        colors.add(red, 2, 0);
        colors.add(green, 3, 0);
        colors.setPadding(new Insets(0,200,0,0));        
        

        
        listViewRooms.setStyle("-fx-control-inner-background: " + defaultcolorcode);


    	scrollpane2.getChildren().add(this.roomsLabel);	
    	scrollpane2.getChildren().add(this.listViewRooms);	

		scrollpane3.getChildren().add(this.usersLabel);
		scrollpane3.getChildren().add(this.listViewUsers);

	     
		window.setTop(colors);
		window.setCenter(center);
		window.setRight(right);
		window.setBottom(bottom);
		window.setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; -fx-background-radius: 5, 4, 3;");


		return window;
	}
	public void setTextFlowJoined(String str) {
		Text txt = new Text();
		txt.setText(str);
		txt.setFill(javafx.scene.paint.Color.GREEN);
		txt.setFont(new Font(11));
		this.textflow.getChildren().addAll(txt);
	    this.textflow.getChildren().add(new Text(System.lineSeparator()));
		
	}
	public void setTextFlowLeft(String str) {
		Text txt = new Text();
		txt.setText(str);
		txt.setFill(javafx.scene.paint.Color.ORANGE);
		txt.setFont(new Font(11));
		this.textflow.getChildren().addAll(txt);
	    this.textflow.getChildren().add(new Text(System.lineSeparator()));
		
	}
	public void settextflow(String text) {
		
		//// color left part of text (Time in message)
		String time = text.substring(0, 17);
		Text colorfultime = new Text();
		colorfultime.setText(time);
		colorfultime.setFill(javafx.scene.paint.Color.CRIMSON);
		colorfultime.setFont(new Font(11));
		
		//// color rest of the text (Message text in Message)
		Text help = new Text();
		String rest = text.substring(17);
		help.setText(rest);
		help.setFont(new Font(13));
		/*
		if (colormode == "light") {
			help.setFill(javafx.scene.paint.Color.valueOf("#040a14"));
		}
		
		else if (colormode == "dark") {
			help.setFill(javafx.scene.paint.Color.valueOf("#00facc"));
		}
		*/
		this.textflow.getChildren().addAll(colorfultime, help);
	    this.textflow.getChildren().add(new Text(System.lineSeparator()));
		
	}
	
	public void setRooms(ArrayList<Room> rooms) {
		this.scrollpane2.getChildren().clear();
		this.listViewRooms.getItems().clear();
   		setListViewRoomsCellColor();
		 setListViewUsersCellColor();


		this.rooms = rooms;
    		for (Room r : rooms) {
    			this.listViewRooms.getItems().add(r.roomName);
    			if (r.searchUser(currentuser)) {
    				currentroom = r.roomName;
    			}
    		}	
		this.scrollpane2.getChildren().addAll(roomsLabel, this.listViewRooms);	
	}
	
	 public void showUsersScrollPane() {
		 Room help = new Room("");
		 this.scrollpane3.getChildren().clear();
		 this.listViewUsers.getItems().clear();
		 setListViewUsersCellColor();

		 for (Room r : rooms) {
			 if (r.searchUser(currentuser)) {help = r; break;}
		 }
		 for (String user : help.userslist) {
			 this.listViewUsers.getItems().add(user);
			 
		 }
		 this.scrollpane3.getChildren().addAll(usersLabel, this.listViewUsers);	

	 }

	 public void addUser(String roomName, String userName) {
		 //currentuser = userName;
		 //currentroom = roomName;
		 boolean help = false;
		 for (Room r : rooms) {
			 if (r.roomName.equals(roomName)) {
				 if (r.searchUser(currentuser)) {
					 help = true;
				 }
			 }
		 }
		 for (Room r : rooms) {
			 if (r.roomName.equals(roomName)) {
				 if (!help) {
					 r.addUser(userName);
				 }
			 }
		 }
	 }	 
	 public void removeUser(String roomName, String userName) {
		 for (Room r : rooms) {
			 if (r.roomName.equals(roomName)) {
				 r.removeUser(userName);
			 }
		 }
	 }
	 
	 static String getColorCode(String str){
		 String help = null;
		 if (str.equals("red")) {help = redcode;}
		 else if (str.equals("blue")) {help = bluecode;}
		 else if (str.equals("green")) {help = greencode;}
		 else if (str.equals("defaultcolor")) {help = "#474746";}
		 return help;
	 }
	
	 public void setListViewRoomsCellColor() {
	     listViewRooms.setCellFactory((ListView<String> l) -> new ColorCell(currentroom, currentcolor));

	 }
	 public void setListViewUsersCellColor() {
		 listViewUsers.setCellFactory((ListView<String> l) -> new ColorCell(currentuser, currentcolor));
	 }
	 
	 public void changeRoom(String newroom) {
		 this.currentroom = newroom;
	 }
}
