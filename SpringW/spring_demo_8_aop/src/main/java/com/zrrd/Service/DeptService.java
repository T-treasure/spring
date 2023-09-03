package com.zrrd.Service;

import com.zrrd.anno.Cache;

public interface DeptService {

    void addDept();

    void updateDept();

    String after(Integer id);

    void afterThrow();

    //测试环绕通知
    void doAround();
}
