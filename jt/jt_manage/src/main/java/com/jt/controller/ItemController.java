package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;

    /*
    * 实现商品信息分页查询
    * URL: /item/getItemList
    * 参数: PageResult对象
    * 返回值: SysResult
    * */
    @GetMapping("/getItemList")
    public SysResult getItemList(PageResult pageResult) {
        pageResult =  itemService.getItemList(pageResult);
        return SysResult.success(pageResult);
    }

    /*
     * 实现商品信息状态更改
     * URL: /item/updateItemStatus
     * 参数: id/status 使用Item对象接收
     * 返回值: SysResult
     * */
    @PutMapping("/updateItemStatus")
    public SysResult updateItemStatus(Item item) {
        itemService.updateItemStatus(item);
        return SysResult.success();
    }

    /*
     * 实现商品信息删除
     * URL: /item/deleteItemById
     * 参数: id
     * 返回值: SysResult
     * */
    @DeleteMapping("/deleteItemById")
    public SysResult deleteItemById(Integer id) {
        itemService.deleteItemById(id);
        return SysResult.success();
    }

    /*
    * 商品新增
    * url:/item/saveItem
    * 参数:{Item,itemDesc,itemParam} 使用ItemVO接收
    * 返回值: SysResult
    * */
    @PostMapping("/saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO) {
        itemService.saveItem(itemVO);
        return SysResult.success();
    }
}
