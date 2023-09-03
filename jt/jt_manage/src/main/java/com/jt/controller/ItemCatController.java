package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {

    @Resource
    private ItemCatService itemCatService;

    /*
    * 实现商品分类功能
    * URL: /itemCat/findItemCatList
    * 参数: level
    * 返回值 SysResult
    * 请求方式: get
    * */
    @GetMapping("/findItemCatList/{level}")
    public SysResult findItemCatList(@PathVariable Integer level) {
        List<ItemCat> itemCatList = itemCatService.findAll(level);
        return SysResult.success(itemCatList);
    }

    /*
    * 根据ID修改状态信息
    * URL: /itemCat/status/{id}/{status}
    * 参数: id/status
    * 返回值: SysResult对象
    * */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(ItemCat itemCat) {
        itemCatService.updateStatus(itemCat);
        return SysResult.success();
    }

    /*
    * 实现商品分类的新增
    * URL: /itemCat/saveItemCat
    * 参数: 商品的form表单
    * 返回值 SysResult
    * */
    @PostMapping("/saveItemCat")
    public SysResult addItemCat(@RequestBody ItemCat itemCat) {
        itemCatService.addItemCat(itemCat);
        return SysResult.success();
    }

    /*
    * 删除商品分类数据
    * URL: /itemCat/deleteItemCat?id=1&level=1
    * 参数: id/level
    * 返回值 SysResult
    * */
    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat) {
        itemCatService.deleteItemCat(itemCat);
        return SysResult.success();
    }

    /*
    * 修改商品分类名称
    * URL: /itemCat/updateItemCat
    * 参数: itemCat对象form表单
    * 返回值 SyResult
    * */
    @PutMapping("/updateItemCat")
    public SysResult updateItemCat(ItemCat itemCat) {
        itemCatService.updateItemCat(itemCat);
        return SysResult.success();
    }
}
