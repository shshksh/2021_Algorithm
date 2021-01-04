package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("res/week2/input2.txt");
        Scanner sc = new Scanner(file);

        int testCases = sc.nextInt();

        for (int i = 0; i < testCases; i++) {
            Maze maze = new Maze(sc);
            System.out.println(maze.isReachable(0, 0, new int[]{-1, 0}));
        }
    }

    static class Maze {

        String[][] maze;
        int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public Maze(Scanner sc) {
            int length = sc.nextInt();
            sc.nextLine();
            maze = new String[length][length];

            for (int i = 0; i < length; i++) {
                maze[i] = sc.nextLine().split(" ");
            }
        }

        public boolean isReachable(int x, int y, int[] direction) {
            if (!isValid(x, y))
                return false;

            int oldX = x;
            int oldY = y;

            maze[x][y] = "2";
            while (isValid(x + direction[0], y + direction[1])) {
                x += direction[0];
                y += direction[1];
                maze[x][y] = "2";
            }

            if (x == maze.length - 1 && y == maze.length - 1)
                return true;

            if (!(isInbound(x + direction[0], y + direction[1])
                    && maze[x + direction[0]][y + direction[1]].equals("2"))) {
                for (int i = 0; i < 4; i++) {
                    if (isReachable(x + offset[i][0], y + offset[i][1], offset[i]))
                        return true;
                }
            }

            while (x != oldX || y != oldY) {
                maze[x][y] = "0";
                x -= direction[0];
                y -= direction[1];
            }
            maze[x][y] = "0";
            return false;
        }

        public boolean isValid(int x, int y) {
            if (isInbound(x, y) && maze[x][y].equals("0")) {
                return true;
            }
            return false;
        }

        public boolean isInbound(int x, int y) {
            return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length;
        }

    }

}
