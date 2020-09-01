![GitHub Logo](/ChatImages/logo.png)
# Chatify
### Chatify is a Desktop Chat Application developed in Java.
This Application allows multiple clients to chat in different rooms.  
Users can also chat with another users privately.  
Graphic User Interface is responsive, and also offers user to change theme color. 

#### Project consists of 2 main running programms:
* 1: Server GUI (Graphic User Interface)
* 2: Client GUI

#### Technologies used:
* Core Java
* Java MultiThreading
* Java Client/Server Architecture (Socket, ServerSocket, In/Out-Streams, etc.)
* JavaFX for building Graphic User Interface (no SceneBuilder, pure JavaFX Code)
* Serializable
* CSS for styling (mostly inline css code)

## 1: Server GUI
This is a simple JavaFX Window, where admin can start or close the server:  
![GitHub Logo](/ChatImages/1.png)  

## 2: Client GUI
When user opens application, server immediately sends rooms. User should then type in username, choose room and click "Connect".  
![GitHub Logo](/ChatImages/2.png)  
After entering username, and trying to connect to the server, the server will check if that username already exists.  
If the user fails to enter unique username (username that no other client on the server has), a small popup will show up, warning the user to enter another username.  
It is also possible to leave the field for username empty and press "Connect", the server will then chose random username, and dedicate it to the client. 
Popup if user enters existing username  
![GitHub Logo](/ChatImages/2a.png)  
  
After succesfull request to connect to the server, main chat window will show up.    
Main chat window has two scrollpanes on the right side:  
1: showing all rooms on the server,  
2: showing all users in the room, which user has chosen.  
At the very top of the window is a bar, where user can change theme color.  
In the center of main chat window is textflow, where all messages are displayed.  
At the bottom is input field for typing the message and sending it, either by pressing enter or by clicking "Send".   
_(Empty messages are not allowed)_  
![GitHub Logo](/ChatImages/3.png)  
  
### Changing room  
Users can change room, by double clicking on the room user wants to join.  
![GitHub Logo](/ChatImages/4.png)  
If user click yes, his room is changed, and all content in the window is updated.  
  
### Private Chat
Beside interraction with all clients from chosen room, user may also start private chat with another user from same room.   
After double clicking on another user's username, following popup will show up:  
  
User A:  
![GitHub Logo](/ChatImages/5.png)  
If user A clicks "Yes": request for private chat is being sent to the server -> server finds user B and sends him request from user A.  
  
User B:  
![GitHub Logo](/ChatImages/5b.png)  
  
    
If user B accepts request for private chat, the server asks both users (A, B) to open new private chat window, where they can start chatting:  
![GitHub Logo](/ChatImages/6.png)  
  
When server is shut down, all user get the message that the server went down:  
  
![GitHub Logo](/ChatImages/7.png)   

  
#### Chat example and other color themes  
Blue theme:  
![GitHub Logo](/ChatImages/blue.png)   
  
Green theme:  
![GitHub Logo](/ChatImages/green.png)  
  
Red Theme:  
![GitHub Logo](/ChatImages/red.png)  
  
White Theme:  
  
![GitHub Logo](/ChatImages/white.png)  

### Info  
Application ist tested and works fine.  
There are still many new functions to implement, such as sending files, images, etc.  
Feel free to download and test it.  
Source Code is in folder [Chat](https://github.com/jurumliyah/ChatApp/Chat) in this [repository](https://github.com/jurumliyah/ChatApp).  

In order to run the server, open "ChatGUI.java" from package "pck".  
To run the program responsible for client, open "Main.java" from package "pck".  
  
You can also download 2 executable files from the folder [ChatExecutables](https://github.com/jurumliyah/ChatApp/ChatExecutables/)  
and directly start server and client.  
_Code is not yet commented, so you may find it hard to navigate through it._  

Feel free to conntact.  :)


