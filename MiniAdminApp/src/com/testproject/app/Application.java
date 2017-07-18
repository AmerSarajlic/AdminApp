package com.testproject.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.testproject.dao.UserDAOImpl;
import com.testproject.data.User;
import com.testproject.utill.InputHelper;

public class Application {

	public static UserDAOImpl userDAOImpl = new UserDAOImpl();

	/**
	 * Mini app workstation
	 */

	public static void app() {
		Scanner input = new Scanner(System.in);
		boolean runing = true;
		while (runing) {

			try {
				boolean on = true;
				while (on) {
					displayMenu();
					System.out.print("\nInsert your option: ");
					int option = input.nextInt();

					switch (option) {
					case 1:
						addUser();
						break;
					case 2:
						displayAllUsers();
						deleteUser();
						break;
					case 3:
						displayAllUsers();
						updateUserPassword();
						break;
					case 4:
						displayAllUsers();
						break;
					case 5:
						runing = false;
						System.out.println("Loging out...........");
						System.exit(0);
						input.close();
						break;
					default:
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid input !!!");
				input.nextLine();
			}
		}
	}

	private static void updateUserPassword() {
		int id = Integer.parseInt(InputHelper.inputHelp("Insert id of user that you want update: "));
		userDAOImpl.updateUserPassword(id);
	}

	private static void displayAllUsers() {
		userDAOImpl.displayAllUsers();
	}

	private static void deleteUser() {

		int id = Integer.parseInt(InputHelper.inputHelp("Insert id of user that you want to delete: "));
		userDAOImpl.deleteUser(id);

	}

	/**
	 * Adding new user data
	 */
	private static void addUser() {

		User user = new User();
		boolean on = true;
		while (on) {

			String userName = InputHelper.inputHelp("Inser user name: ");

			if (isValid(userName)) {
				on = false;
				String password = InputHelper.inputHelp("Insert user password: ");
				user.setUserName(userName);
				user.setUserPassword(password);
				userDAOImpl.createUser(user);
			} else {
				System.out.println("\nInserted user name is already in database. Please try another one.");
				userNameSuggestion(userName);
			}
		}
	}

	/**
	 * Method that suggest valid user names based on user first input
	 */
	private static void userNameSuggestion(String userName) {

		List<String> suggList = new ArrayList<>();

		String suggestion = "";

		for (int i = 0; i < 3; i++) {

			suggestion = userName + (int) (Math.random() * 999);
			suggList.add(suggestion);
		}
		System.out.print("(SUGGESTIONS: ");
		suggList.forEach(name -> System.out.print(name + " "));
		System.out.println(")\n");

	}

	/**
	 * Checking is user name unique
	 */
	private static boolean isValid(String userName) {

		User user = userDAOImpl.findUser(userName);
		if (user == null) {
			return true;
		}
		return false;
	}

	/**
	 * Displaying simple admin menu
	 */
	private static void displayMenu() {
		System.out.println("\n\n1. Add user\n2. Delete user\n3. Update user password\n4. List all users\n5. Log out.");
	}
}
