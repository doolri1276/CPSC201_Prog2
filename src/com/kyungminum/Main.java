package com.kyungminum;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {



    public static void main(String[] args) {

        //파일 있으면 삭제
        File file = new File("data.bin");
        if(file.exists()) file.delete();

        ProcessFile processFile = new ProcessFile("data.bin");
        
        System.out.println(processFile.addAccount(900, "gegwe", "egwg", 4005));
        System.out.println(processFile.addAccount(800, "faf", "egwg", 1009));
        System.out.println(processFile.addAccount(700, "gegefewwe", "egwg", 2097));
//        System.out.println(processFile.addAccount(600, "wafw", "egwg", 989));
//        System.out.println(processFile.addAccount(500, "wf", "egwg", 650));
//        System.out.println(processFile.addAccount(400, "va", "egwg", 0));
//        System.out.println(processFile.addAccount(300, "vw", "egwg", 3.14));
//        System.out.println(processFile.addAccount(200, "ef", "egwg", 3098));
//        System.out.println(processFile.addAccount(100, "aewg", "egwg", 1678));


        processFile.displayFile();

        System.out.println("============");
        System.out.println(processFile.removeAccount(700));
//        System.out.println(processFile.removeAccount(800));
//        System.out.println(processFile.removeAccount(900));




        processFile.displayFile();

        processFile.close();

    }



}
