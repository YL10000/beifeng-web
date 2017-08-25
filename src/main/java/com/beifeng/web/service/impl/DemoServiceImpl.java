/*
 * 项目名：beifeng-web
 * 文件名：DemoServiceImpl.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：Demo 事例service实现类
 * 修改人：yanglin
 * 修改时间：2017年8月24日 上午11:32:58
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beifeng.web.conmon.persistence.support.PageInfo;
import com.beifeng.web.conmon.persistence.support.QueryCondition;
import com.beifeng.web.dao.IDemoDao;
import com.beifeng.web.model.Demo;
import com.beifeng.web.service.IDemoService;

/**
 * DemoServiceImpl
 *	
 * @Description Demo 事例service实现类
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
@Service(value="demoService")
public class DemoServiceImpl implements IDemoService {

    @Autowired
    IDemoDao demoDao;

    @Override
    public Demo selectById(Object id) {
        return demoDao.selectById(id, Demo.class);
    }

    @Override
    public void insert(Demo obj) {
        demoDao.insert(obj);
    }

    @Override
    public Object insertReturnId(Demo obj) {
        demoDao.insertReturnId(obj);
        return obj.getId();
    }

    @Override
    public void update(Demo obj) {
        demoDao.update(obj);
    }

    @Override
    public void updateSelective(Demo obj) {
        demoDao.updateSelective(obj);
    }

    @Override
    public void deleteBySelective(Demo obj) {
        demoDao.deleteBySelective(obj);
    }

    @Override
    public List<Map<String, Object>> selectMapBySelective(Demo obj) {
        return demoDao.selectMapBySelective(obj);
    }

    @Override
    public List<Demo> selectBySelective(Demo obj) {
        return demoDao.selectBySelective(obj);
    }

    @Override
    public void deleteById(Object id) {
        demoDao.deleteById(id, Demo.class);
    }

    @Override
    public List<Map<String, Object>> queryDemos(QueryCondition condition) {
        List<Map<String, Object>> demos=demoDao.queryDemosByCondition(condition);
        return demos;
    }

    @Override
    public Integer getDemoCount(QueryCondition condition) {
        return demoDao.getDemoCountbByCondition(condition);
    }
}
