package com.beifeng.web.model.common;

import com.beifeng.web.conmon.persistence.support.PageInfo;


public class BaseExample {
	
	private boolean  pageEnable = false;
	private int offset = 0;
	private int limit = 10;
	
	/**
	 * 是否执行分页，默认不分页，如果设置为true，则参与分页,默认分页方式为每页10条
	 * @return
	 */
	public boolean isPageEnable() {
		return pageEnable;
	}
	
	/**
	 * 是否执行分页，默认不分页，如果设置为true，则参与分页,默认分页方式为每页10条
	 * @param pageEnable
	 */
	public void setPageEnable(boolean pageEnable) {
		this.pageEnable = pageEnable;
	}

	
	
	/**
	 * 默认每页10条
	 */
	public int getLimit(){
		return this.limit;
	}
	
	
	
	/**
	 * 默认从第0条开始
	 */
	public int getOffset(){
		return this.offset;
	}

	
	/**
	 * 默认从第0条开始
	 * @param offset
	 */
	public void setOffset(int offset) {
		this.pageEnable = true;
		this.offset = offset;
	}

	/**
	 * 默认每页10条
	 * @param limit
	 */
	public void setLimit(int limit) {
		this.pageEnable = true;
		this.limit = limit;
	}
	
	
	/**
	 * 设置分页
	 * @param pageInfo
	 */
	public void setPageInfo( PageInfo pageInfo){
		this.pageEnable = true;
		this.setLimit(pageInfo.getPageSize());
		this.setOffset(pageInfo.getFirstItemPos());
	}
}
