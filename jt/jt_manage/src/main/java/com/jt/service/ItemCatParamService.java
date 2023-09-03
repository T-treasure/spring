package com.jt.service;

import com.jt.pojo.ItemCatParam;

import java.util.List;

public interface ItemCatParamService {
    List<ItemCatParam> findItemCatParamListByType(ItemCatParam itemCatParam);
    void addItemCatParam(ItemCatParam itemCatParam);

    void updateItemCatParam(ItemCatParam itemCatParam);

    void deleteItemCatParamById(Integer id);
}
