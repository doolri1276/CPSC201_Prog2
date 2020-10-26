package com.kyungminum;

import java.io.RandomAccessFile;

public class Block {
    private Record record;
    private int next;
    private int prev;

    Block(){
        record = new Record();

    }

    public void read(RandomAccessFile f){
        record.read(f);
    }

    public void write(RandomAccessFile f){
        record.write(f);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }
}
