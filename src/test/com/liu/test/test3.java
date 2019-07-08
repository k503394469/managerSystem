package com.liu.test;

import com.liu.dao.StudentDao;
import com.liu.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class test3 {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    StudentDao studentDao = null;
    @Test
    public void test1() throws Exception {
        in = Resources.getResourceAsStream("mybatisConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        sqlSession = factory.openSession();
        studentDao = sqlSession.getMapper(StudentDao.class);
        Student student=new Student();
        student.setId(1);
        student.setPassword("abc123");
        Student check = studentDao.loginCheck(student);
        System.out.println(check.getName());
    }
}
