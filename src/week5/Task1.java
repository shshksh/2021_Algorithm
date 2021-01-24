package week5;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command, param;

        AddressTree tree = new AddressTree();
        while (true) {
            System.out.print("$ ");
            command = sc.next();
            switch (command) {
                case "read":
                    param = sc.next();
                    tree.init(param);
                    break;
                case "list":
                    tree.print();
                    break;
                case "find":
                    param = sc.next();
                    tree.find(param);
                    break;
                case "trace":
                    param = sc.next();
                    tree.trace(param);
                    break;
                case "delete":
                    param = sc.next();
                    tree.delete(param);
                    break;
                case "save":
                    param = sc.next();
                    tree.saveAs(param);
                    break;
                case "exit":
                    return;
            }
        }
    }
}