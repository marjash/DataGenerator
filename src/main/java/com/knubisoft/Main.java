package com.knubisoft;

import java.time.LocalDate;
import java.util.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Populate populate = new Populate();

    public static void main(String[] args) {

        Map<List<String>, List<Integer>> map = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        Set<String> set = new LinkedHashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        LocalDate date = LocalDate.parse("2014-04-28");
        Point point = new Point();
        X x = new X();
        int i = 0;
        Object o = populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(queue) {
        }.getType()));
        System.out.println(o);
    }


}
