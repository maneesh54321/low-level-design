package org.ms.transaction;

import java.time.LocalDateTime;
import java.util.Objects;
import org.ms.library.LibraryBook;
import org.ms.member.LibraryMember;

public final class BorrowTransaction {

	private final String id;
	private final LibraryMember member;
	private final LibraryBook libraryBook;
	private final LocalDateTime issueDate;
	private final LocalDateTime dueDate;
	private LocalDateTime returnDate;

	public BorrowTransaction(String id, LibraryMember member, LibraryBook libraryBook,
			LocalDateTime issueDate, LocalDateTime dueDate,
			LocalDateTime returnDate) {
		this.id = id;
		this.member = member;
		this.libraryBook = libraryBook;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}

	public String id() {
		return id;
	}

	public LibraryMember member() {
		return member;
	}

	public LibraryBook libraryBook() {
		return libraryBook;
	}

	public LocalDateTime issueDate() {
		return issueDate;
	}

	public LocalDateTime dueDate() {
		return dueDate;
	}

	public LocalDateTime returnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (BorrowTransaction) obj;
		return Objects.equals(this.id, that.id) &&
				Objects.equals(this.member, that.member) &&
				Objects.equals(this.libraryBook, that.libraryBook) &&
				Objects.equals(this.issueDate, that.issueDate) &&
				Objects.equals(this.dueDate, that.dueDate) &&
				Objects.equals(this.returnDate, that.returnDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, member, libraryBook, issueDate, dueDate, returnDate);
	}

	@Override
	public String toString() {
		return "BorrowTransaction[" +
				"id=" + id + ", " +
				"member=" + member + ", " +
				"libraryBook=" + libraryBook + ", " +
				"issueDate=" + issueDate + ", " +
				"dueDate=" + dueDate + ", " +
				"returnDate=" + returnDate + ']';
	}


}
