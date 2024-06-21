### Use case

Search Movie

#### Actors

Customer

#### Steps

1. Customer searches for a movie in city with title with optional filters of genre, language,
   release date.
2. System presents customer with search result matching the title and filters.
3. Customer selects a movie from the search result.
4. System shows all the details of the movie and presents Customer with option to book ticket.
5. Customer chooses to book ticket.
6. System shows all the cinema halls which are showing the movie along with show schedules.

#### Nouns

Customer, movie, city, title, filters, genre, language, release date, System, search result,
details of the movie, ticket, cinema halls, show schedules

#### Verbs

searches, presents, selects, shows, chooses

***

### UseCase

Book Movie Ticket

#### Actor

Customer, CinemaHall Agent

#### Preconditions

Customer has searched for a show and already selected to book tickets for a scheduled show

#### Steps

1. System asks Customer to enter mobile number and optional email.
2. Customer enters mobile number and email.
3. System creates a session for the ticket booking.
4. System presents customer with the seating arrangement of the selected scheduled show.
5. Customer chooses seats and proceed to checkout.
6. System reserves the selected seats.
7. System calculates the final price.
8. System presents customer with final price and multiple payment options.
9. Customer chooses one payment option and pay the amount.
10. System creates a booking and allocates the selected seats to the booking.
11. System generates a ticket for the booking.
12. System sends the ticket to the mobile number and email stored in session.

#### Nouns

Customer, System, scheduled show, seating arrangement, selected seats, payment, final price, 
payment options, payment option, amount, booking, ticket, mobile number and email

#### Verbs

presents, chooses, asks, enters, reserves, calculates, completes, creates, allocates, generates,
sends
