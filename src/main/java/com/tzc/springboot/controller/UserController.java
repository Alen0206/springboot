package com.tzc.springboot.controller;

import com.tzc.springboot.entity.User;
import com.tzc.springboot.response.Response;
import com.tzc.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lujinqi
 * @Date: 2018/7/24 13:44
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 根据用户ID返回用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Response<User> selectById(@PathVariable("id") Integer id) {
        long beginTime = System.currentTimeMillis();
        User user = userService.selectById(id);
        long endTime = System.currentTimeMillis();
        System.out.println("查询耗时" + (endTime - beginTime));
        return Response.ok(user);
    }

    /**
     * 返回所有用户信息
     *
     * @return
     */
    @GetMapping("/user")
    public Response<List<User>> selectAll() {
        List<User> userList = userService.selectAll();
        return Response.ok(userList);
    }

    /**
     * 根据用户ID删除用户信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public Response<Void> deleteById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return Response.ok();
    }

    /**
     * 新增一个用户
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    public Response<Integer> insertUser(@RequestBody User user) {
        int userId = userService.insertUserReturnId(user);
        return Response.ok(userId);
    }

    /**
     * 更新一个用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("/user/{id}")
    public Response<Void> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        if (userService.selectById(id) == null) {
            return Response.fail("用户不存在");
        }
        user.setId(id);
        userService.updateUser(user);
        return Response.ok();
    }

    /**
     * 传入id数组并获取对应用户信息
     * @param ids
     * @return
     */
    @GetMapping("/users")
    public Response<List<User>> selectByIds(@RequestParam("ids") Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        List<User> userList = userService.selectByIds(list);
        return Response.ok(userList);
    }

}
