package com.jt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = -8958032501167484090L;
    private Integer id;
    private String name;
    private String[] hobbies;
    private Dog dog;//通过对象的引用赋值
    public String getAGE(){
        return "快乐二锅头！";
    }

}
