/*
 * 项目名：beifeng-web
 * 文件名：BaseModel.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：BaseModel处理类
 * 修改人：yanglin
 * 修改时间：2017年8月24日 上午11:22:08
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.model.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.beifeng.web.model.exception.ModelStructureException;



/**
 * 
 * BaseModel
 *	
 * @Description BaseModel处理类
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
public class BaseModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7477713159094055114L;
    
    
    
    public BaseModel() {
        super();
    }
    
    public BaseModel(Integer pageNumber, Integer pageSize) {
        super();
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }



    //当前页
    private Integer pageNumber;
    
    //每页个数
    private Integer pageSize;
    
    //排序
    private String orderBy;
    
    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * tableName
     * 
     * @Description 获取Model对象对应的表名,需要Model对象中的属性定义@Table(name)
     * @return String
     * @see
     */
    public String tableName() {
        Table table = this.getClass().getAnnotation(Table.class);
        if (table != null)
            return table.name();
        else
            throw new ModelStructureException(
                    "undefine Model Object @Table, need Tablename(@Table(name))");
    }

    /**
     * id
     * 
     * @Description 获取Model对象中的主键字段名,需要定义@Id,@Column
     * @return Map<String,String>
     * @see
     */
    public Map<String, String> getIdField() {
        Map<String, String> result = new HashMap<String, String>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {

                if (field.isAnnotationPresent(Column.class)) {

                    Column column = field.getAnnotation(Column.class);
                    if (StringUtils.isNotBlank(column.name())) {

                        // 实体字段名称
                        result.put("modelName", field.getName());

                        // 对应数据库字段名称
                        result.put("dbName", column.name());
                    } else {
                        result.put("modelName", field.getName());
                        result.put("dbName", field.getName());
                    }
                } else {
                    result.put("modelName", field.getName());
                    result.put("dbName", field.getName());
                }
            }
            continue;
        }
        if (result.size() == 0)
            throw new RuntimeException("undefine Model Object @Id");
        return result;
    }

    /**
     * @Fields columnMap 存放Model对象的列信息
     */
    private transient static Map<Class<? extends BaseModel>, List<String>> columnMap = new HashMap<Class<? extends BaseModel>, List<String>>();

    private boolean isNull(String filedname) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {

            if (field.isAnnotationPresent(Column.class)) {

                Column column = field.getAnnotation(Column.class);
                if (StringUtils.isNotBlank(column.name())
                        && filedname.equals(column.name())) {
                    return false;
                }
                if (StringUtils.isBlank(column.name())
                        && filedname.equals(field.getName())) {
                    return isNull(field);
                }

            }
            continue;
        }
        return true;
    }

    private boolean isNull(Field field) {
        try {
            field.setAccessible(true);
            return field.get(this) == null;
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * caculationColumnList
     * 
     * @Description 用于计算类定义 ,需要Model Object中的属性定义@Column(name)
     * @return void
     * @see
     */

    public void caculationColumnList(Boolean hasId) {
        if (columnMap.containsKey(this.getClass()))
            return;

        Field[] fields = this.getClass().getDeclaredFields();
        List<String> columnList = new ArrayList<String>(fields.length);

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)
                    && !field.isAnnotationPresent(Id.class)) {
                Column column = field.getAnnotation(Column.class);
                if (StringUtils.isNotBlank(column.name())) {
                    columnList.add(column.name());
                } else {
                    columnList.add(field.getName());
                }
            }else if (hasId&&field.isAnnotationPresent(Id.class)) {
                Column column = field.getAnnotation(Column.class);
                if (StringUtils.isNotBlank(column.name())) {
                    columnList.add(column.name());
                } else {
                    columnList.add(field.getName());
                }
            }
        }
        columnMap.put(this.getClass(), columnList);
    }

    /**
     * returnWhereColumnsName
     * 
     * @Description 获取用于Where的 有值字段表
     * @return List<WhereColumn>
     * @see
     */
    public List<WhereColumn> returnWhereColumnsName() {
        Field[] fields = this.getClass().getDeclaredFields();
        List<WhereColumn> columnList = new ArrayList<WhereColumn>(fields.length);

        for (Field field : fields) {

            if (field.isAnnotationPresent(Column.class) && isNull(field)) {
                Column column = field.getAnnotation(Column.class);
                if (StringUtils.isNotBlank(column.name())) {

                    columnList.add(new WhereColumn(column.name(),
                            field.getGenericType().equals(String.class)));
                } else {

                    columnList.add(new WhereColumn(field.getName(),
                            field.getGenericType().equals(String.class)));
                }
            }
        }

        return columnList;
    }

    /**
     * WhereColumn
     * 
     * @Description Where条件信息
     * @author EngineerLi
     * @version 版本号:1.0,2015-3-19
     * @see
     * @since
     */
    public class WhereColumn {

        public String name;
        public boolean isString;

        public WhereColumn(String name, boolean isString) {
            this.name = name;
            this.isString = isString;
        }

        @Override
        public String toString() {
            return "WhereColumn [name=" + name + ", isString=" + isString + "]";
        }
    }

    /**
     * returnInsertColumnsName
     * 
     * @Description 用于获取Insert的字段累加
     * @return String
     * @see
     */
    public String returnInsertColumnsName() {
        StringBuilder sb = new StringBuilder();

        List<String> list = columnMap.get(this.getClass());
        int i = 0;
        for (String column : list) {
            if (isNull(column))
                continue;

            if (i++ != 0)
                sb.append(',');
            sb.append(column);
        }
        return sb.toString();
    }

    /**
     * returnInsertColumnsDefine
     * 
     * @Description 用于获取Insert的字段映射累加
     * @return String
     * @see
     */
    public String returnInsertColumnsDefine(Boolean hasId) {

        StringBuilder sb = new StringBuilder();
        List<String> list = columnMap.get(this.getClass());
        Field[] fields = this.getClass().getDeclaredFields();
        int i = 0;
        for (String column : list) {
            if (isNull(column))
                continue;

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (field.isAnnotationPresent(Column.class)
                            && (!field.isAnnotationPresent(Id.class))||(hasId&&field.get(this)!=null&&field.isAnnotationPresent(Id.class))) {
                        Column col = field.getAnnotation(Column.class);
                        if (StringUtils.isNotBlank(col.name()) && column.equals(col.name())) {
                            if (i++ != 0)
                                sb.append(',');
                            sb.append("#{").append(field.getName()).append('}');
                        }
                        if (StringUtils.isBlank(col.name())
                                && column.equals(field.getName())) {
                            if (i++ != 0)
                                sb.append(',');
    
                            sb.append("#{").append(field.getName()).append('}');
                        }
    
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return sb.toString();

    }

    /**
     * returnUpdateSet
     * 
     * @Description 用于获取Update Set的字段累加
     * @return String
     * @see
     */
    public String returnUpdateSet() {
        StringBuilder sb = new StringBuilder();

        List<String> list = columnMap.get(this.getClass());
        Field[] fields = this.getClass().getDeclaredFields();
        int i = 0;
        for (String column : list) {
            if (isNull(column))
                continue;

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)
                        && !field.isAnnotationPresent(Id.class)) {
                    Column col = field.getAnnotation(Column.class);
                    if (StringUtils.isNotBlank(col.name()) && column.equals(col.name())) {
                        if (i++ != 0)
                            sb.append(',');

                        sb.append(column + "=").append("#{").append(field.getName())
                                .append('}');
                    }
                    if (StringUtils.isBlank(col.name())
                            && column.equals(field.getName())) {
                        if (i++ != 0)
                            sb.append(',');

                        sb.append(column + "=").append("#{").append(field.getName())
                                .append('}');
                    }

                }
            }

        }
        return sb.toString();
    }
    
    
    
    /**
     * 
     * returnSelectiveUpdateSet
     * 
     * @Description 拼接选择性修改的字段
     * @return
     * @return String 
     * @throws IllegalAccessException 
     * @throws Exception 
     * @see
     * @since
     */
    public String returnSelectiveUpdateSet() throws Exception {
        StringBuilder sb = new StringBuilder();

        List<String> list = columnMap.get(this.getClass());
        Field[] fields = this.getClass().getDeclaredFields();
        int i = 0;
        for (String column : list) {
            if (isNull(column))
                continue;

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)
                        && !field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    Column col = field.getAnnotation(Column.class);
                    if (StringUtils.isNotBlank(col.name()) && column.equals(col.name())&&field.get(this)!=null) {
                        if (i++ != 0)
                            sb.append(',');

                        sb.append(column + "=").append("#{").append(field.getName())
                                .append('}');
                    }
                    if (StringUtils.isBlank(col.name())
                            && column.equals(field.getName()) && field.get(this)!=null) {
                        if (i++ != 0)
                            sb.append(',');

                        sb.append(column + "=").append("#{").append(field.getName())
                                .append('}');
                    }

                }
            }

        }
        return sb.toString();
    }
    
    /**
     * 
     * returnSelectiveWhereSet
     * 
     * @Description 拼接查询条件
     * @return
     * @throws Exception
     * @return String 
     * @see
     * @since
     */
    public String returnSelectiveWhereSet() throws Exception {
        StringBuilder sb = new StringBuilder();

        List<String> list = columnMap.get(this.getClass());
        Field[] fields = this.getClass().getDeclaredFields();
        int i = 0;
        for (String column : list) {
            if (isNull(column))
                continue;

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Column col = field.getAnnotation(Column.class);
                    if (StringUtils.isNotBlank(col.name()) && column.equals(col.name())&&field.get(this)!=null) {
                        if (i++ != 0)
                            sb.append(" AND ");

                        sb.append(column + "=").append("#{").append(field.getName())
                                .append('}');
                    }
                    if (StringUtils.isBlank(col.name())
                            && column.equals(field.getName()) && field.get(this)!=null) {
                        if (i++ != 0)
                            sb.append(" AND ");

                        sb.append(column + "=").append("#{").append(field.getName())
                                .append('}');
                    }

                }
            }

        }
        return sb.toString();
    }

    public Integer getId() {
        return 0;
    }

    /**
     * toJSONString
     * 
     * @Description 转化Model Object为JSon格式
     * @return String
     * @see
     */
    public String toJSONString() {
        JSONObject json = new JSONObject(this);
        return json.toString();
    }

    /*
     * <p>Title: toString</p> <p>Description: 打印类字段信息</p>
     * 
     * @return
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers()))
                continue;
            Object value = null;
            try {
                f.setAccessible(true);
                value = f.get(this);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null)
                sb.append(f.getName()).append('=').append(value).append(',');
        }
        sb.append(']');
        return sb.toString();
    }
}