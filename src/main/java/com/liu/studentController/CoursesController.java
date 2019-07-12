package com.liu.studentController;

import com.liu.dao.ScoreDao;
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
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "coursesController",value = "/coursesController")
public class CoursesController extends HttpServlet {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    StudentDao studentDao = null;
    ScoreDao scoreDao=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        if ("viewLesson".equals(method)){
            Student stuInfo=(Student) request.getSession().getAttribute("userInfo");
            Integer stuId = stuInfo.getId();
            Student student = studentDao.checkStuScore(stuId);
            int result = 0;
            for (Score s : student.getScores()) {
                result += s.getPoint();
            }
            request.setAttribute("result", result);
            request.setAttribute("studentScore", student);
            request.getRequestDispatcher("/WEB-INF/studentPage/myCourse.jsp").forward(request,response);

        }else if ("checkComm".equals(method)){
            String cid = request.getParameter("cid");
            String sid = request.getParameter("sid");
            Score score = new Score();
            score.setCid(Integer.valueOf(cid));
            score.setSid(Integer.valueOf(sid));

            String comment = scoreDao.lookComments(score);
            System.out.println(comment);
            request.setAttribute("comment",comment);
            request.getRequestDispatcher("/WEB-INF/studentPage/couComment.jsp").forward(request,response);
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
            scoreDao=sqlSession.getMapper(ScoreDao.class);
        }catch (Exception e){
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
