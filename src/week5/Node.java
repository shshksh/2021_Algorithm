package week5;

public class Node implements Comparable<String> {
    public Address value;
    public Node parent = null;
    public Node left = null;
    public Node right = null;

    public Node(String[] params) {
        this.value = new Address(params);
    }

    public Node(String[] data, Node parent) {
        this.value = new Address(data);
        this.parent = parent;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(String name) {
        return value.compareTo(name);
    }

    public String toSaveFormat() {
        return value.saveFormat();
    }

}