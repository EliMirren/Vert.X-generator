package com.szmirren.models;

/**
 * 用于保存配置文件模板
 * 
 * @author Mirren
 *
 */
public class TemplateConfig {
	private String entityAddName;
	private String daoName = "Temp-Default-Dao.txt";
	private String daoAddName = "Temp-Default-DaoAdd.txt";
	private String bizName = "Temp-Default-Biz.txt";
	private String bizAddName = "Temp-Default-BizAdd.txt";
	private String webRouterName = "Temp-Default-ServiceRouter.txt";
	private String webRouterAddName = "Temp-Default-WebRouterAdd.txt";
	private String sqlName = "Temp-Defult-SqlSubClass.txt";
	private String sqlNonIdName = "Temp-Defult-SqlSubClassNonId.txt";
	private String addSQLName = "Temp-Defult-SQLAdd.txt";
	private String assistName = "Temp-Defult-SqlAssist.txt";
	private String assistConditionName = "Temp-Defult-SqlWhereCondition.txt";
	private String abstractSQLName = "Temp-Defult-Abstract{*DBType*}.txt";
	private String sqlAndParamsName = "Temp-Defult-SqlAndParams.txt";
	private String sqlPropertyValueName = "Temp-Defult-SqlPropertyValue.txt";

	public TemplateConfig() {
		super();
	}

	public TemplateConfig(String entityAddName, String daoName, String daoAddName, String bizName, String bizAddName,
			String webRouterName, String webRouterAddName, String sqlName, String sqlNonIdName, String addSQLName,
			String assistName, String assistConditionName, String abstractSQLName, String sqlAndParamsName,
			String sqlPropertyValueName) {
		super();
		this.entityAddName = entityAddName;
		this.daoName = daoName;
		this.daoAddName = daoAddName;
		this.bizName = bizName;
		this.bizAddName = bizAddName;
		this.webRouterName = webRouterName;
		this.webRouterAddName = webRouterAddName;
		this.sqlName = sqlName;
		this.sqlNonIdName = sqlNonIdName;
		this.addSQLName = addSQLName;
		this.assistName = assistName;
		this.assistConditionName = assistConditionName;
		this.abstractSQLName = abstractSQLName;
		this.sqlAndParamsName = sqlAndParamsName;
		this.sqlPropertyValueName = sqlPropertyValueName;
	}

	public String getEntityAddName() {
		return entityAddName;
	}

	public void setEntityAddName(String entityAddName) {
		this.entityAddName = entityAddName;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getDaoAddName() {
		return daoAddName;
	}

	public void setDaoAddName(String daoAddName) {
		this.daoAddName = daoAddName;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizAddName() {
		return bizAddName;
	}

	public void setBizAddName(String bizAddName) {
		this.bizAddName = bizAddName;
	}

	public String getWebRouterName() {
		return webRouterName;
	}

	public void setWebRouterName(String webRouterName) {
		this.webRouterName = webRouterName;
	}

	public String getWebRouterAddName() {
		return webRouterAddName;
	}

	public void setWebRouterAddName(String webRouterAddName) {
		this.webRouterAddName = webRouterAddName;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getSqlNonIdName() {
		return sqlNonIdName;
	}

	public void setSqlNonIdName(String sqlNonIdName) {
		this.sqlNonIdName = sqlNonIdName;
	}

	public String getAddSQLName() {
		return addSQLName;
	}

	public void setAddSQLName(String addSQLName) {
		this.addSQLName = addSQLName;
	}

	public String getAssistName() {
		return assistName;
	}

	public void setAssistName(String assistName) {
		this.assistName = assistName;
	}

	public String getAssistConditionName() {
		return assistConditionName;
	}

	public void setAssistConditionName(String assistConditionName) {
		this.assistConditionName = assistConditionName;
	}

	public String getAbstractSQLName() {
		return abstractSQLName;
	}

	public void setAbstractSQLName(String abstractSQLName) {
		this.abstractSQLName = abstractSQLName;
	}

	public String getSqlAndParamsName() {
		return sqlAndParamsName;
	}

	public void setSqlAndParamsName(String sqlAndParamsName) {
		this.sqlAndParamsName = sqlAndParamsName;
	}

	public String getSqlPropertyValueName() {
		return sqlPropertyValueName;
	}

	public void setSqlPropertyValueName(String sqlPropertyValueName) {
		this.sqlPropertyValueName = sqlPropertyValueName;
	}

}
