package com.ms.show.booking;

import com.ms.cinemahall.CinemaHall;
import com.ms.cinemahall.Screen;
import com.ms.cinemahall.Seat;
import com.ms.show.ShowSchedule;
import com.ms.show.booking.payment.Payment;
import com.ms.show.booking.payment.PaymentReceipt;
import com.ms.show.booking.payment.PaymentStatus;
import com.ms.show.booking.user.Session;
import com.ms.show.booking.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TicketBookingService {

	private final Map<Session, Reservation> sessionReservationMap;

	public TicketBookingService() {
		this.sessionReservationMap = new HashMap<>();
	}

	public Session createSession(String phoneNo, String emailId) {
		return new Session(UUID.randomUUID().toString(), new User(phoneNo, emailId));
	}

	public Reservation selectSeats(CinemaHall cinemaHall, Screen screen,
			ShowSchedule showSchedule, List<Seat> seats, Session session) {
		SeatReservation seatReservation = showSchedule.reserve(seats);
		var reservation = new Reservation(cinemaHall, screen, showSchedule, seatReservation);
		sessionReservationMap.put(session, reservation);
		return reservation;
	}

	public Payment calculatePayment(Reservation reservation, Session session) {
		var totalAmount = reservation.seatReservation().seats().stream()
				.map(seat -> reservation.showSchedule().getSeatPrice(seat.seatNum()))
				.reduce(0, Integer::sum);
		return new Payment(UUID.randomUUID().toString(), totalAmount);
	}

	public Booking bookSeats(Session session, PaymentReceipt receipt) {
		// validate if the payment is successful
		// if payment has failed, throw exception
		if(receipt.paymentStatus() == PaymentStatus.FAILED)
			throw new RuntimeException("Payment has failed!!!");
		// find the reservation using session
		var reservation = sessionReservationMap.get(session);
		// convert the reservation to booking
		return new Booking(reservation.cinemaHall(), reservation.screen(),
				reservation.showSchedule(), session.user());
	}
}
