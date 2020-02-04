package com.ss.rdmslite.UI;

import java.util.Scanner;

import com.ss.rdmslite.utility.Utility;
import com.ss.rdmslite.entity.Author;

public class AuthorUI {
	// private String authorFilePath = "resources/Author.txt";
	// private String bookFilePath = "resources/Book.txt";

	Scanner scanner = new Scanner(System.in);

	public void authorUI() {
		String authorMenu[] = { "Add Author", "Delete Author", "Update Author", "Read all Author", "Quit to Previous" };
		Utility.displayMenu(authorMenu);

		int inputNum = 0;
		// System.out.println("here");
		System.out.println("Please enter the number" + "(" + 1 + "-" + authorMenu.length + "): ");
		// if (scanner.hasNextLine()) {
		try {
			inputNum = Integer.parseInt(scanner.nextLine());
		}
		catch (Exception e) {
			System.err.println("Error");
		}
		
		// }
		// System.out.println("here1");

		if (inputNum == 1) {
			addAuthorUI();
			authorUI();
		}

		if (inputNum == 2) {
			deleteAuthorUI();
			authorUI();
		}

		if (inputNum == 3) {
			updateAuthorUI();
			authorUI();

		}

		if (inputNum == 4) {
			new Author().readRecord();
			authorUI();
		}

		if (inputNum == 5) {
			new MainMenuUI().mainMenuUI();
		}

		// scanner.close();
	}

	public void addAuthorUI() {

		// Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Please enter author details: ");
			System.out.println("First Name: ");
			String firstName = scanner.nextLine();

			System.out.println("Last Name: ");
			String lastName = scanner.nextLine();

			new Author().createRecord(firstName + "," + lastName + "\n");
		} catch (Exception e) {
			System.err.println("Invalid Input");
		}

		// scanner.close();
	}

	public void deleteAuthorUI() {
		// Scanner scanner = new Scanner(System.in);
		new Author().readRecord();

		try {
			System.out.println("Please enter authorID: ");
			System.out.println("Author ID: ");
			int authorID = Integer.parseInt(scanner.nextLine());

			new Author().deleteRecord(authorID);

		} catch (Exception e) {
			System.err.println("Invalid Input");
		}

		// scanner.close();
	}

	public void updateAuthorUI() {

		// Scanner scanner = new Scanner(System.in);
		int input = 0;
		new Author().readRecord();
		try {
			System.out.println("Enter authorID: ");
			input = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.err.println("Error");
		}

		// Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("Please enter author details: ");
			System.out.println("First Name: ");
			String firstName = scanner.nextLine();

			System.out.println("Last Name: ");
			String lastName = scanner.nextLine();
			new Author().updateRecord(input, input + "," + firstName + "," + lastName);
		} catch (Exception e) {
			System.err.println("Errror");
		}

		// scanner.close();
		// scanner1.close();

	}
}
