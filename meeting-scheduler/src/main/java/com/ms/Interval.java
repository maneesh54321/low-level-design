package com.ms;

import java.time.Duration;
import java.time.LocalDateTime;

public record Interval(LocalDateTime dateTime, Duration duration) {

}
