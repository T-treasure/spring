package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Ming
 * 时间 2023/8/6
 */
@Data
@Accessors(chain = true)
public class ItemParamVO {
    private Integer paramId;
    private String paramVals;
}
