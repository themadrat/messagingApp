package messagingApp;
// File Name GreetingClient.java
// Reference : http://www.tutorialspoint.com/java/java_networking.htm



import java.net.*;
import java.io.*;

public class GreetingClient
{
	public void sendClient() {
	      //String serverName = args[0];								// Used for command line parameter
	      //int port = Integer.parseInt(args[1]);						// Used for command line parameter
	      
	      String serverName  = "localhost";								// Establish the local machine as the
	      																// server
	      int port = 6066;												// Hard-code port for connection
	      try
	      {
	         System.out.println("Connecting to " + serverName
	                             + " on port " + port);
	         Socket client = new Socket(serverName, port);
	         System.out.println("Just connected to "
	                      + client.getRemoteSocketAddress());
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);

	         out.writeUTF("Hello from "
	                      + client.getLocalSocketAddress());
	         InputStream inFromServer = client.getInputStream();
	         DataInputStream in =
	                        new DataInputStream(inFromServer);
	         System.out.println("Server says " + in.readUTF());
	         client.close();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	}
}