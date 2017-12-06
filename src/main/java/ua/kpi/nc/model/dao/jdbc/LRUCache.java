package ua.kpi.nc.model.dao.jdbc;

import ua.kpi.nc.model.entity.Salgrade;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCache <K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LRUCache(int initialCapacity) {
        super(initialCapacity + 1, 2, true);
        this.capacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//        return super.removeEldestEntry(eldest);
        return this.size() > capacity;
    }

    public boolean put(K key, Optional<V> value){
        if (value.isPresent()){
            put(key, value.get());
            return true;
        }
        return false;
    }




    public static void main(String[] args) {
        Map<Integer, Boolean> cache = new LRUCache<>(2);

        final Boolean putTrue = cache.put(1, true);
        final Boolean putFalse = cache.put(2, false);
        final Boolean putNull = cache.put(7, null);
        System.out.println(putTrue);
        System.out.println(putFalse);
        System.out.println(putNull);
        System.out.println(cache);
        cache.get(1);
        System.out.println(cache);


        cache.put(3, false);
        cache.put(4, false);
        System.out.println(cache);

        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache);
        System.out.println(cache.remove(4));
        System.out.println(cache);




    }
}
