package com.ms.action;

import com.ms.Game;
import com.ms.card.Card;
import com.ms.player.Player;

public class HitAction implements GameAction {

    private final Game game;

    public HitAction(Game game) {
        this.game = game;
    }

    @Override
    public void execute(Player player) {
        Card card = game.getShoe().takeCard().show();
        System.out.println("Card drawn: \n" + card);
        player.addCard(card);
    }
}
