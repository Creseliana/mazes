package com.creseliana.mazes;

import static com.creseliana.mazes.MazeConst.PATH;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PathCreatorImpl implements PathCreator {

    @Override
    public boolean createPath(List<Direction> directionList, int[][] maze, int row, int column) {
        Random random = new Random();
        List<Direction> availableDirectionList = new ArrayList<>();
        maze[row][column] = PATH;

        do {
            availableDirectionList.clear();
            calculateAvailableDirections(availableDirectionList, directionList, maze, row, column);

            if (availableDirectionList.isEmpty()) {
                return false;
            }

            Direction randomDirection = availableDirectionList.get(
                random.nextInt(availableDirectionList.size()));

            row += randomDirection.getRow();
            column += randomDirection.getColumn();

            maze[row][column] = PATH;
        } while (!isMazeBorder(maze.length, row, column));

        return true;
    }

    private void calculateAvailableDirections(List<Direction> availableDirectionList,
        List<Direction> directionList, int[][] maze, int row, int column) {
        int nextRow, nextColumn;

        for (Direction direction : directionList) {
            nextRow = row + direction.getRow();
            nextColumn = column + direction.getColumn();

            if (existsInMazeBorders(maze.length, nextRow, nextColumn)
                && maze[nextRow][nextColumn] != PATH
                && !hasNearPath(directionList, maze, nextRow, nextColumn)) {

                availableDirectionList.add(direction);
            }
        }
    }

    private boolean existsInMazeBorders(int size, int nextRow, int nextColumn) {
        return nextRow >= 1 && nextColumn >= 0 && nextRow < size && nextColumn < size;
    }

    private boolean isMazeBorder(int size, int row, int column) {
        return row == 0 || row == size - 1 || column == 0 || column == size - 1;
    }

    private boolean hasNearPath(List<Direction> directionList, int[][] maze, int row, int column) {
        return directionList.stream()
            .filter(direction -> {
                int neighbourRow = row + direction.getRow();
                int neighbourColumn = column + direction.getColumn();
                return existsInMazeBorders(maze.length, neighbourRow, neighbourColumn)
                    && maze[neighbourRow][neighbourColumn] == PATH;
            }).count() != 1;
    }
}
