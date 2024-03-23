package org.ms.member;

public record Librarian(String name, LibraryCard card, String password) implements LibraryMember,
		LoginCapable {

	@Override
	public String getName() {
		return name;
	}

	@Override
	public LibraryCard getLibraryCard() {
		return card;
	}

	@Override
	public String getPassword() {
		return password;
	}
}
