package com.szmirren.models;

/**
 * 该类用于存储部分通用的名字
 * 
 * @author Mirren
 *
 */
public enum CommonName {
	TEMPLATE_DIR_NAME("template"),
	ASSIST("SqlAssist"),
	SQL_WHERE_CONDITION("SqlWhereCondition"),
	ABSTRACT_SQL("AbstractSQL"), 
	SQL_AND_PARAMS("SqlAndParams"),
	SQL_PROPERTY_VALUE("SqlPropertyValue"), 
	JSON_UTIL_PACKAGE("io.vertx.core.json.Json"), 
	JSON_OBJECT_PACKAGE("io.vertx.core.json"),
	JSON_OBJECT_NAME("JsonObject"), 
	JSON_ARRAY_PACKAGE("io.vertx.core.json"),
	JSON_ARRAY_NAME("JsonArray");

	private String value;

	CommonName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
