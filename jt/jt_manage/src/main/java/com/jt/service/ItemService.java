package com.jt.service;

import com.jt.pojo.Item;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;

import java.util.List;

public interface ItemService {
    PageResult getItemList(PageResult pageResult);

    void updateItemStatus(Item item);

    void deleteItemById(Integer id);

    void saveItem(ItemVO itemVO);
}
