package pck;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	static String darkcolorcode;
	ArrayList <Room> rooms;
	BorderPane window;
	GridPane colors;
	GridPane right;
	GridPane bottom;
	GridPane center;
	Text text;
	static ScrollPane scrollpane;
	VBox scrollpane2;
	VBox scrollpane3;
	static ListView<String> listViewRooms;
	static ListView<String> listViewUsers;
	ChatLog chatLog = new ChatLog("");
	static Button button;
	TextField type;
	TextFlow textflow;
	Label usersLabel;
	Label roomsLabel;
	String colormode;
	static String nofocus = "-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; -fx-background-radius: 5, 4, 3;";

	
	Window(){
		 redcode = "#ff6363;";
		 greencode = "#75ff66;";
		 bluecode = "#63b9ff;";
		 defaultcolorcode = "#F4F4F4;"; 
		 darkcolorcode = "#252525";
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
 		 currentcolor = "darkcolor"; 
         }
	
	BorderPane createWindow() {
		
		scrollpane.setContent(textflow);
		scrollpane.setStyle(nofocus);
		//BackgroundImage b = new BackgroundImage(new Image("pck/black.jpg",32,32,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		//scrollpane.setBackground(new Background (b));
		//scrollpane.setStyle(" -fx-background-image: url(\"black.jpg\");");
		

		center.getColumnConstraints().add(new GridCol(1));
		center.getRowConstraints().add(new GridRow(1));
		center.add(scrollpane, 0, 0);
		center.setMinWidth(300);
		//center.setBackground(new Background (b));

		
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
		Button dark = new Button();
		red.setStyle  ("-fx-background-color :" + redcode + "; -fx-background-radius: 0px;");
		blue.setStyle("-fx-background-color :" + bluecode + "; -fx-background-radius: 0px;");
		green.setStyle("-fx-background-color :" + greencode + "; -fx-background-radius: 0px;");
		defaultcolor.setStyle("-fx-background-color :" + defaultcolorcode + "; -fx-background-radius: 0px;");
		dark.setStyle("-fx-background-color :" + darkcolorcode + "; -fx-background-radius: 0px;");		

        red.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        blue.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        green.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        defaultcolor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dark.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        styleTheme(currentcolor);
        scrollpane.getStylesheets().add("styles/backgroundimage.css");
        
        red.setOnAction(e -> {
	        currentcolor = "red";setListViewRoomsCellColor(); setListViewUsersCellColor();
	        styleTheme(currentcolor);
        });
        blue.setOnAction(e -> {
	        currentcolor = "blue";setListViewRoomsCellColor();setListViewUsersCellColor();
	        styleTheme(currentcolor);
        });
        green.setOnAction(e -> {
	        currentcolor = "green";setListViewRoomsCellColor();setListViewUsersCellColor();
	        styleTheme(currentcolor);
        });
        defaultcolor.setOnAction(e -> {
	        currentcolor = "defaultcolor";setListViewRoomsCellColor();setListViewUsersCellColor();
	        styleTheme(currentcolor);
        });
        dark.setOnAction(e -> {
            currentcolor = "darkcolor";setListViewRoomsCellColor();setListViewUsersCellColor();
            styleTheme(currentcolor);
            });
        
        for (int i = 0; i<5; i++) {colors.getColumnConstraints().add(new GridCol(5));}
		colors.getRowConstraints().add(new GridRow(1));
		colors.add(defaultcolor, 0, 0);
		colors.add(blue, 1, 0);
        colors.add(red, 2, 0);
        colors.add(green, 3, 0);
        colors.add(dark, 4, 0);
        colors.setPadding(new Insets(0,200,0,0));        
        

        
        listViewRooms.setStyle("-fx-control-inner-background: " + getTabColorCode(currentcolor));
        listViewUsers.setStyle("-fx-control-inner-background: " + getTabColorCode(currentcolor));


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
		txt.setFill(Color.GREEN);
		txt.setFont(Font.font ("Verdana", 10));;
		this.textflow.getChildren().addAll(txt);
	    this.textflow.getChildren().add(new Text(System.lineSeparator()));
		
	}
	public void setTextFlowLeft(String str) {
		Text txt = new Text();
		txt.setText(str);
		txt.setFill(Color.ORANGE);
		txt.setFont(Font.font ("Verdana", 10));
		this.textflow.getChildren().addAll(txt);
	    this.textflow.getChildren().add(new Text(System.lineSeparator()));
		
	}
	public void settextflow(String text) {
		
		//// color left part of text (Time in message)
		String time = text.substring(0, 17);
		Text colorfultime = new Text();
		colorfultime.setText(time);
		colorfultime.setFill(Color.CRIMSON);
		colorfultime.setFont(Font.font ("Verdana", 10));
		
		//// color rest of the text (Message text in Message)
		Text help = new Text();
		String rest = text.substring(17);
		help.setText(rest);
		help.setFill(Color.TEAL);
		help.setFont(Font.font ("Verdana", 12));
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
	 static void styleTheme(String color) {
		 String backgroundcolor = getBackgroundColorCode(color);
		 scrollpane.setStyle(nofocus + "-fx-background-color: " + backgroundcolor + "; -fx-background: " + backgroundcolor + ";");
			 
		 listViewRooms.setStyle("-fx-control-inner-background: " + getTabColorCode(color));
	     listViewUsers.setStyle("-fx-control-inner-background: " + getTabColorCode(color));
		 button.setStyle("-fx-background-color: "+ getDarkColorCode(color) + "; -fx-text-fill: white; -fx-background-radius: 0px;");
	 }
	 static String getColorCode(String str){
		 String help = null;
		 if (str.equals("red")) {help = redcode;}
		 else if (str.equals("blue")) {help = bluecode;}
		 else if (str.equals("green")) {help = greencode;}
		 else if (str.equals("defaultcolor")) {help = "#474746";}
		 else if (str.equals("darkcolor")) {help = darkcolorcode;}
		 return help;
	 }
	 static String getTabColorCode(String str){
		 String help = null;

		 if (str.equals("red")) {help = "#ffcfe4";}
		 else if (str.equals("blue")) {help = "#d4cfff";}
		 else if (str.equals("green")) {help = "#e2ffcf";}
		 else if (str.equals("defaultcolor")) {help = "#edeff0";}
		 else if (str.equals("darkcolor")) {help = "#edeff0";}

		 return help;
	 }	 
	 static String getDarkColorCode(String str){
		 String help = null;

		 if (str.equals("red")) {help = "#960000";}
		 else if (str.equals("blue")) {help = "#006c96";}
		 else if (str.equals("green")) {help = "#009644";}
		 else if (str.equals("defaultcolor")) {help = "#242b2e";}
		 else if (str.equals("darkcolor")) {help = darkcolorcode;}

		 return help;
	 }	
	 static String getBackgroundColorCode(String str){
		 String help = null;

		 if (str.equals("red")) {help = "#302323";}
		 else if (str.equals("blue")) {help = "#232a30";}
		 else if (str.equals("green")) {help = "#252e24";}
		 else if (str.equals("defaultcolor")) {help = defaultcolorcode;}
		 else if (str.equals("darkcolor")) {help = darkcolorcode;}

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
