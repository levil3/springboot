package com.spt.springboot.dao;

import com.spt.springboot.pojo.User;
import com.spt.springboot.query.UserQuery;

import java.util.List;

public interface UserDao {

    // 根据用户名查询用户信息
    User queryUserByName(String name);

    // 插入用户信息
    int insertUser(User user);

    // 根据用户名，修改用户密码
    int updateUser(User user);

    // 根据用户名，删除用户记录
    int deleteUser(User user);

    // 通过指定条件查询用户集合
    List<User> queryUserByParams(UserQuery userQuery);
}
