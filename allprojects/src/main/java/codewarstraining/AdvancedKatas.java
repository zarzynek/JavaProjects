package codewarstraining;



import codewarstraining.Direction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author adamw
 */
public class AdvancedKatas {

    /**
     * ========================================================================
     * Calculator
     *
     * URL: https://www.codewars.com/kata/5235c913397cbf2508000048/train/java
     *
     * A simple calculator that given a string of operators (), +, -, *, / and
     * numbers separated by spaces returns the value of that expression.
     *
     * @param expression an arithmetic expression stored as String
     * @return a result of processing the arithmetic expression stored as Double
     */
    public static Double evaluate(String expression) {
        List<String> elements = new LinkedList<>(Arrays.asList(expression.split(" ")));

        // Process operations stored within brackets
        while (elements.contains("(")) {
            int lastOpeningBracket = elements.lastIndexOf("(");
            int correspondingClosingBracket = elements
                    .subList(lastOpeningBracket, elements.size()).indexOf(")")
                    + lastOpeningBracket;
            AdvancedKatas
                    .evaluateSubExpression(elements.subList(lastOpeningBracket,
                            correspondingClosingBracket + 1));
        }

        // Process the remaining operations
        AdvancedKatas.evaluateSubExpression(elements);

        return Double.parseDouble(elements.get(0));
    }

    /*
    A helper method responsible for identifying individual expressions and 
    ensuring the correct order of processing.
     */
    private static void evaluateSubExpression(List<String> aList) {
        for (int i = 0; i < aList.size(); i++) {
            if (aList.get(i).equals("*") || aList.get(i).equals("/")) {
                AdvancedKatas.calculate(aList, i);
                i--;
            }
        }
        for (int i = 0; i < aList.size(); i++) {
            if (aList.get(i).equals("+") || aList.get(i).equals("-")) {
                AdvancedKatas.calculate(aList, i);
                i--;
            }
        }
    }

    /*
    A helper method responsible for calculating the results of individual
    expressions.
     */
    private static void calculate(List<String> aList, int operandIndex) {
        double result = 0;
        switch (aList.get(operandIndex)) {
            case "*":
                result = Double.parseDouble(aList.get(operandIndex - 1))
                        * Double.parseDouble(aList.get(operandIndex + 1));
                break;
            case "/":
                result = Double.parseDouble(aList.get(operandIndex - 1))
                        / Double.parseDouble(aList.get(operandIndex + 1));
                break;
            case "+":
                result = Double.parseDouble(aList.get(operandIndex - 1))
                        + Double.parseDouble(aList.get(operandIndex + 1));
                break;
            case "-":
                result = Double.parseDouble(aList.get(operandIndex - 1))
                        - Double.parseDouble(aList.get(operandIndex + 1));
                break;
        }

        AdvancedKatas.reduce(aList, result, operandIndex - 1, operandIndex + 1);
    }

    /*
    A helper method responsible for replacing each processed expression with
    its result
     */
    private static void reduce(List<String> aList, Double aResult, int aStartIndex, int anEndIndex) {
        // Remove the sub-expression
        for (int i = aStartIndex; i <= anEndIndex; i++) {
            aList.remove(aStartIndex);
        }
        // Add the result
        aList.add(aStartIndex, String.valueOf(aResult));

        // Remove brackets where present        
        if ("(".equals(aList.get(0))) {
            aList.remove(0);
        }
        if (")".equals(aList.get(aList.size() - 1))) {
            aList.remove(aList.size() - 1);
        }
    }

    /**
     * ========================================================================
     * The Lift URL:
     * https://www.codewars.com/kata/58905bfa1decb981da00009e/train/java
     *
     *
     */
    /*
    PLAN:
    1. Set up variables:
        1.a. Create an empty Linked List to store the results
        1.b. Create a flag to mark the direction of the lift (ENUM: UP / DOWN ?) 
        1.c. Create a data structure to store occupants of the lift.
             Research the optimal structure (sortedList?)
    2. Iterate through the outer array
        2.a. Start:
            ->Add 0 to the list
            ->Set the direction flag (use a universal condition)
        2.b. At each floor:
            -> Stop if <lift occupant requested this floor> OR 
               <a person on this floor wants to go in the direction of travel>
            -> Record the stop (add it to the results list)
            -> Let lift occants out (optional)
            -> Check whether direction needs switching
            -> Let new people in (optional and dependant on capacity)
            -> Move to the next stop
            
     */
    public static int[] theLift(final int[][] queues, final int capacity) {

        // Set up variables
        List<Integer> floorsVisited = new LinkedList<>();
        Direction currentDirection = Direction.UP;
        int currentFloorNumber = 0;
        List<Integer> liftOccupants = new LinkedList<>();
        queues[currentFloorNumber] = AdvancedKatas.letPeopleIn(currentFloorNumber, liftOccupants, queues[currentFloorNumber], capacity, currentDirection);

        // Add ground floor to the list of visited floors
        if (queues[0].length == 0) {
            floorsVisited.add(currentFloorNumber);
        }

        // Process the requests
        while (AdvancedKatas.checkFutherFloorsRequests(queues) || liftOccupants.size() > 0) {
            if (AdvancedKatas.checkIfStopRequired(currentFloorNumber, queues, liftOccupants, currentDirection, capacity)) {
                floorsVisited.add(currentFloorNumber);
                AdvancedKatas.letPeopleOut(currentFloorNumber, liftOccupants);
                queues[currentFloorNumber] = AdvancedKatas.letPeopleIn(currentFloorNumber, liftOccupants, queues[currentFloorNumber], capacity, currentDirection);
                if (AdvancedKatas.setDirection(queues, liftOccupants, currentFloorNumber, currentDirection, capacity) != currentDirection) {
                    currentDirection = AdvancedKatas.setDirection(queues, liftOccupants, currentFloorNumber, currentDirection, capacity);
                    queues[currentFloorNumber] = AdvancedKatas.letPeopleIn(currentFloorNumber, liftOccupants, queues[currentFloorNumber], capacity, currentDirection);
                }
            }
            if (currentDirection == Direction.UP) {
                currentFloorNumber++;
            } else {
                currentFloorNumber--;
            }
        }

        // Go back to the ground floor
        if (floorsVisited.get(floorsVisited.size() - 1) != 0) {
            floorsVisited.add(0);
        }

        return floorsVisited.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Direction setDirection(int[][] queues, List<Integer> liftOccupants, int currentFloorNumber, Direction currentDirection, int capacity) {
        if (currentDirection == Direction.UP) {
            //Keep the current direction if any of the lift occupants requested a floor above the current floor 
            if (AdvancedKatas.checkLiftOccupantsRequests(liftOccupants, currentFloorNumber, currentDirection)) {
                return currentDirection;
                // Keep the current direction if there are people waiting on further floors and the lift has capacity
            } else if (AdvancedKatas.checkFutherFloorsRequests(Arrays.copyOfRange(queues, currentFloorNumber + 1, queues.length))
                    && liftOccupants.size() < capacity) {
                return currentDirection;
            } else {
                return Direction.DOWN;
            }

        } else {
            // Keep current direction if any of the lift occupants requested floor below the current floor
            if (AdvancedKatas.checkLiftOccupantsRequests(liftOccupants, currentFloorNumber, currentDirection)) {
                return currentDirection;
                // Keep current direction if there are people waiting on floors below the current floor and the lift has capacity
            } else if (AdvancedKatas.checkFutherFloorsRequests(Arrays.copyOfRange(queues, 0, currentFloorNumber))
                    && liftOccupants.size() < capacity) {
                return currentDirection;
                // Change direction to UP is there are people on this floor waiting to go UP or
                // there are people on the floors above the current floor and the lift has capacity
            } else if (AdvancedKatas.checkFutherFloorsRequests(Arrays.copyOfRange(queues, currentFloorNumber, queues.length))
                    && liftOccupants.size() < capacity) {
                return Direction.UP;
            } else {
                return currentDirection;
            }
        }
    }

    private static boolean checkIfStopRequired(int currentFloorNumber, int[][] queues, List<Integer> liftOccupants, Direction currentDirection, int capacity) {
        // Stop if any of the lift occupants requested this floor
        if (liftOccupants.contains(currentFloorNumber)) {
            return true;
        } else {
            // Stop if someone on the current floor is waiting to go in the current direction, or if there is no more people waiting on the further floors 
            if (currentDirection == Direction.UP) {
                return AdvancedKatas.checkCurrentFloorRequests(queues[currentFloorNumber], currentFloorNumber, currentDirection)
                        ? true
                        : (!AdvancedKatas.checkFutherFloorsRequests(Arrays.copyOfRange(queues, currentFloorNumber + 1, queues.length))
                        && (liftOccupants.size() == 0));
            } else {
                return AdvancedKatas.checkCurrentFloorRequests(queues[currentFloorNumber], currentFloorNumber, currentDirection)
                        ? true
                        : (!AdvancedKatas.checkFutherFloorsRequests(Arrays.copyOfRange(queues, 0, currentFloorNumber))
                        && (liftOccupants.size() == 0));
            }
        }
    }

    private static void letPeopleOut(int currentFloorNumber, List<Integer> liftOccupants) {
        liftOccupants.removeIf(e -> e == currentFloorNumber);
    }

    // Return new floorQueue
    private static int[] letPeopleIn(int currentFloorNumber, List<Integer> liftOccupants, int[] floorQueue, int capacity, Direction currentDirection) {
        int personCounter = 0;
        List<Integer> remainingFloorQueue = new LinkedList<>();
        while (personCounter < floorQueue.length) {
            if ((currentDirection == Direction.UP && floorQueue[personCounter] > currentFloorNumber && liftOccupants.size() < capacity)
                    || (currentDirection == Direction.DOWN && floorQueue[personCounter] < currentFloorNumber) && liftOccupants.size() < capacity) {
                liftOccupants.add(floorQueue[personCounter]);
            } else {
                remainingFloorQueue.add(floorQueue[personCounter]);
            }
            personCounter++;
        }
        return remainingFloorQueue.stream().mapToInt(Integer::intValue).toArray();
    }

    private static boolean checkFutherFloorsRequests(int[][] queues) {
        return Arrays.stream(queues).filter(e -> e.length > 0).findAny().isPresent();
    }

    private static boolean checkCurrentFloorRequests(int[] currentFloorQueue, int currentFloorNumber, Direction currentDirection) {
        return currentDirection == Direction.UP
                ? Arrays.stream(currentFloorQueue).filter(e -> e > currentFloorNumber).findAny().isPresent()
                : Arrays.stream(currentFloorQueue).filter(e -> e < currentFloorNumber).findAny().isPresent();
    }

    private static boolean checkLiftOccupantsRequests(List<Integer> liftOccupants, int currentFloorNumber, Direction currentDirection) {
        return currentDirection == Direction.UP
                ? liftOccupants.stream().max(Integer::compare).orElse(currentFloorNumber) > currentFloorNumber
                : liftOccupants.stream().min(Integer::compare).orElse(currentFloorNumber) < currentFloorNumber;
    }
}

enum Direction {
    DOWN,
    UP
}

