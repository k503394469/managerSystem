package com.liu.controller;

import com.liu.dao.StudentDao;
import com.liu.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "studentController",value = "/studentController")
public class StudentController extends HttpServlet {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    StudentDao studentDao = null;
    Student tempStu=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method=request.getParameter("method");
        if ("insertStu".equals(method)){
            tempStu=new Student();
            tempStu.setName(request.getParameter("name"));
            tempStu.setAge(Integer.valueOf(request.getParameter("age")));
            tempStu.setSex(request.getParameter("sex"));
            tempStu.setEmail(request.getParameter("email"));
            tempStu.setAddress(request.getParameter("address"));
            tempStu.setDept(request.getParameter("dept"));
            int result=studentDao.insertStudent(tempStu);
            sqlSession.commit();
            if (result>0){
                request.setAttribute("result","InsertSuccess");
                request.getRequestDispatcher("/WEB-INF/viewPage/result.jsp").forward(request,response);
            }else {
                request.setAttribute("result","InsertFailed");
                request.getRequestDispatcher("/WEB-INF/viewPage/result.jsp").forward(request,response);
            }
        }else if("update".equals(method)){
            tempStu=new Student();
            tempStu.setId(Integer.valueOf(request.getParameter("id")));
            tempStu.setName(request.getParameter("name"));
            tempStu.setAge(Integer.valueOf(request.getParameter("age")));
            tempStu.setSex(request.getParameter("sex"));
            tempStu.setEmail(request.getParameter("email"));
            tempStu.setAddress(request.getParameter("address"));
            tempStu.setDept(request.getParameter("dept"));
            int result = studentDao.updateStudent(tempStu);
            sqlSession.commit();
            if (result>0){
                request.setAttribute("result","UpdateSuccess");
                System.out.println(tempStu);
                request.getRequestDispatcher("/WEB-INF/viewPage/result.jsp").forward(request,response);
            }else {
                request.setAttribute("result","UpdateFailed");
                System.out.println(tempStu);
                request.getRequestDispatcher("/WEB-INF/viewPage/result.jsp").forward(request,response);
            }
        }else if ("fuzzy".equals(method)){
            String name=request.getParameter("name");
            List<Student> studentList = studentDao.findStudentByName("%" + name + "%");
            request.setAttribute("studentList",studentList);
            request.getRequestDispatcher("/WEB-INF/viewPage/studentView.jsp").forward(request,response);
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