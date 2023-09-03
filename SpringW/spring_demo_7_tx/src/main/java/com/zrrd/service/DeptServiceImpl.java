package com.zrrd.service;

import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
    @Override
    public void addDept() {
        System.out.println("调用DeptService，实现入库");
    }
}
