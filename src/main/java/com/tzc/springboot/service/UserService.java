package com.tzc.springboot.service;

import com.tzc.springboot.entity.User;

import java.util.List;

/**
 * @Author: lujinqi
 * @Date: 2018/7/24 13:38
 */

public interface UserService {

    /**
     * 根据用户ID查找用户
     * @param id
     * @return
     */
    User selectById(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();

    /**
     * 根据用户ID删除用户
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 新增一个用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新一个用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 新建一个用户并返回他的ID
     * @param user
     * @return
     */
    int insertUserReturnId(User user);


    List<User> selectByIds(List<Integer> list);


    /**
     * redis测试
     * @return
     */
    User getUser(Integer id);


    int deleteUser(Integer id);



}
