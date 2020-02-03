package com.ss.rdmslite.UI;

import java.util.Scanner;

import com.ss.rdmslite.entity.Author;
import com.ss.rdmslite.entity.Book;
import com.ss.rdmslite.entity.Publisher;
import com.ss.rdmslite.utility.Utility;
import java.util.List;

public class BookUI {
	// private String authorFilePath = "resources/Author.txt";
	// private String bookFilePath = "resources/Book.txt";

	Scanner scanner = new Scanner(System.in);

	public void bookUI() {
		String bookMenu[] = { "Add Book", "Delete Book", "Update Book", "Read all Book", "Quit to Previous" };
		Utility.displayMenu(bookMenu);

		int count = 0;

		try {
			while (count < 1 || count > bookMenu.length) {
				System.out.print("Please enter the number" + "(" + 1 + "-" + bookMenu.length + "): ");
				count = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Invalid Input: ");

		}

		if (count == 1) {
			addBookUI();
			bookUI();

		} else if (count == 2) {
			deleteBookUI();
			bookUI();

		} else if (count == 3) {
			updateBookUI();
			bookUI();

		} else if (count == 4) {
			new Book().readRecord();
			bookUI();

		} else if (count == 5) {
			new MainMenuUI().mainMenuUI();
		}

		// scanner.close();

	}

	private void updateBookUI() {
		// TODO Auto-generated method stub
		// Scanner scanner = new Scanner(System.in);
		new Book().readRecord();
		int input = 0;
		String bookName = null;
		int authorID = 0, publisherID = 0;
		try {
			System.out.print("Enter bookID: ");
			input = Integer.parseInt(scanner.nextLine());

			System.out.print("Enter Book name: ");
			bookName = scanner.nextLine();
			List<String> authorData = new Author().readRecord();

			System.out.print("Enter Author ID: ");
			authorID = Integer.parseInt(scanner.nextLine());

			String idAuthorPattern = "^" + authorID + "{1},.*";

			boolean hasAuthorID = Utility.checkPattern(authorData, idAuthorPattern);

			while (!hasAuthorID) {
				System.out.print("Enter Author ID: ");
				authorID = Integer.parseInt(scanner.nextLine());

				idAuthorPattern = "^" + authorID + "{1},.*";

				hasAuthorID = Utility.checkPattern(authorData, idAuthorPattern);

			}
			List<String> publisherData = new Publisher().readRecord();

			System.out.print("Enter Publisher ID: ");
			publisherID = Integer.parseInt(scanner.nextLine());

			String idPublisherPattern = "^" + publisherID + "{1},.*";

			boolean hasPublisherID = Utility.checkPattern(publisherData, idPublisherPattern);

			while (!hasPublisherID) {
				System.out.print("Enter Publisher ID: ");
				publisherID = Integer.parseInt(scanner.nextLine());

				idPublisherPattern = "^" + publisherID + "{1},.*";

				hasPublisherID = Utility.checkPattern(publisherData, idPublisherPattern);

			}
			new Book().updateRecord(input, input + "," + bookName + "," + authorID + "," + publisherID);
		} catch (Exception e) {
			System.err.println("Invalid Input: ");
		}

		//// scanner.close();

	}

	private void deleteBookUI() {
		// TODO Auto-generated method stub
		// Scanner scanner = new Scanner(System.in);
		new Book().readRecord();
		try {
			System.out.println("Please enter bookID: ");
			System.out.print("Book ID: ");
			int bookID = Integer.parseInt(scanner.nextLine());

			new Book().deleteRecord(bookID);

		} catch (Exception e) {
			System.err.println("Invalid Input");
		}

		//// scanner.close();
	}

	private void addBookUI() {
		// TODO Auto-generated method stub
		// Scanner scanner = new Scanner(System.in);

		String bookName = null;
		int authorID = 0, publisherID = 0;

		try {
			System.out.println("Please enter book details: ");
			System.out.print("Enter Book name: ");
			bookName = scanner.nextLine();
			List<String> authorData = new Author().readRecord();

			System.out.print("Enter Author ID: ");
			authorID = Integer.parseInt(scanner.nextLine());

			String idAuthorPattern = "^" + authorID + "{1},.*";

			boolean hasAuthorID = Utility.checkPattern(authorData, idAuthorPattern);

			while (!hasAuthorID) {
				System.out.print("Enter Author ID: ");
				authorID = Integer.parseInt(scanner.nextLine());

				idAuthorPattern = "^" + authorID + "{1},.*";

				hasAuthorID = Utility.checkPattern(authorData, idAuthorPattern);

			}
			List<String> publisherData = new Publisher().readRecord();

			System.out.print("Enter Publisher ID: ");
			publisherID = Integer.parseInt(scanner.nextLine());

			String idPublisherPattern = "^" + publisherID + "{1},.*";

			boolean hasPublisherID = Utility.checkPattern(publisherData, idPublisherPattern);

			while (!hasPublisherID) {
				System.out.print("Enter Publisher ID: ");
				publisherID = Integer.parseInt(scanner.nextLine());

				idPublisherPattern = "^" + publisherID + "{1},.*";

				hasPublisherID = Utility.checkPattern(publisherData, idPublisherPattern);

			}
			new Book().createRecord(bookName + "," + authorID + "," + publisherID);
		} catch (Exception e) {
			System.err.println("Invalid Input: ");
		}
		// input.close();

	}
}
