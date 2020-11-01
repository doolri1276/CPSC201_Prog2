package com.kyungminum;

import java.io.IOException;

public class Main {



    public static void main(String[] args) {
        ProcessFile processFile = new ProcessFile("data.bin");
        try {
            processFile.searchPointer(100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
