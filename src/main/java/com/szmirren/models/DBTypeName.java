package com.szmirren.models;

/**
 * 数据库类型名称
 * 
 * @author Mirren
 *
 */
public enum DBTypeName {
	MYSQL("MySQL"), POSTGRE_SQL("PostgreSQL"), SQL_SERVER("SqlServer"), ORACLE("Oracle");

	private String value;

	DBTypeName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
