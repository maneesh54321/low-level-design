package com.learning.board;

import com.learning.units.Obstacle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcreteBoard implements BuildableBoard, Board {

  private final int size;

  private final List<Obstacle> obstacles;

  public ConcreteBoard(int size) {
    this.size = size;
    this.obstacles = new ArrayList<>();
  }

  @Override
  public Optional<Obstacle> getObstacle(int position) {
    return obstacles.stream().filter(obstacle -> obstacle.isApplicable(position)).findFirst();
  }

  @Override
  public int getLastTilePosition() {
    return size * size;
  }

  @Override
  public boolean isValidPosition(int position) {
    return position <= getLastTilePosition() && position > 0;
  }

  @Override
  public void addObstacle(Obstacle obstacle) {
    assert obstacle != null;
    this.obstacles.add(obstacle);
  }
}
