# Library Management System (LMS)

### Feature List
1. Register librarian.
2. Track number of books in the library.
3. Records of new books can be added by librarian.
4. Register library members.
5. Books can be borrowed with due dates by members.
6. Books can be returned by members.
7. Notify library members for returning a book after due date.
8. Calculate fine on late returned books.
9. Renew a borrowed book.
10. Book an unavailable book & get notified when the book is available.
11. Search for a book's availability.
12. Search for a book's position in library.
13. Load a complete library in library management system.
14. UnRegister library members and librarian.

# Use cases

### Search Book
#### Actor
Library Member or Librarian
#### Preconditions
Member is on search screen of Library Browser Kiosk.
#### Steps
1. Member enters values in book name, author name or genre.
2. Member clicks search.
3. List of books matching the above criteria with current availability shows up on screen.

### Find Book
#### Actor
Library Member or Librarian
#### Preconditions
Member has been presented with list of books according to the search criteria and is interested in 
one of the book in the result.
#### Steps
1. Member clicks a book in search result.
2. Kiosk shows the rack numbers where book is located in library.
   1. Kiosk shows "No copies are available for issue" on screen.

### Librarian Login
#### Actor
Librarian
#### Preconditions
Librarian is on login screen of kiosk.
#### Steps
1. Librarian enters his/her library card id.
2. Librarian enters his/her password.
3. Librarian clicks login.
4. System validates the credentials.
    1. System shows a message "Invalid credentials".
5. System creates a login session for the Librarian.

#### Nouns
Librarian, Library Card id, Password, Login, Credentials, message, Login Session
#### Verbs
enters, clicks, validates, creates

### Add Library Member
#### Actor
Librarian
#### Preconditions
Librarian is logged in and is on add member screen.
#### Steps
1. Librarian enters new member's name.
2. Librarian clicks add member.
3. System validates the login session.
4. System validates if the member is not an existing member.
   1. System shows a message "The name entered is already a member".
5. System shows a message "Member registered!!" & generates a Library card.

#### Nouns
Librarian, New Member's name, System, Existing Member, Library Card

#### Verbs
enters, clicks, validates, shows, presents, generates


### Borrow Book
#### Actor
Library Member
#### Preconditions
Member has found the book & standing at the checkout kiosk
#### Steps
1. Member enters his/her library card id.
2. Member enters the id of each book he/she wants to borrow.
3. Member clicks borrow book.
4. System validates if the user is a Library member.
   1. Kiosk shows error "User is not registered in the library".
5. System marks the book as unavailable for issue.
6. System records the transaction with member, books, issue date & due date for each book.
7. Kiosk displays Member id, name, list of books borrowed and their due date.
8. Kiosk generates a receipt.
9. Member shows the receipt at the gate to security guard to sign off.

#### Nouns:
Member, library card, book, System, Transaction, issue date, due date.
#### Verbs:
enters, borrow, displays, records, validates

### Return Book
#### Actor
Library Member
#### Preconditions
Member has book for return and standing at the kiosk
#### Steps
1. Member enters the book id.
2. Repeat step 2 for each book.
3. Member clicks return.
4. System verifies if the books belongs to library.
5. System calculates total fine if applicable.
6. System records a return of the book with return date.

