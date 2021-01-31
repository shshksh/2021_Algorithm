package week5;

import java.io.*;
import java.util.Scanner;

public class AddressTree {
    private Node root;

    public void init(String fileName) {
        File file = new File("res/week5/" + fileName);
        try {
            Scanner sc = new Scanner(file);
            sc.nextLine();

            while (sc.hasNextLine()) {
                insert(parseCSV(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No exist such file.");
        }
    }

    private String[] parseCSV(String nextLine) {
        String[] data = new String[6];
        int nextIndex;
        for (int i = 0; i < 5; i++) {
            if (nextLine.startsWith("\""))
                nextIndex = nextLine.indexOf("\"", 1) + 1;
            else
                nextIndex = nextLine.indexOf(",");
            data[i] = nextLine.substring(0, nextIndex);
            nextLine = nextLine.substring(nextIndex + 1);
        }
        data[5] = nextLine;
        return data;
    }

    private void insert(String[] data) {
        if (root == null) {
            root = new Node(data);
        } else {
            insert(root, null, data);
        }
    }

    private void insert(Node curr, Node parent, String[] data) {
        if (curr == null) {
            if (parent.compareTo(data[0]) <= 0)
                parent.left = new Node(data, parent);
            else
                parent.right = new Node(data, parent);
        } else {
            if (curr.compareTo(data[0]) <= 0)
                insert(curr.left, curr, data);
            else
                insert(curr.right, curr, data);
        }
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null)
            return;

        print(node.left);
        System.out.println(node);
        print(node.right);
    }

    public void find(String targetName) {
        Node target = find(root, targetName);
        if (target != null)
            System.out.println(target);
    }

    private Node find(Node node, String targetName) {
        if (node == null) {
            System.out.println("Can't find given name.");
            return null;
        }
        if (node.compareTo(targetName) == 0)
            return node;
        else if (node.compareTo(targetName) < 0)
            return find(node.left, targetName);
        else
            return find(node.right, targetName);
    }

    public void trace(String targetName) {
        trace(root, targetName);
    }

    private void trace(Node node, String targetName) {
        if (node == null) {
            System.out.println("Can't find given name.");
            return;
        }

        System.out.println(node);
        if (node.compareTo(targetName) < 0)
            trace(node.left, targetName);
        else if (node.compareTo(targetName) > 0)
            trace(node.right, targetName);
    }

    public void delete(String targetName) {
        Node target = find(root, targetName);
        if (target != null)
            delete(target);
    }

    private void delete(Node target) {
        Node substitute, parent;
        if (target.left == null || target.right == null) {
            substitute = target.left == null ? target.right : target.left;
            parent = target.parent;
            if (target == root) {
                root = substitute;
            } else {
                if (parent.left == target)
                    parent.left = substitute;
                else if (parent.right == target)
                    parent.right = substitute;
            }
            if (substitute != null)
                substitute.parent = parent;
        } else {
            substitute = successorOf(target);
            target.value = substitute.value;
            delete(substitute);
        }
    }

    private Node successorOf(Node target) {
        if (target.right != null)
            return minimumOf(target.right);
        Node parent = target.parent;
        while (parent != null && target == parent.right) {
            target = parent;
            parent = target;
        }
        return parent;
    }

    private Node minimumOf(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    public void saveAs(String fileName) {
        File file = new File("res/week5/" + fileName);
        try {
            if (file.exists())
                file.delete();
            file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("name,company_name,address,zip,phone,email");
            save(root, bw);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save(Node node, BufferedWriter bw) throws IOException {
        if (node == null)
            return;
        bw.newLine();
        bw.write(node.toSaveFormat());
        save(node.left, bw);
        save(node.right, bw);
    }
}
