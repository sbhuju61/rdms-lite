package com.ss.rdmslite.entity;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import com.ss.rdmslite.utility.Utility;

public class Book implements Entity {

	private String bookFilePath = "resources/Book.txt";

	public void createRecord(String data) {
		Path filePath = Paths.get(bookFilePath);

		if (!Files.exists(filePath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			Utility.createFile("Book");
		}

		long bookID = 0;

		try {
			bookID = Files.lines(filePath).count() + 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.err.println("Invalid file");
		}

		Utility.writeFile(bookID + "," + data + "\n", bookFilePath);
		System.out.println("Added: " + bookID + "," + data);
	}

	public List <String> readRecord() {
		// TODO Auto-generated method stub
		System.out.println("All the books are: ");

		return Utility.readFile(bookFilePath);
	}

	public void updateRecord(int id, String data) {
		// TODO Auto-generated method stub
		List<String> bookData = null;

		try {
			bookData = Files.readAllLines(Paths.get(bookFilePath));

			for (int i = 0; i < bookData.size(); i++) {
				String idPattern = "^" + id + "{1},.*";
				if (bookData.get(i).matches(idPattern)) {

					bookData.set(i,data);

				}
			}
		} catch (Exception e) {
			System.err.println("Cannot read from " + bookFilePath);
		}

		Utility.emptyFile(bookFilePath);

		bookData.forEach(line -> Utility.writeFile(line + "\n", bookFilePath));
		
		System.out.println("Updated: " + data);

	}

	public void deleteRecord(int id) {

		List<String> bookData = null;
		try {
			bookData = Files.readAllLines(Paths.get(bookFilePath));

			for (int i = 0; i < bookData.size(); i++) {
				String idPattern = "^" + id + "{1},.*";
				if (bookData.get(i).matches(idPattern)) {
					System.out.println("Book Deleted: " + bookData.get(i));
					bookData.set(i, "Deleted");

				}
			}
		} catch (Exception e) {
			System.err.println("Cannot read from " + bookFilePath);
		}

		Utility.emptyFile(bookFilePath);

		bookData.forEach(line -> Utility.writeFile(line + "\n", bookFilePath));

	}

	

}
