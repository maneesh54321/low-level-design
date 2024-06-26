package com.ms.show;

import com.ms.cinemahall.Seat;
import com.ms.show.booking.SeatBooking;
import com.ms.show.booking.SeatReservation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class ShowScheduleBookings {

	private final ShowSchedule showSchedule;

	private final Map<Seat, SeatReservation> seatReservations;

	private final Map<Seat, SeatBooking> seatBookings;

	public ShowScheduleBookings(ShowSchedule showSchedule) {
		this.showSchedule = showSchedule;
		this.seatReservations = new HashMap<>();
		this.seatBookings = new HashMap<>();
	}

	public SeatReservation reserve(List<Seat> seats) {
		// validate if the seats are valid seats
		// validate if the seats are available
		for (Seat seat: seats) {
			// throw exception if not available
			if(!showSchedule.getScreen().hasSeat(seat)
					|| seatReservations.containsKey(seat)
					|| seatBookings.containsKey(seat)) {
				throw new RuntimeException("Seats are occupied!!!");
			}
		}
		// reserve the seats if available
		var seatReservation = new SeatReservation(UUID.randomUUID().toString(), seats);
		seats.forEach(seat -> seatReservations.put(seat, seatReservation));
		return seatReservation;
	}

	public SeatBooking finalizeReservation(SeatReservation seatReservation) {
		// check if the reservation is a valid reservation
		if(seatReservations.containsValue(seatReservation)) {
			// convert the reservation to booking if valid reservation
			SeatBooking seatBooking = new SeatBooking(seatReservation.id(), seatReservation.seats());
			seatReservation.seats().forEach(seat -> {
				seatBookings.put(seat, seatBooking);
				seatReservations.remove(seat);
			});
			return seatBooking;
		}
		// throw exception if reservation is invalid
		throw new RuntimeException("Invalid reservation!!!");
	}

}
