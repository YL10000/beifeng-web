/*
 * 项目名：beifeng-web
 * 文件名：DemoDaoTest.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：Demodao测试
 * 修改人：yanglin
 * 修改时间：2017年8月24日 下午3:03:07
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.dao.demo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.beifeng.web.dao.BaseDaoTest;
import com.beifeng.web.dao.IDemoDao;
import com.beifeng.web.dao.sys.log.IOpLogDao;
import com.beifeng.web.model.Demo;
import com.beifeng.web.model.sys.log.OpLog;

/**
 * DemoDaoTest
 *	
 * @Description Demodao测试
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
public class DemoDaoTest extends BaseDaoTest {

    @Autowired
    IDemoDao demoDao;
    
    @Autowired
    IOpLogDao opLogDao;
    
    @Test
    public void queryDemosTest() {
        //List<Map<String, Object>> demos=demoDao.queryDemos();
        //System.out.println(demos);
        opLogDao.insert(new OpLog("测试", "insert", 1, "", null, new Date()));
    }
    
    @Test
    public void insertDemoTest(){
        Demo demo=new Demo("李四",34);
        demo.setId(3);
        demoDao.insertReturnId(demo);
        System.out.println(demo.getId());
    }
    
    @Test
    public void getDemoByIdTest(){
        Demo demo=demoDao.selectById(2,Demo.class);
        System.out.println(demo.getName());
        System.out.println(demo.getId());
        System.out.println(demo);
    }
    
    @Test
    public void updateDemoTest() {
        Demo demo=new Demo();
        demo.setId(9);
        demo.setName("赵六002");
        demoDao.updateSelective(demo);
    }
    
    @Test
    public void deleteById(){
        demoDao.deleteById(6, Demo.class);
    }
    
    @Test
    public void deleteBySelective() {
        Demo demo=new Demo();
        demo.setName("李四");
        demo.setId(3);
        demoDao.deleteBySelective(demo);
    }
    
    @Test
    public void selectBySelective(){
        Demo demo=new Demo(0,2);
        List<Map<String, Object>> demoMaps=demoDao.selectMapBySelective(demo);
        System.out.println(demoMaps);
        demo.setOrderBy("age desc");
        demo.setPageNumber(1);
        List<Demo> demos=demoDao.selectBySelective(demo);
        System.out.println(demos);
    }
    
    
}
