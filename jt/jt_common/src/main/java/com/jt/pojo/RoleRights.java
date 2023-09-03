package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Ming
 * 时间 2023/8/6
 */
@TableName("role_rights")
@Data
@Accessors(chain = true)
public class RoleRights {
    private Integer roleId;
    private Integer rightsId;
}
