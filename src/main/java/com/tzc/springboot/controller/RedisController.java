package com.tzc.springboot.controller;

import com.tzc.springboot.entity.User;
import com.tzc.springboot.response.Response;
import com.tzc.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

/**
 * @Author: lujinqi
 * @Date: 2018/7/25 10:38
 */
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private UserService userService;

    /**
     * redis新增
     * @return
     */
    @GetMapping("/setValue")
    public String setValue(){
        if (!template.hasKey("kenny")){
            template.opsForValue().append("kenny","zz");
            return "使用redis缓存数据成功";
        }else {
            template.delete("kenny");
            return "key已存在";
        }
    }

    /**
     * redis查询
     * @return
     */
    @GetMapping("/getValue")
    public String getValue(){
        if (!template.hasKey("kenny")){
            return "key不存在，请先保存数据";
        }else {
            String kenny = template.opsForValue().get("kenny");
            return "获取到缓存中的数据: kenny" + kenny;
        }
    }

    /**
     * redis测试
     * @param
     * @return
     */
//    @GetMapping(value = "/redis/{id}",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//    public Response<User> getUser(@PathVariable("id") Integer id){
//        long beginTime = System.currentTimeMillis();
//        User user = userService.getUser(id);
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime-beginTime);
//        return Response.ok(user);
//    }
//
//    @DeleteMapping("/redis/{id}")
//    public Response<Void> deleteUser(@PathVariable("id") Integer id){
//        userService.deleteUser(id);
//        return Response.ok();
//    }


   @GetMapping("/redis/{name}")
    public Response<String> setName(@PathVariable("name") String name){
       if (!template.hasKey(name)){
           template.opsForValue().set(name,name);
       }else {
           template.opsForValue().append(name,name);
       }
        String realName = template.opsForValue().get(name);
        return Response.ok(realName);
    }

    @DeleteMapping("/redis/{name}")
    public Response<String> deleteName(@PathVariable("name") String name){

       if (template.hasKey(name)) template.delete(name);
       return Response.ok();
    }


}
