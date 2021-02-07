package week6;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        LocationGraph graph = new LocationGraph();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();
            sc.nextLine();
            switch (command) {
                case "bfs":
                    graph.bfs(sc.nextLine());
                    break;
                case "dfs":
                    graph.dfs(sc.nextLine());
                    break;
                case "exit":
                    return;
            }
        }
    }

}
