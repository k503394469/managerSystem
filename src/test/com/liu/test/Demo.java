package com.liu.test;

public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        Ji ji = demo.new Ji(demo);
        Ou ou = demo.new Ou(demo);
        new Thread(ji).start();
        new Thread(ou).start();
    }

    boolean flag;

    public class Ji implements Runnable {
        Demo d;
        public Ji(Demo d) {
            this.d = d;
        }

        public void run() {
            int i = 1;
            while (i < 100) {
                synchronized (d) {
                    if (!d.flag) {
                        System.out.println("奇线程:" + i);
                        i += 2;
                        d.flag = true;
                        d.notify();
                    } else {
                        try {
                            d.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public class Ou implements Runnable {
        Demo d;
        public Ou(Demo d) {
            this.d = d;
        }
        public void run() {
            int i = 2;
            while (i <= 100) {
                synchronized (d) {
                    if (flag) {
                        System.out.println("偶线程:" + i);
                        i += 2;
                        d.flag = false;
                        d.notify();
                    } else {
                        try {
                            d.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
