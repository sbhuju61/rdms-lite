package com.ss.rdmslite.entity;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.ss.rdmslite.utility.Utility;

public class Publisher implements Entity {
	private  String publisherFilePath = "resources/Publisher.txt";
	private  String bookFilePath = "resources/Book.txt";

	public void createRecord(String data) {
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
			System.out.println("Bad file");
		}
		Utility.writeFile(publisherID + "," + data, filePath.toString());
		System.out.println("Added: " + publisherID + "," + data);
	}

	public List<String> readRecord() {
		// TODO Auto-generated method stub
		System.out.println("All the publisher are: ");
		return Utility.readFile(publisherFilePath);
		

		
	}

	public  void updateRecord(int id, String data) {
		// TODO Auto-generated method stub
		List<String> publisherData = null;
		try {
			publisherData = readRecord();

			for (int i = 0; i < publisherData.size(); i++) {
				String idPattern = "^" + id + "{1},.*";
				if (publisherData.get(i).matches(idPattern)) {

					publisherData.set(i,data);

				}
			}
		} catch (Exception e) {
			System.err.println("Cannot read from " + publisherFilePath);
		}
		Utility.emptyFile(publisherFilePath);

		publisherData.forEach(line -> Utility.writeFile(line + "\n", publisherFilePath));
		
		System.out.println("Updated: " + data);
		

	}

	public  void deleteRecord(int id) {
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
		
	}

	private  void deleteBooksOfPublisher(int publisherID) {
		// TODO Auto-generated method stub

		System.out.println("All the books are: ");
		
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

	
}
