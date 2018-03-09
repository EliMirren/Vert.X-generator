package com.szmirren.models;

/**
 * 首页配置信息
 * 
 * @author Mirren
 *
 */
public class HistoryConfig {
	private String historyConfigName;
	private String projectPath;
	private String entityPackage;
	private String entityName;
	private String daoPackage;
	private String daoName;
	private String bizPackage;
	private String bizName;
	private String routerPackage;
	private String routerName;
	private String sqlPackage;
	private String sqlName;
	private String assistPackage;
	private String abstractSqlPackage;
	private String sqlParamsPackage;
	private String codeFormat;
	private boolean isCreateEntity;
	private boolean isCreateDao;
	private boolean isCreateBiz;
	private boolean isCreateRouter;
	private boolean isJsonToCamel;

	private String dbType;// 数据库类型
	private DaoConfig daoConfig;
	private BizConfig bizConfig;
	private RouterConfig routerConfig;
	private SQLConfig sqlConfig;
	private TemplateConfig templateConfig;

	public HistoryConfig() {
		super();
	}

	public HistoryConfig(String historyConfigName, String projectPath, String entityPackage, String entityName,
			String daoPackage, String daoName, String bizPackage, String bizName, String routerPackage,
			String routerName, String sqlPackage, String sqlName, String assistPackage, String abstractSqlPackage,
			String sqlParamsPackage, String codeFormat, boolean isCreateEntity, boolean isCreateDao,
			boolean isCreateBiz, boolean isCreateRouter, boolean isJsonToCamel) {
		super();
		this.historyConfigName = historyConfigName;
		this.projectPath = projectPath;
		this.entityPackage = entityPackage;
		this.entityName = entityName;
		this.daoPackage = daoPackage;
		this.daoName = daoName;
		this.bizPackage = bizPackage;
		this.bizName = bizName;
		this.routerPackage = routerPackage;
		this.routerName = routerName;
		this.sqlPackage = sqlPackage;
		this.sqlName = sqlName;
		this.assistPackage = assistPackage;
		this.abstractSqlPackage = abstractSqlPackage;
		this.sqlParamsPackage = sqlParamsPackage;
		this.codeFormat = codeFormat;
		this.isCreateEntity = isCreateEntity;
		this.isCreateDao = isCreateDao;
		this.isCreateBiz = isCreateBiz;
		this.isCreateRouter = isCreateRouter;
		this.isJsonToCamel = isJsonToCamel;
	}

	public String getHistoryConfigName() {
		return historyConfigName;
	}

	public void setHistoryConfigName(String historyConfigName) {
		this.historyConfigName = historyConfigName;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
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

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getBizPackage() {
		return bizPackage;
	}

	public void setBizPackage(String bizPackage) {
		this.bizPackage = bizPackage;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getRouterPackage() {
		return routerPackage;
	}

	public void setRouterPackage(String routerPackage) {
		this.routerPackage = routerPackage;
	}

	public String getRouterName() {
		return routerName;
	}

	public void setRouterName(String routerName) {
		this.routerName = routerName;
	}

	public String getSqlPackage() {
		return sqlPackage;
	}

	public void setSqlPackage(String sqlPackage) {
		this.sqlPackage = sqlPackage;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getAssistPackage() {
		return assistPackage;
	}

	public void setAssistPackage(String assistPackage) {
		this.assistPackage = assistPackage;
	}

	public String getAbstractSqlPackage() {
		return abstractSqlPackage;
	}

	public void setAbstractSqlPackage(String abstractSqlPackage) {
		this.abstractSqlPackage = abstractSqlPackage;
	}

	public String getSqlParamsPackage() {
		return sqlParamsPackage;
	}

	public void setSqlParamsPackage(String sqlParamsPackage) {
		this.sqlParamsPackage = sqlParamsPackage;
	}

	public String getCodeFormat() {
		return codeFormat;
	}

	public void setCodeFormat(String codeFormat) {
		this.codeFormat = codeFormat;
	}

	public boolean isCreateEntity() {
		return isCreateEntity;
	}

	public void setCreateEntity(boolean isCreateEntity) {
		this.isCreateEntity = isCreateEntity;
	}

	public boolean isCreateDao() {
		return isCreateDao;
	}

	public void setCreateDao(boolean isCreateDao) {
		this.isCreateDao = isCreateDao;
	}

	public boolean isCreateBiz() {
		return isCreateBiz;
	}

	public void setCreateBiz(boolean isCreateBiz) {
		this.isCreateBiz = isCreateBiz;
	}

	public boolean isCreateRouter() {
		return isCreateRouter;
	}

	public void setCreateRouter(boolean isCreateRouter) {
		this.isCreateRouter = isCreateRouter;
	}

	public boolean isJsonToCamel() {
		return isJsonToCamel;
	}

	public void setJsonToCamel(boolean isJsonToCamel) {
		this.isJsonToCamel = isJsonToCamel;
	}

	public DaoConfig getDaoConfig() {
		return daoConfig;
	}

	public void setDaoConfig(DaoConfig daoConfig) {
		this.daoConfig = daoConfig;
	}

	public BizConfig getBizConfig() {
		return bizConfig;
	}

	public void setBizConfig(BizConfig bizConfig) {
		this.bizConfig = bizConfig;
	}

	public RouterConfig getRouterConfig() {
		return routerConfig;
	}

	public void setRouterConfig(RouterConfig routerConfig) {
		this.routerConfig = routerConfig;
	}

	public SQLConfig getSqlConfig() {
		return sqlConfig;
	}

	public void setSqlConfig(SQLConfig sqlConfig) {
		this.sqlConfig = sqlConfig;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public TemplateConfig getTemplateConfig() {
		return templateConfig;
	}

	public void setTemplateConfig(TemplateConfig templateConfig) {
		this.templateConfig = templateConfig;
	}

}
