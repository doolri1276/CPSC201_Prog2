package com.kyungminum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Block {
    public static final int SIZE = 54;
    private Record record;
    private int next;
    private int prev;

    /**
     * Constructor
     * */
    Block(){
        record = new Record();
        next = -1;
        prev = -1;
    }

    /**
     * Methods
     * */
    public void read(RandomAccessFile f){
        try {
            record.read(f);
            prev = f.readInt();
            next = f.readInt();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(RandomAccessFile f) throws IOException {
        record.write(f);
        f.writeInt(prev);
        f.writeInt(next);
    }

    public void display(){
        System.out.println(toString());
    }

    public void clearRecord(){
        record = new Record();
    }

    /**
     * Getter and Setter
     * */
    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public long getNext() {
        return next;
    }

    public void setNext(long next) {
        this.next = (int) next;
    }

    public long getPrev() {
        return prev;
    }

    public void setPrev(long prev) {
        this.prev = (int) prev;
    }

    @Override
    public String toString() {
        return prev + "\t"+record.getAccountNumber()+"\t"+next+"\n"+
        "["+record.getFirstName()+"] ["+record.getLastName()+"] "+record.getBalance()+"\n";
    }
}
