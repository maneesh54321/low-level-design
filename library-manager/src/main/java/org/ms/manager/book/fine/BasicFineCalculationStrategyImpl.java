package org.ms.manager.book.fine;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BasicFineCalculationStrategyImpl implements FineCalculationStrategy {

	private static final float FINE_PER_DAY = 10;

	@Override
	public Fine calculateFine(LocalDateTime dueDate, LocalDateTime returnDate) {
		if (returnDate.isBefore(dueDate) || returnDate.isEqual(dueDate)) {
			return new Fine(0, returnDate);
		}
		float fineAmt = Duration.between(returnDate, dueDate).get(ChronoUnit.DAYS) * FINE_PER_DAY;

		return new Fine(fineAmt, returnDate);
	}
}
