package org.ms.library;

public record Author(String name) {

	public boolean matches(Author author) {
		return author.name() == null || this.name.contains(author.name());
	}
}
