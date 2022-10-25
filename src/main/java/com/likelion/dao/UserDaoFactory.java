package com.likelion.dao;
public class UserDaoFactory {
    public UserDao localUserDao(){
        UserDao userDao = new UserDao(new LocalConnectionMaker());
        return userDao;
    }
}
