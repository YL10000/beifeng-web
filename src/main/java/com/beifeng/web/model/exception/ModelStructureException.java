/*
 * 项目名：beifeng-web
 * 文件名：ModelStructureException.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：实体结构异常处理类文件
 * 修改人：yanglin
 * 修改时间：2017年8月24日 上午11:22:08
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.model.exception;

/**
 * 
 * ModelStructureException
 *	
 * @Description 实体结构异常处理类
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
public class ModelStructureException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8811843485151506043L;
    

    public ModelStructureException() {
        super();
    }

    public ModelStructureException(String msg) {
        super(msg);
    }

    public ModelStructureException(String msg, Throwable e) {
        super(msg, e);
    }
}
