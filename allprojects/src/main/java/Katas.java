/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adamw
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.List;

public class Katas {

    public static List<String> nameList = Arrays.asList("Adam", "Magdalena", "Jerzy", "Elzbieta");

    /*
    Now, starting again from a list of names, give me the total number of
    letters in all the names with more than 5 letters
     */
    public static int LetterCount(List<String> names) {
        return names.stream()
                .filter(name -> name.length() > 5)
                .map(name -> name.length())
                .reduce(0, (sumOfLengths, nameLength) -> sumOfLengths + nameLength);
    }
}

