package com.jt.controller;

import com.jt.pojo.ItemCatParam;
import com.jt.service.ItemCatParamService;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemCatParam")
public class ItemCatParamController {

    @Resource
    private ItemCatParamService itemCatParamService;

    /*
    * 实现商品分类参数查询
    * URL:/itemCatParam/findItemCatParamListByType?itemCatId=5555&paramType=1
    * 参数: itemCatId  paramType
    * 返回值: SysResult
    * */
    @GetMapping("/findItemCatParamListByType")
    public SysResult findItemCatParamListByType(ItemCatParam itemCatParam) {
        List<ItemCatParam> itemCatParamList = itemCatParamService.findItemCatParamListByType(itemCatParam);
        return SysResult.success(itemCatParamList);
    }

    /*
    * 实现商品分类参数新增
    * URL: /itemCatParam/addItemCatParam
    * 参数: itemCatParam接收 form表单对象  JSON串
    * 返回值: SysResult
    * */
    @PostMapping("/addItemCatParam")
    public SysResult addItemCatParam(@RequestBody ItemCatParam itemCatParam) {
        itemCatParamService.addItemCatParam(itemCatParam);
        return SysResult.success();
    }

    /*
    * 实现商品分类参数的更新
    * URL:/itemCatParam/updateItemCatParam
    * 参数: itemCatParam
    * 返回值: SysResult
    * */
    @PutMapping("/updateItemCatParam")
    public SysResult updateItemCatParam(@RequestBody ItemCatParam itemCatParam) {
        itemCatParamService.updateItemCatParam(itemCatParam);
        return SysResult.success();
    }

    /*
     * 实现商品分类参数的删除
     * URL:/itemCatParam/deleteItemCatParamById?paramId=20
     * 参数: paramId = 20
     * 返回值: SysResult
     * */
    @DeleteMapping("/deleteItemCatParamById")
    public SysResult deleteItemCatParamById(@RequestParam(value = "paramId") Integer id) {
        itemCatParamService.deleteItemCatParamById(id);
        return SysResult.success();
    }
}
