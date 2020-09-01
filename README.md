![GitHub Logo](/ChatImages/logo.png)
# Chatify
### Chatify is a Desktop Chat Application developed in Java.
This Application allows multiple clients to chat in different rooms.  
Users can also chat with another user privately.  
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

### Client GUI is JavaFX window, where client(user) can interract with other users.
![GitHub Logo](/ChatImages/2.png)


