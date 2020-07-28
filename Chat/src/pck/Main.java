package pck;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
 
	public class Main extends Application {
		
		static TextField namefield = new TextField();
        static TextField roomfield = new TextField();
        static Button button =  new Button("Connect");
        static ChoiceBox choicebox = new ChoiceBox();
        static ArrayList<Room> rooms = new ArrayList();
        static GridPane root;
        static Scene scene;
        static Window window = new Window();
        static BorderPane root2 = window.createWindow();
        static Socket socket;
        static ObjectInputStream in;
        static ObjectOutputStream out;
        static String roomName;
        static String userName;
        
        static int port = 4909;
        static String adress = "localhost";
   
	    public static void main(String[] args) {	
	    	establishConnection();
    		window.setRooms(rooms);
    		
	    	System.out.println("prva linija");
	        Application.launch(Main.class, args);
	    }
	    
	    public void start(Stage primaryStage) {
	    	root = createWindow();
	    	scene = new Scene(root, 400,400);
	        
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
	    
        	String newUser = namefield.getText();
        	String roomName = (String) choicebox.getValue();
        	if (newUser.length() == 0) {
        		String name = getRandomName();
        		System.out.println("new user name: " + name);
    			//button.getScene().getWindow().hide();
        		addMeToServer(roomName,newUser);
        		getUpdate();
        		//window.scrollpane2.setContent(new Text("blabla"));
    	        createChatWindow();  

    	        }
        	else {
        		boolean test = checkUser (newUser);
            	if (!test) {
        			//button.getScene().getWindow().hide();
            		addMeToServer(roomName,newUser);
            		getUpdate();
            		createChatWindow();
            		    	        	}
            	else { System.out.println("Enter Another name"); new Popup();}
        		}
        	}
	    
	    String getRandomName() {
	    	String name;
	    	for (int i = 0; ; i++)
	    		if (!checkUser(name="user" + i))
					break;
	    	return name;
	    }
	    

	    void createChatWindow() {
	    	//scene.setRoot(root2);
	    	Stage stage = new Stage();
			stage.setScene(new Scene(root2,400,400));
			stage.show();
			
	    }
    
	   static boolean checkUser(String name) {
		   CheckUserMessage msg = new CheckUserMessage(name);
		   boolean test= false;
		   try {
			out.writeObject(msg);
			out.reset();
			boolean help  = (boolean)in.readObject();
			test = help;
			
		   } catch (IOException e) {e.printStackTrace();
		   } catch (ClassNotFoundException e) {e.printStackTrace();}
		   return test;
	   }
	   
	   static void establishConnection() {
			try {
				socket = new Socket(adress,port);
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());	
				rooms = (ArrayList<Room>)in.readObject();
			
			} catch (UnknownHostException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();
			} catch (ClassNotFoundException e) {e.printStackTrace();
			}
			
	}
	   
		static String getRoomName (String userName) {
			String name = null;
			for (Room room : rooms) {
				for (String user : room.userslist) {
					if (user.equals(userName)) {
						name = room.roomName;
					}
				}
			}
			return name;
		}
	   
		static void addMeToServer(String roomName, String newUser) {
			AddMeMessage msg = new AddMeMessage(roomName, newUser);
			try {
				out.writeObject(msg);
				out.reset();
			} catch (IOException e) {e.printStackTrace();
			}
		}
		static void updateUser(UpdateUser msg) {

			String userName = msg.userName;
			String roomName = msg.roomName;
			
			for (Room room : rooms) {
				if (room.roomName.contentEquals(roomName)) {
					room.addUser(userName);
				}
			}
			
		}
		
		
		public void getUpdate() {
			UpdateUser msg;
			try {
				msg = (UpdateUser) in.readObject();
				window.addUser(msg.roomName, msg.userName);
			} catch (IOException | ClassNotFoundException e) {e.printStackTrace();
			}
		}
/*
		@Override
		public void run() {			 
				 Message msg;
				try {
					while ( ( msg = (Message) in.readObject()) != null) {
						if (msg instanceof UpdateUser) {
							updateUser((UpdateUser) msg);
							window.scrollpane2.setContent(new Text(((UpdateUser) msg).userName));
				
						}
					}
				} catch (ClassNotFoundException e) {e.printStackTrace();
				} catch (IOException e) {e.printStackTrace();
				}
	
	}
		*/

}