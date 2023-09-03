package com.jt.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author Ming
 * 时间 2023/8/6
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    /*
     * user对象: username/password  用户传的明文
     * 业务思路:
     *   1.将密码进行加密操作  MD5加密方式
     *   2.根据新的用户和密码查询数据
     *   3.结果: null 没查到  u/p错误  return null
     *       不为null u/p正确 返回令牌token(全球唯一的密钥)
     * UUID算法
     * MD5hash方式升级
     * 用户名admin  密码admin123456
     * */
    @Override
    public String findUserByUP(User user) {
        //1.将密码进行加密  一般可能添加  盐值(由公司的域名构成)
        //hash(MD5(www.baidu.com123456))
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        //2.根据用户名和密码进行查询数据库
        user.setPassword(md5Pass);
        //根据对象中不为null的属性当做where条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        User userDB = userMapper.selectOne(queryWrapper);

        //3.返回密钥token
        String token = UUID.randomUUID().toString().replace("-","");

        return userDB == null ? null : token;
    }

    /*
    * 业务说明: 将后台实现分页查询
    * 分页sql: select * from user limit 起始位置,查询记录数
    * 查询第一页 每页20条
    * select * from user limit 0,20
    *
    * MP实现分页的查询
    * MP通过分页对象进行查询  获取分页的相关数据
    * */
    @Override
    public PageResult findUserByPage(PageResult pageResult) {
        //1.定义分页对象  2个参数
        IPage<User> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        //2.定义查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //3.判断用户是否有参数
        queryWrapper.like(StringUtils.hasLength(pageResult.getQuery()),"username", pageResult.getQuery());
        page = userMapper.selectPage(page, queryWrapper);
        //获取总记录数
        //获取分页后的结果
        pageResult.setTotal(page.getTotal()).setRows(page.getRecords());
        return pageResult;
    }

    @Override
    public void updateStatus(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void addUser(User user) {
        //实现密码加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass).setStatus(true);
        userMapper.insert(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }
}
