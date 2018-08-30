package com.tzc.springboot.service.Impl;

import com.tzc.springboot.entity.User;
import com.tzc.springboot.mapper.UserMapper;
import com.tzc.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lujinqi
 * @Date: 2018/7/24 13:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int insertUserReturnId(User user) {
         userMapper.insertUserReturnId(user);
         return user.getId();
    }

    @Override
    public List<User> selectByIds(List<Integer> list) {
        return userMapper.selectUserByIds(list);
    }

    @Cacheable(value = "userInfo",key = "'userId'+#id")
    @Override
    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @CacheEvict(value = "userInfo",key = "'userId'+#id")
    @Override
    public int deleteUser(Integer id) {
        return 1;
    }
}
