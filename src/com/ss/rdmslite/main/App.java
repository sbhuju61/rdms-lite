package com.ss.rdmslite.main;

import java.util.Scanner;

import com.ss.rdmslite.entity.Author;
import com.ss.rdmslite.entity.Book;
import com.ss.rdmslite.entity.Publisher;
import com.ss.rdmslite.utility.Utility;

public class App {
	public static void greetingMessage() {
		System.out.println("Welcome to the SmoothStack Library Management System.\n");
		mainMenuUI();
		
	}
	public static void mainMenuUI () {
		String mainMenu[] = { "Author Services", "Publisher Services", "Book Services", "Exit" };
		Utility.displayMenu(mainMenu);

		Scanner scanner = new Scanner(System.in);
		int input = 0;

		while (input < 1 || input > mainMenu.length - 1) {
			try {
				System.out.print("Please enter the number" + "(" + 1 + "-" + mainMenu.length + "): ");
				input = scanner.nextInt();
				System.out.println();
			} catch (Exception e) {
				System.err.println("\nInvalid Input: " + scanner.nextLine());
			}

			if (input == 1) {
				Author.authorUI();
			} 
			if (input == 2) {
				Publisher.publisherUI();
			} 
			if (input == 3) {
				Book.bookUI();
			
			} 
			if (input == 4) {
				System.out.println("Good Bye!");
				System.exit(0);
			}
			
			

		}

		scanner.close();
	}


	public static void main(String[] args) {

		greetingMessage();
	}

}
