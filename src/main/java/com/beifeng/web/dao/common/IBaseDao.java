/*
 * 项目名：beifeng-web
 * 文件名：IBaseDao.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：Dao基础接口文件
 * 修改人：yanglin
 * 修改时间：2017年8月24日 下午2:28:07
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.beifeng.web.model.Demo;
import com.beifeng.web.model.common.BaseModel;

/**
 * IBaseDao
 *	
 * @Description Dao基础接口,封装：公共方法 
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
public interface IBaseDao<T extends BaseModel> {
    
    /**
     * selectOneById
     * 
     * @Description 根据id查找数据,无具体实现需有子接口自行实现
     * @param id
     * @return T
     * @see
     * @since
     */
    @Options(flushCache = true)
    public abstract T selectOneById(Integer id);
    
    /**
     * insert
     * 
     * @Description 通用插入方法
     * @param obj
     * @see
     * @since
     */
    @InsertProvider(type = SqlBuilderTemplete.class, method = "insert")
    public void insert(T obj);
    
    /**
     * 
     * insertReturnId
     * 
     * @Description 插入并返回自增的id
     * @param obj
     * @return void 
     * @see
     * @since
     */
    @InsertProvider(type = SqlBuilderTemplete.class, method = "insert")
    @SelectKey(keyProperty="id",keyColumn="id", before = false, resultType = int.class, statement = { "SELECT LAST_INSERT_ID() AS ID" })
    void insertReturnId(T obj);

    /**
     * update
     * 
     * @Description 通用更新方法
     * @param obj
     * @see
     * @since
     */
    @UpdateProvider(type = SqlBuilderTemplete.class, method = "update")
    public void update(T obj);
    
    /**
     * 
     * updateSelective
     * 
     * @Description 通用更改方法，选择性修改
     * @param obj
     * @return void 
     * @see
     * @since
     */
    @UpdateProvider(type = SqlBuilderTemplete.class, method = "updateSelective")
    void updateSelective(T obj);

    /**
     * delete
     * 
     * @Description 通用删除方法
     * @param obj
     * @see
     * @since
     */
    @DeleteProvider(type = SqlBuilderTemplete.class, method = "deleteBySelective")
    public void deleteBySelective(T obj);
    
    @SelectProvider(type = SqlBuilderTemplete.class, method = "selectBySelective")
    @ResultType(value=Map.class)
    List<Map<String, Object>> selectMapBySelective(T obj);
    
    
    
    
}
