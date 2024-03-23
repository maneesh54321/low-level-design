package org.ms.manager.book;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.ms.library.BookRepository;
import org.ms.library.LibraryBook;
import org.ms.manager.book.fine.Fine;
import org.ms.manager.book.fine.FineCalculationStrategy;
import org.ms.manager.exception.InvalidBookException;
import org.ms.manager.exception.InvalidMemberException;
import org.ms.member.LibraryMemberRepository;
import org.ms.transaction.BorrowTransaction;
import org.ms.transaction.BorrowTransactionRepository;
import org.ms.utils.Constants;
import org.ms.utils.UniqueIdUtil;

public class BookManager {

	private final BookRepository bookRepository;

	private final LibraryMemberRepository libraryMemberRepository;

	private final BorrowTransactionRepository transactionRepository;

	private final FineCalculationStrategy fineCalculationStrategy;

	public BookManager(BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository,
			BorrowTransactionRepository transactionRepository,
			FineCalculationStrategy fineCalculationStrategy) {
		this.bookRepository = bookRepository;
		this.libraryMemberRepository = libraryMemberRepository;
		this.transactionRepository = transactionRepository;
		this.fineCalculationStrategy = fineCalculationStrategy;
	}

	public BorrowReceipt borrowBook(String memberId, String[] bookIds) {
		LocalDateTime issueDateTime = LocalDateTime.now();
		LocalDateTime dueDateTime = issueDateTime.plusDays(Constants.MAX_BORROW_DAYS);
		return libraryMemberRepository.getMember(memberId).map(member -> {
			var borrowReceipt = new BorrowReceipt(member.getLibraryCard().id(), member.getName(), bookIds,
					issueDateTime);
			Arrays.asList(bookIds).forEach(bookId -> {
				LibraryBook libraryBook = this.bookRepository.getBook(bookId);
				// mark the book as issued
				libraryBook.setIssued(true);
				// Record a transaction
				BorrowTransaction borrowTransaction = new BorrowTransaction(UniqueIdUtil.generateUniqueId(),
						member, libraryBook, issueDateTime, dueDateTime, LocalDateTime.MAX);
				transactionRepository.saveTransaction(borrowTransaction);
			});
			return borrowReceipt;
		}).orElseThrow(() -> new InvalidMemberException("Member is not registered in library!!"));
	}

	public Fine returnBook(String[] bookIds) {
		LocalDateTime returnDate = LocalDateTime.now();
		// validate books are valid & belong to Library
		boolean allBooksValid = Arrays.stream(bookIds).allMatch(bookRepository::isValidBook);
		if (!allBooksValid) {
			throw new InvalidBookException("Invalid book!!");
		}
		var transactions = Arrays.stream(bookIds).map(bookId -> {
			// find the corresponding borrow transaction and add return date.
			return transactionRepository.findTransaction(bookId).map(txn -> {
				// calculate fine for the book
				Fine fine = fineCalculationStrategy.calculateFine(txn.dueDate(), returnDate);
				txn.setReturnDate(returnDate);
				return fine;
			}).orElseThrow();
		}).toList();
		float totalAmt =  transactions.stream().reduce(0f, (aFloat, fine) -> aFloat + fine.amount(), Float::sum);
		return new Fine(totalAmt, returnDate);
	}
}
