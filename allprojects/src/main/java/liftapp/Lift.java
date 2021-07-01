package liftapp;

// Imports

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * The Lift. Based on exercise available on the Codewars portal. For a more
 * detailed description of this exercise please visit:
 * https://www.codewars.com/kata/58905bfa1decb981da00009e/train/java
 * <p>
 * Emulates behaviour of a lift. Takes a two dimensional int array representing
 * requests from people waiting on each floor in a building, and a single
 * integer value representing the current capacity of the lift.
 * <p>
 * The order in which the requests are processed depends on: -> the request
 * values on each floor -> lift capacity -> current direction of travel.
 * <p>
 * The process returns an int array, which lists all the visited floors in order
 * in which they have been visited.
 *
 * @author adamw
 */
public class Lift {

    //Variables
    private final int[][] queues;
    private final int capacity;
    private Direction currentDirection;
    private int currentFloorNumber;
    private List<Integer> visitedFloors;
    private List<Integer> liftOccupants;

    // Constructor
    public Lift(int[][] queues, int capacity) {
        this.queues = queues;
        this.capacity = capacity;
        this.currentDirection = Direction.UP;
        this.currentFloorNumber = 0;
        this.visitedFloors = new ArrayList<>();
        this.liftOccupants = new ArrayList<>();

        if (this.queues[0].length == 0) {
            this.visitedFloors.add(this.currentFloorNumber);
        }
    }

    // Methods
    public int[] runLift() {
        while (this.checkFurtherFloorsRequests(0, this.queues.length) || this.liftOccupants.size() > 0) {
            if (this.checkIfStopRequired()) {
                this.visitedFloors.add(this.currentFloorNumber);
                this.letPeopleOut();
                this.queues[this.currentFloorNumber] = this.letPeopleIn();
                if (this.setDirection() != this.currentDirection) {
                    this.currentDirection = this.setDirection();
                    this.queues[this.currentFloorNumber] = this.letPeopleIn();
                }
            }
            if (this.currentDirection == Direction.UP) {
                this.currentFloorNumber++;
            } else {
                this.currentFloorNumber--;
            }
        }

        // After all requests have been processed go back to the ground floor
        if (this.visitedFloors.get(this.visitedFloors.size() - 1) != 0) {
            this.visitedFloors.add(0);
        }

        return this.visitedFloors.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean checkIfStopRequired() {
        if (this.liftOccupants.contains(this.currentFloorNumber)) {
            return true;
        } else {
            if (this.currentDirection == Direction.UP) {
                return this.checkCurrentFloorRequests()
                        ? true
                        : (!this.checkFurtherFloorsRequests(this.currentFloorNumber + 1, this.queues.length)
                        && (this.liftOccupants.size() == 0));
            } else {
                return this.checkCurrentFloorRequests()
                        ? true
                        : (!this.checkFurtherFloorsRequests(0, this.currentFloorNumber)
                        && (this.liftOccupants.size() == 0));
            }
        }
    }

    private Direction setDirection() {
        if (this.currentDirection == Direction.UP) {
            if (this.checkLiftOccupantsRequests()) {
                return this.currentDirection;
            } else if (this.checkFurtherFloorsRequests(this.currentFloorNumber + 1, this.queues.length)
                    && this.liftOccupants.size() < this.capacity) {
                return this.currentDirection;
            } else {
                return Direction.DOWN;
            }
        } else {
            if (this.checkLiftOccupantsRequests()) {
                return this.currentDirection;
            } else if (this.checkFurtherFloorsRequests(0, this.currentFloorNumber)
                    && this.liftOccupants.size() < this.capacity) {
                return this.currentDirection;
            } else if (this.checkFurtherFloorsRequests(this.currentFloorNumber, this.queues.length)
                    && this.liftOccupants.size() < this.capacity) {
                return Direction.UP;
            } else {
                return this.currentDirection;
            }
        }
    }

    private void letPeopleOut() {
        this.liftOccupants.removeIf(e -> e == this.currentFloorNumber);
    }

    private int[] letPeopleIn() {
        int personCounter = 0;
        List<Integer> remainingFloorQueue = new LinkedList<>();
        int currentFloorQueue[] = this.queues[this.currentFloorNumber];
        while (personCounter < currentFloorQueue.length) {
            if ((this.currentDirection == Direction.UP && currentFloorQueue[personCounter] > this.currentFloorNumber && this.liftOccupants.size() < this.capacity)
                    || (this.currentDirection == Direction.DOWN && currentFloorQueue[personCounter] < this.currentFloorNumber) && this.liftOccupants.size() < this.capacity) {
                this.liftOccupants.add(currentFloorQueue[personCounter]);
            } else {
                remainingFloorQueue.add(currentFloorQueue[personCounter]);
            }
            personCounter++;
        }
        return remainingFloorQueue.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean checkFurtherFloorsRequests(int fromFloor, int toFloor) {
        return Arrays.stream(Arrays.copyOfRange(this.queues, fromFloor, toFloor)).anyMatch(e -> e.length > 0);
    }

    private boolean checkCurrentFloorRequests() {
        return this.currentDirection == Direction.UP
                ? Arrays.stream(this.queues[this.currentFloorNumber]).anyMatch(e -> e > this.currentFloorNumber)
                : Arrays.stream(this.queues[this.currentFloorNumber]).anyMatch(e -> e < this.currentFloorNumber);
    }

    private boolean checkLiftOccupantsRequests() {
        return this.currentDirection == Direction.UP
                ? this.liftOccupants.stream().max(Integer::compare).orElse(this.currentFloorNumber) > this.currentFloorNumber
                : this.liftOccupants.stream().min(Integer::compare).orElse(this.currentFloorNumber) < this.currentFloorNumber;
    }
}
