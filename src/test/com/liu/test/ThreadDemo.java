package com.liu.test;

public class ThreadDemo implements Runnable {
    private int temp = 100;

    public void run() {
        while (true) {
            synchronized (this) {
                if (temp > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + temp);
                    temp--;
                }
            }
        }

    }
}
