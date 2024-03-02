package com.learning.board;

import com.learning.units.Obstacle;
import java.util.Optional;

public interface Board {
  Optional<Obstacle> getObstacle(int position);

  int getLastTilePosition();

  boolean isValidPosition(int position);
}
