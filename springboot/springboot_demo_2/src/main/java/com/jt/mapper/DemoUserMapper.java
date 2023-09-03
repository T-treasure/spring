package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.DemoUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//把这个接口交给spring容器管理   会自动生成代理对象
@Mapper
public interface DemoUserMapper extends BaseMapper<DemoUser> {
    List<DemoUser> findAll();

    void insertUser(DemoUser user);

    void updateUser(DemoUser user);

    void deleteUser(String name);
}
