/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import java.math.BigInteger;

/**
 *
 * @author adamw
 */
public class Checking extends Account {

    // List properties specific to Checking account
    private final int ACCOUNTTYPEID = 2;
    private final String accountNumber;
    private static BigInteger debitCardNumberCounter = new BigInteger("100000000000");
    private static int debitCardPinCounter = 9999;
    private  BigInteger debitCardNumber;
    private  int debitCardPin;

    // Write constructor for Checking account (on top of the Account contstructor)
    public Checking(int aSocialSecurityNumber) {
        // Call Account constructor
        super(aSocialSecurityNumber);
        // Set accountNumber
        this.accountNumber = "" + this.ACCOUNTTYPEID
                + String.valueOf(super.getSocialSecurityNumber()).substring(String.valueOf(super.getSocialSecurityNumber()).length() - 3)
                + String.valueOf(super.getId())
                + String.valueOf((int) (Math.random() * ((999 - 100) + 1)) + 100);
        // Assign debitCardNumber
        this.debitCardNumber = Checking.debitCardNumberCounter;
        Checking.debitCardNumberCounter = Checking.debitCardNumberCounter.add(new BigInteger("1"));
        // Ad debitCardPin
        this.debitCardPin = Checking.debitCardPinCounter;
        Checking.debitCardPinCounter--;
    }

    // Set debitCardNumber
    // implement showInfo
    public void showInfo() {
        System.out.println("Account Number: " + this.accountNumber + System.lineSeparator()
                + "Debit Card Number: " + this.debitCardNumber + System.lineSeparator()
                + "Debit Card PIN: " + this.debitCardPin);
    }

    // List methods specific to Checking account
    // Setters and getters
    public  BigInteger getDebitCardNumber() {
        return this.debitCardNumber;
    }

    public  void setDebitCardNumber(BigInteger debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    public  int getDebitCardPin() {
        return this.debitCardPin;
    }

    public  void setDebitCardPin(int debitCardPin) {
        this.debitCardPin = debitCardPin;
    }

}
