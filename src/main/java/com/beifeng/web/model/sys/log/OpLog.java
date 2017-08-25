/*
 * 项目名：beifeng-web
 * 文件名：OpLog.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：操作日志
 * 修改人：yanglin
 * 修改时间：2017年8月25日 下午5:33:57
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.model.sys.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beifeng.web.model.common.BaseModel;

/**
 * OpLog
 *	
 * @Description 操作日志
 * @author yanglin
 * @version 1.0,2017年8月25日
 * @see
 * @since
 */
@Table(name="sys_op_log")
public class OpLog extends BaseModel {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column
    private Integer id;
    
    @Column(name="remark")
    private String remark;
    
    @Column(name="method")
    private String method;
    
    @Column(name="result")
    private Integer result;
    
    @Column(name="content")
    private String content;
    
    @Column(name="exception")
    private String exception;
    
    @Column(name="create_time")
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "OpLog [id=" + id + ", remark=" + remark + ", method=" + method
                + ", result=" + result + ", content=" + content + ", exception="
                + exception + ", create_time=" + create_time + "]";
    }

    public OpLog() {
        super();
    }

    public OpLog(String remark, String method, Integer result, String content,
            String exception, Date create_time) {
        super();
        this.remark = remark;
        this.method = method;
        this.result = result;
        this.content = content;
        this.exception = exception;
        this.create_time = create_time;
    }
    
    
    
    
}
