package com.ss.rdmslite.UI;

import java.util.Scanner;
import com.ss.rdmslite.utility.Utility;

public class MainMenuUI {
	
	Scanner scanner = new Scanner(System.in);
	
	public void mainMenuUI() {
		String mainMenu[] = { "Author Services", "Publisher Services", "Book Services", "Exit" };
		Utility.displayMenu(mainMenu);
		
		
		int input = 0;
		
	
		try {
			while (input < 1 || input > mainMenu.length) {
			System.out.print("Please enter the number" + "(" + 1 + "-" + mainMenu.length + "): ");
			input = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Error: ");
		}

		if (input == 1) {
			new AuthorUI().authorUI();
		}
		if (input == 2) {
			new PublisherUI().publisherUI();
		}
		if (input == 3) {
			new BookUI().bookUI();

		}
		if (input == 4) {
			System.out.println("Good Bye!");
			System.exit(0);
		}

		//scanner.close();
	}
}
