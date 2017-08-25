/*
 * 项目名：beifeng-web
 * 文件名：Demo.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：Demo 实体
 * 修改人：yanglin
 * 修改时间：2017年8月24日 上午11:22:08
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beifeng.web.model.common.BaseModel;

/**
 * Demo
 *	
 * @Description Demo 实体
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
@Table(name="demo")
public class Demo extends BaseModel  {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="age")
    private Integer age;

    public Demo() {
        super();
    }

    public Demo(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    
    public Demo(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Demo [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
