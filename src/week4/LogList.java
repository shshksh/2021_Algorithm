package week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LogList {
    private Log[] logs;
    private int itemCount = 0;
    private String sortBy = null;

    public LogList() {
        logs = new Log[1000];
    }

    public void init(String fileName) {
        File logFile = new File("res/week4/" + fileName);
        try {
            Scanner sc = new Scanner(logFile);
            sc.nextLine();

            while (sc.hasNextLine()) {
                if (itemCount == logs.length)
                    reallocate();
                logs[itemCount++] = new Log(sc.nextLine().split(","));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No exist such file.");
        }
    }

    private void reallocate() {
        Log[] reallocatedArray = new Log[logs.length * 2];
        System.arraycopy(logs, 0, reallocatedArray, 0, logs.length);
        logs = reallocatedArray;
    }

    public void sortLogs(String option) {
        switch (option) {
            case "-t":
                Arrays.sort(logs, 0, itemCount, Log.timeComparator);
                break;
            case "-ip":
                Arrays.sort(logs, 0, itemCount, Log.ipComparator);
                break;
            default:
                System.out.println("Wrong option");
                return;
        }
        sortBy = option;
    }

    public void printLogs() {
        switch (sortBy) {
            case "-t":
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(logs[i].getTime());
                    System.out.println("\t" + "IP: " + logs[i].getIp());
                    System.out.println("\t" + "URL: " + logs[i].getUrl());
                    System.out.println("\t" + "Status: " + logs[i].getStatus());
                }
                break;
            case "-ip":
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(logs[i].getIp());
                    System.out.println("\t" + "Time: " + logs[i].getTime());
                    System.out.println("\t" + "URL: " + logs[i].getUrl());
                    System.out.println("\t" + "Status: " + logs[i].getStatus());
                }
                break;
        }
    }
}
