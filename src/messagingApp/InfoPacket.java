package messagingApp;

import java.util.Date;

public class InfoPacket
{
	//generic holder class that contains info to send to server
	
	public String packetType = "";
	
	public String packetArguments = "";
	
	public String userID = "";
	
	public String issues = ""; //if the server has any issues when handling something, it'll go here
	
	public Long creationTime;
	
	/*PACKET TYPES -> SEND TO SERVER
	 * "ping" - send a ping to the server at regular intervals to update the "active" status - does not send any information back
	 * "delete" - send a ID to the server, returns true/false 
	 * "users" - send request to server, recieve list of every active user
	 * "message" - send a message to the server, returns message ID if successful, OR an error - ae, if username is already in use
	 * "update" - request every message since a timestamp. likely an arraylist of messages.
	 */
	
	InfoPacket()
	{
		//constructor
		creationTime = new Date().getTime();
		//System.out.println(creationTime);
	}
	
}