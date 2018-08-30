package com.tzc.springboot.mapper;

import com.tzc.springboot.entity.User;
import com.tzc.springboot.util.MyMapper;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

    /**
     * 新建一个用户并返回ID
     * @param user
     * @return
     */
    int insertUserReturnId(User user);

    int updateUser(User user);

    List<User> selectUserByIds(List<Integer> list);

}