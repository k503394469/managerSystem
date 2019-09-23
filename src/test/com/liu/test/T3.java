package com.liu.test;

import java.util.Random;

public class T3 {
    public static void main(String[] args) {
        int i=0,s=0;

        do{

            if (i%2==0){

                i++;

                continue;

            }

            i++;

            s = s + i;

        } while (i<7);

        System.out.println(s);
    }

    public static boolean exist(int[] brr, int num) {
        for (int i = 0; i < 5; i++) {
            if (brr[i] == num) {
                return true;
            }
        }
        return false;
    }
}
