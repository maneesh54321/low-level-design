package org.example.elevator.request;

import org.example.elevator.Direction;
import org.example.floor.Floor;

public record Request(Floor floor, RequestType type, Direction direction) {
    public static Request createUpRequest(Floor floor){
        return new Request(floor, RequestType.CALL, Direction.UP);
    }

    public static Request createDownRequest(Floor floor){
        return new Request(floor, RequestType.CALL, Direction.DOWN);
    }

    public static Request createStopRequest(Floor floor){
        return new Request(floor, RequestType.STOP, null);
    }
}
