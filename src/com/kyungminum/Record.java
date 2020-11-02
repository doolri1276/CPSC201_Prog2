package com.kyungminum;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class Record {
    public static final int NAME_LIMIT = 15;
    private int accountNumber;
    private char[] firstName;
    private char[] lastName;
    private double balance;

    /**
     * Constructors
     * */
    Record(){
        this(-1, new char[0],new char[0],0);
    }

    public Record(int accountNumber, String fn, String ln, double balance) {
        this(accountNumber, fn.trim().toCharArray(), ln.trim().toCharArray(), balance);
    }

    Record(int accountNumber, char[] fn, char[] ln, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.firstName = new char[NAME_LIMIT];
        this.lastName = new char[NAME_LIMIT];
        for(int i=0;i<NAME_LIMIT;i++){
            firstName[i] = i<fn.length?fn[i]:' ';
            lastName[i] = i<ln.length?ln[i]:' ';
        }

    }

    /**
     * Methods
     * */
    public void read(RandomAccessFile f) throws IOException {
        accountNumber = f.readInt();
        firstName = f.readUTF().toCharArray();
        lastName = f.readUTF().toCharArray();
        balance = f.readDouble();
    }

    public void write(RandomAccessFile f) throws IOException {
        f.writeInt(accountNumber);
        f.writeUTF(String.valueOf(firstName));
        f.writeUTF(String.valueOf(lastName));
        f.writeDouble(balance);
    }

    /**
    * Getters and Setters
    * */
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return String.valueOf(firstName);
    }

    public void setFirstName(char[] firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return String.valueOf(lastName);
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
