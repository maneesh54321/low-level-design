package com.learning.loader;

import com.learning.board.BuildableBoard;
import com.learning.board.ConcreteBoard;
import com.learning.units.Ladder;
import com.learning.units.Snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BoardLoader {
  public BuildableBoard createBoard(InputStream inputStream) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    String line = bufferedReader.readLine();
    int boardSize = Integer.parseInt(line);
    BuildableBoard board = new ConcreteBoard(boardSize);
    int noOfLadders = Integer.parseInt(bufferedReader.readLine());
    while (noOfLadders-- > 0) {
      line = bufferedReader.readLine();
      String[] nodes = line.split(" ");
      board.addObstacle(new Ladder(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1])));
    }
    int noOfSnakes = Integer.parseInt(bufferedReader.readLine());
    while (noOfSnakes-- > 0) {
      line = bufferedReader.readLine();
      String[] nodes = line.split(" ");
      board.addObstacle(new Snake(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1])));
    }
    return board;
  }
}
