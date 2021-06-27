/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import liftapp.Lift;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author adamw
 */
public class OopLiftTest {

    @Test
    public void testTrickyQueues() {
        final int[][] queues = {
            new int[0],
            new int[]{0, 0, 0, 6},
            new int[0],
            new int[0],
            new int[0],
            new int[]{6, 6, 0, 0, 0, 6},
            new int[0],};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 1, 5, 6, 5, 1, 0, 1, 0}, testLift.runLift());
    }

    @Test
    public void testUpAndDown() {
        final int[][] queues = {
            new int[]{3},
            new int[]{2},
            new int[]{0},
            new int[]{2},
            new int[0],
            new int[0],
            new int[]{5},};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 1, 2, 3, 6, 5, 3, 2, 0}, testLift.runLift());
    }

    @Test
    public void testDownAndDown() {
        final int[][] queues = {
            new int[0],
            new int[]{0},
            new int[0],
            new int[0],
            new int[]{2},
            new int[]{3},
            new int[0],};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 5, 4, 3, 2, 1, 0}, testLift.runLift());
    }

    @Test
    public void testEmpty() {
        final int[][] queues = {
            new int[0],
            new int[0],
            new int[0],
            new int[0],
            new int[0],
            new int[0],
            new int[0],};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0}, testLift.runLift());
    }

    @Test
    public void testBugs() {
        final int[][] queues = {
            new int[]{8, 8, 6},
            new int[]{8, 3, 4, 7},
            new int[0],
            new int[]{2, 6, 8, 5},
            new int[0],
            new int[]{0, 8, 8, 4, 1},
            new int[0],
            new int[]{3, 2, 2, 3},
            new int[0],};
        Lift testLift = new Lift(queues, 9);
        assertArrayEquals(new int[]{0, 1, 3, 4, 5, 6, 7, 8, 7, 5, 4, 3, 2, 1, 0}, testLift.runLift());
    }

    @Test
    public void testDown() {
        final int[][] queues = {
            new int[0],
            new int[0],
            new int[]{1, 1},
            new int[0],
            new int[0],
            new int[0],
            new int[0],};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 2, 1, 0}, testLift.runLift());
    }

    @Test
    public void testYoYo() {
        final int[][] queues = {
            new int[0],
            new int[0],
            new int[]{4, 4, 4, 4},
            new int[0],
            new int[]{2, 2, 2, 2},
            new int[0],
            new int[0],};
        Lift testLift = new Lift(queues, 2);
        assertArrayEquals(new int[]{0, 2, 4, 2, 4, 2, 0}, testLift.runLift());
    }

    @Test
    public void testUp() {
        final int[][] queues = {
            new int[0],
            new int[0],
            new int[]{5, 5, 5},
            new int[0],
            new int[0],
            new int[0],
            new int[0],};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 2, 5, 0}, testLift.runLift());
    }

    @Test
    public void testUpAndUp() {
        final int[][] queues = {
            new int[0],
            new int[]{3},
            new int[]{4},
            new int[0],
            new int[]{5},
            new int[0],
            new int[0],};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 0}, testLift.runLift());
    }

    @Test
    public void testHighlander() {
        final int[][] queues = {
            new int[0],
            new int[]{2},
            new int[]{3, 3, 3},
            new int[]{1},
            new int[0],
            new int[0],
            new int[0],};
        Lift testLift = new Lift(queues, 1);
        assertArrayEquals(new int[]{0, 1, 2, 3, 1, 2, 3, 2, 3, 0}, testLift.runLift());
    }

    @Test
    public void testFireDrill() {
        final int[][] queues = {
            new int[0],
            new int[]{0, 0, 0, 0},
            new int[]{0, 0, 0, 0},
            new int[]{0, 0, 0, 0},
            new int[]{0, 0, 0, 0},
            new int[]{0, 0, 0, 0},
            new int[]{0, 0, 0, 0},};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 6, 5, 4, 3, 2, 1, 0, 5, 4, 3, 2, 1, 0, 4, 3, 2, 1, 0, 3, 2, 1, 0, 1, 0},
                testLift.runLift());
    }

    @Test
    public void testEnterOnGroundFloor() {
        final int[][] queues = {
            new int[]{1, 2, 3, 4},
            new int[0],
            new int[0],
            new int[0],
            new int[0],
            new int[0],
            new int[0],};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 0}, testLift.runLift());
    }

    @Test
    public void testLiftFullDown() {
        final int[][] queues = {
            new int[0],
            new int[0],
            new int[0],
            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            new int[0],
            new int[0],
            new int[0],};
        Lift testLift = new Lift(queues, 5);
        assertArrayEquals(new int[]{0, 3, 1, 3, 1, 3, 1, 0}, testLift.runLift());
    }
}
