package com.liu.test;

import javafx.beans.binding.ObjectExpression;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class TTTTTTTTTTT {
    public static void main(String[] args) throws Exception{
//        Random r=new Random();
//        Integer []arr=new Integer[5];
//        for (int i=0;i<arr.length;i++){
//            arr[i]=r.nextInt(100);
//            System.out.println(arr[i]);
//        }
//        System.out.println("---------");
//       for (int start=0,end=arr.length-1;start<=end;start++,end--){
//           int temp=arr[start];
//           arr[start]=arr[end];
//           arr[end]=temp;
//       }
//       for (Integer j:arr){
//           System.out.println(j);
//       }
        Properties properties=new Properties();
        BufferedWriter bw=new BufferedWriter(new FileWriter("chat.txt",true));
        List<String> toFile=new ArrayList<String>();
        toFile.add("iii");
        toFile.add("ppp");
        for (String s:toFile){
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }
    @Test
    public void t1() throws Exception{
        BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream("moon.jpg"));
        BufferedInputStream fis=new BufferedInputStream(new FileInputStream("C:\\Users\\Mr.c\\Desktop\\Moon.jpg"));
        byte []b=new byte[4096];
        int len;
        while ((len=fis.read(b))!=-1){
//            System.out.println(new String(b,0,len));
            fos.write(b,0,len);
        }
        fis.close();
        fos.close();
    }
    @Test
    public void t2() throws Exception{
        BufferedWriter bw=new BufferedWriter(new FileWriter("3.txt"));
        BufferedReader br=new BufferedReader(new FileReader("2.txt"));
//        char []b=new char[4096];
//        int len;
//        while ((len=br.read())!=-1){
//            System.out.println(new String (b,0,len));
//        }
        int read;

        while ((read=br.read())!=-1){
            System.out.print((char) read);
        }
    }
    @Test
    public  void t3() throws Exception{
        ThreadDemo td1=new ThreadDemo();
        Thread t1=new Thread(td1,"一号");
        Thread t2=new Thread(td1,"二号");

        t1.start();
        t2.start();

    }
}
