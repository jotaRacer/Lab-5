package structures.hash;

/**
 * Tabla de hash tipo mapa que almacena pares clave-valor.
 * Utiliza encadenamiento (chaining) con listas enlazadas para resolver colisiones.
 * Claves: tipo genérico K
 * Valores: tipo genérico V
 */
public class HashST<K, V> {
    private int capacity;
    private HashNode<K, V>[] buckets;
    private int size;

    /**
     * Constructor que inicializa la tabla de hash con capacidad 53.
     * Todos los buckets se inicializan como null.
     */
    public HashST() {
        this.capacity = 53;
        this.buckets = new HashNode[capacity];
        this.size = 0;
    }

    /**
     * Calcula el índice hash para una clave dada.
     * 
     * @param key La clave para calcular el hash
     * @return El índice en el arreglo de buckets
     */
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    /**
     * Agrega un nuevo par clave-valor a la tabla de hash.
     * Si la clave ya existe, reemplaza el valor.
     * 
     * @param key La clave
     * @param value El valor
     */
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> current = buckets[index];

        // Buscar si la clave ya existe
        while (current != null) {
            if (current.getKey().equals(key)) {
                // Reemplazar el valor existente
                current = new HashNode<>(key, value);
                current.setNext(buckets[index].getNext());
                buckets[index] = current;
                return;
            }
            current = current.getNext();
        }

        // La clave no existe, agregar nuevo nodo al inicio de la lista
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.setNext(buckets[index]);
        buckets[index] = newNode;
        size++;
    }

    /**
     * Obtiene el valor asociado a una clave dada.
     * 
     * @param key La clave a buscar
     * @return El valor asociado, o null si no existe
     */
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = buckets[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Verifica si una clave está presente en la tabla de hash.
     * 
     * @param key La clave a verificar
     * @return true si la clave existe, false en caso contrario
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Retorna el número de pares clave-valor almacenados en la tabla.
     * 
     * @return El tamaño de la tabla de hash
     */
    public int size() {
        return size;
    }
} 