package com.spt.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.spt.springboot.exceptions.ParamsException;
import com.spt.springboot.pojo.User;
import com.spt.springboot.query.UserQuery;
import com.spt.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api(value = "用户管理模块")
@Controller
public class UserController {

    @Resource
    private UserService userService;

    // 根据用户名查询用户信息
    @ApiOperation(value = "通过用户名查询用户信息")
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "path")
    @GetMapping("user/name/{name}")
    @ResponseBody
    public User queryUserByName(@PathVariable String name) {
        System.out.println("测试热部署");
        return userService.queryUserByName(name);
    }

    @ApiOperation(value = "通过用户对象插入用户信息")
    @ApiImplicitParam(name = "user",value = "用户实体类")
    @PostMapping("user")
    @ResponseBody
    public Map insertUser(@RequestBody User user) {
        Map<String,Object> map = new HashMap<>();

        // 通过try catch 捕获service层抛出的异常
//        try {
//            userService.insertUser(user);
//            map.put("msg","添加成功");
//            map.put("code",200);
//        }catch (ParamsException p) {
//            map.put("msg",p.getMsg());
//            map.put("code",500);
//            p.printStackTrace();
//        }catch (Exception e) {
//            map.put("msg",e.getMessage());
//            map.put("code",500);
//            e.printStackTrace();
//        }

        userService.insertUser(user);
        map.put("msg","添加成功");
        map.put("code",200);
        return map;
    }

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    @PutMapping("user")
    @ResponseBody
    @ApiOperation(value = "通过用户对象更新用户信息")
    @ApiImplicitParam(name = "user",value = "用户实体类")
    public Map updateUser(@RequestBody User user) {
        Map<String,Object> map = new HashMap<>();

        // 通过try catch 捕获service层抛出的异常
//        try {
//            userService.updateUser(user);
//            map.put("msg","更新成功");
//            map.put("code",200);
//        }catch (ParamsException p) {
//            map.put("msg",p.getMsg());
//            map.put("code",500);
//            p.printStackTrace();
//        }catch (Exception e) {
//            map.put("msg",e.getMessage());
//            map.put("code",500);
//            e.printStackTrace();
//        }

        userService.updateUser(user);
        map.put("msg","更新成功");
        map.put("code",200);
        return map;
    }

    /**
     * 删除用户对象
     * @param user
     * @return
     */
    @DeleteMapping("user")
    @ResponseBody
    @ApiOperation(value = "通过用户对象删除用户信息")
    @ApiImplicitParam(name = "user",value = "用户实体类")
    public Map deleteUser(@RequestBody User user) {
        Map<String,Object> map = new HashMap<>();

        // 通过try catch 捕获service层抛出的异常
//        try {
//            userService.deleteUser(user);
//            map.put("msg","删除成功");
//            map.put("code",200);
//        }catch (ParamsException p) {
//            map.put("msg",p.getMsg());
//            map.put("code",500);
//            p.printStackTrace();
//        }catch (Exception e) {
//            map.put("msg",e.getMessage());
//            map.put("code",500);
//            e.printStackTrace();
//        }

        userService.deleteUser(user);
        map.put("msg","删除成功");
        map.put("code",200);
        return map;
    }

    /**
     *  分页条件查询
     * @param userQuery
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public PageInfo<User> queryUserByParams(UserQuery userQuery) {
        return userService.queryUserByParams(userQuery);
    }

    /**
     * 数据校验
     *      需要在被校验的对象上添加Valid注解（设置在形参前）
     * @param user
     * @return
     */
    @PutMapping("userdata")
    @ResponseBody
    public Map updateUser02(@Valid User user) {
        Map<String,Object> map = new HashMap<>();
        userService.updateUser(user);
        map.put("msg","更新成功");
        map.put("code",200);
        return map;
    }

}
