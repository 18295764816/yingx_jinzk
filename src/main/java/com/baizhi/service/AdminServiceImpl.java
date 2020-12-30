package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AdminServiceImpl
 * @time 2020/11/12-15:24
 */

@Service
@Component
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao ad;

    @Override
    public Admin login(Admin admin) {
        Admin a = ad.login(admin.getUsername());
        if( a == null ) throw new RuntimeException("用户名错误");
        if( a.getPassword().equals(admin.getPassword())==false ) throw new RuntimeException("密码错误");
        return a;
    }

    @Override
    public void insert(Admin admin) {

        ad.insert(admin);
    }
}
