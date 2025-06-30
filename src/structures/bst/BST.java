package structures.bst;

import java.util.ArrayList;
import java.util.List;




/**
 * Árbol binario de búsqueda (BST) que asocia claves int (número de victorias)
 * con listas de nombres de jugadores (String).
 */
public class BST {
    private Node root;

    /**
     * Inserta un nuevo par clave-valor en el BST.
     * Si la clave ya existe, agrega el valor a la lista asociada.
     */
    public void insert(int key, String value) {
        root = insert(root, key, value);
    }

    private Node insert(Node node, int key, String value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key < node.key) {
            node.left = insert(node.left, key, value);
        } else if (key > node.key) {
            node.right = insert(node.right, key, value);
        } else {
            node.values.add(value);
        }
        return node;
    }

    /**
     * Devuelve la lista de nombres de jugadores asociados a una cantidad de victorias dada.
     * Si no existe, retorna null.
     */
    public List<String> get(int key) {
        Node node = getNode(root, key);
        return node != null ? node.values : null;
    }

    private Node getNode(Node node, int key) {
        if (node == null) return null;
        if (key < node.key) return getNode(node.left, key);
        if (key > node.key) return getNode(node.right, key);
        return node;
    }

    /**
     * Retorna una lista de todos los jugadores cuya cantidad de victorias está entre lo y hi (inclusive).
     */
    public List<String> winRange(int lo, int hi) {
        List<String> result = new ArrayList<>();
        winRange(root, lo, hi, result);
        return result;
    }

    private void winRange(Node node, int lo, int hi, List<String> result) {
        if (node == null) return;
        if (lo < node.key) winRange(node.left, lo, hi, result);
        if (lo <= node.key && node.key <= hi) result.addAll(node.values);
        if (hi > node.key) winRange(node.right, lo, hi, result);
    }

    /**
     * Retorna la lista de jugadores con la cantidad de victorias mayor más cercana a wins.
     * Si no existe, retorna null.
     */
    public List<String> winSuccessor(int wins) {
        Node successor = null;
        Node current = root;
        while (current != null) {
            if (current.key > wins) {
                successor = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return successor != null ? successor.values : null;
    }
}
