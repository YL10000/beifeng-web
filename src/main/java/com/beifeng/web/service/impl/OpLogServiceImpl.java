/*
 * 项目名：beifeng-web
 * 文件名：OpLogServiceImpl.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：操作日志service实现类
 * 修改人：yanglin
 * 修改时间：2017年8月25日 下午5:51:27
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beifeng.web.dao.sys.log.IOpLogDao;
import com.beifeng.web.model.sys.log.OpLog;
import com.beifeng.web.service.IOpLogService;

/**
 * OpLogServiceImpl
 *	
 * @Description 操作日志service实现类
 * @author yanglin
 * @version 1.0,2017年8月25日
 * @see
 * @since
 */
@Service("opLogService")
public class OpLogServiceImpl implements IOpLogService {
    
    @Autowired
    IOpLogDao opLogDao;

    @Override
    public void insert(OpLog obj) {
        opLogDao.insert(obj);
    }

    /* (non-Javadoc)
     * @see com.beifeng.web.service.IBaseService#insertReturnId(com.beifeng.web.model.common.BaseModel)
     */
    @Override
    public Object insertReturnId(OpLog obj) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.beifeng.web.service.IBaseService#update(com.beifeng.web.model.common.BaseModel)
     */
    @Override
    public void update(OpLog obj) {
    }

    /* (non-Javadoc)
     * @see com.beifeng.web.service.IBaseService#updateSelective(com.beifeng.web.model.common.BaseModel)
     */
    @Override
    public void updateSelective(OpLog obj) {
    }

    /* (non-Javadoc)
     * @see com.beifeng.web.service.IBaseService#deleteBySelective(com.beifeng.web.model.common.BaseModel)
     */
    @Override
    public void deleteBySelective(OpLog obj) {
    }

    /* (non-Javadoc)
     * @see com.beifeng.web.service.IBaseService#selectMapBySelective(com.beifeng.web.model.common.BaseModel)
     */
    @Override
    public List<Map<String, Object>> selectMapBySelective(OpLog obj) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.beifeng.web.service.IBaseService#selectById(java.lang.Object)
     */
    @Override
    public OpLog selectById(Object id) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.beifeng.web.service.IBaseService#selectBySelective(com.beifeng.web.model.common.BaseModel)
     */
    @Override
    public List<OpLog> selectBySelective(OpLog obj) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.beifeng.web.service.IBaseService#deleteById(java.lang.Object)
     */
    @Override
    public void deleteById(Object id) {
    }

}
