package org.ms.manager.book.fine;

import java.time.LocalDateTime;

public interface FineCalculationStrategy {
	Fine calculateFine(LocalDateTime dueDate, LocalDateTime returnDate);
}
