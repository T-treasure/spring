package com.jt.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private ItemCatMapper itemCatMapper;

    //查询三级商品分类的嵌套
    //select * from item_cat where parent_id = 0
    //for循环  2.递归的写法
    @Override
    public List<ItemCat> findAll(Integer level) {
        /*if(level == 1) {
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", 0);
            List<ItemCat> oneList = itemCatMapper.selectList(queryWrapper);
            return oneList;
        }
        if(level == 2) {
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", 0);
            List<ItemCat> oneList = itemCatMapper.selectList(queryWrapper);
            //根据一级查询二级信息
            for(ItemCat oneItemCat : oneList) {
                queryWrapper.clear();
                queryWrapper.eq("parent_id", oneItemCat.getId());
                List<ItemCat> twoList = itemCatMapper.selectList(queryWrapper);
                oneItemCat.setChildren(twoList);
            }
            return oneList;
        }

        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        List<ItemCat> oneList = itemCatMapper.selectList(queryWrapper);
        //根据一级查询二级信息
        for(ItemCat oneItemCat : oneList) {
            queryWrapper.clear();
            queryWrapper.eq("parent_id", oneItemCat.getId());
            List<ItemCat> twoList = itemCatMapper.selectList(queryWrapper);
            for(ItemCat twoItemCat : twoList) {
                queryWrapper.clear();
                queryWrapper.eq("parent_id", twoItemCat.getId());
                List<ItemCat> threeList = itemCatMapper.selectList(queryWrapper);
                twoItemCat.setChildren(threeList);
            }
            oneItemCat.setChildren(twoList);
        }
        return oneList;*/

        Map<Integer, List<ItemCat>> map = getMap();
//        if(level == 1) return map.get(0);
//        if(level == 2) return findTwoItemCatList(map);
//        return findThreeItemCatList(map);

        return findChildrenList(0, level, map);
        //return getItemList(0 , level);


    }

    @Override
    @Transactional
    public void updateStatus(ItemCat itemCat) {
        itemCatMapper.updateById(itemCat);
    }

    @Override
    @Transactional
    public void addItemCat(ItemCat itemCat) {
        itemCat.setStatus(true);
        itemCatMapper.insert(itemCat);
    }

    /*
    * 实现商品分类删除
    * 实现思路: 根据level 判断层级 之后根据ID删除
    * 删除: 1.delete from item_cat where id in(100,200)
    * */
    @Override
    public void deleteItemCat(ItemCat itemCat) {
        //1.判断等级是否为3级
        if(itemCat.getLevel() == 3) {
            itemCatMapper.deleteById(itemCat.getId());
        }
        if(itemCat.getLevel() == 2) {
            //1.删除3级信息
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", itemCat.getId());
            itemCatMapper.delete(queryWrapper);
            itemCatMapper.deleteById(itemCat.getId());
        }
        if(itemCat.getLevel() == 1) {
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            if(itemCat.getChildren() != null) {
                for(ItemCat i : itemCat.getChildren()) {
                    queryWrapper.clear();
                    queryWrapper.eq("parent_id", i.getId());
                    //三
                    itemCatMapper.delete(queryWrapper);
                }
            }
            queryWrapper.eq("parent_id", itemCat.getId());
            //二
            itemCatMapper.delete(queryWrapper);
            //一
            itemCatMapper.deleteById(itemCat.getId());
        }
    }

    @Override
    public void updateItemCat(ItemCat itemCat) {
        itemCatMapper.updateById(itemCat);
    }



    private List<ItemCat> findChildrenList(Integer id, Integer level, Map<Integer, List<ItemCat>> map) {
        if(id == null) id = 0;
        List<ItemCat> itemCatList = map.get(id);
        if(level == 1) {
            return itemCatList;
        }
        if (itemCatList == null) {
            return null;
        }
        for(ItemCat itemCat : itemCatList) {
            if(itemCat.getLevel() < level) {
                List<ItemCat> childrenList = findChildrenList(itemCat.getId(), level, map);
                itemCat.setChildren(childrenList);
            }
        }
        return itemCatList;
    }

    private List<ItemCat> findTwoItemCatList(Map<Integer, List<ItemCat>> map) {
        //获取一级商品分类
        List<ItemCat> oneList = map.get(0);
        //根据一级查询二级
        for(ItemCat itemCat : oneList) {
            List<ItemCat> itemCatList = map.get(itemCat.getId());
            itemCat.setChildren(itemCatList);
        }
        return oneList;
    }

    private List<ItemCat> findThreeItemCatList(Map<Integer, List<ItemCat>> map) {
        List<ItemCat> oneList = findTwoItemCatList(map);
        for(ItemCat itemCat : oneList) {
            if(itemCat.getChildren() != null) {
                for(ItemCat itemCat2 : itemCat.getChildren()) {
                    List<ItemCat> threeList = map.get(itemCat2.getId());
                    itemCat2.setChildren(threeList);
                }
            }
        }
        return oneList;
    }


    //查询出来所有元素,把parent_id相同的放在一起
    private Map<Integer, List<ItemCat>> getMap() {
        Map<Integer, List<ItemCat>> map = new HashMap<>();
        //1.查询所有数据
        List<ItemCat> itemCatList = itemCatMapper.selectList(null);
        //2.封装map集合
        //2.1存储依据: 如果key存在 获取add操作
        //2.2如果key不存在 则将key进行存储  同时将自己当做第一个元素进行保存
        for(ItemCat itemCat : itemCatList) {
            int parentId = itemCat.getParentId();
            //判断map集合中是否有父级
            if(map.containsKey(parentId)) {
                //有父级  将自己添加到子级中
                map.get(parentId).add(itemCat);
            }else {
                List<ItemCat> list = new ArrayList<>();
                list.add(itemCat);
                map.put(parentId, list);
            }
        }
        return map;
    }


    //获取ItemList集合多次SQL的递归写法
    private List<ItemCat> getItemList(int id, int level) {
        if(level <= 0)return null;
        List<ItemCat> itemCatList;
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        itemCatList = itemCatMapper.selectList(queryWrapper);
        for(ItemCat i : itemCatList) {
            List<ItemCat> list = getItemList(i.getId(), level - 1);
            i.setChildren(list);
        }
        return itemCatList;
    }
}
