package com.jt.pojo;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
//将对象交给Spring容器管理
@Component
@PropertySource(value = "classpath:/dept.properties", encoding = "UTF-8")
@Data   //Data是lombok使用最多的 自动生成get/set/toString/equals/hashcode 等方法
@Accessors(chain = true) //重写set方法  返回this对象
@NoArgsConstructor
@AllArgsConstructor
public class Dept {

    @Value("${dept.id}")
    private Integer id;
    @Value("${dept.name}")
    private String name;

    @Value("${dept.id2}")
    private Integer id2;
    @Value("${dept.name2}")
    private String name2;

//@Accessors(chain = true) //重写set方法  返回this对象
//    public Dept setId(Integer id) {
//        this.id = id;
//        return this;
//    }

}
