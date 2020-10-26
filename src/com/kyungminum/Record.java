package com.kyungminum;

import java.io.RandomAccessFile;

public class Record {
    private int accountNumber;
    private char[] firstName;
    private char[] lastName;
    private double balance;



    Record(){
        accountNumber = -1;
        firstName = new char[15];
        lastName = new char[15];
        balance = 0;
    }

    public void read(RandomAccessFile f){

    }

    public void write(RandomAccessFile f){

    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public char[] getFirstName() {
        return firstName;
    }

    public void setFirstName(char[] firstName) {
        this.firstName = firstName;
    }

    public char[] getLastName() {
        return lastName;
    }

    public void setLastName(char[] lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
