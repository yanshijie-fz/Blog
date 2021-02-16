package com.shijie.service;

import com.shijie.pojo.User;

public interface UserService {

    User queryAllUser();

    User queryUserByName(String user_name);

    int addUser(User user);
}
