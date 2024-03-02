package com.learning.dice;

import java.util.Random;

public class BasicDice implements Dice {

  private static final Random RANDOM = new Random();

  @Override
  public int roll() {
    return RANDOM.nextInt(1, 7);
  }
}
