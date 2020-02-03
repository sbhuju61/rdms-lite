/**
 * 
 */
package com.ss.rdmslite.utility;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.List;
import java.util.stream.Collectors;



/* @author srv-max
*
*/
public class Utility {
	public static void displayMenu(String menu[]) {
		for (int i = 0; i < menu.length; i++) {
			System.out.print(i + 1 + ")" + menu[i] + "   ");
		}
		System.out.println("");
	}

	public static void writeFile(String data, String filePath) {
		try {

			Files.write(Paths.get(filePath), data.getBytes(), StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.err.println("Cannot write `" + data + "` to " + filePath);
		}
	}
	public static void emptyFile (String filePath) {
		try {
			FileChannel.open(Paths.get(filePath), StandardOpenOption.WRITE).truncate(0).close();
		} catch (IOException e) {

		}

	}

	public static List<String> readFile(String filePath) {
		List<String> allLines = null;
		try {
			allLines = Files.readAllLines(Paths.get(filePath));
			

		} catch (Exception e) {
			System.err.println("Cannot read from " + filePath);
		}
		allLines = allLines.stream().filter(line -> !line.equals("Deleted")).collect(Collectors.toList());
		
		allLines.forEach(line -> System.out.println(line));
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
	
	public static boolean checkPattern(List<String> dataList, String pattern) {
		
		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).matches(pattern)) {
				return true;
			}
		}
		return false;
		
	}

}
