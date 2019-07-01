package com.liu.controller;

import org.apache.ibatis.io.Resources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

@WebServlet(name = "gotoExit",value = "/gotoExit")
public class GotoExit extends HttpServlet {
    Properties properties=null;
    OutputStream out=null;
    Integer times=0;
    String path=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            out=new FileOutputStream(path);
            times = (Integer) request.getServletContext().getAttribute("times");
            properties=new Properties();
            properties.setProperty("times_of_view", String.valueOf(times));
            properties.store(out,"viewTime");
        }
        request.getSession().invalidate();
        response.sendRedirect("/managerSystem/login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    public void init() throws ServletException {
        try {
            path = GotoExit.class.getResource("/times_of_connected.properties").getPath().replaceAll("%20"," ");
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
