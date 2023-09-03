package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Ming
 * 时间 2023/8/6
 */
@Data
@Accessors(chain = true)
@TableName("report")
public class Report {
    private Integer id;
    private String name;
    private Integer count;
}
