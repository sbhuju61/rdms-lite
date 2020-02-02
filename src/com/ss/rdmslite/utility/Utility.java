/**
 * 
 */
package com.ss.rdmslite.utility;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.List;

/* @author srv-max
*
*/
public class Utility {
	public static void displayMenu(String menu[]) {
		for (int i = 0; i < menu.length; i++) {
			System.out.print(i + 1 + ")" + menu[i] + "   ");
		}
		System.out.println("\n");
	}

	public static void writeFile(String data, String filePath) {
		try {

			Files.write(Paths.get(filePath), data.getBytes(), StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.err.println("Cannot write `" + data + "` to " + filePath);
		}
	}

	public static List<String> readFile(String filePath) {
		List<String> allLines = null;
		try {
			allLines = Files.readAllLines(Paths.get(filePath));
			

		} catch (Exception e) {
			System.err.println("Cannot read from " + filePath);
		}

		return allLines;
	}

	public static void createFile(String fileName) {
		String fullFilePath = "resources/" + fileName + ".txt";
		Path filePath = Paths.get(fullFilePath);
		try {
			Files.createFile(filePath);
		} catch (Exception e) {
			System.err.println("Cannot create " + fullFilePath);
		}
	}

}
