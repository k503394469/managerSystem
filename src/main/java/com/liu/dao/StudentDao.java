package com.liu.dao;

import com.liu.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    Student loginCheck(Student student);
    List<Student> findAll();
    Student checkStuScore(Integer id);
    Integer insertStudent(Student student);
    Integer updateStudent(Student student);
    Student findStudentById(Integer id);
    Integer deleteStudent(Integer id);
    Integer totalStudentForFuzzy(String name);
    List<Student> findStudentByName(Map<String,Object> pageInfo);
    Integer totalStudent();
    List<Student> getAllStudent(Map<String,Integer> pageInfo);
    Integer batchInsertDel(List<Student> students);
    List<Student> findStudentByIds(@Param("ids") List<Integer> ids);
    Integer batchDeleteStudent(@Param("ids") List<Integer> ids);
    Integer insertTodel(Student delStu);
}
