/*
 * 项目名：beifeng-web
 * 文件名：IDemoService.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：
 * 修改人：yanglin
 * 修改时间：2017年8月24日 上午11:31:28
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.service;

import java.util.List;
import java.util.Map;

import com.beifeng.web.conmon.persistence.support.QueryCondition;
import com.beifeng.web.dao.IDemoDao;
import com.beifeng.web.dao.common.IBaseDao;
import com.beifeng.web.model.Demo;

/**
 * IDemoService
 *	
 * @Description Demo 事例 service接口
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
public interface IDemoService extends IBaseService<Demo>{

    List<Map<String, Object>> queryDemos(QueryCondition condition);
    
    Integer getDemoCount(QueryCondition condition);

}
