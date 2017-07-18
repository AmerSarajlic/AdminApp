package com.testproject.utill;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputHelper {

	/**
	 * <p>
	 * This method is used as user input helper.
	 * </p>
	 * 
	 * @param str
	 *            represent console text that ask user to enter specific data
	 * @return inserted user data
	 */
	public static String inputHelp(String str) {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		System.out.print(str);
		System.out.flush();

		try {
			return stdin.readLine();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
}
