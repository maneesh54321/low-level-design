package com.ms.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CardShuffler {

	private static final Random RANDOM = new Random();

	public List<Card> shuffle(List<Card> cards) {
		List<Card> shuffledCards = new LinkedList<>();
		cards.forEach(card -> {
			int cardIndex = RANDOM.nextInt(52);
			if(shuffledCards.size() > cardIndex) {
				shuffledCards.add(cardIndex, card);
			} else {
				if(RANDOM.nextInt(11) < 5) {
				 shuffledCards.add(card);
				} else {
					shuffledCards.addFirst(card);
				}
			}
		});
		return shuffledCards;
	}
}
