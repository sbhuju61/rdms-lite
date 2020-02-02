package com.ss.rdmslite.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.ss.rdmslite.main.App;
import com.ss.rdmslite.utility.Utility;

public class Book {

	private static String bookFilePath = "resources/Book.txt";

	public static void createRecord(String data) {
		Path filePath = Paths.get(bookFilePath);
		// TODO Auto-generated method stub
		if (!Files.exists(filePath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			Utility.createFile("Book");
		}
		long bookID = 0;
		try {
			bookID = Files.lines(filePath).count() + 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		Utility.writeFile(bookID + "," + data, bookFilePath);
		bookUI();
	}

	public static void readRecord() {
		// TODO Auto-generated method stub
		System.out.println("All the books are: ");
		Utility.readFile(bookFilePath).stream().filter(line -> !line.equals("Deleted"))
				.forEach(line -> System.out.println(line));
		bookUI();

	}

	public void updateRecord(int id) {
		// TODO Auto-generated method stub

	}

	public static void deleteRecord(int id) {
		// TODO Auto-generated method stub

	}

	public static void bookUI() {
		String bookMenu[] = { "Add Book", "Delete Book", "Update Book", "Read all Book", "Quit to Previous" };
		Utility.displayMenu(bookMenu);

		Scanner scanner = new Scanner(System.in);
		int count = 0;

		while (count < 1 || count > bookMenu.length - 1) {
			try {
				System.out.print("Please enter the number" + "(" + 1 + "-" + bookMenu.length + "): ");
				count = scanner.nextInt();
				System.out.println();
			} catch (Exception e) {
				System.err.println("\nInvalid Input: " + scanner.nextLine());
			}

			if (count == 1) {
				addBookUI();
			} else if (count == 2) {
				deleteBookUI();
			} else if (count == 3) {
				Book.updateBookUI();
			} else if (count == 4) {
				Book.readRecord();
			} else if (count == 5) {
				App.mainMenuUI();
			}

		}

		scanner.close();

	}

	private static void updateBookUI() {
		// TODO Auto-generated method stub

	}

	private static void deleteBookUI() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Utility.readFile(bookFilePath).stream().filter(line -> !line.equals("Deleted"))
				.forEach(line -> System.out.println(line));
		;
		System.out.println("\n");
		try {
			System.out.println("Please enter bookID: ");
			System.out.print("Book ID: ");
			int bookID = scanner.nextInt();

			Book.deleteRecord(bookID);

		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner.nextLine());
		}

		scanner.close();
	}

	private static void addBookUI() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Please enter book details: ");
			System.out.print("Book name: ");
			String bookName = scanner.nextLine();

			System.out.print("Author ID: ");
			int authorID = scanner.nextInt();
			
			System.out.print("Publisher ID: ");
			int publisherID = scanner.nextInt();
			
			
			Book.createRecord(bookName + "," + authorID + "," + publisherID + "\n");
		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner.nextLine());
		}

		scanner.close();

	}

}
