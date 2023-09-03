package com.jt.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.RightsMapper;
import com.jt.pojo.Rights;
import com.jt.service.RightsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RightsServiceImpl implements RightsService {

    @Resource
    private RightsMapper rightsMapper;

    /*
    * 查询一级/二级菜单
    * 查询条件: 一级菜单 parent_id = 0
    * 查询条件: 二级菜单 parent_id = 1
    * 利用左连接的方式 实现数据的封装 resultMap
    * */
    @Override
    public List<Rights> findRightsList() {
        //查询一级菜单数据
        QueryWrapper<Rights> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        List<Rights> rightsList = rightsMapper.selectList(queryWrapper);

        //查询二级菜单数据
        for(Rights rights : rightsList) {
            //查询该元素的二级菜单
            //QueryWrapper<Rights> queryWrapper2 = new QueryWrapper<>();
            queryWrapper.clear();
            queryWrapper.eq("parent_id", rights.getId());
            List<Rights> childrenList = rightsMapper.selectList(queryWrapper);
            rights.setChildren(childrenList);
        }

        return rightsList;
    }
}
