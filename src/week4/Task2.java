package week4;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command, param;

        LogList logs = new LogList();
        while (true) {
            System.out.print("$ ");
            command = sc.next();
            switch (command) {
                case "read":
                    param = sc.next();
                    logs.init(param);
                    break;
                case "sort":
                    param = sc.next();
                    logs.sortLogs(param);
                    break;
                case "print":
                    logs.printLogs();
                    break;
                case "exit":
                    return;
            }
        }
    }
}
