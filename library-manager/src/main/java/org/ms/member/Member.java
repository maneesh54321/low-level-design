package org.ms.member;

public record Member(LibraryCard card, String name) implements LibraryMember {

	@Override
	public String getName() {
		return name;
	}

	@Override
	public LibraryCard getLibraryCard() {
		return card;
	}
}
