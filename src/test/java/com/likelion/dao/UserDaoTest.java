package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao;

    @BeforeEach
    void beforeEach(){
        userDao = new UserDaoFactory().localUserDao();
    }


    @Test
    void addGet() throws SQLException {
        String id = "05";
        userDao.add(new User(id, "gildong","1123"));
        User findUser = userDao.findById(id);
        assertEquals(findUser.getName(),"05");
    }
}//
