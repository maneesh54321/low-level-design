package org.ms.library;

public record Book(String title, String genre, int publicationYear, String isbn, Author author) {

	public boolean matches(Book book) {
		if(book.title() != null && !this.title.contains(book.title())){
			return false;
		}
		if(book.genre() != null && !this.genre.contains(book.genre())){
			return false;
		}
		return book.author() == null || this.author.matches(book.author());
	}
}
