/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liftapp;

import java.util.Arrays;

/**
 *
 * @author adamw
 */
public class LiftApp {
    public static void main(String[] args)
    {
        final int[][] queues = {
            new int[0],
            new int[]{0, 0, 0, 6},
            new int[0],
            new int[0],
            new int[0],
            new int[]{6, 6, 0, 0, 0, 6},
            new int[0],};
        Lift aLift = new Lift(queues, 5);
        
        System.out.println(Arrays.toString(aLift.runLift()));
    }
    
}
