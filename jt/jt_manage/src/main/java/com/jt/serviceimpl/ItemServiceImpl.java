package com.jt.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.mapper.ItemParamMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParam;
import com.jt.service.ItemService;
import com.jt.vo.ItemParamVO;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;
    @Resource
    private ItemDescMapper itemDescMapper;
    @Resource
    private ItemParamMapper itemParamMapper;
    private static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public PageResult getItemList(PageResult pageResult) {
        //查询条件和分页条件
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(pageResult.getQuery()),"title", pageResult.getQuery());
        //分页对象
        IPage<Item> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        page = itemMapper.selectPage(page, queryWrapper);
        //将查询的Page对象中的数据拿出来放入pageResult中
        pageResult.setTotal(page.getTotal()).setRows(page.getRecords());

        return pageResult;
    }

    @Override
    public void updateItemStatus(Item item) {
        itemMapper.updateById(item);
    }

    @Override
    public void deleteItemById(Integer id) {
        itemMapper.deleteById(id);
    }

    /*
    * 实现三张表的入库
    * */
    @Override
    @Transactional
    public void saveItem(ItemVO itemVO) {
        //1.商品入库
        Item item = itemVO.getItem();
        item.setStatus(true);   //启用默认状态
        //要求入库后返回主键
        //MP如果设定了主键自增则会自动实现数据的回显
        itemMapper.insert(item);
        //2.入库商品的详情
        ItemDesc itemDesc = itemVO.getItemDesc();
        itemDesc.setId(item.getId());
        itemDescMapper.insert(itemDesc);
        //3.入库商品参数
        //1.一个商品应该有自己单独的参数 动态参数/静态属性  KEY-VALUE
        ItemParam itemParam = itemVO.getItemParam();
        ItemParamVO[] dynamic = itemParam.getDynamicArray();
        ItemParamVO[] staticParam = itemParam.getStaticArray();
        //将页面传递的数据转化为JSON 之后进行数据库的保存
        try {
            String dynamicJSON = MAPPER.writeValueAsString(dynamic);
            String staticJSON = MAPPER.writeValueAsString(staticParam);
            //封装商品参数信息
            itemParam.setDynamicArgs(dynamicJSON)
                    .setStaticArgs(staticJSON)
                    .setId(item.getId());
            //实现入库操作
            itemParamMapper.insert(itemParam);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //如果程序执行报错则抛出异常
            throw new RuntimeException();
        }
    }
}
