package week4;

import java.util.Comparator;

public class Log {

    private final String ip;
    private final String time;
    private final String url;
    private final String status;

    public static Comparator<Log> timeComparator =
            (o1, o2) -> o1.getTime().compareTo(o2.getTime());
    public static Comparator<Log> ipComparator = (o1, o2) -> {
        if (o1.getIp().equals(o2.getIp()))
            return o1.getTime().compareTo(o2.getTime());
        else
            return o1.getIp().compareTo(o2.getIp());
    };

    Log(String[] arr) {
        this.ip = arr[0];
        this.time = arr[1].substring(1);
        this.url = arr[2];
        this.status = arr[3];
    }

    public String getIp() {
        return ip;
    }

    public String getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }
}
