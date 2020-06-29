package com.example.test;

import com.example.domain.Student;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class StudentTest {

    @Test
    public void testSelectStudent(){
        SqlSession session = MySqlSessionFactory.getSqlSession();

        List<Student> list = session.selectList("mapper.StudentMapper.selectStudent");

        for (Student s: list ) {
            System.out.println(s);
        }

        session.close();
    }
}
