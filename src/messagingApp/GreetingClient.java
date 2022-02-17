package messagingApp;
// File Name GreetingClient.java
// Reference : http://www.tutorialspoint.com/java/java_networking.htm



import java.net.*;
import java.io.*;

public class GreetingClient
{
	public String serverName  = "localhost";
	
	private int port = 6066;											// Hard-code port for connection
	public String sendClient(String JsonPacket) {
		/*
		 * Method:				sendClient
		 * 
		 * Method Parameters:	String JsonPacket
		 * 
		 * Method Return:		void
		 * 
		 * Synopsis:			This method will be the point of
		 * 						communication between this client
		 * 						and the server.
		 * 
		 * Modifications:		Date:		Name:			Modification:
		 * 						<02/08/2022	Stephen Johnson	Original Client Code
		 * 						02/08/2022 	Jared Shaddick	Created Method to Prevent
		 * 													the Client from Starting First
		 * 						02/15/2022	Jared Shaddick	Adapted Code to Allow Specification of
		 * 													the Server Name
		 */
	      //String serverName = args[0];								// Used for command line parameter
	      //int port = Integer.parseInt(args[1]);						// Used for command line parameter
			
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

	        out.writeUTF(JsonPacket);
	        InputStream inFromServer = client.getInputStream();
	        DataInputStream in =
                        new DataInputStream(inFromServer);
	        String serverIn = in.readUTF();
	        System.out.println("Server says " + serverIn);
	        client.close();
	        return serverIn;
	    }catch(IOException e)
	    {
	         e.printStackTrace();
	    }
		return null;
	}
}