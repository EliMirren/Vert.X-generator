package com.szmirren.models;

import java.util.List;

/**
 * 表的属性与实体类的配置
 * 
 * @author Mirren
 *
 */
public class EntityAttribute {

	private String tableName;// 表的名字
	private String tableAlias;// 表的别名
	private String primaryKey;// 主键的名字
	private List<AttributeCVF> attrs;// 表的属性
	private ClassConfig config;// 生成实体类设置
	private List<String> importPackages;// 需要引入的包名
	private String entityPackage;// 实体类存放的包名
	private String entityName;// 实体类名字

	public EntityAttribute() {
		super();
	}

	public EntityAttribute(String tableName, String tableAlias, String primaryKey, List<AttributeCVF> attrs,
			ClassConfig config, List<String> importPackages, String entityPackage, String entityName) {
		super();
		this.tableName = tableName;
		this.tableAlias = tableAlias;
		this.primaryKey = primaryKey;
		this.attrs = attrs;
		this.config = config;
		this.importPackages = importPackages;
		this.entityPackage = entityPackage;
		this.entityName = entityName;
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

	/**
	 * 如果别名为空字符串时返回
	 * 
	 * @param tableAlias
	 */
	public void setTableAlias(String tableAlias) {
		if (tableAlias == null || "".equals(tableAlias.trim())) {
			return;
		}
		this.tableAlias = tableAlias;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<AttributeCVF> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<AttributeCVF> attrs) {
		this.attrs = attrs;
	}

	public ClassConfig getConfig() {
		return config;
	}

	public void setConfig(ClassConfig config) {
		this.config = config;
	}

	public List<String> getImportPackages() {
		return importPackages;
	}

	public void setImportPackages(List<String> importPackages) {
		this.importPackages = importPackages;
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
