package com.liu.dao;

import com.liu.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    List<Student> findAll();
    Student checkStuScore(Integer id);
    Integer insertStudent(Student student);
    Integer updateStudent(Student student);
    Student findStudentById(Integer id);
    Integer deleteStudent(Integer id);
    Integer totalStudentForFuzzy(String name);
    List<Student> findStudentByName(Map<String,String> pageInfo);
    Integer totalStudent();
    List<Student> getAllStudent(Map<String,Integer> pageInfo);
}
