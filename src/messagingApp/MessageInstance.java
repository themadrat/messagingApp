package messagingApp;

import java.util.Date;

public class MessageInstance {
	public String userID;
    public String MessageContent;
    public Long TimeSent;

    MessageInstance()
    {
        TimeSent = new Date().getTime();
    }
}
