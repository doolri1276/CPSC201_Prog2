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
    }

    public void read(RandomAccessFile f){

    }

}
