package messagingApp;

import java.util.Date;

public class MessageInstance {
	//holder class that message data
	public String userID;
    public String MessageContent;
    public Long TimeSent;

    MessageInstance()
    {
    	//constructor
        TimeSent = new Date().getTime();
    }
}
