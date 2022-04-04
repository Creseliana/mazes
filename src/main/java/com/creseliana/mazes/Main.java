package com.creseliana.mazes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Valeria Sterzhanova
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        MazeGenerator mazeGenerator = new MazeGeneratorImpl();

        int[][] maze = mazeGenerator.generate(5);

        for (int[] row : maze) {
            System.out.println(Arrays.toString(row));
        }
    }
}
