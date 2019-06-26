package com.liu.controller;

import com.liu.dao.ManagerDao;
import com.liu.domain.Manager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "gotoIndex", value = "/gotoIndex")
public class GotoIndex extends HttpServlet {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    ManagerDao managerDao = null;

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
            Boolean loginCheck = managerDao.loginCheck(manager);
            if (loginCheck) {
                HttpSession session = request.getSession();
                session.setAttribute("userInfo", manager);
                System.out.println("success" + manager);
                request.getRequestDispatcher("/WEB-INF/viewPage/manager.jsp").forward(request, response);
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
            in = Resources.getResourceAsStream("mybatisConfig.xml");
            builder = new SqlSessionFactoryBuilder();
            factory = builder.build(in);
            sqlSession = factory.openSession();
            managerDao = sqlSession.getMapper(ManagerDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        this.sqlSession.close();
        try {
            this.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
