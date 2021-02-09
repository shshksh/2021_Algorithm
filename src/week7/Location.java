package week7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Location implements Cloneable {
    public final int id;
    public final String place;
    private final Double longitude;
    private final Double latitude;
    public Double weight;

    public List<Location> edges = new ArrayList<>();
    public Location src;

    public Location(String[] data, int id) {
        this.id = id;
        this.place = data[0];
        this.longitude = Double.parseDouble(data[1]);
        this.latitude = Double.parseDouble(data[2]);
    }

    public void addEdge(Location dst) throws CloneNotSupportedException {
        Location adj = (Location) dst.clone();
        adj.weight = calDistance(
                latitude, longitude,
                adj.latitude, adj.longitude
        );
        adj.src = this;
        adj.edges = null;
        edges.add(adj);
    }

    private double calDistance(double lat1, double lon1, double lat2, double lon2) {
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

    private double rad2deg(double rad) {
        return rad * (double) 180 / Math.PI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.src.id && src.id == location.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(place) + Objects.hash(src.place);
    }

    @Override
    public String toString() {
        return id + " " + latitude + " " + longitude;
    }

}
