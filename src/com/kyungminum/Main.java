package com.kyungminum;

import java.io.IOException;

public class Main {



    public static void main(String[] args) {
        ProcessFile processFile = new ProcessFile("data.bin");

        processFile.searchPointer(100);

    }



}
