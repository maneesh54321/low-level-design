package org.ms.transaction;

import java.util.List;
import java.util.Optional;

public class BorrowTransactionRepository {
	private final List<BorrowTransaction> transactions;

	public BorrowTransactionRepository(List<BorrowTransaction> transactions) {
		this.transactions = transactions;
	}

	public void saveTransaction(BorrowTransaction borrowTransaction){
		this.transactions.add(borrowTransaction);
	}

	public Optional<BorrowTransaction> findTransaction(String bookId) {
		return this.transactions.stream().filter(transaction -> transaction.libraryBook().getId().equals(bookId)).findFirst();
	}
}
