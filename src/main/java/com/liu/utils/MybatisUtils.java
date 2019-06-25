package com.liu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtils {
    InputStream in = null;
    SqlSessionFactoryBuilder builder = null;
    SqlSessionFactory factory=null;
    SqlSession session=null;
    public SqlSession init(SqlSessionFactoryBuilder builder)throws Exception{
        in= Resources.getResourceAsStream("mybatisConfig.xml");
        builder=new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session=factory.openSession();
        return session;

    }
    public void destory(SqlSession session)throws Exception{
        session.close();
        this.in.close();
    }
}
