package com.liu.test.thtest;

public class Main {
    public static void main(String[] args) {
        Demo d=new Demo();
        Ji ji=new Ji(d);
        Ou ou=new Ou(d);
        Thread t1=new Thread(ji);
        Thread t2=new Thread(ou);
        t1.start();
        t2.start();

    }
}
