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

	public int getTotalValue(){
		var aceCount = (int)cards.stream().filter(card -> card.face() == Face.ACE).count();
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		nonAceValue = nonAceValue + (aceCount > 0 ? aceCount - 1 : 0);
		if (aceCount > 0) {
			return nonAceValue > 10 ? nonAceValue + 1 : nonAceValue + 11;
		} else {
			return nonAceValue;
		}
	}

	public boolean isBusted() {
		var aceCount = (int)cards.stream().filter(card -> card.face() == Face.ACE).count();
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		nonAceValue = nonAceValue + (aceCount > 0 ? aceCount - 1 : 0);
		if (aceCount > 0){
			return nonAceValue + 1 > 21;
		} else {
			return nonAceValue > 21;
		}
	}

	public boolean isBlackjack() {
		var aceCount = (int)cards.stream().filter(card -> card.face() == Face.ACE).count();
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		nonAceValue = nonAceValue + (aceCount > 0 ? aceCount - 1 : 0);
		if (aceCount > 0){
			return nonAceValue + 11 == 21 || nonAceValue + 1 == 21;
		} else {
			return nonAceValue == 21;
		}
	}

	public boolean isSoftSeventeen() {
		var aceCount = (int)cards.stream().filter(card -> card.face() == Face.ACE).count();
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		nonAceValue = nonAceValue + (aceCount > 0 ? aceCount - 1 : 0);
		if (aceCount > 0){
			return nonAceValue >= 6;
		}
		return false;
	}

	public boolean isHardSeventeen() {
		var aceCount = (int)cards.stream().filter(card -> card.face() == Face.ACE).count();
		var nonAceValue = cards.stream()
				.filter(card -> card.face() != Face.ACE)
				.mapToInt(card -> card.face().getValue()).sum();
		nonAceValue = nonAceValue + (aceCount > 0 ? aceCount - 1 : 0);
		if (aceCount > 0){
			return nonAceValue + 1 >= 17;
		} else {
			return nonAceValue >= 17;
		}
	}

	@Override
	public String toString() {
		return "Hand{" +
				"cards=" + cards +
				'}';
	}

	public void showAllCards() {
		cards.forEach(Card::show);
	}
}
