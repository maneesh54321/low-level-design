package com.ms.show.booking;

import com.ms.cinemahall.CinemaHall;
import com.ms.cinemahall.Seat;
import com.ms.show.ShowSchedule;
import com.ms.show.booking.payment.gateway.PaymentGateway;
import com.ms.show.booking.printer.BookingPrinter;
import com.ms.show.booking.printer.Ticket;
import java.util.List;
import java.util.Scanner;

public class TicketBookingController {

	private final TicketBookingService ticketBookingService;

	private final PaymentGateway paymentGateway;

	private final BookingPrinter printer;

	public TicketBookingController(TicketBookingService ticketBookingService,
			PaymentGateway paymentGateway, BookingPrinter printer) {
		this.ticketBookingService = ticketBookingService;
		this.paymentGateway = paymentGateway;
		this.printer = printer;
	}

	public Ticket book(CinemaHall cinemaHall, ShowSchedule showSchedule, List<Seat> seats) {
		try(Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter phone no:");
			String phoneNo = sc.nextLine();
			System.out.println("Enter email address:");
			String email = sc.nextLine();
			var session = ticketBookingService.createSession(phoneNo, email);
			var reservation = ticketBookingService.selectSeats(cinemaHall, showSchedule.getScreen(),
					showSchedule, seats, session);
			var payment = ticketBookingService.calculatePayment(reservation, session);
			var paymentReceipt = paymentGateway.pay(payment);
			var booking = ticketBookingService.bookSeats(session, paymentReceipt);
			return printer.printBooking(booking);
		}
	}
}
