package com.liu.studentController;

import com.liu.dao.ManagerDao;
import com.liu.dao.StudentDao;
import com.liu.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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

@WebServlet(name = "gotoMyController",value = "/gotoMyController")
public class GotoMyController extends HttpServlet {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    StudentDao studentDao = null;
    Properties properties=null;
    Integer times=0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        if (id == null || id == "" || password == null || password == "") {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        Student check=new Student();
        check.setId(Integer.valueOf(id));
        check.setPassword(password);
        if (check!=null){
            ServletContext servletContext = request.getServletContext();
            servletContext.setAttribute("times",times);
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", check);
            System.out.println("success" + check);
            request.getRequestDispatcher("/WEB-INF/studentPage/studentMan.jsp").forward(request, response);
            times++;
        }else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    public void init() throws ServletException {
        try {
            in = Resources.getResourceAsStream("mybatisConfig.xml");
            builder = new SqlSessionFactoryBuilder();
            factory = builder.build(in);
            sqlSession = factory.openSession();
            studentDao = sqlSession.getMapper(StudentDao.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            sqlSession.close();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
