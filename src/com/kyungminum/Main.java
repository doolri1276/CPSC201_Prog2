package com.kyungminum;

import java.io.IOException;
import java.util.Random;

public class Main {



    public static void main(String[] args) {
        ProcessFile processFile = new ProcessFile("data.bin");

        Random r = new Random();
        System.out.println(processFile.addAccount(900, "gegwe", "egwg", r.nextDouble()/100));
//        System.out.println(processFile.addAccount(800, "faf", "egwg", r.nextDouble()/100));
//        System.out.println(processFile.addAccount(700, "gegefewwe", "egwg", r.nextDouble()/100));
//        System.out.println(processFile.addAccount(600, "wafw", "egwg", r.nextDouble()/100));
//        System.out.println(processFile.addAccount(500, "wf", "egwg", r.nextDouble()/100));
//        System.out.println(processFile.addAccount(400, "va", "egwg", r.nextDouble()/100));
//        System.out.println(processFile.addAccount(300, "vw", "egwg", r.nextDouble()/100));
//        System.out.println(processFile.addAccount(200, "ef", "egwg", r.nextDouble()/100));
//        System.out.println(processFile.addAccount(100, "aewg", "egwg", r.nextDouble()/100));


        processFile.displayFile();

        processFile.close();

    }



}
