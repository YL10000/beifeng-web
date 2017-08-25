/*
 * 文件名： PersistentObject.java
 * 
 * 创建日期： 2008-11-27
 *
 * Copyright(C) 2008, by conntsing.
 *
 * 原始作者: <a href="mailto:sun128837@126.com" alt="changqing">conntsing</a>
 *
 */
package com.beifeng.web.model.common;

import java.io.Serializable;


/**
 * 实体类必须实现的接口，目的是每个实体类必须提供一个逐渐ID
 * @author <a href="mailto:zhsj0110@163.com">zhoushijun</a>
 *
 */
public abstract class PersistentObject implements Serializable {
	
	private static final long serialVersionUID = -1970096435615711838L;

	public abstract Object getId();
	
	

//	public void setData(Object vo) throws ApplicationBaseException {
//		try {
//			CloneUtil.copyProperties(this, vo);
//		}		catch (IllegalAccessException e) {
////			String msg = ExceptionMessage
////					.getString("Class.Common.CloneUtil.CopyProperties.Exception");
////			msg = msg + "from Obj " + this.getClass().getName();
////			msg = msg + "to Obj " + vo.getClass().getName();
//			throw new ApplicationBaseException("Class.Common.CloneUtil.CopyProperties.Exception");
//		}		catch (InvocationTargetException e) {
////			String msg = ExceptionMessage
////					.getString("Class.Common.CloneUtil.CopyProperties.Exception");
////			msg = msg + "from Obj " + this.getClass().getName();
////			msg = msg + "to Obj " + vo.getClass().getName();
//			throw new ApplicationBaseException("Class.Common.CloneUtil.CopyProperties.Exception");
//		}
//	}
//
//	public boolean equals(Object o) {
//		if (this == o) {
//			return true;
//		}
//		if (!(o instanceof PersistentObject)) {
//			return false;
//		}
//		EqualsBuilder eq = new EqualsBuilder();
//		if (this.getId() != null) {
//			return eq.append(this.getId(),
//					((PersistentObject) o).getId()).isEquals();
//		}		else {
//			return (((PersistentObject) o).getId() == null);
//		}
//	}
//
//	public String toString() {
//		StringBuilder builder = new StringBuilder(this.getClass().getName());
//		builder.append("@identifier=").append(this.getId());
//		
////		
////		ToStringBuilder strBuilder = new ToStringBuilder(this);
////		if (this.getIdentifier() != null) {
////			strBuilder.append("identifier", this.getIdentifier());
////		}
//		return builder.toString();
//	}
//
//	public int hashCode() {
//		return (this.getId() != null ? new HashCodeBuilder(17, 37)
//				.append(this.getId().toString()).toHashCode() : 0);
//	}
}
