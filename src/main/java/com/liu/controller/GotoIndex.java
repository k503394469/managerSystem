package com.liu.controller;

import com.liu.dao.ManagerDao;
import com.liu.domain.Manager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.dom4j.io.SAXReader;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "gotoIndex", value = "/gotoIndex")
public class GotoIndex extends HttpServlet {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    ManagerDao managerDao = null;
    Properties properties=null;
//    SAXReader saxReader=null;
//    Document doc=null;
    Integer times=0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if ("back".equals(method)) {
            if (request.getSession().getAttribute("userInfo") != null) {
                request.getRequestDispatcher("/WEB-INF/viewPage/manager.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("error", "sign in,please!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || username == "" || password == null || password == "") {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        Manager manager = new Manager();
        manager.setAccount(username);
        manager.setPassword(password);

        try {
            managerDao = sqlSession.getMapper(ManagerDao.class);
            Manager loginCheck = managerDao.loginCheck(manager);
            System.out.println(loginCheck);
//            if (loginCheck==null){
//                loginCheck=0;
//            }
            if (loginCheck!=null) {
                //次数
                ServletContext servletContext = request.getServletContext();
                servletContext.setAttribute("times",times);
                HttpSession session = request.getSession();
                session.setAttribute("userInfo", loginCheck);
                System.out.println("success" + loginCheck);
                request.getRequestDispatcher("/WEB-INF/viewPage/manager.jsp").forward(request, response);
                times++;
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                System.out.println("failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
        try {
//            saxReader=new SAXReader();
//            doc=saxReader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("times_of_connected.properties"));
//            Element rootEl=doc.getRootElement();
//            times= (Integer) rootEl.getData();
            properties=new Properties();
            properties.load(Resources.getResourceAsStream("times_of_connected.properties"));
            times= Integer.valueOf(properties.getProperty("times_of_view"));
            in = Resources.getResourceAsStream("mybatisConfig.xml");
            builder = new SqlSessionFactoryBuilder();
            factory = builder.build(in);
            sqlSession = factory.openSession();
            managerDao = sqlSession.getMapper(ManagerDao.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            this.sqlSession.close();
            this.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
