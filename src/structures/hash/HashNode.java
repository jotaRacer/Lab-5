package structures.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * Nodo de una tabla de hash tipo mapa que almacena pares clave-valor.
 * Utiliza encadenamiento para resolver colisiones.
 */
public class HashNode<K, V> {
    private K key;
    private V value;
    private HashNode<K, V> next;

    /**
     * Constructor que inicializa un nodo con clave y valor dados.
     * El siguiente nodo se inicializa como null.
     * 
     * @param key La clave
     * @param value El valor
     */
    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    /**
     * Obtiene la clave del nodo.
     * 
     * @return La clave
     */
    public K getKey() {
        return key;
    }

    /**
     * Obtiene el valor del nodo.
     * 
     * @return El valor
     */
    public V getValue() {
        return value;
    }

    /**
     * Obtiene el siguiente nodo en la cadena.
     * 
     * @return El siguiente HashNode, o null si es el último
     */
    public HashNode<K, V> getNext() {
        return next;
    }

    /**
     * Establece el siguiente nodo en la cadena.
     * 
     * @param next El HashNode que será el siguiente
     */
    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
} 