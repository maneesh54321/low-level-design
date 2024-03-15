package org.ms.elevator.core;

public record Request(int floor, Direction direction, RequestType type) {
}
