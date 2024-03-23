package org.ms.library;

public class LibraryBook {
	private final String id;

	private final Book book;

	private final Rack rack;

	private boolean issued;

	public LibraryBook(String id, Book book, Rack rack) {
		this.id = id;
		this.book = book;
		this.rack = rack;
	}

	public String getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public Rack getRack() {
		return rack;
	}

	public boolean isIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}
}
