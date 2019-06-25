package com.liu.controller;

import com.liu.dao.StudentDao;
import com.liu.domain.Score;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "gotoStudentManager", value = "/gotoStudentManager")

public class GotoStudentManager extends HttpServlet {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    StudentDao studentDao = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        sqlSession.clearCache();
        if ("view".equals(method)) {
            sqlSession.clearCache();
            Integer pageNow= Integer.valueOf(request.getParameter("page"));
            final Integer pageSize=10;
            Integer rowCount=studentDao.totalStudent();//総記録数
            int totalPage=(rowCount/pageSize==0)?(rowCount/pageSize):(rowCount/pageSize)+1;
            request.getSession().setAttribute("totalPage",totalPage);
            Map<String,Integer> pageInfo=new LinkedHashMap<String, Integer>();
            pageInfo.put("pageNow",(pageNow-1)*pageSize);
            pageInfo.put("pageSize",pageSize);
            List<Student> allStudent = studentDao.getAllStudent(pageInfo);

            request.setAttribute("allStudent",allStudent);
            request.setAttribute("totalPage",totalPage);
            request.setAttribute("pageNow",pageNow);
            System.out.println(pageNow);

            request.getRequestDispatcher("/WEB-INF/viewPage/studentView.jsp").forward(request, response);
        } else if ("add".equals(method)) {
            request.getRequestDispatcher("/WEB-INF/viewPage/addStudent.jsp").forward(request, response);
        } else if ("update".equals(method)) {
            Student tempStu = studentDao.findStudentById(Integer.valueOf(request.getParameter("id")));
            request.setAttribute("tempStu", tempStu);
            request.getRequestDispatcher("/WEB-INF/viewPage/updateStudent.jsp").forward(request, response);
        } else if ("delete".equals(method)) {
            Integer result = studentDao.deleteStudent(Integer.valueOf(request.getParameter("id")));
            sqlSession.commit();
            if (result > 0) {
                List<Student> studentList = studentDao.findAll();
                request.setAttribute("studentList", studentList);
                request.getRequestDispatcher("/gotoStudentManager?method=view&page=1").forward(request, response);
            } else {
                request.setAttribute("result", "DeleteFailed");
                request.getRequestDispatcher("/WEB-INF/viewPage/result.jsp").forward(request, response);
            }
        } else if ("look".equals(method)) {
            Student studentScore = studentDao.checkStuScore(Integer.valueOf(request.getParameter("id")));
            int result = 0;
            for (Score s : studentScore.getScores()) {
                result += s.getPoint();
            }
            request.setAttribute("result", result);
            request.setAttribute("studentScore", studentScore);
            request.getRequestDispatcher("/WEB-INF/viewPage/scoreLook.jsp").forward(request, response);
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
