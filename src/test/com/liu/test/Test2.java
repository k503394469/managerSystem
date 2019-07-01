package com.liu.test;

import org.apache.ibatis.io.Resources;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.xml.parsers.*;

public class Test2 {
    public static void main(String[] args) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(sdf.format(date));
    }
    @Test
    public void xmlTest() throws Exception{
        InputStream in = Resources.getResourceAsStream("mybatisConfig.xml");
        System.out.println();

    }
}
