package com.kyungminum;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ProcessFile {
    private static final int FILE_SIZE = 3;
    private RandomAccessFile f;
    private long DP = -1;
    private long FP = -1;
    private long cur = -1;
    private int size = 0;

    ProcessFile(String fileName){
        try {
            f = new RandomAccessFile(fileName, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        displayFile();

    }

    public void initializeFile(){
        try {
            f.writeLong(-1);
            f.writeLong(16);
            cur = f.getFilePointer();
            Block b = new Block();

            for(int i=0;i<FILE_SIZE;i++){
                cur = f.getFilePointer();
                if(i==0)b.setPrev(-1);
                else b.setPrev(cur-Block.SIZE);
                if(i==FILE_SIZE-1) b.setNext(-1);
                else b.setNext(cur+Block.SIZE);
                b.write(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayFile(){
        cur = 0;
        size = 0;

        System.out.println("============================");
        try {
            f.seek(cur);
            DP = f.readLong();
            System.out.println("DP : "+DP);
            FP = f.readLong();
            System.out.println("FP : "+FP);
            cur = f.getFilePointer();
            Block b = new Block();
            for (int i=0;i<FILE_SIZE;i++){
                b.read(f);
                if(b.getRecord().getAccountNumber()!= -1) size++;
                b.display();

            }

            System.out.println("SIZE : "+size+" ==============");
            System.out.println();
        } catch (IOException e) {
            initializeFile();
            displayFile();
        }




    }

    public boolean addAccount(int accountName, String firstname, String lastname, double balance){
        return addAccount(new Record(accountName, firstname, lastname, balance));
    }

    public boolean addAccount(int accountName, char[] firstname, char[] lastname, double balance){
        return addAccount(new Record(accountName, firstname, lastname, balance));
    }

    public boolean addAccount(Record r){
        if (this.size == FILE_SIZE){
            return false;
        }
        int acct = r.getAccountNumber();
        long cur = FP;

        Block b = new Block();

        if (size == 0){
            try{
                DP = cur;
                f.seek(0);
                f.writeLong(DP);

                f.seek(cur);
                b.read(f);
                long next = b.getNext();
                b.setRecord(r);
                b.setPrev(-1L);
                b.setNext(-1L);
                f.seek(cur);
                b.write(f);

                size += 1;

                f.seek(next);
                b.read(f);
                b.setPrev(-1L);
                f.seek(next);
                b.write(f);

                FP = next;

                f.seek(8);
                f.writeLong(FP);

                return true;

            } catch (IOException e) {
                return false;
            }
        } else {
            if (searchPointer(acct) != -1L){
                System.out.println(searchPointer(acct));
                return false;
            }
            try{
                long prev = searchPrevPointer(acct);
                System.out.println(prev);
                if(prev == -1L){
                    size += 1;

                    f.seek(cur);
                    b.read(f);
                    long next = b.getNext();
                    b.setRecord(r);
                    b.setPrev(-1L);
                    b.setNext(DP);
                    f.seek(cur);
                    b.write(f);

                    if (size != FILE_SIZE){
                        f.seek(next);
                        b.read(f);
                        b.setPrev(-1L);
                        f.seek(next);
                        b.write(f);
                    }

                    f.seek(DP);
                    b.read(f);
                    b.setPrev(cur);
                    f.seek(DP);
                    b.write(f);

                    DP = cur;
                    f.seek(0);
                    f.writeLong(DP);

                    FP = next;
                    f.seek(8);
                    f.writeLong(FP);


                    return true;
                }
                f.seek(prev);
                long next = b.getNext();
                if(next == -1L){
                    f.seek(cur);
                    b.read(f);
                    long nextP = b.getNext();
                    b.setRecord(r);
                    b.setPrev(prev);
                    b.setNext(-1L);
                    f.seek(cur);
                    b.read(f);

                    f.seek(prev);
                    b.read(f);
                    b.setNext(FP);
                    f.seek(prev);
                    b.read(f);

                    f.seek(nextP);
                    b.read(f);
                    b.setPrev(-1L);
                    f.seek(nextP);
                    b.read(f);

                    FP = nextP;
                    f.seek(8);
                    f.writeLong(FP);
                    size += 1;

                    return true;
                }
                else{
                    f.seek(cur);
                    b.setRecord(r);
                    b.setPrev(prev);
                    b.setNext(next);

                    f.seek(prev);
                    b.setNext(cur);

                    f.seek(next);
                    b.setPrev(cur);

                    FP+=b.SIZE;
                    size += 1;

                    return true;
                }
            } catch (IOException e){
                return false;
            }
        }
    }

    public Block searchBlock(int acct) {
        if(DP == -1) return null;
        long cur = DP;
        Block b = new Block();
        Record r = new Record();
        try {
            f.seek(cur);
        } catch (IOException e) {
            return null;
        }
        b.read(f);
        r = b.getRecord();
        while(r.getAccountNumber()<acct){
            cur = b.getNext();
            if (cur == -1){
                break;
            }
            try {
                f.seek(cur);
            } catch (IOException e) {
                return null;
            }
            b.read(f);
            r = b.getRecord();
        }
        if (cur == -1){
            return null;
        }
        if (acct > r.getAccountNumber()){
            return null;
        }
        return b;
    }


    public long searchPrevPointer(int acct) {
        if(DP == -1) return -1L;
        long cur = DP;
        Block b = new Block();
        Record r = new Record();
        try {
            f.seek(cur);
        } catch (IOException e) {
            return -1L;
        }
        b.read(f);
        r = b.getRecord();
        while(r.getAccountNumber()<acct){
            System.out.println(cur);
            cur = b.getNext();
            if (cur == -1){
                break;
            }
            try {
                f.seek(cur);
            } catch (IOException e) {
                return -1L;
            }
            b.read(f);
            r = b.getRecord();
        }
        if (cur == -1){
            return -1l;
        }
        if (acct > r.getAccountNumber()){
            return cur;
        }
        return -1L;
    }

    public boolean removeAccount(int accountName){
        if(DP == -1) return false;


        Block b = searchBlock(accountName);
        long cur = searchPointer(accountName);

        if(b == null || cur == -1L) return false;

        try{
            if(b.getPrev() == -1){
                f.seek(0);
                DP = b.getNext();
                f.writeLong(DP);

                b.clearRecord();
                b.setPrev(-1);
                b.setNext(FP);
                f.seek(cur);
                b.write(f);

                f.seek(FP);
                b.read(f);
                b.setPrev(cur);
                f.seek(FP);
                b.write(f);

                FP = cur;
                f.seek(8);
                f.writeLong(FP);


                if(DP!=-1){
                    f.seek(DP);
                    b.read(f);
                    b.setPrev(-1);
                    f.seek(DP);
                    b.write(f);
                }


            }else if(b.getNext()==-1){
                b.setNext(FP);
                FP = cur;
                f.seek(8);
                f.writeLong(FP);

                b.clearRecord();
                long prev = b.getPrev();
                b.setPrev(-1L);
                f.seek(cur);
                b.write(f);

                long next = b.getNext();
                f.seek(next);
                b.read(f);
                b.setPrev(cur);
                f.seek(next);
                b.write(f);

                f.seek(prev);
                b.read(f);
                b.setNext(-1L);
                f.seek(prev);
                b.write(f);

            }else{
                long prev = b.getPrev(), next = b.getNext(), fp = FP;
                b.clearRecord();
                b.setPrev(-1);
                b.setNext(FP);
                FP = cur;
                f.seek(8);
                f.writeLong(FP);

                f.seek(prev);
                b.read(f);
                b.setNext(next);
                f.seek(prev);
                b.write(f);

                f.seek(next);
                b.read(f);
                b.setPrev(prev);
                f.seek(next);
                b.write(f);

                f.seek(fp);
                b.read(f);
                b.setPrev(cur);
                f.seek(fp);
                b.write(f);
            }
            size--;
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long searchPointer(int acct) {
        if(DP == -1) return -1L;
        long cur = DP;
        Block b = new Block();
        Record r = new Record();
        try {
            f.seek(cur);
        } catch (IOException e) {
            return -1L;
        }
        b.read(f);
        r = b.getRecord();
        while(r.getAccountNumber()<acct){
            cur = b.getNext();
            if (cur == -1){
                break;
            }
            try {
                f.seek(cur);
            } catch (IOException e) {
                return -1L;
            }
            b.read(f);
            r = b.getRecord();
        }
        if (cur == -1){
            return -1L;
        }
        if (acct != r.getAccountNumber()){
            return -1L;
        }
        return cur;
    }

    public void close(){
        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}