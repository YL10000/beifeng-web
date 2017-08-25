/*
 * 项目名：beifeng-web
 * 文件名：SqlBuilderTemplete.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：通用MySQL生成器类文件
 * 修改人：yanglin
 * 修改时间：2017年8月24日 下午2:28:07
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.dao.common;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SqlBuilder;

import com.beifeng.web.model.Demo;
import com.beifeng.web.model.common.BaseModel;

/**
 * 
 * SqlBuilderTemplete
 * 
 * @Description 通用MySQL生成器
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
public class SqlBuilderTemplete<T extends BaseModel> {

    /**
     * insert
     * 
     * @Description 通用Insert方法
     * @param obj
     *            操作对象
     * @return String 返回插入SQL语句
     * @see SqlBuilder#BEGIN()
     * @see SqlBuilder#INSERT_INTO(String)
     * @see SqlBuilder#VALUES(String, String)
     */
    public String insert(T obj) {
        BEGIN();
        INSERT_INTO(obj.tableName());
        obj.caculationColumnList(true);
        VALUES(obj.returnInsertColumnsName(), obj.returnInsertColumnsDefine(true));
        return SQL();
    }

    /**
     * update
     * 
     * @Description 通用Update方法,更改所有字段
     * @param obj
     *            操作对象
     * @return String 返回更新SQL语句
     * @see SqlBuilder#BEGIN()
     * @see SqlBuilder#UPDATE(String)
     * @see SqlBuilder#SET(String)
     * @see SqlBuilder#WHERE(String)
     */
    public String update(T obj) {
        Map<String, String> id = new HashMap<String, String>();
        id = obj.getIdField();
        String dbName = id.get("dbName");
        String modelName = id.get("modelName");
        BEGIN();
        UPDATE(obj.tableName());
        obj.caculationColumnList(false);
        SET(obj.returnUpdateSet());
        WHERE(dbName + "=#{" + modelName + "}");
        return SQL();
    }

    /**
     * 
     * updateSelective
     * 
     * @Description 通用修改方法，选择性修改，只有不为空的字段进行修改
     * @param obj
     * @return
     * @return String
     * @see
     * @since
     */
    public String updateSelective(T obj) {
        Map<String, String> id = new HashMap<String, String>();
        id = obj.getIdField();
        String dbName = id.get("dbName");
        String modelName = id.get("modelName");
        BEGIN();
        UPDATE(obj.tableName());
        obj.caculationColumnList(false);
        try {
            SET(obj.returnSelectiveUpdateSet());
            WHERE(dbName + "=#{" + modelName + "}");
            return SQL();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * delete
     * 
     * @Description 通用Delete方法
     * @param obj
     *            操作对象
     * @return String 返回删除SQL语句
     * @see SqlBuilder#BEGIN()
     * @see SqlBuilder#DELETE_FROM(String)
     * @see SqlBuilder#WHERE(String)
     */
    public String deleteBySelective(T obj) {
        try {
            Map<String, String> id = new HashMap<String, String>();
            id = obj.getIdField();
            String dbName = id.get("dbName");
            String modelName = id.get("modelName");
            BEGIN();
            DELETE_FROM(obj.tableName());
            obj.caculationColumnList(true);
            String whereCondition=obj.returnSelectiveWhereSet();
            if (StringUtils.isBlank(whereCondition)) {
              //没有查询条件
                whereCondition="1=2";
            }
            WHERE(whereCondition);
            return SQL();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "";
    }

    /**
     * 
     * selectById
     * 
     * @Description 通用根据id获取指定的实体
     * @param params
     * @return
     * @return String
     * @see
     * @since
     */
    public String selectById(Map<String, Object> params) {
        T t = null;
        try {
            Class<T> clazz = (Class<T>) params.get("clazz");
            t = clazz.newInstance();
            Map<String, String> info = new HashMap<String, String>();
            info = t.getIdField();
            String dbName = info.get("dbName");
            BEGIN();
            t.caculationColumnList(true);
            SELECT(dbName+","+t.returnInsertColumnsName());
            FROM(t.tableName());
            WHERE(dbName + "=" + params.get("id"));

        } catch (Exception e) {
            e.printStackTrace();
            // t=null;
        }
        return SQL();
    }
    
    /**
     * 
     * deleteById
     * 
     * @Description 根据id进行删除
     * @param params
     * @return
     * @return String 
     * @see
     * @since
     */
    public String deleteById(Map<String, Object> params) {
        T t = null;
        try {
            Class<T> clazz = (Class<T>) params.get("clazz");
            t = clazz.newInstance();
            Map<String, String> info = new HashMap<String, String>();
            info = t.getIdField();
            String dbName = info.get("dbName");
            String modelName = info.get("modelName");
            BEGIN();
            DELETE_FROM(t.tableName());
            WHERE(dbName + "=" + params.get("id"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SQL();
    }
    
    
    public String selectBySelective(T obj) {
        if (obj==null) {
            throw new NullPointerException("查询条件对象不能为空，如果没有条件可以放一个空对象");
        }
        String sql="";
        try {
            Map<String, String> info = new HashMap<String, String>();
            info = obj.getIdField();
            String dbName = info.get("dbName");
            BEGIN();
            obj.caculationColumnList(true);
            SELECT(dbName+","+obj.returnInsertColumnsName());
            FROM(obj.tableName());
            String whereCondition=obj.returnSelectiveWhereSet();
            if (StringUtils.isBlank(whereCondition)) {
              //没有查询条件
                whereCondition="1=1";
            }
            WHERE(whereCondition);
            sql=SQL();
            if (StringUtils.isNotBlank(obj.getOrderBy())) {
                sql+=" ORDER BY "+obj.getOrderBy();
            }
            if (obj.getPageSize()!=null&&obj.getPageNumber()!=null) {
                Integer pageNumber=obj.getPageNumber();
                Integer pageSize=obj.getPageSize();
                Integer startIndex=pageNumber*pageSize;
                sql+=" LIMIT "+startIndex+","+pageSize;
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

    public static void main(String[] args) throws Exception {
        SqlBuilderTemplete<Demo> builderTemplete = new SqlBuilderTemplete<Demo>();
        Demo demo = new Demo();
        demo.setName("李四");
        //demo.setAge(25);
        //demo.setId(3);
        demo.setOrderBy("age desc");
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("id", 4);
        params.put("clazz", Demo.class);
        
        //String sql = builderTemplete.selectById(params);
        String sql = builderTemplete.selectBySelective(demo);
        System.out.println(sql);
        
        
        System.out.println("_____________");
    }
}