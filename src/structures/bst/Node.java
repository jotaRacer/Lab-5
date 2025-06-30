package structures.bst;
import java.util.ArrayList;
import java.util.List;

public class Node {
    public  int key;
    public  List<String> values;
    public  Node left, right;

        Node(int key, String value) {
            this.key = key;
            this.values = new ArrayList<>();
            this.values.add(value);
            this.left = null;
            this.right = null;
        }
    
}
