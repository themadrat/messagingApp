package messagingApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class messageInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIP;
	private JTextField textFieldUser;
	///hello

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
		
		JTextPane textPaneMessageHistory = new JTextPane();
		textPaneMessageHistory.setEnabled(false);
		textPaneMessageHistory.setEditable(false);
		textPaneMessageHistory.setVisible(false);
		textPaneMessageHistory.setBounds(10, 46, 451, 391);
		contentPane.add(textPaneMessageHistory);
		
		JButton btnSend = new JButton("Send Message");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				String IPAddress = "";
				String Username = "";
				lblIP.setVisible(false);
				lblUsername.setVisible(false);
				IPAddress = textFieldIP.getText();
				System.out.println(IPAddress);
				Username = textFieldUser.getText();
				System.out.println(Username);
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
				lblMessageHistoryLabel.setVisible(true);
				lblUserList.setVisible(true);
				textPaneMessageInput.setVisible(true);
				textPaneUsersOnline.setVisible(true);
			}
		});
		btnSubmitUserInfo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnSubmitUserInfo.setBounds(287, 647, 240, 66);
		contentPane.add(btnSubmitUserInfo);
		
	}
}
