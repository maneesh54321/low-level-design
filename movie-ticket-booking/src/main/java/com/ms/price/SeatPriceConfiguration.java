package com.ms.price;

import com.ms.cinemahall.SeatingConfiguration;

import java.util.ArrayList;
import java.util.List;

public class SeatPriceConfiguration {
    private final SeatingConfiguration seatingConfiguration;
    private final List<SeatPrice> seatPrices;

    public SeatPriceConfiguration(SeatingConfiguration seatingConfiguration) {
        this.seatingConfiguration = seatingConfiguration;
        this.seatPrices = new ArrayList<>();
    }

    public void setSeatPrice(String seatLevel, String seatType, int amount) {
        this.seatPrices.add(new SeatPrice(seatLevel, seatType, amount));
    }
}
