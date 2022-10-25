package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
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

    @Test
    void findById(){
        String id = "gil";
        assertThrows(NoSuchElementException.class, () -> userDao.findById(id));
    }
}//
