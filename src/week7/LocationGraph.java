package week7;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LocationGraph {
    private final Map<String, Location> locations = new LinkedHashMap<>();
    private int[] nodeSet;
    private int[] nodeWeight;

    public LocationGraph() {
        init();
        nodeSet = new int[locations.size()];
        nodeWeight = new int[locations.size()];
    }

    private void init() {
        File file = new File("res/week7/alabama.txt");

        try {
            Scanner sc = new Scanner(file);
            int i = 0;

            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split("\t");
                locations.put(data[0], new Location(data, i++));
            }
            sc.close();

            linkAllEdges();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void linkAllEdges() {
        File file = new File("res/week7/roadList2.txt");

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

    public void mst() {
        Set<Location> edgeSet = new HashSet<>();
        for (Location location : locations.values()) {
            edgeSet.addAll(location.edges);
        }
        List<Location> edgeList = new ArrayList<>(edgeSet);
        edgeList.sort(Comparator.comparingDouble(loc -> loc.weight));

        List<List<Integer>> mstEdgeList = new ArrayList<>();
        for (int i = 0; i < nodeSet.length; i++) {
            mstEdgeList.add(new ArrayList<>());
        }

        wupc(edgeList, mstEdgeList);
        saveMst(mstEdgeList);
    }

    private void wupc(List<Location> edgeList, List<List<Integer>> mstEdgeList) {
        for (int i = 0; i < nodeSet.length; i++)
            nodeSet[i] = i;
        Arrays.fill(nodeWeight, 1);

        int edgeCount = 0;
        for (int i = 0; i < edgeList.size() && edgeCount < locations.size() - 1; i++) {
            Location edge = edgeList.get(i);
            if (findSet(edge.id) != findSet(edge.src.id)) {
                union(edge.src.id, edge.id);
                edgeCount++;

                mstEdgeList.get(edge.src.id).add(edge.id);
                mstEdgeList.get(edge.id).add(edge.src.id);
            }
        }
    }

    private int findSet(int id) {
        while (id != nodeSet[id]) {
            nodeSet[id] = nodeSet[nodeSet[id]];
            id = nodeSet[id];
        }
        return id;
    }

    private void union(int srcId, int dstId) {
        int srcRoot = findSet(srcId);
        int dstRoot = findSet(dstId);

        if (nodeWeight[srcRoot] > nodeWeight[dstRoot]) {
            nodeSet[dstRoot] = srcRoot;
            nodeWeight[srcRoot] += nodeWeight[dstRoot];
        } else {
            nodeSet[srcRoot] = dstRoot;
            nodeWeight[dstRoot] += nodeWeight[srcRoot];
        }
    }

    private void saveMst(List<List<Integer>> mstEdgeList) {
        File file = new File("res/week7/mst.txt");

        try {
            if (file.exists())
                file.delete();
            file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Location value : locations.values()) {
                bw.write(value.toString() + " " + mstEdgeList.get(value.id).size());
                mstEdgeList.get(value.id).sort(Integer::compareTo);
                for (Integer vertex : mstEdgeList.get(value.id)) {
                    bw.write(" " + vertex);
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
