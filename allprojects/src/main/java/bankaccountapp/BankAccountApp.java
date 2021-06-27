/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

/**
 *
 * @author adamw
 */
public class BankAccountApp {
    public static void main(String[] args)
    {
        Account acc1 = new Checking(12345);
        Account acc2 = new Checking(67890);
        acc1.showInfo();
        acc2.showInfo();
    }
    
}
