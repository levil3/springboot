package com.spt.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spt.springboot.dao.UserDao;
import com.spt.springboot.pojo.User;
import com.spt.springboot.query.UserQuery;
import com.spt.springboot.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *  Ehcache缓存
 *      @Cacheable（声明在方法上）
 *          查询操作，将查询的内容存到缓存中，下次获取时从缓存中获取数据。如果缓存中没有数据，则从数据库中查询，并设置在缓存中
 *      @CachePut（声明在方法上）
 *          添加/修改操作，当数据库中的数据执行添加/修改操作时，缓存中的数据也会进行更新。添加时，则新存缓存对象，修改时，则更新缓存
 *          注：方法需要返回User对象
 *      @CacheEvict（声明在方法上）
 *          删除操作，数据库中的数据删除，对应缓存中的数据也会删除
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    // 根据用户名查询用户信息
    @Cacheable(value = "users",key = "#name") // 根据用户名缓存
    public User queryUserByName(String name) {
        return userDao.queryUserByName(name);
    }

    /**
     *  插入用户信息
     * @param user
     */
    public void insertUser(User user) {
        // 判断用户名是否为空
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空！");
        // 判断密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(user.getPwd()),"密码不能为空！");
        // 判断用户名是否存在
        AssertUtil.isTrue(userDao.queryUserByName(user.getUserName()) != null,"用户名已存在");
        // 执行dao方法，判断插入操作是否成功
        AssertUtil.isTrue(userDao.insertUser(user) < 1,"插入用户失败");
    }

    /**
     *  根据用户名，修改用户密码
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @CachePut(value = "users",key = "#user.userName")
    public void updateUser(User user) {
        // 判断用户名是否为空
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空！");
        // 判断密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(user.getPwd()),"密码不能为空！");
        // 执行dao方法，判断更新操作是否成功
        AssertUtil.isTrue(userDao.updateUser(user) < 1,"更新用户失败");
    }

    /**
     *  根据用户名，删除用户记录
     * @param user
     */
    @CacheEvict(value = "users",key = "#user.userName")
    public void deleteUser(User user) {
        // 判断用户名是否为空
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空！");
        // 判断密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(user.getPwd()),"密码不能为空！");
        // 判断用户名是否存在
        AssertUtil.isTrue(userDao.queryUserByName(user.getUserName()) == null,"用户名不存在");
        // 执行dao方法，判断更新操作是否成功
        AssertUtil.isTrue(userDao.deleteUser(user) < 1,"删除用户失败");
    }

    public PageInfo<User> queryUserByParams(UserQuery userQuery) {
        // 开启分页
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        // 查询用户集合
        List<User> list = userDao.queryUserByParams(userQuery);
        // 对查询集合分页
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
