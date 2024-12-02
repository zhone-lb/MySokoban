package model.algorithm;

public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K,V>> {
    public K key;
    public V value;
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Pair<K,V> o) {
        return (key == o.key ? value.compareTo(o.value) : key.compareTo(o.key));
    }
}
