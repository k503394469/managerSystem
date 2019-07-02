package com.liu.test;

import com.liu.controller.GotoExit;
import org.apache.ibatis.io.Resources;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import javax.xml.parsers.*;

import static java.lang.System.out;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
    @Test
    public void xmlTest() throws Exception{
        String path = this.getClass().getResource("/times_of_connected.properties").getPath().replaceAll("%20"," ");
        OutputStream out=new FileOutputStream(path);

        Properties properties=new Properties();
        properties.setProperty("times_of_view", 89+"");
        properties.store(out,"viewTime");
    }
}
