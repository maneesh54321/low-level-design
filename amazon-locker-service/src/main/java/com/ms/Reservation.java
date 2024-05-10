package com.ms;

import java.time.LocalDateTime;

public record Reservation(Order order, LocalDateTime time, Item item) {
}
