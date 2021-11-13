package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        /* TODO impl */
      return   memory.computeIfPresent(model.getId(), (k, n) -> {
            Base stored = memory.get(model.getId());
            if (stored.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            } else {
                 stored = new Base(model.getId(), model.getVersion() + 1);
                 stored.setName(model.getName());
            }
             return stored;
        }) != null;

    }

    public boolean delete(Base model) {
      return memory.remove(model.getId(), model);
    }

}
