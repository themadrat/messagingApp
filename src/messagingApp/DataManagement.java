package messagingApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DataManagement {

	private final static String dataDirectory = "data/userData.txt";				//Default directory for storing user data
	private Scanner fileScanner;													//Used for reading files
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
		File userDataFile = new File(dataDirectory);								//New file variable used to check if a file exists already
		if(userDataFile.exists()) {
			timeToSave = contentCheck(userDataFile);								//Used to check if the file contents are different than the provided strings
			if (!timeToSave) {
				saveUserData();														//Calls the method used for saving data
			}
		}
		else {
			saveUserData();															//Calls the method used for saving data
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
		boolean contentIdentical = false;											//Boolean variable used for checking if the strings match the file contents
		String userCheck = "";
		String IPCheck = "";
		try {
			fileScanner = new Scanner(dataFile);									//Scanner instantiated
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPCheck = fileScanner.nextLine();											//checks if the IP address matches
		userCheck = fileScanner.nextLine();											//checks if the username matches
		if (userCheck.matches(userID) && IPCheck.matches(userIP)) {
			contentIdentical = true;
		}
		else {
			contentIdentical = false;
		}
		
		return contentIdentical;													//returns the boolean variable
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
		File userDataFile = new File(dataDirectory);								//file variable created and instantiated
		FileWriter saveUserData = new FileWriter(userDataFile);						//file writer variable created and instantiated
		if (lineCounter == 0) {
			saveUserData.write(userIP + "\n");										//uses file writer to write data to the file
			lineCounter++;
		}
		if (lineCounter == 1) {
			saveUserData.write(userID + "\n");										//uses file writer to write data to the file
			lineCounter++;
		}
		saveUserData.close();														//closes the file writer
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
		String[] userInfo = new String[2];											//string array used for storing IP and username
		File userDataFile = new File(dataDirectory);								//file variable created and instantiated
		fileScanner = new Scanner(userDataFile);									//scanner instantiated
		userInfo[0] = fileScanner.nextLine();										//assigns the IP to the array
		userInfo[1] = fileScanner.nextLine();										//assigns the username to the array
		return userInfo;															//returns the array
	}
}
