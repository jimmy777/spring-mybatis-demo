package com.example.test;

import com.example.domain.User;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class UserTest {

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("----- 先执行 --------");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("----- 后执行 --------");
    }

    @Test
    public void testInsertAUser() {
        SqlSession session = MySqlSessionFactory.getSqlSession();
        User user = new User("admin", "男", 26);

        session.insert("mapper.UserMapper.save", user);
        session.commit();
        session.close();
    }

    @Test
    public void testFindAUser(){
        SqlSession session = MySqlSessionFactory.getSqlSession();
        User one = session.selectOne("mapper.UserMapper.selectOne", 3);
        System.out.println(one);

        session.close();

    }

    @Test
    public void testUpdateAUser(){
        SqlSession session = MySqlSessionFactory.getSqlSession();
        User one = session.selectOne("mapper.UserMapper.selectOne", 4);

//        one.setId(3);
        one.setName("tom");
        one.setAge(25);

        session.update("mapper.UserMapper.update", one);
        session.commit();
        session.close();
    }

    @Test
    public void testDeleteAUser(){
        SqlSession session = MySqlSessionFactory.getSqlSession();

        session.delete("mapper.UserMapper.delete", 1);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectManyUsers() {
        SqlSession session = MySqlSessionFactory.getSqlSession();

        /*List<Map<String, Object>> list = session.selectList("mapper.UserMapper.selectMany");
        for (Map<String, Object> row: list){
            System.out.println(row);
        }*/

        List<User> list = session.selectList("mapper.UserMapper.selectMany");
        for (User user: list) {
            System.out.println(user);
        }

        session.close();
    }

    @Test
    public void testSelectManyUsersMap() {
        SqlSession session = MySqlSessionFactory.getSqlSession();

        List<User> list = session.selectList("mapper.UserMapper.selectMany2");
        for(User user: list) {
            System.out.println(user);
        }

        session.close();
    }
}
