package com.liu.controller;

import com.liu.dao.StudentDao;
import com.liu.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            sqlSession.clearCache();
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
            sqlSession.clearCache();
            String name=request.getParameter("name");
            if ((name==null||"".equals(name)||""==name)){
                request.setAttribute("result","please enter name!");
                request.getRequestDispatcher("/WEB-INF/viewPage/result.jsp").forward(request,response);
                return;
            }
            request.setAttribute("name",name);

            Integer pageNow= Integer.valueOf(request.getParameter("page"));
            if (pageNow<=0){
                pageNow=1;
            }
            final Integer pageSize=5;
            Integer rowCount=studentDao.totalStudentForFuzzy("%" + name + "%");//総記録数

            int totalPage=(rowCount%pageSize==0)?(rowCount/pageSize):(rowCount/pageSize)+1;
//            request.getSession().setAttribute("totalPage",totalPage);
            Map<String,Object> pageInfo=new LinkedHashMap<String, Object>();

            pageInfo.put("pageNow",(pageNow-1)*pageSize);
            pageInfo.put("pageSize",pageSize);
            pageInfo.put("name","%" + name + "%");
            List<Student> studentList = studentDao.findStudentByName(pageInfo);
            request.setAttribute("totalPage",totalPage);
            request.setAttribute("allStudent",studentList);
            request.setAttribute("pageNow",pageNow);
            request.setAttribute("method","fuzzy");
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
