package com.ms;

import java.time.Duration;
import java.time.LocalDateTime;

public record Interval(LocalDateTime startTime, Duration duration) {
    public boolean overlaps(Interval interval) {
        var thisEndTime = this.startTime.plusMinutes(duration.toMinutes());
        if(interval.startTime.isAfter(startTime) && interval.startTime.isBefore(thisEndTime)) {
            return true;
        }
        var inputEndTime = interval.startTime.plusMinutes(duration.toMinutes());
        return inputEndTime.isAfter(startTime) && inputEndTime.isBefore(thisEndTime);
    }
}
