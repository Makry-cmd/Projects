import java.util.LinkedList;

class HashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        LinkedList<Entry<K, V>> bucket = table[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }


        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    bucket.remove(entry);
                    size--;
                    return entry.value;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
