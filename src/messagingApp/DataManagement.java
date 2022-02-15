package messagingApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DataManagement {

	private final static String dataDirectory = "data/userData.txt";
	private Scanner fileScanner;
	private String userID = "";
	private String userIP = "";
	
	public void getUserInfo(String username, String IP) throws IOException {
		/*
		 * Method:				getUserInfo
		 * 
		 * Method Parameters:	String username, String IP
		 * 
		 * Method Return:		None
		 * 
		 * Synopsis:			This method will receive the data
		 * 						used for communications with the client
		 * 						and server.
		 * 
		 * Modifications:		Date:		Name:			Modifications:
		 * 						02/10/2022	Jared Shaddick	Block Comment Created
		 * 						02/15/2022 	Jared Shaddick	Initial Setup
		 */
		boolean timeToSave = false;
		userID = username;
		userIP = IP;
		File userDataFile = new File(dataDirectory);
		if(userDataFile.exists()) {
			timeToSave = contentCheck(userDataFile);
			if (!timeToSave) {
				saveUserData();
			}
		}
		else {
			saveUserData();
		}
		
		
	}
	
	private boolean contentCheck(File dataFile) {
		/*
		 * Method:				contentCheck
		 * 
		 * Method Parameter:	File dataFile
		 * 
		 * Method Return:		boolean
		 * 
		 * Synopsis:			This method is used for checking if
		 * 						the content within a file is identical
		 * 						to the data provided in the application.
		 * 
		 * Modifications:		Date:		Name:			Modification:
		 * 						02/15/2022	Jared Shaddick	Initial Setup
		 * 						02/15/2022 	Jared Shaddick	Block Comments Established
		 */
		boolean contentIdentical = false;
		String userCheck = "";
		String IPCheck = "";
		try {
			fileScanner = new Scanner(dataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPCheck = fileScanner.nextLine();
		userCheck = fileScanner.nextLine();
		if (userCheck.matches(userID) && IPCheck.matches(userIP)) {
			contentIdentical = true;
		}
		else {
			contentIdentical = false;
		}
		
		return contentIdentical;
	}
	
	private void saveUserData() throws IOException {
		/*
		 * Method:				saveUserData
		 * 
		 * Method Parameters:	None
		 * 
		 * Method Return:		void
		 * 
		 * Synopsis:			If the method, getUserInfo, has determined that
		 * 						a data file associated with the storage of user data
		 * 						is not present.
		 * 
		 * Modifications:		Date:		Name:			Modification:
		 * 						02/08/2022	Jared Shaddick	Initial Setup
		 */
		int lineCounter = 0;
		File userDataFile = new File(dataDirectory);
		FileWriter saveUserData = new FileWriter(userDataFile);
		if (lineCounter == 0) {
			saveUserData.write(userIP + "\n");
			lineCounter++;
		}
		if (lineCounter == 1) {
			saveUserData.write(userID + "\n");
			lineCounter++;
		}
		saveUserData.close();
	}
	
	public String[] loadUserInfo() throws FileNotFoundException {
		/*
		 * Method:				loadUserInfo
		 * 
		 * Method Parameter:	None
		 * 
		 * Method Return:		String[]
		 * 
		 * Synopsis:			This method is used for loading the
		 * 						user info that is stored within the file.
		 * 
		 * Modifications:		Date:		Name:			Modification:
		 * 						02/15/2022	Jared Shaddick	Initial Setup
		 * 						02/15/2022 	Jared Shaddick	Block Comments Established
		 */
		String[] userInfo = new String[2];
		File userDataFile = new File(dataDirectory);
		fileScanner = new Scanner(userDataFile);
		userInfo[0] = fileScanner.nextLine();
		userInfo[1] = fileScanner.nextLine();
		return userInfo;
	}
}
