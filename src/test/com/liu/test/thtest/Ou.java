package com.liu.test.thtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ou implements Runnable {
    Demo d;
    public Ou(Demo d) {
        this.d = d;
    }
    public void run() {
        int i=2;
        d.setFlag(false);
        while (i<=100){
            synchronized (d) {
                if (!d.isFlag()) {
                    System.out.println("奇线程:" + i);
                    i += 2;
                    d.setFlag(true);
                    d.notify();
                } else {
                    try {
                        d.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
