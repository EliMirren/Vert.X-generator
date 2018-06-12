package com.szmirren.entity;

import java.util.List;

/**
 * 实体类的上下文
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class EntityContent {
	/** 实体类的包名 */
	private String classPackage;
	/** 实体类的类型 */
	private String className;
	/** 数据库表的名字 */
	private String tableName;
	/** 表的别名 */
	private String tableAlias;
	/** 数据库表的主键名字 */
	private String primaryKey;

	/** 实体类的属性信息 */
	private List<FieldAttribute> attrs;
	/**
	 * 初始化
	 */
	public EntityContent() {
		super();
	}
	/**
	 * 初始化
	 * 
	 * @param classPackage
	 *          包名
	 * @param className
	 *          类名
	 * @param tableName
	 *          表名
	 */
	public EntityContent(String classPackage, String className, String tableName) {
		super();
		this.classPackage = classPackage;
		this.className = className;
		this.tableName = tableName;
	}

	public String getClassPackage() {
		return classPackage;
	}
	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableAlias() {
		return tableAlias;
	}
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public List<FieldAttribute> getAttrs() {
		return attrs;
	}
	public void setAttrs(List<FieldAttribute> attrs) {
		this.attrs = attrs;
	}
	@Override
	public String toString() {
		return "EntityContent [classPackage=" + classPackage + ", className=" + className + ", tableName=" + tableName + ", tableAlias="
				+ tableAlias + ", primaryKey=" + primaryKey + ", attrs=" + attrs + "]";
	}

}
