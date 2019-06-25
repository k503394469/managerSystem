package com.liu.test;

import com.liu.dao.StudentDao;
import com.liu.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test1 {

    public static void main(String[] args) throws Exception {
        InputStream in = null;
        SqlSessionFactoryBuilder builder = null;
        SqlSessionFactory factory = null;
        SqlSession sqlSession = null;
        StudentDao studentDao = null;
        in = Resources.getResourceAsStream("mybatisConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        sqlSession = factory.openSession();
        studentDao = sqlSession.getMapper(StudentDao.class);

        Map<String, Integer> testMap = new LinkedHashMap<String, Integer>();
        testMap.put("pageNow", 0);
        testMap.put("pageSize", 5);
        List<Student> allStudent = studentDao.getAllStudent(testMap);
        for (Student u:allStudent){
            System.out.println(u);
        }
        System.out.println(studentDao.totalStudent());
        sqlSession.close();
        in.close();
    }
}
