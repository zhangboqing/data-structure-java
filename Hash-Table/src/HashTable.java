import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author zhangboqing
 * @date 2019/12/1
 */
public class HashTable<K, V> {

    private static final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperThreshold = 10;
    private static final int lowerThreshold = 2;
    private static int capacityIndex = 0;

    private TreeMap<K, V>[] hashTables;
    private int M;
    private int size;

    public HashTable(int M) {
        this.M = M;
        this.size = 0;
        hashTables = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTables[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(capacity[capacityIndex]);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        if (key == null) {
            return;
        }

        int hash = hash(key);
        TreeMap<K, V> map = hashTables[hash];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            if (size >= upperThreshold * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacityIndex);
            }
        }
    }

    private void resize(int capacity) {
        TreeMap[] newHashTables = new TreeMap[capacity];
        for (int i = 0; i < capacity; i++) {
            newHashTables[i] = new TreeMap();
        }
        this.M = capacity;
        for (int i = 0; i < hashTables.length; i++) {
            TreeMap<K, V> map = hashTables[i];
            Set<Map.Entry<K, V>> entries = map.entrySet();
            for (Map.Entry<K, V> entry : entries) {
                newHashTables[hash(entry.getKey())].put(entry.getKey(), entry.getValue());
            }
        }
        this.hashTables = newHashTables;
    }

    public V remove(K key) {
        if (key == null) {
            return null;
        }

        V removeV = null;
        int hash = hash(key);
        TreeMap<K, V> map = hashTables[hash];
        if (map.containsKey(key)) {
            removeV = map.remove(key);
            size--;
            if (size <= lowerThreshold && capacityIndex - 1 > 0) {
                capacityIndex--;
                resize(capacityIndex);
            }
        }
        return removeV;
    }

    public void set(K key, V value) {
        if (key == null) {
            return;
        }
        TreeMap<K, V> map = hashTables[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        }
    }

    public boolean contains(K key) {
        if (key == null) {
            return false;
        }
        return hashTables[hash(key)].containsKey(key);
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        return hashTables[hash(key)].get(key);
    }

}
