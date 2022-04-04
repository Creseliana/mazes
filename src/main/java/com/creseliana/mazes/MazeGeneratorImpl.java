package com.creseliana.mazes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Valeria Sterzhanova
 */
public class MazeGeneratorImpl implements MazeGenerator {

    private static final int PATH = 1;

    @Override
    public int[][] generate(int size) {
        boolean incomplete;
        List<Direction> directionList = List.of(Direction.values());
        int[][] maze = new int[size][size];
        maze[0][3] = PATH; //todo initial coordinates
        pavePath(directionList, maze, 1, 3); //todo initial coordinates

        do {
            incomplete = pavePaths(maze, directionList);
        } while (incomplete);

        return maze;
    }

    private boolean pavePath(List<Direction> directionList, int[][] maze, int row, int column) {
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

//            //todo check
//            if (isBorder(maze.length, row, column) && !isBottomBorder(maze.length, row)) {
//                continue;
//            }
            maze[row][column] = PATH;
            System.out.println(row + "; " + column);
        } while (!isBorder(maze.length, row, column));

        return true;
    }

    private boolean existsInBounds(int size, int nextRow, int nextColumn) {
        return nextRow >= 1 && nextColumn >= 0 && nextRow < size && nextColumn < size;
    }

    private boolean isBorder(int size, int row, int column) {
        return row == 0 || row == size - 1 || column == 0 || column == size - 1;
    }

    private void calculateAvailableDirections(List<Direction> availableDirectionList,
        List<Direction> directionList, int[][] maze, int row, int column) {
        int nextRow, nextColumn;

        for (Direction direction : directionList) {
            nextRow = row + direction.getRow();
            nextColumn = column + direction.getColumn();

            if (existsInBounds(maze.length, nextRow, nextColumn)
                && maze[nextRow][nextColumn] != PATH) {
                availableDirectionList.add(direction);
            }
        }
    }

    private boolean pavePaths(int[][] maze, List<Direction> directionList) {
        boolean incomplete = false;

        for (int i = 1; i < maze.length - 1; i++) {
            for (int j = 1; j < maze[i].length - 1; j++) {
                if (maze[i][j] != PATH) {
                    continue;
                }
                boolean pathPaved = pavePath(directionList, maze, i, j);
                if (pathPaved && !incomplete) {
                    incomplete = true;
                }
            }
        }
        return incomplete;
    }
}
