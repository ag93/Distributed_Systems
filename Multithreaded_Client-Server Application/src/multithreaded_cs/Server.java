/*
Author - Aniket Gade
UTA ID - 1001505046

NOTE: This code is based on the original code by Gliderman's Channel. “Java Ep32 - Networking Pt2 - Multithreaded Server.” YouTube, YouTube, 19 May 2016, www.youtube.com/watch?v=RQ2v0CSV4tY.
*/

/*
This class is used to create a Server Socket and accept all the connections that are requested
by multiple clients. Every connection is handled by a new thread by calling the ServerConnection 
class. All the connections are recoreded by using an Array List. 
*/
package multithreaded_cs;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    static ServerSocket ss; // Initialize a ServerSocket
    boolean shouldRun = true; //This value basically is always true as we will accepting connections unitil the exit button is pressed.
    ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>(); //Used to keep a record of all the incoming connections.
    
    public static void main(String[] args) {
        new display_gui().setVisible(true); //Displays the server GUI.
        new Server(); //Calls the server method to start accepting the connections.
    }
    
public Server() {
        try
        {
            ss = new ServerSocket(19999); //Creates a Server Socket at port 19999.
            while(shouldRun) //This loop is supposed to work forever and accept all incoming connections unit the user hits "EXIT" on server GUI.
            {
                Socket s = ss.accept(); //Accept an incoming connection from a client.
                ServerConnection sc = new ServerConnection(s, this); //Call Server connection with parameters passed as the "Socket which accepted the connection and an instance of this class"
                sc.start(); //Start the thread process
                connections.add(sc); //Add the connection to the arratlist.
            }
        }
        catch(Exception e)
        {
            display_gui.status_bar.setText(e.toString()); //Display the error
        }
    }
}


