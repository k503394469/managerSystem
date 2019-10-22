package com.liu.test.thtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ji implements Runnable {
    Demo d;

    public Ji(Demo d) {
        this.d = d;
    }

    public void run() {
        int i = 1;
        d.setFlag(true);
        while (i < 100) {
            synchronized (d) {
                if (d.isFlag()) {
                    System.out.println("奇线程:" + i);
                    i += 2;
                    d.setFlag(false);
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