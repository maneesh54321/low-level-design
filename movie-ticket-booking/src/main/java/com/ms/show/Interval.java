package com.ms.show;

import java.time.Duration;
import java.time.LocalDateTime;

public record Interval(LocalDateTime startTime, Duration duration) {
}
