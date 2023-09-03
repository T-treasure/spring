package com.zrrd.pojo;

import java.util.List;
import java.util.Map;
import java.util.Set;

//构建pojo
//多种注入方式
//属性注入（底层set注入）
//set注入
//构造注入
public class User {

    private Integer id;
    private String name;
    private Set<Integer> set;
    private List<Integer> list;
    private Map<Integer,Integer> map;

    public User() {
    }

    public User(Integer id, String name, Set<Integer> set, List<Integer> list, Map<Integer, Integer> map) {
        this.id = id;
        this.name = name;
        this.set = set;
        this.list = list;
        this.map = map;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getSet() {
        return set;
    }

    public void setSet(Set<Integer> set) {
        this.set = set;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", set=" + set +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
