package week6;

import java.util.ArrayList;
import java.util.List;

public class Location implements Cloneable {
    private final String place;
    private final Double longitude;
    private final Double latitude;
    private Double weight = 0.0;
    private List<Location> adj = new ArrayList<>();


    public Location(String[] data) {
        this.place = data[0];
        this.longitude = Double.parseDouble(data[1]);
        this.latitude = Double.parseDouble(data[2]);
    }

    public Location clone(Location src) throws CloneNotSupportedException {
        Location dst = (Location) super.clone();
        dst.weight =
                calDistance(dst.latitude, dst.longitude, src.latitude, src.longitude);
        return dst;
    }

    public double calDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta, dist;
        theta = lon1 - lon2;
        dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // 단위 mile 에서 km 변환.
        dist = dist * 1000.0; // 단위 km 에서 m 로 변환
        return dist;
    }

    private double deg2rad(double deg) {
        return deg * Math.PI / (double) 180;
    }

    // 주어진 라디언(radian) 값을 도(degree) 값으로 변환
    private double rad2deg(double rad) {
        return rad * (double) 180 / Math.PI;
    }

    public void addAdjLocation(Location location) {
        adj.add(location);
    }

    @Override
    public String toString() {
        return "Location{" +
                "place='" + place + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public String getPlace() {
        return place;
    }

    public List<Location> getAdj() {
        return adj;
    }
}
