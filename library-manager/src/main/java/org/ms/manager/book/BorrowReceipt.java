package org.ms.manager.book;

import java.time.LocalDateTime;

public record BorrowReceipt(String memberId, String memberName, String[] bookIds,
                            LocalDateTime issueDateTime) {
}
