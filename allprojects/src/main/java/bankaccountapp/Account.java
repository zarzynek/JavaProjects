/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountapp;

import java.math.BigDecimal;

/**
 *
 * @author adamw
 */
public abstract class Account implements IBaseRate {

    //List common properties for Savings and Checking accounts
    private int socialSecurityNumber;
    private static int idCounter = 10000;
    private int id;
    private BigDecimal balance;

    // Write constructor to set up base properties and initialize the account
    public Account(int aSocialSecurityNumber) {
        this.balance = new BigDecimal("0");
        this.socialSecurityNumber = aSocialSecurityNumber;
        
        this.id = Account.idCounter;
        Account.idCounter++;
    }

    // List common methods (deposit withdraw transfer showInfo etc)
    public void deposit(BigDecimal anAmount) {
        this.balance = balance.add(anAmount);
    }

    public void withdraw(BigDecimal anAmount) {
        this.balance = balance.subtract(anAmount);
    }

    public void transfer(BigDecimal anAmount, Account anAccount) {
        this.withdraw(anAmount);
        anAccount.deposit(anAmount);
    }

    abstract void showInfo();

    //Setters and Getters
    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
