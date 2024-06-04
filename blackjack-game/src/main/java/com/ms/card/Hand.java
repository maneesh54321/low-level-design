package com.ms.card;

import java.util.List;

public class Hand {

	private final List<Card> cards;

	public Hand(List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public void addCards(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public int getTotalValue(){
		var hasAce = cards.stream().anyMatch(card -> card.face() == Face.ACE);
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		if (hasAce) {
			return nonAceValue > 10 ? nonAceValue + 1 : nonAceValue + 11;
		} else {
			return nonAceValue;
		}
	}

	public boolean isBusted() {
		var hasAce = cards.stream().anyMatch(card -> card.face() == Face.ACE);
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		if (hasAce){
			return nonAceValue + 1 > 21;
		} else {
			return nonAceValue > 21;
		}
	}

	public boolean isBlackjack() {
		var hasAce = cards.stream().anyMatch(card -> card.face() == Face.ACE);
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		if (hasAce){
			return nonAceValue + 11 == 21 || nonAceValue + 1 == 21;
		} else {
			return nonAceValue == 21;
		}
	}

	public boolean isSoftSeventeen() {
		var hasAce = cards.stream().anyMatch(card -> card.face() == Face.ACE);
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		if(hasAce) {
			return nonAceValue >= 6;
		}
		return false;
	}

	public boolean isHardSeventeen() {
		var hasAce = cards.stream().anyMatch(card -> card.face() == Face.ACE);
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		if(hasAce) {
			return nonAceValue + 1 >= 17;
		} else {
			return nonAceValue >= 17;
		}
	}
}
