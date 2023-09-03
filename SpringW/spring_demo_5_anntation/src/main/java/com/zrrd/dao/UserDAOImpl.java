package com.zrrd.dao;

import com.zrrd.pojo.User;
import org.springframework.stereotype.Repository;

/*
 * <bean id="类名首字母小写" class="UserDAOImpl.class"></>
 * value是默认存在，可以不写 但是和其他属性配用时需要书写
 */
@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) {
        System.out.println("添加用户：" + user);
    }
}
