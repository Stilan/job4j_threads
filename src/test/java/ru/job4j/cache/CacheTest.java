package ru.job4j.cache;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CacheTest  {

    @Test
    public void addTest() {
        Cache cache = new Cache();
        Base base = new Base(0, 0);
        assertThat(cache.add(base), is(true));
    }

    @Test
    public void deleteTest() {
        Cache cache = new Cache();
        Base base = new Base(0, 0);
        cache.add(base);
        assertThat(cache.delete(base), is(true));
    }

    @Test
    public void updateTest() {
        Cache cache = new Cache();
        Base base = new Base(0, 0);
        cache.add(base);
        base.setName("Name");
        assertThat(cache.update(base), is(true));
    }
}