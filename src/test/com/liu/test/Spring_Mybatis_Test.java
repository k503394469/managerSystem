package com.liu.test;

import com.liu.dao.ManagerDao;
import com.liu.dao.StudentDao;
import com.liu.domain.Manager;
import com.liu.domain.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_Mybatis_Test {
    @Test
    public void test1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String names:beanDefinitionNames){
            System.out.println(names);
        }
        ManagerDao managerDao=(ManagerDao) ctx.getBean("manager");
        Manager manager=new Manager();
        manager.setAccount("manaTest1");
        manager.setPassword("abc123");
        Manager manager1 = managerDao.loginCheck(manager);
        if (manager1!=null){
            System.out.println(manager1);
        }
    }
}
