package org.ms.library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {

	private final List<LibraryBook> books;

	public BookRepository() {
		books = new ArrayList<>();
	}

	public void addBook(LibraryBook book) {
		books.add(book);
	}

	public List<LibraryBook> search(Book searchCriteria) {
		return books.stream().filter(libraryBook -> libraryBook.getBook().matches(searchCriteria))
				.collect(Collectors.toList());
	}

	public List<LibraryBook> find(Book book) {
		return books.stream().filter(libraryBook -> libraryBook.getBook().equals(book))
				.collect(Collectors.toList());
	}

	public LibraryBook getBook(String libraryBookId) {
		return books.stream().filter(libraryBook -> libraryBook.getId().equals(libraryBookId))
				.findFirst().orElseThrow();
	}

	public boolean isValidBook(String libraryBookId) {
		return books.stream().anyMatch(book -> book.getId().equals(libraryBookId));
	}
}
