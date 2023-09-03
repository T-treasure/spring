package com.zrrd.Service;

import com.zrrd.anno.Cache;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {

    @Override
    @Cache
    public void addDept() {
        System.out.println("添加部门信息！");
    }

    @Override
    @Cache
    public void updateDept() {
        System.out.println("更新部门信息！");
    }

    @Override
    @Cache
    public String after(Integer id) {
        return "Spring通知测试！";
    }

    @Override
    @Cache
    public void afterThrow() {
        System.out.println("用户执行目标方法");
        //手动执行除零异常
        int i = 1 / 0;
    }

    @Override
    @Cache
    public void doAround() {
        for (int i = 0; i < 100; i ++) {
            System.out.println(i);
        }
        System.out.println("实现用户的入库操作！");
    }


}
