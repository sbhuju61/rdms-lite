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

public class Publisher {
	private static String publisherFilePath = "resources/Publisher.txt";
	private static String bookFilePath = "resources/Book.txt";

	public static void createRecord(String data) {
		// TODO Auto-generated method stub
		Path filePath = Paths.get(publisherFilePath);
		// TODO Auto-generated method stub
		if (!Files.exists(filePath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			Utility.createFile("Publisher");
		}
		long publisherID = 0;
		try {
			publisherID = Files.lines(filePath).count() + 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		Utility.writeFile(publisherID + "," + data, filePath.toString());
		publisherUI();
	}

	public static void readRecord() {
		// TODO Auto-generated method stub
		System.out.println("All the publisher are: ");
		Utility.readFile(publisherFilePath).stream().filter(line -> !line.equals("Deleted"))
				.forEach(line -> System.out.println(line));
		System.out.println();
		publisherUI();
	}

	public static void updateRecord(int id, String data) {
		// TODO Auto-generated method stub
		List<String> publisherData = null;
		try {
			publisherData = Files.readAllLines(Paths.get(publisherFilePath));

			for (int i = 0; i < publisherData.size(); i++) {
				String idPattern = "^" + id + "{1},.*";
				if (publisherData.get(i).matches(idPattern)) {

					publisherData.remove(i);

				}
			}
		} catch (Exception e) {
			System.err.println("Cannot read from " + publisherFilePath);
		}
		try {
			FileChannel.open(Paths.get(publisherFilePath), StandardOpenOption.WRITE).truncate(0).close();
		} catch (IOException e) {

		}

		publisherData.forEach(line -> Utility.writeFile(line + "\n", publisherFilePath));
		Utility.writeFile(data, "resources/Publisher.txt");
		System.out.println("Updated: " + data);
		publisherUI();

	}

	public static void deleteRecord(int id) {
		// TODO Auto-generated method stub
		deleteBooksOfPublisher(id);

		List<String> publisherData = null;
		try {
			publisherData = Files.readAllLines(Paths.get(publisherFilePath));

			for (int i = 0; i < publisherData.size(); i++) {
				String idPattern = "^" + id + "{1},.*";
				if (publisherData.get(i).matches(idPattern)) {
					System.out.println("Publisher Deleted: " + publisherData.get(i));
					publisherData.set(i, "Deleted");

				}
			}
			System.out.println();
		} catch (Exception e) {
			System.err.println("Cannot read from " + publisherFilePath);
		}

		try {
			FileChannel.open(Paths.get(publisherFilePath), StandardOpenOption.WRITE).truncate(0).close();
		} catch (IOException e) {

		}

		publisherData.forEach(line -> Utility.writeFile(line + "\n", publisherFilePath));
		publisherUI();
	}

	private static void deleteBooksOfPublisher(int publisherID) {
		// TODO Auto-generated method stub

		System.out.println("All the books are: ");
		System.out.println();
		Utility.readFile(bookFilePath).stream().filter(line -> !line.equals("Deleted"))
				.forEach(line -> System.out.println(line));
		List<String> bookData = null;
		try {
			bookData = Files.readAllLines(Paths.get(bookFilePath));

			for (int i = 0; i < bookData.size(); i++) {
				String idPattern = "^.+,.+," + ".+," + publisherID + "$";
				if (bookData.get(i).matches(idPattern)) {
					System.out.println("");
					System.out.println("Book Deleted: " + bookData.get(i));

					bookData.set(i, "Deleted");

				}
			}
			System.out.println();
		} catch (Exception e) {
			System.err.println("Cannot read from " + bookFilePath);
		}
		try {
			FileChannel.open(Paths.get(bookFilePath), StandardOpenOption.WRITE).truncate(0).close();
		} catch (IOException e) {

		}

		bookData.forEach(line -> Utility.writeFile(line + "\n", bookFilePath));

	}

	public static void publisherUI() {
		String publisherMenu[] = { "Add Publisher", "Delete Publisher", "Update Publisher", "Read all Publisher",
				"Quit to Previous" };
		Utility.displayMenu(publisherMenu);

		Scanner scanner = new Scanner(System.in);
		int count = 0;

		while (count < 1 || count > publisherMenu.length - 1) {
			try {
				System.out.print("Please enter the number" + "(" + 1 + "-" + publisherMenu.length + "): ");
				count = scanner.nextInt();
				System.out.println();
			} catch (Exception e) {
				System.err.println("\nInvalid Input: " + scanner.nextLine());
			}

			if (count == 1) {
				addPublisherUI();
			} else if (count == 2) {
				deletePublisherUI();
			} else if (count == 3) {
				Publisher.updatePublisherUI();
			} else if (count == 4) {
				Publisher.readRecord();
			} else if (count == 5) {
				App.mainMenuUI();
			}

		}

		scanner.close();
	}

	private static void updatePublisherUI() {
		// TODO Auto-generated method stub
		Scanner scanner1 = new Scanner(System.in);
		int input = 0;

		try {
			System.out.print("Enter publisherID: ");
			input = scanner1.nextInt();
		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner1.nextLine());
		}

		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("Please enter publisher details: ");
			System.out.print("Publisher Name: ");
			String publisherName = scanner.nextLine();

			System.out.print("Publisher Address: ");
			String publisherAddress = scanner.nextLine();
			Publisher.updateRecord(input, input + "," + publisherName + "," + publisherAddress + "\n");
		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner.nextLine());
		}

		scanner.close();
		scanner1.close();

	}

	private static void deletePublisherUI() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Utility.readFile("resources/Publisher.txt").stream().filter(line -> !line.equals("Deleted"))
				.forEach(line -> System.out.println(line));
		;
		System.out.println("\n");
		try {
			System.out.println("Please enter publisherID: ");
			System.out.print("Publisher ID: ");
			int publisherID = scanner.nextInt();

			Publisher.deleteRecord(publisherID);

		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner.nextLine());
		}

		scanner.close();

	}

	private static void addPublisherUI() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Please enter publisher details: ");
			System.out.print("Publisher name: ");
			String publisherName = scanner.nextLine();

			System.out.print("Publisher address: ");
			String publisherAddress = scanner.nextLine();
			Publisher.createRecord(publisherName + "," + publisherAddress + "\n");
		} catch (Exception e) {
			System.err.println("\nInvalid Input: " + scanner.nextLine());
		}

		scanner.close();
	}
}
