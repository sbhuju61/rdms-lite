package com.ss.rdmslite.entity;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

import com.ss.rdmslite.main.App;
import com.ss.rdmslite.utility.Utility;

public class Author {
	private static String authorFilePath = "resources/Author.txt";
	private static String bookFilePath = "resources/Book.txt";

	public static void createRecord(String data) {
		Path filePath = Paths.get(authorFilePath);
		// TODO Auto-generated method stub
		if (!Files.exists(filePath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			Utility.createFile("Author");
		}
		long authorID = 0;
		try {
			authorID = Files.lines(filePath).count() + 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		Utility.writeFile(authorID + "," + data, filePath.toString());
		authorUI();
	}

	public static void readRecord() {
		// TODO Auto-generated method stub
		System.out.println("All the authors are: ");
		Utility.readFile("resources/Author.txt").stream().filter(line -> !line.equals("Deleted"))
				.forEach(line -> System.out.println(line));
		authorUI();
	}

	public static void updateRecord(int id, String data) {
		// TODO Auto-generated method stub
		
		List<String> authorData = null;
		try {
			authorData = Files.readAllLines(Paths.get(authorFilePath));

			for (int i = 0; i < authorData.size(); i++) {
				String idPattern = "^" + id + "{1},.*";
				if (authorData.get(i).matches(idPattern)) {

					authorData.remove(i);

				}
			}
		} catch (Exception e) {
			System.err.println("Cannot read from " + authorFilePath);
		}
		try {
			FileChannel.open(Paths.get(authorFilePath), StandardOpenOption.WRITE).truncate(0).close();
		} catch (IOException e) {

		}

		authorData.forEach(line -> Utility.writeFile(line + "\n", authorFilePath));
		Utility.writeFile(data, "resources/Author.txt");
		System.out.println("Updated: " + data);
		authorUI();

	}

	public static void deleteRecord(int id) {
		// TODO Auto-generated method stub
		deleteBooksOfAuthor(id);

		List<String> authorData = null;
		try {
			authorData = Files.readAllLines(Paths.get(authorFilePath));

			for (int i = 0; i < authorData.size(); i++) {
				String idPattern = "^" + id + "{1},.*";
				if (authorData.get(i).matches(idPattern)) {
					System.out.println("Author Deleted: " + authorData.get(i));
					authorData.set(i, "Deleted");

				}
			}
		} catch (Exception e) {
			System.err.println("Cannot read from " + authorFilePath);
		}

		try {
			FileChannel.open(Paths.get(authorFilePath), StandardOpenOption.WRITE).truncate(0).close();
		} catch (IOException e) {

		}

		authorData.forEach(line -> Utility.writeFile(line + "\n", authorFilePath));
		authorUI();
	}

	public static void deleteBooksOfAuthor(int authorID) {

		System.out.println("All the books are: ");
		System.out.println();
		Utility.readFile(bookFilePath).stream().filter(line -> !line.equals("Deleted"))
				.forEach(line -> System.out.println(line));
		List<String> bookData = null;
		try {
			bookData = Files.readAllLines(Paths.get(bookFilePath));

			for (int i = 0; i < bookData.size(); i++) {
				String idPattern = ".+,.+," + authorID + ",+.+";
				if (bookData.get(i).matches(idPattern)) {
					System.out.println("");
					System.out.println("Book Deleted: " + bookData.get(i));

					bookData.set(i, "Deleted");

				}
			}
		} catch (Exception e) {
			System.err.println("Cannot read from " + bookFilePath);
		}
		try {
			FileChannel.open(Paths.get(bookFilePath), StandardOpenOption.WRITE).truncate(0).close();
		} catch (IOException e) {

		}

		bookData.forEach(line -> Utility.writeFile(line + "\n", bookFilePath));
	}

	public static void authorUI() {
		String authorMenu[] = { "Add Author", "Delete Author", "Update Author", "Read all Author", "Quit to Previous" };
		Utility.displayMenu(authorMenu);

		Scanner scanner = new Scanner(System.in);
		int count = 0;

		while (count < 1 || count > authorMenu.length - 1) {
			try {
				System.out.print("Please enter the number" + "(" + 1 + "-" + authorMenu.length + "): ");
				count = scanner.nextInt();
				System.out.println();
			} catch (Exception e) {
				System.err.println("\nInvalid Input: " + scanner.nextLine());
			}

			if (count == 1) {
				addAuthorUI();
			} else if (count == 2) {
				deleteAuthorUI();
			} else if (count == 3) {
				Author.updateAuthorUI();
			} else if (count == 4) {
				Author.readRecord();
			} else if (count == 5) {
				App.mainMenuUI();
			}

		}

		scanner.close();
	}

	public static void deleteAuthorUI() {
		Scanner scanner = new Scanner(System.in);
		Utility.readFile("resources/Author.txt").stream().filter(line -> !line.equals("Deleted"))
				.forEach(line -> System.out.println(line));
		;
		System.out.println("\n");
		try {
			System.out.println("Please enter authorID: ");
			System.out.print("Author ID: ");
			int authorID = scanner.nextInt();

			Author.deleteRecord(authorID);

		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner.nextLine());
		}

		scanner.close();
	}

	public static void addAuthorUI() {

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Please enter author details: ");
			System.out.print("First Name: ");
			String firstName = scanner.nextLine();

			System.out.print("Last Name: ");
			String lastName = scanner.nextLine();
			Author.createRecord(firstName + "," + lastName + "\n");
		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner.nextLine());
		}

		scanner.close();
	}

	public static void updateAuthorUI() {

		Scanner scanner1 = new Scanner(System.in);
		int input = 0;

		try {
			System.out.print("Enter authorID: ");
			input = scanner1.nextInt();
		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner1.nextLine());
		}

		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("Please enter author details: ");
			System.out.print("First Name: ");
			String firstName = scanner.nextLine();

			System.out.print("Last Name: ");
			String lastName = scanner.nextLine();
			Author.updateRecord(input, input + "," + firstName + "," + lastName + "\n");
		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner.nextLine());
		}

		scanner.close();
		scanner1.close();

	}

}
