package com.kyungminum;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ProcessFile {
    private static final int FILE_SIZE = 10;
    private RandomAccessFile f;
    private long DP = -1;
    private long FP = -1;
    private long cur = -1;

    ProcessFile(String fileName){
        try {
            f = new RandomAccessFile(fileName, "rw");

            displayFile();

        } catch (EOFException e) {
            //Initialize file
            initializeFile();
            try {
                displayFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

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
                //b.display();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayFile() throws IOException {
        cur = 0;


        f.seek(cur);
        DP = f.readLong();
        System.out.println("DP : "+DP);
        FP = f.readLong();
        System.out.println("FP : "+FP);
        cur = f.getFilePointer();
        Block b = new Block();
        for (int i=0;i<FILE_SIZE;i++){
            b.read(f);
            b.display();

        }


    }

    public void addAccount(int accountName, char[] firstname, char[] lastname, double balance){

    }

    public Block seek(long actNumber){

        return null;
    }

    public void removeAccount(int accountName){

    }
}
