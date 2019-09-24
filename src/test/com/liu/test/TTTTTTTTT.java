package com.liu.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TTTTTTTTT {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("F:/start/555.png");
        FileInputStream fis = new FileInputStream("F:/start/Python.png");
//        fos.write("hello".getBytes());
//        fos.write("123".getBytes());
//        fos.write("qwe".getBytes());
        byte[] r = new byte[2048];
        int length;
        while ((length=fis.read(r)) != -1) {
            fos.write(length);
        }
        fos.close();
        fis.close();
    }
}
