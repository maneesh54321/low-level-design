package org.example.floor;

import java.util.Objects;

public class Floor {
    private int floorNo;

    public Floor(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Floor floor = (Floor) o;
        return floorNo == floor.floorNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorNo);
    }

    @Override
    public String toString() {
        return "Floor{" +
            "floorNo=" + floorNo +
            '}';
    }
}
