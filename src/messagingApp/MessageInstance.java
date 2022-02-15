package messagingApp;

import java.util.Date;

public class MessageInstance {
	String userID;
    String MessageContent;
    Date TimeSent;

    MessageInstance()
    {
        TimeSent = new Date();
    }
}
