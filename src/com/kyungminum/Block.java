package com.kyungminum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Block {
    public static final int SIZE = 46;
    private Record record;
    private long next;
    private long prev;

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
            prev = f.readLong();
            next = f.readLong();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(RandomAccessFile f) throws IOException {
        record.write(f);
        f.writeLong(prev);
        f.writeLong(next);
    }

    public void display(){
        System.out.println(prev + "\t"+record.getAccountNumber()+"\t"+next);
        System.out.println(record.getFirstName()+" "+record.getLastName()+" "+record.getBalance());
        System.out.println();
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
        this.next = next;
    }

    public long getPrev() {
        return prev;
    }

    public void setPrev(long prev) {
        this.prev = prev;
    }
}
