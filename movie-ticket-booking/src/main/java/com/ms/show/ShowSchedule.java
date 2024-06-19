package com.ms.show;

import com.ms.cinemahall.Screen;
import com.ms.show.price.SeatPriceCatalog;

import java.time.LocalDateTime;

public class ShowSchedule {
    private final Show show;
    private final Screen screen;
    private final Interval interval;
    private SeatPriceCatalog seatPriceCatalog;

    public ShowSchedule(Show show, Screen screen, Interval interval, SeatPriceCatalog seatPriceCatalog) {
        this.show = show;
        this.screen = screen;
        this.interval = interval;
        this.seatPriceCatalog = seatPriceCatalog;
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

    public SeatPriceCatalog getSeatPriceCatalog() {
        return seatPriceCatalog;
    }

    public void setSeatPriceCatalog(SeatPriceCatalog seatPriceCatalog) {
        this.seatPriceCatalog = seatPriceCatalog;
    }

    public int getSeatPrice(int seatNum) {
        return seatPriceCatalog.getSeatPrice(seatNum);
    }

    public boolean overlaps(LocalDateTime time){
        return true;
    }
}
