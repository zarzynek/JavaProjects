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
        if (seconds == 0) {
            return "now";
        }
        LinkedHashMap<String, Integer> values = Katas.calculateValues(seconds);
        return Katas.buildAnswer(values);
    }

    private static LinkedHashMap<String, Integer> calculateValues(int seconds) {
        LinkedHashMap<String, Integer> values = new LinkedHashMap<>();
        values.put("year", 0);
        values.put("day", 0);
        values.put("hour", 0);
        values.put("minute", 0);
        values.put("second", 0);

        int currentValue;
        if (seconds >= 31536000) {
            currentValue = (int) Math.floor(seconds / 31536000);
            values.replace("year", values.get("year"), values.get("year") + currentValue);
            seconds -= currentValue * 31536000;
        }
        if (seconds >= 86400) {
            currentValue = (int) Math.floor(seconds / 86400);
            values.replace("day", values.get("day"), values.get("day") + currentValue);
            seconds -= currentValue * 86400;
        }
        if (seconds >= 3600) {
            currentValue = (int) Math.floor(seconds / 3600);
            values.replace("hour", values.get("hour"), values.get("hour") + currentValue);
            seconds -= currentValue * 3600;
        }
        if (seconds >= 60) {
            currentValue = (int) Math.floor(seconds / 60);
            values.replace("minute", values.get("minute"), values.get("minute") + currentValue);
            seconds -= currentValue * 60;
        }
        if (seconds >= 1) {
            currentValue = (int) Math.floor(seconds / 1);
            values.replace("second", values.get("second"), values.get("second") + currentValue);
        }

        return values;
    }

    private static String buildAnswer(LinkedHashMap<String, Integer> values) {
        StringBuilder answer = new StringBuilder();
        Set<String> valuesKeySet = values.keySet();

        Iterator<String> itr = valuesKeySet.iterator();
        while (itr.hasNext()) {
            String currentKey = itr.next();
            if (values.get(currentKey) > 0) {
                answer.append(values.get(currentKey) == 1 ? values.get(currentKey) + " " + currentKey : values.get(currentKey) + " " + currentKey + "s");
                itr.remove();
                long remainingValuesCount = values.values().stream().filter(e -> e >= 1).count();
                answer.append(remainingValuesCount > 1 ? ", " : (remainingValuesCount == 0 ? "" : " and "));
            }
        }

        return answer.toString();
    }

    /*
    ============================================================================
    Permutations
    https://www.codewars.com/kata/5254ca2719453dcc0b00027d/train/java
     */
    public static List<String> singlePermutations(String s) {
        
        return null;
    }
}
