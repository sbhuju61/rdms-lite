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

public class Author implements Entity {
	private String authorFilePath = "resources/Author.txt";
	private String bookFilePath = "resources/Book.txt";

	public void createRecord(String data) {
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
		System.out.println("Added: " + authorID + "," + data);
	}

	public List<String> readRecord() {
		// TODO Auto-generated method stub
		System.out.println("All the authors are: ");
		return Utility.readFile("resources/Author.txt");

	}

	public void updateRecord(int id, String data) {
		// TODO Auto-generated method stub

		List<String> authorData = null;
		try {
			authorData = readRecord();

			for (int i = 0; i < authorData.size(); i++) {
				String idPattern = "^" + id + "{1},.*";
				if (authorData.get(i).matches(idPattern)) {

					authorData.set(i, data);

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

		System.out.println("Updated: " + data);

	}

	public void deleteRecord(int id) {
		// TODO Auto-generated method stub
		deleteBooksOfAuthor(id);

		List<String> authorData = null;
		try {
			authorData = readRecord();

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

	}

	public void deleteBooksOfAuthor(int authorID) {

		System.out.println("All the books are: ");
		readRecord();
		List<String> bookData = null;
		try {
			bookData = new Book().readRecord();

			for (int i = 0; i < bookData.size(); i++) {
				String idPattern = ".+,.+," + authorID + ",+.+";
				if (bookData.get(i).matches(idPattern)) {

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

}
