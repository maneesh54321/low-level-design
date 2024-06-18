package com.ms.show;

import com.ms.cinemahall.Screen;
import com.ms.price.SeatPriceConfiguration;

public class ShowSchedule {
    private final Show show;
    private final Screen screen;
    private final Interval interval;
    private SeatPriceConfiguration seatPriceConfiguration;

    public ShowSchedule(Show show, Screen screen, Interval interval, SeatPriceConfiguration seatPriceConfiguration) {
        this.show = show;
        this.screen = screen;
        this.interval = interval;
        this.seatPriceConfiguration = seatPriceConfiguration;
    }

    public Show getShow() {
        return show;
    }

    public Screen getScreen() {
        return screen;
    }

    public Interval getInterval() {
        return interval;
    }

    public SeatPriceConfiguration getSeatPriceConfiguration() {
        return seatPriceConfiguration;
    }

    public void setSeatPriceConfiguration(SeatPriceConfiguration seatPriceConfiguration) {
        this.seatPriceConfiguration = seatPriceConfiguration;
    }
}
