/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codewarstraining;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Adam
 */
public class Katas {

    /*
    ============================================================================
    Scramblies
    https://www.codewars.com/kata/55c04b4cc56a697bb0000048
     */
    public static boolean scramble(String str1, String str2) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int arr1CurrentIndex = 0;

        for (int i = 0; i < arr2.length; i++) {
            while (arr1[arr1CurrentIndex] != arr2[i]) {
                arr1CurrentIndex++;
                if (arr1CurrentIndex == arr1.length) {
                    return false;
                }
            }
            arr1CurrentIndex++;
        }
        return true;
    }

    /*
    ============================================================================
    Weight for weight
    https://www.codewars.com/kata/55c6126177c9441a570000cc/train/java
     */
    public static String orderWeight(String strng) {
        // 0. Process empty string
        if ("".equals(strng)) {
            return strng;
        }

        // 1. Remove trailing spaces and non unique spaces between numbers
        strng = strng.strip();
        while (strng.contains("  ")) {
            strng = strng.replaceAll("  ", " ");
        }
        long[] values = Stream.of(strng.split(" ")).mapToLong(Long::valueOf).toArray();

        // 2. Calculate the weights (helper method)
        long[][] weightsAndValues = new long[values.length][2];
        for (int i = 0; i < values.length; i++) {
            weightsAndValues[i][0] = Katas.calculateWeight(values[i]);
            weightsAndValues[i][1] = values[i];
        }

        // 3. Order according to weights, including where two or more weights are equal
        Arrays.sort(weightsAndValues, new Comparator<long[]>() {
            @Override
            public int compare(long[] first, long[] second) {
                if (first[0] > second[0]) {
                    return 1;
                } else if (first[0] < second[0]) {
                    return -1;
                } else if (String.valueOf(first[1]).compareTo(String.valueOf(second[1])) > 0) {
                    return 1;
                } else if (String.valueOf(first[1]).compareTo(String.valueOf(second[1])) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        // 4. Extract the values and return them as String
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < weightsAndValues.length; i++) {
            result = result.append(weightsAndValues[i][1]).append(" ");
        }

        return result.toString().strip();
    }

    private static long calculateWeight(long number) {
        long weight = 0;
        if (number < 10) {
            return number;
        }
        do {
            weight = weight + (number % 10);
            number = number - (number % 10);
            number = number / 10;
            if (number < 10) {
                weight = weight + number;
            }
        } while (number >= 10);
        return weight;
    }

    /*
    ============================================================================
    Bit Counting
    https://www.codewars.com/kata/526571aae218b8ee490006f4
     */
    public static int countOnes(int n) {
        if (n == 0) {
            return 0;
        } else {
            return 1 + Katas.countOnes(n - (int) Math.pow(2, Math.floor(Math.log((double) n) / Math.log(2))));
        }
    }

    /*
    ============================================================================
    Human readable duration format
    https://www.codewars.com/kata/52742f58faf5485cae000b9a/train/java
     */
    public static String formatDuration(int seconds) {
        // Use a map to store values for seconds, minutes, hours, days, years
        // 1. A function to calculate each value and store it
        Map<String, Integer> values = Katas.calculateValues(seconds);
        // 2. A function to create a string (a long one, think whether it should be split)
        return "";
    }

    private static Map<String, Integer> calculateValues(int seconds) {
        HashMap<String, Integer> values = new HashMap<>();
        values.put("seconds", 0);
        values.put("minutes", 0);
        values.put("hours", 0);
        values.put("days", 0);
        values.put("years", 0);

        int currentValue;
        if (seconds >= 31536000) {
            currentValue = (int) Math.floor(seconds / 31536000);
            values.replace("years", values.get("years"), values.get("years") + currentValue);
            seconds -= currentValue * 31536000;
        }
        if (seconds >= 86400) {
            currentValue = (int) Math.floor(seconds / 86400);
            values.replace("days", values.get("days"), values.get("days") + currentValue);
            seconds -= currentValue * 86400;
        }
        if (seconds >= 3600) {
            currentValue = (int) Math.floor(seconds / 3600);
            values.replace("hours", values.get("hours"), values.get("hours") + currentValue);
            seconds -= currentValue * 3600;
        }
        if (seconds >= 60) {
            currentValue = (int) Math.floor(seconds / 60);
            values.replace("minutes", values.get("minutes"), values.get("minutes") + currentValue);
            seconds -= currentValue * 60;
        }
        if (seconds >= 1) {
            currentValue = (int) Math.floor(seconds / 1);
            values.replace("seconds", values.get("seconds"), values.get("seconds") + currentValue);
        }
        
        return values;
    }
    
    private static String buildAnswer (Map<Integer, String> values)

}
