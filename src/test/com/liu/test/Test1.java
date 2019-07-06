package com.liu.test;

import com.liu.dao.StudentDao;
import com.liu.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    StudentDao studentDao = null;

    @Test
    public void test() throws Exception{
        in = Resources.getResourceAsStream("mybatisConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        sqlSession = factory.openSession();
        studentDao = sqlSession.getMapper(StudentDao.class);
        List <Integer> ids=new ArrayList<Integer>();
        ids.add(4);
        ids.add(18);
        List<Student> studentByIds = studentDao.findStudentByIds(ids);
        for (Student ii:studentByIds){
            System.out.println(ii);
        }
        sqlSession.close();
        in.close();
    }
}
