package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("res/week2/input1.txt");
        Scanner sc = new Scanner(file);

        int testCases = sc.nextInt();

        for (int i = 0; i < testCases; i++) {
            Maze maze = new Maze(sc);
            System.out.println(maze.countOfPath(0, 0, 0));
        }
    }

    static class Maze {

        String[][] maze;
        int k;
        int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public Maze(Scanner sc) {
            int length = sc.nextInt();
            sc.nextLine();
            maze = new String[length][length];

            for (int i = 0; i < length; i++) {
                maze[i] = sc.nextLine().split(" ");
            }

            k = sc.nextInt();
        }

        public int countOfPath(int x, int y, int length) {
            if (!isValid(x, y, length))
                return 0;
            if (x == maze.length - 1 && y == maze.length - 1)
                return 1;

            int count = 0;
            maze[x][y] = "2";
            for (int i = 0; i < 4; i++) {
                count += countOfPath(x + offset[i][0], y + offset[i][1],
                        length + 1);
            }
            maze[x][y] = "0";
            return count;
        }

        public boolean isValid(int x, int y, int length) {
            if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length
                    || !maze[x][y].equals("0")
                    || length > k) {
                return false;
            }
            return true;
        }

    }
    
}