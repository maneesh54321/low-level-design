package com.ms.show;

import com.ms.cinemahall.Screen;
import com.ms.cinemahall.Seat;
import com.ms.show.booking.SeatBooking;
import com.ms.show.booking.SeatReservation;
import com.ms.show.price.SeatPriceCatalog;
import java.time.LocalDateTime;
import java.util.List;

public class ShowSchedule {

	private final Show show;
	private final Screen screen;
	private final Interval interval;
	private SeatPriceCatalog seatPriceCatalog;
	private final ShowScheduleBookings showScheduleBookings;

	public ShowSchedule(Show show, Screen screen, Interval interval,
			SeatPriceCatalog seatPriceCatalog) {
		this.show = show;
		this.screen = screen;
		this.interval = interval;
		this.seatPriceCatalog = seatPriceCatalog;
		this.showScheduleBookings = new ShowScheduleBookings(this);
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

	public boolean overlaps(LocalDateTime time) {
		return interval.overlapsWith(time);
	}

	public SeatReservation reserve(List<Seat> seats) {
		return this.showScheduleBookings.reserve(seats);
	}

	public SeatBooking finalizeBooking(SeatReservation seatReservation) {
		return this.showScheduleBookings.finalizeReservation(seatReservation);
	}

	@Override
	public String toString() {
		return "ShowSchedule{" +
				"show=" + show +
				", interval=" + interval +
				'}';
	}
}
