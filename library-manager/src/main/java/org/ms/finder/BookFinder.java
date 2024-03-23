package org.ms.finder;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.ms.library.Book;
import org.ms.library.BookRepository;
import org.ms.library.LibraryBook;
import org.ms.library.Rack;

public class BookFinder {

	private final BookRepository bookRepository;

	public BookFinder(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> search(Book searchCriteria) {
		return bookRepository.search(searchCriteria).stream().map(LibraryBook::getBook).distinct()
				.collect(Collectors.toList());
	}

	public LibrarySearchResult find(Book book) {
		List<LibraryBook> books = bookRepository.find(book);
		int totalQty = books.size();
		int availableQty = 0;
		var racks = new HashSet<Rack>();
		for (LibraryBook libraryBook: books) {
			if(libraryBook.isIssued()) availableQty++;
			racks.add(libraryBook.getRack());
		}
		if(availableQty == 0) {
			System.out.println("No copies are available for issue!!!");
		}
		return new LibrarySearchResult(book, racks, totalQty, availableQty);
	}
}
