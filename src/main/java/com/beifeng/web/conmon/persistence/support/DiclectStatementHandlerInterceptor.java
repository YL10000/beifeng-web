package com.beifeng.web.conmon.persistence.support;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.beifeng.web.model.common.BaseExample;
import com.byws.commons.persistence.support.dialect.MySQLDialect;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class DiclectStatementHandlerInterceptor implements Interceptor {

	private static final String DIALECT = "com.byws.commons.persistence.support.dialect.MySQLDialect";

	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler statement = (RoutingStatementHandler) invocation.getTarget();
		PreparedStatementHandler handler = (PreparedStatementHandler) ReflectUtil.getFieldValue(statement, "delegate");
//		RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(handler, "rowBounds");
		DefaultParameterHandler parameterHandler = (DefaultParameterHandler) ReflectUtil.getFieldValue(handler, "parameterHandler");
		Object obj = parameterHandler.getParameterObject();
		
		if( obj instanceof BaseExample){
			BaseExample baseExample = (BaseExample)obj;
//			PageInfobaseExample.getPageInfo();
//			if (baseExample.isPageEnable() && baseExample.getLimit() > 0 
//					&& baseExample.getLimit() < RowBounds.NO_ROW_LIMIT) {
			
			//是否执行分页
			if ( baseExample.isPageEnable() ) {
				BoundSql boundSql = statement.getBoundSql();
				String sql = boundSql.getSql();
				sql = sql.trim();
				//防止部分insert、update语句使用example时造成追加分页语句，但是这里也无法完全避免这种情况，
				//必须在开发时特别注意，非分页语句要执行example.setPageEnable(false);
				if( sql.startsWith("select") ||sql.startsWith("SELECT") ){
					MySQLDialect dialect = (MySQLDialect) Class.forName(DIALECT).newInstance();
					sql = dialect.getLimitString(sql, baseExample.getOffset(), baseExample.getLimit());

					ReflectUtil.setFieldValue(boundSql, "sql", sql);
				}
			}
		}
		
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}