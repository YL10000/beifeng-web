/*
 * 项目名：beifeng-web
 * 文件名：IOpLogDao.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：操作日志注解
 * 修改人：yanglin
 * 修改时间：2017年8月25日 下午5:38:33
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.conmon.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * MethodLog
 *	
 * @Description 操作日志注解
 * @author yanglin
 * @version 1.0,2017年8月25日
 * @see
 * @since
 */
@Target({ElementType.METHOD,ElementType.TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface MethodLog {  
    String remark() default "";
    
}  