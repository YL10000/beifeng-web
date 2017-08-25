/*
 * 文件名：BaseDaoTest.java
 * 项目名：beifeng-web
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：测试父类，使单元测试数据回滚
 * 修改人：yanglin
 * 修改时间：2017年8月24日 下午3:01:29
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * BaseDaoTest
 *	
 * @Description 测试父类，使单元测试数据回滚 
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persist.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",
        defaultRollback = false)
public class BaseDaoTest {

}
