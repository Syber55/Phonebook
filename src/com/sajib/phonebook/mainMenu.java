package com.sajib.phonebook;

import java.util.Scanner;
import com.sajib.sqlConnection.*;

public class mainMenu {

	public static final Scanner inp = new Scanner(System.in);

	public static void main(String[] args) {
		User user = new User();
		User.loadUsers();
		boolean loginLoop = true;
		String loginUser="";
		while (loginLoop) {
			System.out.println("Please log-in or register: \n1.Login\n2.Register");
			int decision = inp.nextInt();
			boolean loginCheck = false;
			if (decision == 1) {
				do {
					System.out.println("Please enter you username and password");
					String usernm = inp.next();
					String passwrd = inp.next();
					if (user.checkUsernameAndPassword(usernm, passwrd)) {
						System.out.println("Username and password are correct");
						loginLoop = false;
						loginCheck = true;
						loginUser = usernm;
					} else {
						System.out
								.println("Username or password are incorrect...\nExit application?\nType in 1 to exit");
						int exitLogin = inp.nextInt();
						if (exitLogin == 1) {
							System.out.println("Goodbye");
							System.exit(0);
						}
					}

				} while (!loginCheck);
			}

			else if (decision == 2) {
				System.out.println("Please enter your username, password and phonenumber");
				String usernm = inp.next();
				String passwrd = inp.next();
				int phonenum = inp.nextInt();
				user.User(usernm, passwrd, phonenum);
				loginUser = usernm;
			}
			while(true) {
				System.out.println("Welcome, " + loginUser);
				System.out.println("Select one of the following options:");
				System.out.println("1.Add a new contact\n2.Edit a contact\n3.Delete a contact\n4.Print every contact\n5.Search your contact list\n6.Exit");
				int selection = inp.nextInt();
				switch(selection) {
				case 1: System.out.println("Input the name and phone number of the contact you wish to add:");
				String contactName = inp.next();
				int contactNumber = inp.nextInt();
					statements.addContact(loginUser, contactName, contactNumber);
					break;
				case 2: break;
				case 3: break;
				case 4: break;
				case 5: break;
				case 6: System.out.println("Goodbye");
				System.exit(0);
				break;
				default:System.out.println("Wrong input, please try again");
				}
			}
		}

	}
}
