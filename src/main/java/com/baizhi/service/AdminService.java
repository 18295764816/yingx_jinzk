package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {

    //登录
    public Admin login(Admin admin);
    //添加
    public void insert(Admin admin);
}
