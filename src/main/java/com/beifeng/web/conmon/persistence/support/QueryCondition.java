package com.beifeng.web.conmon.persistence.support;

import java.util.HashMap;
import java.util.Map;

import  com.beifeng.web.model.common.BaseExample;

public class QueryCondition extends BaseExample {
	
	private PageInfo pageInfo = null;


	private boolean distinct = false;
	
	/** 要排序的字段 */
	private String orderBy;

	/** 按什么排序，只能是：asc||desc */
	private String order = "asc";

	/** 扩展查询条件 */
	private Map<String,Object> params = null;
	

	/**
	 * 获得如何排序
	 * 
	 * @return 返回 asc或者desc。
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置如何排序
	 * 
	 * @param order
	 *            要设置的排序方式，asc或者desc。
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 获得要排序的字段
	 * 
	 * @return 返回排序字段。
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置要排序的字段
	 * 
	 * @param orderBy
	 *            要设置的排序字段。
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	
	/**
	 * 默认false
	 * @return
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 *  默认false
	 * @param distinct
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * 获得查询条件
	 * 
	 * @return 返回 searchCondition。
	 */
	public Map<String,Object> getParams() {
		return params;
	}

	/**
	 * 设置查询条件，本条件由调用该类的方法注入SearchCondition实例。
	 * 
	 * @param searchCondition
	 *            要设置的 searchCondition。
	 */
	public void setSarams(Map<String,Object> pramMap) {
		this.params = params;
	}
	
	/**
	 * 添加扩展查询条件
	 * @param filed
	 * @param value
	 */
	public void addParam(String filed ,Object value){
		if( null == this.params ){
			this.params = new HashMap<String , Object>();
		}
		this.params.put(filed, value);
	}
	
	/**
	 * 获取分页信息
	 * @return
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	/**
	 * 设置分页信息
	 * @param pageInfo
	 */
	public void setPageInfo(PageInfo pageInfo) {
		super.setPageInfo(pageInfo);
		this.pageInfo = pageInfo;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getName());
		builder.append(",orderBy=").append(  this.orderBy);
		builder.append(",order=").append(  this.order);
		builder.append( null == params ? "" : "," + params.toString());

		return builder.toString();
	}

}
