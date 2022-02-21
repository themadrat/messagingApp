package messagingApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class messageInterface extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textFieldIP;
	private JTextField textFieldUser;
	private JLabel lblReturningQuestion;
	private JButton btnLoadUser;
	private JTextPane textPaneMessageHistory;
	private JScrollPane messageScrollBar;
	
	public Date startTime = new Date();
	
	private DataManagement DM = new DataManagement();
	private MessageInstance messageInstance;
	
	private String UserIPString;
	private String UserIDString;
	
	private InfoPacket infoPacket;
	
	private GreetingClient User = new GreetingClient();
	
	private int seconds = 1;
	private final Integer FRAMETIME = seconds * 1000;// 1 second = 1000 for input
	private Timer tickTok = new Timer(FRAMETIME,this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					messageInterface frame = new messageInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public messageInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 808);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPaneMessageInput = new JTextPane();
		textPaneMessageInput.setEnabled(false);
		textPaneMessageInput.setVisible(false);
		textPaneMessageInput.setBounds(10, 519, 451, 117);
		contentPane.add(textPaneMessageInput);
		
		textPaneMessageHistory = new JTextPane();
		textPaneMessageHistory.setEnabled(false);
		textPaneMessageHistory.setEditable(false);
		//textPaneMessageHistory.setBounds(10, 46, 451, 391);
		messageScrollBar = new JScrollPane(textPaneMessageHistory);
		messageScrollBar.setVisible(false);
		messageScrollBar.setBounds(10, 46, 451, 391);
		contentPane.add(messageScrollBar);
		//contentPane.add(textPaneMessageHistory);
		
		JTextPane textPaneUsersOnline = new JTextPane();
		textPaneUsersOnline.setEnabled(false);
		textPaneUsersOnline.setEditable(false);
		textPaneUsersOnline.setVisible(false);
		textPaneUsersOnline.setBounds(471, 46, 240, 391);
		contentPane.add(textPaneUsersOnline);
		
		JLabel lblMessageHistoryLabel = new JLabel("Message History");
		lblMessageHistoryLabel.setEnabled(false);
		lblMessageHistoryLabel.setVisible(false);
		lblMessageHistoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMessageHistoryLabel.setBounds(10, 11, 155, 24);
		contentPane.add(lblMessageHistoryLabel);
		
		JLabel lblUserList = new JLabel("Users In Server");
		lblUserList.setEnabled(false);
		lblUserList.setVisible(false);
		lblUserList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserList.setBounds(471, 11, 141, 24);
		contentPane.add(lblUserList);
		
		JButton btnSend = new JButton("Send Message");
		btnSend.addActionListener(new ActionListener() {
			//=======================================================================
			//|Method			:	CreateLines(String Command)					   			
			//|																   		
			//|Method parameters:	String Command								   		
			//|																   		
			//|What it does		:	Creates points in an array to be used for drawing
			//|						
			//|																	    
			//|Change log		:	Date		Creator    	Notes			    
			//|						===========	========   	=============	    
			//|						Feb 3 2022	J. Shaddick	Initial setup
			//|
			//|						Feb 10 2022	J. Smith   	Added code to convert the infoPacket
			//|												class object into a json string
			
			//|						Feb 15 2022	J. Smith   	Added code to implement message instance class
			//=======================================================================
			public void actionPerformed(ActionEvent e) {
				//Creates a new packet object
				infoPacket = new InfoPacket();
				messageInstance = new MessageInstance();
				
				messageInstance.MessageContent = textPaneMessageInput.getText();
				messageInstance.userID = UserIDString;
				
				String messageInstance2Json = new Gson().toJson(messageInstance);
				
				//Fills out the needed info for sending a Json packet
				infoPacket.packetType = "message";
				infoPacket.packetArguments = messageInstance2Json;
				infoPacket.userID = UserIDString;
				
				//Converts the object into a string
				String packetString_json = new Gson().toJson(infoPacket);
				
				//Calls method to send packet (now as a string) to the server through the client
				User.sendClient(packetString_json);
				updateMessages();
			}
		});
		btnSend.setVisible(false);
		btnSend.setEnabled(false);
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSend.setBounds(10, 647, 188, 34);
		contentPane.add(btnSend);
		
		textFieldIP = new JTextField();
		textFieldIP.setFont(new Font("Tahoma", Font.PLAIN, 47));
		textFieldIP.setBounds(287, 448, 422, 66);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		
		JLabel lblIP = new JLabel("IP Address:");
		lblIP.setFont(new Font("Tahoma", Font.PLAIN, 47));
		lblIP.setBounds(37, 447, 240, 66);
		contentPane.add(lblIP);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 47));
		lblUsername.setBounds(37, 524, 240, 66);
		contentPane.add(lblUsername);
		
		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Tahoma", Font.PLAIN, 47));
		textFieldUser.setColumns(10);
		textFieldUser.setBounds(287, 525, 422, 66);
		contentPane.add(textFieldUser);
		
		JButton btnSubmitUserInfo = new JButton("Submit");
		btnSubmitUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Method:				actionPerformed
				 * 
				 * Method Parameters:	ActionEvent e
				 * 
				 * Method Return:		None
				 * 
				 * Synopsis:			This method will acquire the username and IP address
				 * 						and will send it to the data packet. It will also use data
				 * 						management class to save the info so the user will not
				 * 						need to remember their IP or the username they provided.
				 * 
				 * Modifications:		Date:		Name:			Modifications:
				 * 						02/08/2022	Jared Shaddick	Initial Setup
				 * 						02/10/2022	Joey Smith		Adjusted Code for use for
				 * 													info packets
				 */
				UserIPString = textFieldIP.getText();
				UserIDString = textFieldUser.getText();
				if(UserIDString.isBlank() || UserIPString.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error: Insufficient Data");
				}
				else {
					try {
						DM.getUserInfo(UserIDString ,UserIPString);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					User.serverName = UserIPString;
					
					lblIP.setVisible(false);
					lblUsername.setVisible(false);
					textFieldIP.setVisible(false);
					textFieldUser.setVisible(false);
					btnSubmitUserInfo.setVisible(false);
					textFieldIP.setEnabled(false);
					textFieldUser.setEnabled(false);
					btnSubmitUserInfo.setEnabled(false);
					btnSend.setVisible(true);
					btnSend.setEnabled(true);
					textPaneMessageHistory.setEnabled(true);
					lblMessageHistoryLabel.setEnabled(true);
					lblUserList.setEnabled(true);
					textPaneMessageInput.setEnabled(true);
					textPaneUsersOnline.setEnabled(true);
					textPaneMessageHistory.setVisible(true);
					messageScrollBar.setVisible(true);
					lblMessageHistoryLabel.setVisible(true);
					lblUserList.setVisible(true);
					textPaneMessageInput.setVisible(true);
					textPaneUsersOnline.setVisible(true);
					btnLoadUser.setEnabled(false);
					btnLoadUser.setVisible(false);
					lblReturningQuestion.setVisible(false);
					
					tickTok.start(); //starts the timer
				}
			}
		});
		btnSubmitUserInfo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnSubmitUserInfo.setBounds(287, 647, 240, 66);
		contentPane.add(btnSubmitUserInfo);
		
		lblReturningQuestion = new JLabel("Returning User?");
		lblReturningQuestion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblReturningQuestion.setBounds(537, 647, 214, 34);
		contentPane.add(lblReturningQuestion);
		
		btnLoadUser = new JButton("Load Local User");
		btnLoadUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Method:				actionPerformed
				 * 
				 * Method Parameters:	ActionEvent e
				 * 
				 * Method Return:		None
				 * 
				 * Synopsis:			This method will load user data from a file
				 * 						if it exists so the user will not need to re-enter
				 * 						the IP address and username every time the program starts.
				 * 
				 * Modifications:		Date:		Name:			Modifications:
				 * 						02/15/2022	Jared Shaddick	Initial Setup
				 * 						02/15/2022 	Jared Shaddick	Block Comments Established
				 */
				String[] userDataArray = new String[2];
				try {
					userDataArray = DM.loadUserInfo();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				UserIPString = userDataArray[0];
				UserIDString = userDataArray[1];
				
				User.serverName = UserIPString;
				
				lblIP.setVisible(false);
				lblUsername.setVisible(false);
				textFieldIP.setVisible(false);
				textFieldUser.setVisible(false);
				btnSubmitUserInfo.setVisible(false);
				textFieldIP.setEnabled(false);
				textFieldUser.setEnabled(false);
				btnSubmitUserInfo.setEnabled(false);
				btnSend.setVisible(true);
				btnSend.setEnabled(true);
				textPaneMessageHistory.setEnabled(true);
				messageScrollBar.setVisible(true);
				lblMessageHistoryLabel.setEnabled(true);
				lblUserList.setEnabled(true);
				textPaneMessageInput.setEnabled(true);
				textPaneUsersOnline.setEnabled(true);
				textPaneMessageHistory.setVisible(true);
				lblMessageHistoryLabel.setVisible(true);
				lblUserList.setVisible(true);
				textPaneMessageInput.setVisible(true);
				textPaneUsersOnline.setVisible(true);
				btnLoadUser.setEnabled(false);
				btnLoadUser.setVisible(false);
				lblReturningQuestion.setVisible(false);
				
				tickTok.start(); //starts the timer
			}
		});
		btnLoadUser.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnLoadUser.setBounds(537, 692, 214, 34);
		contentPane.add(btnLoadUser);
		
		
	}
	
	public void updateMessages() {
		textPaneMessageHistory.setText(null);
		InfoPacket InfoPacket = new InfoPacket();
		Gson converter = new Gson();
		InfoPacket.packetType = "update";
		InfoPacket.packetArguments = converter.toJson(startTime.getTime());
		
		//Converts the object into a string
		String packetString_json = converter.toJson(InfoPacket);
		
		String packetFromServer = "";
		packetFromServer = User.sendClient(packetString_json);
		ArrayList<MessageInstance> inPacket = new ArrayList<MessageInstance>();
		inPacket = converter.fromJson(packetFromServer, new TypeToken<ArrayList<MessageInstance>>() {}.getType());
		System.out.println(inPacket.get(1).MessageContent);
		for (MessageInstance messageIn : inPacket) {
			try {
				appendString(messageIn.MessageContent, messageIn.userID );
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void appendString(String message, String user) throws BadLocationException
	{
		String messageInfo = user + ": " + message + "\n";
	    StyledDocument document = (StyledDocument) textPaneMessageHistory.getDocument();
	    document.insertString(document.getLength(), messageInfo, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//updateMessages();
	}
}
