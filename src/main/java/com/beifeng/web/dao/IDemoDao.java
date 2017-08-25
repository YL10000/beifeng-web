/*
 * 项目名：beifeng-web
 * 文件名：DemoDao.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：demo演示dao
 * 修改人：yanglin
 * 修改时间：2017年8月24日 上午11:21:13
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;

import com.beifeng.web.conmon.persistence.support.QueryCondition;
import com.beifeng.web.dao.common.IBaseDao;
import com.beifeng.web.dao.common.SqlBuilderTemplete;
import com.beifeng.web.model.Demo;
import com.beifeng.web.model.common.BaseModel;

/**
 * DemoDao
 *	
 * @Description demo演示dao
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
public interface IDemoDao extends IBaseDao<Demo> {
    
    
    /**
     * 
     * selectById
     * 
     * @Description 根据id获取指定的对象
     * @param id
     * @param clazz
     * @return
     * @return Demo 
     * @see
     * @since
     */
    @SelectProvider(type = SqlBuilderTemplete.class, method = "selectById")
    @ResultType(value=Demo.class)
    Demo selectById(@Param("id")Object id, @Param("clazz")Class<Demo> clazz);
    
    /**
     * 
     * selectBySelective
     * 
     * @Description 根据查询条件返还对象列表
     * @param obj
     * @return
     * @return List<Demo> 
     * @see
     * @since
     */
    @SelectProvider(type = SqlBuilderTemplete.class, method = "selectBySelective")
    @ResultType(value=Demo.class)
    List<Demo> selectBySelective(Demo obj);
    
    
    /**
     * 
     * deleteById
     * 
     * @Description 根据id删除指定的对象
     * @param id
     * @param clazz
     * @return void 
     * @see
     * @since
     */
    @DeleteProvider(type=SqlBuilderTemplete.class,method="deleteById")
    void deleteById(@Param("id")Object id, @Param("clazz")Class<Demo> clazz);
    
    List<Map<String, Object>> queryDemosByCondition(QueryCondition condition);
    
    Integer getDemoCountbByCondition(QueryCondition condition);
}
