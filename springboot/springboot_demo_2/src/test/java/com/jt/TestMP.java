package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.DemoUserMapper;
import com.jt.pojo.DemoUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestMP {

    /**
     * queryWrapper.lt（）——小于
     * queryWrapper.le（）——小于等于
     * queryWrapper.gt（）——大于
     * queryWrapper.ge（）——大于等于
     * queryWrapper.eq（）——等于
     * queryWrapper.ne（）——不等于
     * <p>
     * queryWrapper.betweeen（“age”,10,20）——age在值10到20之间
     * queryWrapper.notBetweeen（“age”,10,20）——age不在值10到20之间
     * <p>
     * queryWrapper.like（“属性”,“值”）——模糊查询匹配值‘%值%’
     * queryWrapper.notLike（“属性”,“值”）——模糊查询不匹配值‘%值%’
     * queryWrapper.likeLeft（“属性”,“值”）——模糊查询匹配最后一位值‘%值’
     * queryWrapper.likeRight（“属性”,“值”）——模糊查询匹配第一位值‘值%’
     * <p>
     * queryWrapper.isNull（）——值为空或null
     * queryWrapper.isNotNull（）——值不为空或null
     * <p>
     * queryWrapper.in（“属性”，条件，条件 ）——符合多个条件的值
     * queryWrapper.notIn(“属性”，条件，条件 )——不符合多个条件的值
     * <p>
     * queryWrapper.or（）——或者
     * queryWrapper.and（）——和
     * <p>
     * queryWrapper.orderByAsc(“属性”)——根据属性升序排序
     * queryWrapper.orderByDesc(“属性”)——根据属性降序排序
     * <p>
     * queryWrapper.inSql(“sql语句”)——符合sql语句的值
     * queryWrapper.notSql(“sql语句”)——不符合SQL语句的值
     * <p>
     * queryWrapper.esists（“SQL语句”）——查询符合SQL语句的值
     * queryWrapper.notEsists（“SQL语句”）——查询不符合SQL语句的值
     * <p>
     * queryWrapper.limit
     */
    @Resource
    private DemoUserMapper userMapper;

    @Test
    public void insert() {
        DemoUser user = new DemoUser();
        user.setName("孙策").setAge(15).setSex("男");
        userMapper.insert(user);
    }

    /*
     * 测试更新操作 修改id = 231的数据 name="" age = 18 原则:
     * 根据对象不为空的属性当做set条件 set  name = xxx
     * 用ById  id必须赋值  而且id当做唯一的where条件
     */
    @Test
    public void update() {
        DemoUser user = new DemoUser();
        user.setId(231).setName("大乔").setAge(18).setSex("女");
        userMapper.updateById(user);
    }

    /*
     * 1.查询id等于21的用户 根据id查询数据  1条数据
     * 2.查询 name="白骨精" sex="女" 的用户  List数据
     * queryWrapper  条件构造器  拼接where条件的  原则：根据属性不为null拼接where条件
     * */
    @Test
    public void testSelect() {
        //1.根据ID查询
        DemoUser user = userMapper.selectById(21);
        System.out.println(user);
        //2.根据属性去查值
        DemoUser user2 = new DemoUser();
        user2.setName("白骨精").setSex("女");
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>(user2);
        List<DemoUser> userList = userMapper.selectList(queryWrapper);
        System.out.println("userList = " + userList);
    }

    /*
     * 2.需求: 查询 age > 18 并且性别为女的用户
     * sql: select * from demo_user where age > 18 and sex = '女';
     * 特殊字符 >gt  <lt  =eq  >=ge  <=le
     * 默认连接符and
     * */
    @Test
    public void testSelect2() {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 18)
                .eq("sex", "女");
        for (DemoUser user : userMapper.selectList(queryWrapper)) {
            System.out.println("user = " + user);
        }
    }

    /*
     *
     * 3.需求: 查询 name中包含"精"字的数据
     * sql: like "%精%"
     *      以精开头 like  "精%"
     *      以精结尾 like  "%精"
     * notLike
     * likeRight
     * likeLeft
     */
    @Test
    public void testSelect3() {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "精");
        List<DemoUser> userList = userMapper.selectList(queryWrapper);
        for (DemoUser user : userList) {
            System.out.println(user);
        }
    }

    /*
     * 4.需求: 查询 sex="男" 以id倒叙排列
     * sql: select * from demo_user where sex = "男" order by id desc;
     */
    @Test
    public void testSelect4() {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", "男").orderByDesc("id");
        List<DemoUser> userList = userMapper.selectList(queryWrapper);
        for (DemoUser user : userList) {
            System.out.println(user);
        }
    }

    /*
     *  5.需求: 查询 id = 1,3,5,6,7
     *  sql: select * from demo_user where id in(xx,xx,xx)
     */
    @Test
    public void testSelect5() {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        Integer[] ids = {1, 3, 5, 6, 7};
        queryWrapper.in("id", Arrays.asList(ids));
        List<DemoUser> userList = userMapper.selectList(queryWrapper);
        for (DemoUser user : userList) {
            System.out.println(user);
        }
    }

    /*
     * 需求：如果根据name属性和age属性查询数据  有的时候某个数据为null
     * 要求动态查询  where name = xxx    age > xxx
     * sql: select * from demo_user where (name != null) name = xxx and age > xxx
     * condition: 内部编辑了一个判断条件
     *          如果返回值结果为true  则拼接该字段
     *          否则不拼接该字段
     * StringUtils.hasLength(name)  判断字符串是否有效
     */
    @Test
    public void testSelect6() {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        String name = "大乔";
        int age = 18;
//        boolean condition = name != null && !"".equals(name);
        queryWrapper.eq(StringUtils.hasLength(name), "name", name)
                .gt("age", age);
        for (DemoUser user : userMapper.selectList(queryWrapper)) {
            System.out.println(user);
        }
    }

    /*
     * 需求: 只想查询第一列数据
     * 如果 queryWrapper = null 不需要where条件
     */
    @Test
    public void testSelect7() {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", "男");
        List<Object> obj= userMapper.selectObjs(queryWrapper);
        System.out.println("obj = " + obj);

    }

    /*
     * 需求: 只想查询name/sex字段
     */
    @Test
    public void testSelect8() {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","sex");
        for (DemoUser user : userMapper.selectList(queryWrapper)) {
            System.out.println(user);
        }
    }

    /*
     * 返回有效字段
     */
    @Test
    public void testSelect9() {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","sex");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    /*
     * 更新数据
     * 将name = "大乔" 改为 name = 孙策  age = 20
     * 性别: 改为男
     * 年龄: 改为18
     * sql: update demo_user set name = 'xxx', sex = "男" where name = "xxx"
     */
    @Test
    public void testUpdate() {
        DemoUser user = new DemoUser();
        user.setName("孙策").setAge(18).setSex("男");
        UpdateWrapper<DemoUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "大乔").eq("age", 20);
        userMapper.update(user, updateWrapper);
    }
}
