package com.creseliana.mazes;

import static com.creseliana.mazes.MazeConst.PATH;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;

/**
 * @author Valeria Sterzhanova
 */
@RequiredArgsConstructor
public class MazeGeneratorImpl implements MazeGenerator {

    private final PathCreator pathCreator;

    @Override
    public int[][] generate(int size) {
        boolean incomplete;
        int[][] maze = new int[size][size];
        int initialColumn = new Random().nextInt(size - 1) + 1;
        List<Direction> directionList = List.of(Direction.values());

        maze[0][initialColumn] = PATH;
        pathCreator.createPath(directionList, maze, 1, initialColumn);

        do {
            incomplete = false;
            for (int i = 1; i < maze.length - 1; i++) {
                for (int j = 1; j < maze[i].length - 1; j++) {
                    if (maze[i][j] != PATH) {
                        continue;
                    }
                    if (pathCreator.createPath(directionList, maze, i, j) && !incomplete) {
                        incomplete = true;
                    }
                }
            }
        } while (incomplete);

        return maze;
    }
}
