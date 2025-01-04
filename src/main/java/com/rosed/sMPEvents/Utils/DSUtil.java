package com.rosed.sMPEvents.Utils;

import java.util.*;

public class DSUtil {

//    public static void main(String[] args) {
//
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("a", 100);
//        map.put("b", 300);
//        map.put("c", 50);
//        map.put("d", 50);
//        map.put("e", 120);
//
//        sortByValue(map).forEach((string, num)-> {
//            System.out.println(string + " " + num);
//        });
//    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
