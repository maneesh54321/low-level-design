## UseCase - Play Game
### Actors: Player, Dealer
### Preconditions:
### Steps:

1. System starts the game.
2. Each player place bet on table. 
3. Dealer hands one face up card to each player and himself.
4. Dealer hands one face up card to each player and hole card to himself.
5. Player on the left of dealer take turn.
6. System checks player's card.
   1. If player has a blackjack, then System declares player win.
   2. System removes the player from game.
   3. Goto step 10.
7. Player stands.
    1. Player hits.
    2. System checks Player's card.
    3. If player has been handed a blackjack, then System declares player win.
    4. System removes the player from game.
    5. If player is busted, then System declares player lose. 
    6. System removes the Player from game.
    7. If Player has neither received a blackjack nor busted, then repeat step 6.
8. Player left to current player gets turn and step 5-10 is repeated until there is no player left.
9. Dealer takes turn.
10. Dealer reveals the hole card.
11. System checks Dealer's card.
12. Dealer has total value less than 17.
    * Dealer is bust.
    * System declares all players win.
    * System ends the game.
    * Dealer has total value more than or equal to 17.
    * Dealer stands.
    * System checks the cards of each player in game.
      * If player has total value more than Dealer, System declares player win. 
      * If player has total value less than Dealer, System declares player lose.
      * If player has total value equal to Dealer, System declares tie.
    * System ends the Game.
13. Dealer hits.
14. Repeat step 10-13 until Dealer bust or has total value greater than or equal to 17.

### Nouns
System, Game, Player, Bet, Table, Dealer, Face up card, Hole card, Turn, Card, BlackJack, Bust, Total value.
### Verbs
start game, place bet, hand card, take turn, check card, declare player win/lose, remove player from game, stands, 
hits, reveal hole card, end game
