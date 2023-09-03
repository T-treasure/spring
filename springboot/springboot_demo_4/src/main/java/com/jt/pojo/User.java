package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName(value = "demo_user")
//包装数据安全性有效性实现序列化
public class User implements Serializable {

    private static final long serialVersionUID = -3214208980480411143L;
    @TableId(type = IdType.AUTO) //主键自增
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

}
