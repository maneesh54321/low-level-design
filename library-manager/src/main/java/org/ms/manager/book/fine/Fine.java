package org.ms.manager.book.fine;

import java.time.LocalDateTime;

public record Fine(float amount, LocalDateTime createdAt) {
}
