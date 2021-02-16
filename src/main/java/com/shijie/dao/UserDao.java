package com.shijie.dao;


import com.shijie.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    User queryAllUser();

    User queryUserByName(String user_name);

    int addUser(User user);
}
