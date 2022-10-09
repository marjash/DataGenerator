package com.knubisoft;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PopulateTest {
    private static final Populate populate = new Populate();

    @Test
    void create() {
        Map<List<String>, List<Integer>> map = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        Set<String> set = new LinkedHashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        LocalDate date = LocalDate.parse("2014-04-28");
        Point point = new Point();
        X x = new X();
        Integer i = 0;
        assertEquals(LinkedHashMap.class,
                populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(map){
                }.getType())).getClass());
        assertEquals(ArrayList.class,
                populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(list){
                }.getType())).getClass());
        assertEquals(HashSet.class,
                populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(set){
                }.getType())).getClass());
        assertEquals(PriorityQueue.class,
                populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(queue){
                }.getType())).getClass());
        assertEquals(LocalDate.class,
                populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(date){
                }.getType())).getClass());
        assertEquals(Point.class,
                populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(point){
                }.getType())).getClass());
        assertEquals(X.class,
                populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(x){
                }.getType())).getClass());
        assertEquals(i.getClass(),
                populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(i){
                }.getType())).getClass());
    }
}