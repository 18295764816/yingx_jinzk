package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDao {
    //登录
    public Admin login(String username);
    //添加
    public void insert(Admin admin);

}
