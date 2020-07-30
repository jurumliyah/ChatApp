package pck;
import java.awt.Color;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Window {
	String currentroom;
	String redcode;
	String greencode;
	String bluecode;
	String defaultcolorcode; 
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
	
	Window(){
		 redcode = "#ffebeb;";
		 greencode = "#e1fceb;";
		 bluecode = "#ebe6ff;";
		 defaultcolorcode = "#e6e6e6;"; 
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
 		 
 		 
         }
	
	BorderPane createWindow() {
		
		scrollpane.setContent(textflow);
		center.getColumnConstraints().add(new GridCol(1));
		center.getRowConstraints().add(new GridRow(1));
		center.add(scrollpane, 0, 0);
		center.setMinWidth(300);

		
		scrollpane2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		//scrollpane2.setMaxWidth(120);
		scrollpane3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    	this.scrollpane2.getChildren().add(this.roomsLabel);	
    	//this.scrollpane2.setc;	



		
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
		red.setStyle  ("-fx-background-color :" + redcode);
		blue.setStyle("-fx-background-color :" + bluecode);
		green.setStyle("-fx-background-color :" + greencode);
		defaultcolor.setStyle("-fx-background-color :" + defaultcolorcode);

        red.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        blue.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        green.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        defaultcolor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        
        red.setOnAction(e -> {scrollpane.setStyle("-fx-background-color:" + redcode + "-fx-background:" + redcode);
        text.setStyle("-fx-fill:white;");
        });
        blue.setOnAction(e -> {scrollpane.setStyle("-fx-background-color:" + bluecode + "-fx-background:" + bluecode);
        text.setStyle("-fx-fill:white;");
        });
        green.setOnAction(e -> {scrollpane.setStyle("-fx-background-color:" + greencode + "-fx-background:" + greencode);
        text.setStyle("-fx-fill:white;");
        });
        defaultcolor.setOnAction(e -> {scrollpane.setStyle("-fx-background-color:" + defaultcolorcode + "-fx-background:" + defaultcolorcode);
        text.setStyle("-fx-fill:black;");
        });
        
       /* type.setOnAction((event)->{
            this.sendText(user+": "+textfield.getText());
            type.setText("");
           });
           */
        
        for (int i = 0; i<4; i++) {colors.getColumnConstraints().add(new GridCol(4));}
		colors.getRowConstraints().add(new GridRow(1));
		colors.add(defaultcolor, 0, 0);
		colors.add(blue, 1, 0);
        colors.add(red, 2, 0);
        colors.add(green, 3, 0);
        colors.setPadding(new Insets(0,200,0,0));
		
		//for (int i = 0; i<2; i++) {right.getRowConstraints().add(new GridRow(2));}
        
        
        listViewRooms.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                System.out.println(listViewRooms.getSelectionModel().getSelectedItem());
            }
        });
		 this.scrollpane3.getChildren().add(this.usersLabel);
		 this.scrollpane3.getChildren().add(this.listViewUsers);

	     
		window.setTop(colors);
		window.setCenter(center);
		window.setRight(right);
		window.setBottom(bottom);

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
	public void settextflow(String text) {
		String time = text.substring(0, 17);
		//System.out.println(time);
		Text colorfultime = new Text();
		colorfultime.setText(time);
		colorfultime.setFill(javafx.scene.paint.Color.CRIMSON);
		colorfultime.setFont(new Font(11));
		
		
		Text help = new Text();
		String rest = text.substring(17);
		help.setText(rest);
		help.setFill(javafx.scene.paint.Color.valueOf("#040a14"));
		help.setFont(new Font(13));
		//help.setStyle("-fx-font-weight: lightbold;");

		this.textflow.getChildren().addAll(colorfultime, help);
	    this.textflow.getChildren().add(new Text(System.lineSeparator()));
		
	}
	
	

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
    		for (Room r : rooms) {
    			this.listViewRooms.getItems().add(r.roomName);
    		}	
		this.scrollpane2.getChildren().add(this.listViewRooms);	
	}
	public void setListViewUser(ArrayList<Room> rooms) {
		this.rooms = rooms;
    		for (Room r : rooms) {
    			this.listViewRooms.getItems().add(r.roomName);
    		}	
    		
		this.scrollpane2.getChildren().add(this.listViewRooms);	
	}


	 public void addUser(String roomName, String userName) {
		 for (Room r : rooms) {
			 if (r.roomName.equals(roomName)) {
				 r.addUser(userName);
			 }
		 }
	 }
	 public void showUsersScrollPane(String userName) {
		 Room help = new Room("");
		 for (Room r : rooms) {
			 if (r.searchUser(userName)) {help = r; break;}
		 }
		 for (String user : help.userslist) {
			 this.listViewUsers.getItems().add(user);
			 
		 }


		// System.out.println("showusersscrolpane metoda");
		 
	 }
}