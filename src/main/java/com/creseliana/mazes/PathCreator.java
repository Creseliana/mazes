package com.creseliana.mazes;

import java.util.List;

public interface PathCreator {

    boolean createPath(List<Direction> directionList, int[][] maze, int row, int column);
}
