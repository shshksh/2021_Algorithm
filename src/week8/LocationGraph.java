package week8;

import java.io.File;
import java.util.*;

public class LocationGraph {
    private final Map<String, Location> locations = new LinkedHashMap<>();

    public LocationGraph() {
        init();
    }

    private void init() {
        File file = new File("res/week8/alabama.txt");

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split("\t");
                locations.put(data[0], new Location(data));
            }
            sc.close();

            linkAllEdges();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void linkAllEdges() {
        File file = new File("res/week8/roadList2.txt");

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] edge = sc.nextLine().split("\t");
                Location src = locations.get(edge[0]);
                Location dst = locations.get(edge[1]);

                src.addEdge(dst);
                dst.addEdge(src);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void path(String fromName, String toName) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<Location> q =
                new PriorityQueue<>(Comparator.comparingDouble(node -> node.distance));
        for (Location value : locations.values()) {
            value.prev = null;
            value.distance = Double.MAX_VALUE;
        }

        Location from = locations.get(fromName);
        from.distance = 0.0;
        q.add(from);

        while (!visited.contains(toName)) {
            Location minWeightEdge = q.poll();
            while (minWeightEdge != null && visited.contains(minWeightEdge.place))
                minWeightEdge = q.poll();
            if (minWeightEdge == null) {
                System.out.println("Error");
                return;
            }
            if (minWeightEdge.place.equals(toName))
                break;
            visited.add(minWeightEdge.place);

            for (Location edge : minWeightEdge.edges) {
                Location node = locations.get(edge.place);
                if (!visited.contains(node.place)) {
                    if (node.distance > minWeightEdge.distance + node.weight) {
                        node.distance = minWeightEdge.distance + node.weight;
                        node.prev = minWeightEdge;
                        q.offer(node);
                    }
                }
            }
        }

        printPath(locations.get(toName));
    }

    private void printPath(Location location) {
        if (location == null) {
            return;
        }
        printPath(location.prev);
        System.out.println(location.place);
    }
}
