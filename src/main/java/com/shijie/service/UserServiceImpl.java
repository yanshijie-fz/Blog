package com.shijie.service;

import com.shijie.dao.UserDao;
import com.shijie.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public User queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public User queryUserByName(String user_name) {
        return userDao.queryUserByName(user_name);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }
}
