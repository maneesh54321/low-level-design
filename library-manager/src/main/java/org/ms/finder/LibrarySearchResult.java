package org.ms.finder;

import java.util.Objects;
import java.util.Set;
import org.ms.library.Book;
import org.ms.library.Rack;

public record LibrarySearchResult(Book book, Set<Rack> racks, int totalQty, int availableQty) {
	public LibrarySearchResult {
		Objects.requireNonNull(book);
	}
}
