package org.ms.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryMemberRepository {

	private final List<LibraryMember> members;

	public LibraryMemberRepository() {
		members = new ArrayList<>();
	}

	public void addMember(LibraryMember member) {
		this.members.add(member);
	}

	public Optional<LibraryMember> getMember(String memberId) {
		return members.stream().filter(member -> member.getLibraryCard().id().equals(memberId))
				.findFirst();
	}

	public boolean exists(String name) {
		return members.stream().anyMatch(member -> member.getName().equals(name));
	}
}
