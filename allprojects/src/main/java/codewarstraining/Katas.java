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
    Roman Numerals Encoder
    https://www.codewars.com/kata/51b62bf6a9c58071c600001b/train/java
     */
    public static String solution(int n) {
        StringBuilder romanNumeral = new StringBuilder("");
        // 1000s
        while (n >= 1000) {
            romanNumeral.append("M");
            n -= 1000;
        }
        // 900
        if (n >= 900) {
            romanNumeral.append("CM");
            n -= 900;
        }
        // 500
        if (n >= 500) {
            romanNumeral.append("D");
            n -= 500;
        }
        // 400
        if (n >= 400) {
            romanNumeral.append("CD");
            n -= 400;
        }
        // 100s
        while (n >= 100) {
            romanNumeral.append("C");
            n -= 100;
        }
        //90
        if (n >= 90) {
            romanNumeral.append("XC");
            n -= 90;
        }
        // 50
        if (n >= 50) {
            romanNumeral.append("L");
            n -= 50;
        }
        // 40
        if (n >= 40) {
            romanNumeral.append("XL");
            n -= 40;
        }
        // 10s
        while (n >= 10) {
            romanNumeral.append("X");
            n -= 10;
        }
        //9
        if (n >= 9) {
            romanNumeral.append("IX");
            n -= 9;
        }
        // 5
        if (n >= 5) {
            romanNumeral.append("V");
            n -= 5;
        }
        // 5
        if (n >= 4) {
            romanNumeral.append("IV");
            n -= 4;
        }
        // 1s
        while (n >= 1) {
            romanNumeral.append("I");
            n -= 1;
        }
        return romanNumeral.toString();
    }

}
