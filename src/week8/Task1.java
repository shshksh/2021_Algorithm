package week8;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        LocationGraph graph = new LocationGraph();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            switch (command) {
                case "path":
                    graph.path(sc.nextLine(), sc.nextLine());
                    break;
                case "exit":
                    return;
            }
        }
    }

}
