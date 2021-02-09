package week7;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        LocationGraph graph = new LocationGraph();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();
            sc.nextLine();
            switch (command) {
                case "mst":
                    graph.mst();
                    break;
                case "exit":
                    return;
            }
        }
    }

}
