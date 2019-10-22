package com.liu.test;

import org.junit.Test;

import java.io.*;
import java.net.*;

public class NetTest {
    @Test
    public void t1()throws Exception{
        InetAddress address=InetAddress.getByName("23.200.231.127");
        String hostName = address.getHostName();
        System.out.println(hostName);
        System.out.println(address.getHostAddress());
    }
    @Test
    public void t2()throws Exception{
        //client
        Socket s=new Socket("10.235.10.76",10000);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String send;
        while ((send=br.readLine())!=null){
            if ("886".equals(send)){
                bw.write(send,0,send.length());
                break;
            }else {
                bw.write(send,0,send.length());
            }
        }
        bw.close();
        br.close();
    }
    @Test
    public void t3()throws Exception{
        //Server
        ServerSocket ss=new ServerSocket(10000);
        Socket accept = ss.accept();
    }
}
