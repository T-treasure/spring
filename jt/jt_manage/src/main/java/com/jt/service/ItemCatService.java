package com.jt.service;


import com.jt.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findAll(Integer level);

    void updateStatus(ItemCat itemCat);

    void addItemCat(ItemCat itemCat);

    void deleteItemCat(ItemCat itemCat);

    void updateItemCat(ItemCat itemCat);

}
