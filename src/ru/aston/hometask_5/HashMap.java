import java.util.LinkedList;
import java.util.Iterator;

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
    private static final float LOAD_FACTOR = 0.75f;

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode() & 0x7FFFFFFF) % capacity;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key == null ? key == null : entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if ((float) size / capacity >= LOAD_FACTOR) {
            resize();
        }

        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        LinkedList<Entry<K, V>> bucket = table[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key == null ? key == null : entry.key.equals(key)) {
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
            Iterator<Entry<K, V>> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.key == null ? key == null : entry.key.equals(key)) {
                    iterator.remove();
                    size--;
                    return entry.value;
                }
            }
        }
        return null;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        @SuppressWarnings("unchecked")
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[newCapacity];

        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int newIndex = (entry.key == null ? 0 : (entry.key.hashCode() & 0x7FFFFFFF)) % newCapacity;
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new LinkedList<>();
                    }
                    newTable[newIndex].add(entry);
                }
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}