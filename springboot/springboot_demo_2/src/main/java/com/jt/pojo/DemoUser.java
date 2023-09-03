package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
//没有添加注解的属性值 默认一类型(DemoUser)当做表名
@TableName("demo_user")
public class DemoUser {

    //定义主键自增
    @TableId(type = IdType.AUTO)
    //UUID随机hash算法 几乎保证不重复
//    @TableId(type = IdType.ASSIGN_UUID)
    private Integer id;
//    @TableField(value = "name", exist = true)
    private String name;
    private Integer age;
    private String sex;

}
