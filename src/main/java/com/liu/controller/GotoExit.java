package com.liu.controller;

import com.liu.utils.ViewTimesRecord;
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
    OutputStream out=null;
    String path=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        synchronized (this){
//            ViewTimesRecord vtr=new ViewTimesRecord();
//            vtr.viewController(request,request.getServletContext(),"/times_of_connected.properties");
//        }

        request.getSession().invalidate();
        response.sendRedirect("/managerSystem/login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    public void init() throws ServletException {
        try {
            path = this.getClass().getResource("/times_of_connected.properties").getPath().replaceAll("%20"," ");
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
