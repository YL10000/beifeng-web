/*
 * 项目名：beifeng-web
 * 文件名：IBaseService.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：
 * 修改人：yanglin
 * 修改时间：2017年8月25日 下午3:51:50
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import com.beifeng.web.dao.common.SqlBuilderTemplete;
import com.beifeng.web.model.Demo;
import com.beifeng.web.model.common.BaseModel;

/**
 * IBaseService
 *	
 * @Description 功能详细描述 
 * @author yanglin
 * @version 1.0,2017年8月25日
 * @param <T>
 * @see
 * @since
 */
public interface IBaseService<T extends BaseModel> {

    /**
     * insert
     * 
     * @Description 通用插入方法
     * @param obj
     * @see
     * @since
     */
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
    Object insertReturnId(T obj);

    /**
     * update
     * 
     * @Description 通用更新方法
     * @param obj
     * @see
     * @since
     */
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
    void updateSelective(T obj);

    /**
     * delete
     * 
     * @Description 通用删除方法
     * @param obj
     * @see
     * @since
     */
    public void deleteBySelective(T obj);
    
    List<Map<String, Object>> selectMapBySelective(T obj);
    
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
    T selectById(Object id);
    
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
    List<T> selectBySelective(T obj);
    
    
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
    void deleteById(@Param("id")Object id);
}
