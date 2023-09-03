package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Ming
 * 时间 2023/8/6
 */
@TableName("item_desc")
@Data
@Accessors(chain = true)
public class ItemDesc extends BasePojo{

    @TableId//指标识是ID 不设置自增
    private Integer id;
    private String itemDesc;

}
