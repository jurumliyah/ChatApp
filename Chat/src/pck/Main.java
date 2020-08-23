package pck;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
 
	public class Main extends Application implements Runnable {
		
		static TextField namefield = new TextField();
        static Button button =  new Button("Connect");
        static ChoiceBox choicebox = new ChoiceBox();
        static ArrayList<Room> rooms = new ArrayList();
        static GridPane root;
        static Scene scene;
        static Window window = new Window();
        static BorderPane root2 = window.createWindow();
        static Socket socket = null;
        static ObjectInputStream in = null;
        static ObjectOutputStream out = null;
        static String roomName;
        static String userName;
        static Stream stream;
        
        
        static int port = 4909;
        static String adress = "localhost";
   
	    public static void main(String[] args) {
	    	stream = new Stream(adress, port);
			ArrayList<Room> helplist;
			try {
				helplist = (ArrayList<Room>)stream.in.readObject();
				rooms = helplist;
				window.setRooms(rooms);
				System.out.println("Client: establishConnect OK");

			} catch (ClassNotFoundException | IOException e) {e.printStackTrace();
			}
	       launch(args);
	    }
	    
	    public void start(Stage primaryStage) {
	    	root = createWindow();
	    	scene = new Scene(root, 400,400);	        
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    
	    }
	    
	    static GridPane createWindow() {
	        button.setOnAction( e -> {handle();});
	        namefield.setOnAction( e -> {handle();});
	       
	    	GridPane root = new GridPane();
	        Label label = new Label("W E L L C O M E");
	        Label namelabel = new Label("Name: ");
	        Label roomlabel = new Label("Room: ");
	        choicebox = new ChoiceBox();
	        
	        for (Room room : rooms) {
	        	choicebox.getItems().add(room.roomName);
	        }
	        choicebox.setValue(rooms.get(0).roomName);
	        choicebox.setTooltip(new Tooltip("Select the room"));

	        
	        root.setAlignment(Pos.CENTER);
	        root.setHgap(10);
	        root.setVgap(30);
	        namefield.setPromptText("enter name");
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
	    
	    static void handle() {
	    
        	userName = namefield.getText();
        	roomName = (String) choicebox.getValue();
        	if (userName.length() == 0) {
        		userName = getRandomName();
    			button.getScene().getWindow().hide();
    			System.out.println(" TEST 1");
        		addMeToRoom(roomName, userName);
        		System.out.println(" TEST 1");
        		window.currentroom = roomName;
        		window.currentuser = userName;
        		window.setListViewRoomsCellColor();
        		window.setListViewUsersCellColor();
    	        createChatWindow();
    	        Main runnable=new Main();
    	        Thread t1 =new Thread(runnable);
    	        t1.start();
    	        
    	        }
        	else {
        		boolean test = checkUser (userName);
            	if (!test) {
        			button.getScene().getWindow().hide();
            		addMeToRoom(roomName, userName);
            		window.currentroom = roomName;
            		window.currentuser = userName;
            		window.setListViewRoomsCellColor();
            		window.setListViewUsersCellColor();
            		createChatWindow();
            		Main runnable=new Main();
        	        Thread t1 =new Thread(runnable);
        	        t1.start();
            		 }
            	else { System.out.println("Enter Another name"); new Popup();}
        		}
        	}
	    
	    static String getRandomName() {
	    	String name;
	    	for (int i = 0; ; i++)
	    		if (!checkUser(name="user" + i))
					break;
	    	return name;
	    }
	    

	    static void createChatWindow() {
	    	 window.button.setOnAction((event)->{
	    		 String message;
	    		 message = window.type.getText();
	    		 if(isValid(message)) {  		 
				        UpdateMessage msg = new UpdateMessage( setMsgText(" : "+userName + " : " + window.type.getText()) );
						try {
							System.out.println("Button clicked");
							System.out.println("Client: myMessage is: " + msg.text + " .");
							stream.out.writeObject(msg);
							stream.out.reset();
						} catch (IOException e) {e.printStackTrace();}
	    		 	}
				window.type.setText("");
				
	    		 });
	    	 
	    	 window.type.setOnAction((event)->{
	    		 String message;
	    		 message = window.type.getText();
	    		 if(isValid(message)) {  		 
				        UpdateMessage msg = new UpdateMessage( setMsgText(" : "+userName + " : " + window.type.getText()) );
						try {
							System.out.println("Button clicked");
							System.out.println("Client: myMessage is: " + msg.text + " .");
							stream.out.writeObject(msg);
							stream.out.reset();
						} catch (IOException e) {e.printStackTrace();}
	    		 }
				window.type.setText("");

	    		 });
	    	 
	         window.listViewRooms.setOnMouseClicked((MouseEvent event) -> {
	             if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
	                 System.out.println(window.listViewRooms.getSelectionModel().getSelectedItem());
		             String newRoom = window.listViewRooms.getSelectionModel().getSelectedItem();

	                 // Create new PopUp for double click on Room List (Ask the user about changing Room) 
	                 ChangeRoomWindow changeRoomWindow = new ChangeRoomWindow(newRoom);
	                 // If answer is NO -> close PopUp and do nothing else
	                 changeRoomWindow.button2.setOnAction(e -> {changeRoomWindow.primaryStage.close();});
	                 // If answer is YES -> close PopUp and implement changeroom methods
	                 changeRoomWindow.button.setOnAction(e -> {
	                 //window.changeRoom(window.listViewRooms.getSelectionModel().getSelectedItem());
	               	changeRoomRequest(newRoom);
	               	//roomName = "";
	               	changeRoomWindow.primaryStage.close();

	               	});
	             }
	         });
	         
	         window.listViewUsers.setOnMouseClicked((MouseEvent event) -> {
	             if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
	                 System.out.println(window.listViewUsers.getSelectionModel().getSelectedItem());
		             String user = window.listViewUsers.getSelectionModel().getSelectedItem();

	                // Create new PopUp for double click on Room List (Ask the user about changing Room) 
	                PrivateChatWindow privatechatwindow = new PrivateChatWindow(user);
	                // If answer is NO -> close PopUp and do nothing else
	                privatechatwindow.button2.setOnAction(e -> {privatechatwindow.primaryStage.close();});
	                // If answer is YES -> close PopUp and implement changeroom methods
	                privatechatwindow.button.setOnAction(e -> {
	                //window.changeRoom(window.listViewRooms.getSelectionModel().getSelectedItem());
	            	privateChatRequest(userName, user);
	                //roomName = "";
	                privatechatwindow.primaryStage.close();

	               	});
	             }
	         });

	    	Stage stage = new Stage();
	    	scene = new Scene(root2, 800,600);
			stage.setScene(scene);
			stage.show();
			
	    }
    
	   static boolean checkUser(String name) {
		   CheckUserMessage msg = new CheckUserMessage(name);
		   boolean test= false;
		   try {
			stream.out.writeObject(msg);
			stream.out.reset();
			boolean help  = (boolean)stream.in.readObject();
			test = help;
			
		   } catch (IOException e) {e.printStackTrace();
		   } catch (ClassNotFoundException e) {e.printStackTrace();}
		   return test;
	   }
	   
	   static void establishConnection() {
			try {
				socket = new Socket(adress,port);
				out = new ObjectOutputStream(socket.getOutputStream());	
				in = new ObjectInputStream(socket.getInputStream());
				ArrayList<Room> helplist = (ArrayList<Room>)in.readObject();
				{synchronized (rooms) {rooms = helplist;}}
				
				System.out.println("Client: establishConnect OK");
			
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
	   
		static void addMeToRoom(String roomName, String newUser) {
			AddMeMessage msg = new AddMeMessage(roomName, newUser);
			try {
				stream.out.writeObject(msg);
				stream.out.reset();
				//   Excepting two Objects to be sent from server as of now:
				//   1.UpdateUser Message 2. UserJoined Message
				
			} catch (IOException e) {e.printStackTrace();
			}
		}
		
		static String setFirstMessage(String userName) {
			return getTime() + " : " + userName + " joined Chat";
		}
		static String setMsgText(String str) {
			String string;
			string = getTime() + str;
			return string;
		}
		static String getTime() {
			LocalDateTime time = LocalDateTime.now();
		    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		    String string = format.format(time);
		    return string;		
		}
		/*
		 * isValid Method checks if message that User is sending is not empty
		 * Parameter String message is value from Window.TextField
		 */
		static boolean isValid (String message) {
			boolean test = false;;
			if (message.length() == 0) {return false;}
			
			for (int i = 0; i < message.length(); i++) {
				if (message.charAt(i) != ' ') {
					test = true;
				}
			}
			return test;
		}
		/*
		 * Client sends sends message to the Server, requesting to change the room.
		 * 
		 */
		public static void changeRoomRequest(String newroom) {
			try {
				stream.out.writeObject( new ChangeRoomMessage(userName, newroom, roomName) );
				stream.out.reset();
				System.out.println("Change room req: old/new room:  " + roomName + " , " + newroom);
			} catch (IOException e) {e.printStackTrace();}
		}
		public static void privateChatRequest (String user1, String user2) {
			/*
			 * TO IMPLEMENT
			 */
		}
		
		
		public void run() {
			
			Message msg;
			try {
				while (( (msg = (Message) stream.in.readObject()) != null) && (!Thread.currentThread().isInterrupted())){

					if (msg instanceof UpdateUser) {
						{synchronized(this) {

						System.out.println("Client: in RUN: instanceof UpdateUSER   :");
		
						UpdateUser help = (UpdateUser) msg;
						Platform.runLater(()-> {
							window.addUser(help.roomName, help.userName);
							//window.currentroom = help.roomName;
							//roomName = help.roomName;
							//window.setRooms(window.rooms);
							window.showUsersScrollPane();	
							} );
						}
					}}
					
					if (msg instanceof UserJoinedMessage) {
						{synchronized(this) {

						System.out.println("Client: in RUN: instanceof UserJoinedMessage:");
					
						UserJoinedMessage help= (UserJoinedMessage) msg;
						Platform.runLater(()-> {
							window.chatLog.addText(help.text);
							window.setTextFlowJoined(help.text);
							window.scrollpane.setContent(window.textflow);
							window.scrollpane.setVvalue(1.0);  } );
						}
					}}
					
					if(msg instanceof RoomsMessage) {
						{synchronized(this) {
							
							System.out.println("Client: in RUN: instanceofRoomsMessage:");
							RoomsMessage help = (RoomsMessage) msg;
				            Platform.runLater(()-> {
								rooms = help.rooms;
								window.setRooms(rooms);
								roomName = window.currentroom;
								userName = window.currentuser;
				            	//
								window.showUsersScrollPane(); 
				            	} );

						}
					}}
					
					if (msg instanceof UpdateMessage ) {
						{synchronized(this) {
							
							System.out.println("Client: in RUN: instanceofUpdateMessage:");
							UpdateMessage help = (UpdateMessage) msg;
							System.out.println("Client: I received: " + help.text + " .");
				            Platform.runLater(()-> {
								window.chatLog.addText(help.text);
								window.text = window.chatLog.text;
								window.settextflow(help.text);
								window.scrollpane.setContent(window.textflow);
								window.scrollpane.setVvalue(1.0); } );
						}}
		
					}
					if (msg instanceof UserLeftMessage ) {
						{synchronized(this) {
							
							System.out.println("Client: in RUN: instanceofUserLeft:");
							UserLeftMessage help = (UserLeftMessage) msg;
							System.out.println("Client: User left: " + help.text + " .");
				            Platform.runLater(()-> {
				            	//window.removeUser(help.roomName, help.userName);
								window.chatLog.addText(help.text);
								window.text = window.chatLog.text;
								window.setTextFlowLeft(help.text);
								window.scrollpane.setContent(window.textflow);
								window.scrollpane.setVvalue(1.0);

								window.showUsersScrollPane();
								
								window.setListViewRoomsCellColor();
				        		window.setListViewUsersCellColor();

								} );
						}}
		
					}
				}
			} catch (ClassNotFoundException | IOException e) {e.printStackTrace();
			}
			
		}
}