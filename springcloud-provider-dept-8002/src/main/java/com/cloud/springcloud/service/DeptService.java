package com.cloud.springcloud.service;

import com.cloud.springcloud.dao.DeptDao;
import com.cloud.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {


    @Autowired
    DeptDao deptDao;

    /*
    增加部门
    */
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    /*
    查询部门
     */
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }

    /*
    查询所有部门
     */
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }
}
