package com.jt.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatParamMapper;
import com.jt.pojo.ItemCatParam;
import com.jt.service.ItemCatParamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemCatParamServiceImpl implements ItemCatParamService {

    @Resource
    private ItemCatParamMapper itemCatParamMapper;

    @Override
    public List<ItemCatParam> findItemCatParamListByType(ItemCatParam itemCatParam) {
        return itemCatParamMapper.selectList(new QueryWrapper<>(itemCatParam));
    }

    @Override
    public void addItemCatParam(ItemCatParam itemCatParam) {
        itemCatParamMapper.insert(itemCatParam);
    }

    @Override
    public void updateItemCatParam(ItemCatParam itemCatParam) {
        itemCatParamMapper.updateById(itemCatParam);
    }

    @Override
    public void deleteItemCatParamById(Integer id) {
        itemCatParamMapper.deleteById(id);
    }
}
