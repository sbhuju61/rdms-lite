package com.ss.rdmslite.UI;

import java.util.Scanner;

import com.ss.rdmslite.entity.Publisher;
import com.ss.rdmslite.utility.Utility;

public class PublisherUI {
	
	Scanner scanner = new Scanner(System.in);
	
	public  void publisherUI() {
		String publisherMenu[] = { "Add Publisher", "Delete Publisher", "Update Publisher", "Read all Publisher",
				"Quit to Previous" };
		Utility.displayMenu(publisherMenu);

		
		int count = 0;

		
			try {
				while (count < 1 || count > publisherMenu.length) {
				System.out.print("Please enter the number" + "(" + 1 + "-" + publisherMenu.length + "): ");
				count = Integer.parseInt(scanner.nextLine());
			}
				
			} catch (Exception e) {
				System.err.println("Invalid Input: ");
			}

			if (count == 1) {
				addPublisherUI();
				publisherUI();
				
			} else if (count == 2) {
				deletePublisherUI();
				publisherUI();
				
			} else if (count == 3) {
				updatePublisherUI();
				publisherUI();
			
			} else if (count == 4) {
				new Publisher().readRecord();
				publisherUI();
				
			} else if (count == 5) {
				new MainMenuUI().mainMenuUI();
			}

		

		//scanner.close();
	}

	private  void updatePublisherUI() {
		// TODO Auto-generated method stub
		//Scanner scanner = new Scanner(System.in);
		int input = 0;
		new Publisher().readRecord();
		try {
			System.out.print("Enter publisherID: ");
			input = Integer.parseInt(scanner.nextLine());
			
		} catch (Exception e) {
			System.err.println("Invalid Input: ");
		}
		
		//Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("Please enter publisher details: ");
			System.out.print("Publisher Name: ");
			String publisherName = scanner.nextLine();

			System.out.print("Publisher Address: ");
			String publisherAddress = scanner.nextLine();
			new Publisher().updateRecord(input, input + "," + publisherName + "," + publisherAddress);
		} catch (Exception e) {
			System.err.println("Invalid Input: ");
		}

		//scanner.close();
		//scanner1.close();

	}

	private  void deletePublisherUI() {
		// TODO Auto-generated method stub
		//Scanner scanner = new Scanner(System.in);
		new Publisher().readRecord();
		
		try {
			System.out.println("Please enter publisherID: ");
			System.out.print("Publisher ID: ");
			int publisherID = Integer.parseInt(scanner.nextLine());

			new Publisher().deleteRecord(publisherID);

		} catch (Exception e) {
			System.err.println("Invalid Input: ");
		}

		//scanner.close();

	}

	private  void addPublisherUI() {
		// TODO Auto-generated method stub
		//Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Please enter publisher details: ");
			System.out.print("Publisher name: ");
			String publisherName = scanner.nextLine();

			System.out.print("Publisher address: ");
			String publisherAddress = scanner.nextLine();
			new Publisher().createRecord(publisherName + "," + publisherAddress + "\n");
		} catch (Exception e) {
			System.err.println("Invalid Input: ");
		}

		//scanner.close();
	}
}
