/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package com.byws.commons.persistence.support.dialect;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An SQL dialect for MySQL (prior to 5.x).
 *
 * @author Gavin King
 */
public class MySQLDialect extends Dialect {

	public MySQLDialect() {
		super();
		
	}

	protected void registerVarcharTypes() {
	}

	public String getAddColumnString() {
		return "add column";
	}
	
	public boolean qualifyIndexName() {
		return false;
	}

	public boolean supportsIdentityColumns() {
		return true;
	}
	
	public String getIdentitySelectString() {
		return "select last_insert_id()";
	}

	public String getIdentityColumnString() {
		return "not null auto_increment"; //starts with 1, implicitly
	}

	

	public boolean supportsLimit() {
		return true;
	}
	
	public String getDropForeignKeyString() {
		return " drop foreign key ";
	}

	/**
	 * 
	 * @param sql
	 * @param offset  当前位置
	 * @param limit   最大条数
	 * @return
	 */
	public String getLimitString(String sql, int  offset , int limit) {
		return new StringBuilder( sql.length() + 20 )
				.append( sql )
				.append( " limit " ).append(offset).append(",").append(limit)
				.toString();
	}

	public char closeQuote() {
		return '`';
	}

	public char openQuote() {
		return '`';
	}

	public boolean supportsIfExistsBeforeTableName() {
		return true;
	}

	public String getSelectGUIDString() {
		return "select uuid()";
	}

	public boolean supportsCascadeDelete() {
		return false;
	}
	
	public String getTableComment(String comment) {
		return " comment='" + comment + "'";
	}

	public String getColumnComment(String comment) {
		return " comment '" + comment + "'";
	}

	public boolean supportsTemporaryTables() {
		return true;
	}

	public String getCreateTemporaryTableString() {
		return "create temporary table if not exists";
	}

	public String getDropTemporaryTableString() {
		return "drop temporary table";
	}

	public Boolean performTemporaryTableDDLInIsolation() {
		// because we [drop *temporary* table...] we do not
		// have to doAfterTransactionCompletion these in isolation.
		return Boolean.FALSE;
	}


	public boolean supportsCurrentTimestampSelection() {
		return true;
	}

	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}

	public String getCurrentTimestampSelectString() {
		return "select now()";
	}

	public int registerResultSetOutParameter(CallableStatement statement, int col) throws SQLException {
		return col;
	} 
	
	public ResultSet getResultSet(CallableStatement ps) throws SQLException {
		boolean isResultSet = ps.execute(); 
		while (!isResultSet && ps.getUpdateCount() != -1) { 
			isResultSet = ps.getMoreResults(); 
		} 
		return ps.getResultSet();
	}

	public boolean supportsRowValueConstructorSyntax() {
		return true;
	}


	// locking support

	public String getForUpdateString() {
		return " for update";
	}

	public String getWriteLockString(int timeout) {
		return " for update";
	}

	public String getReadLockString(int timeout) {
		return " lock in share mode";
	}


	// Overridden informational metadata ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public boolean supportsEmptyInList() {
		return false;
	}

	public boolean areStringComparisonsCaseInsensitive() {
		return true;
	}

	public boolean supportsLobValueChangePropogation() {
		// note: at least my local MySQL 5.1 install shows this not working...
		return false;
	}

	public boolean supportsSubqueryOnMutatingTable() {
		return false;
	}
}
