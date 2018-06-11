package com.szmirren.options;

/**
 * 配置信息
 * 
 * @author Mirren
 *
 */
public class HistoryConfig {
	/** 配置信息的名字 */
	private String historyConfigName;
	/** 生产路径 */
	private String projectPath;
	/** 实体类的包名 */
	private String entityPackage;
	/** 实体类的类名 */
	private String entityName;
	/** service包名 */
	private String servicePackage;
	/** service类名 */
	private String serviceName;
	/** service实现类包名 */
	private String serviceImplPackage;
	/** service实现类名 */
	private String serviceImplName;
	/** router类包名 */
	private String routerPackage;
	/** router类名 */
	private String routerName;
	/** SQL类的包 */
	private String sqlPackage;
	/** SQL类名 */
	private String sqlName;
	/** sqlAssist包名 */
	private String sqlAssistPackage;
	/** abstractSql包名 */
	private String abstractSqlPackage;
	/** sqlAndParams包名 */
	private String sqlAndParamsPackage;
	/** 单元测试包名 */
	private String unitTestPackage;
	/** 单元测试类名 */
	private String unitTestName;
	/** 字符编码格式 */
	private String codeFormat;

	/** 数据库配置文件 */
	private DatabaseConfig dbConfig;
	/** 实体类配置文件 */
	private EntityConfig entityConfig;
	/** Service配置文件 */
	private ServiceConfig serviceConfig;
	/** Service实现类的配置文件 */
	private ServiceImplConfig serviceImplConfig;
	/** Router的配置文件 */
	private RouterConfig routerConfig;
	/** SQL的配置文件 */
	private SqlConfig sqlConfig;
	/** SqlAssist的配置文件 */
	private SqlAssistConfig assistConfig;
	/** AbstractSql的配置文件 */
	private AbstractSqlConfig abstractSqlConfig;
	/** SqlAndParamsConfig配置文件 */
	private SqlAndParamsConfig sqlAndParamsConfig;
	/** 单元测试配置文件 */
	private UnitTestConfig unitTestConfig;
	/** 自定义包类的配置文件 */
	private CustomConfig customConfig;
	/** 自定义属性的配置文件 */
	private CustomPropertyConfig customPropertyConfig;

	/**
	 * 初始化
	 */
	public HistoryConfig() {
		super();
	}

	/**
	 * 初始化一个首页属性的配置信息文件
	 * 
	 * @param projectPath
	 * @param entityPackage
	 * @param entityName
	 * @param servicePackage
	 * @param serviceName
	 * @param serviceImplPackage
	 * @param serviceImplName
	 * @param routerPackage
	 * @param routerName
	 * @param sqlPackage
	 * @param sqlName
	 * @param sqlAssistPackage
	 * @param abstractSqlPackage
	 * @param sqlAndParamsPackage
	 * @param codeFormat
	 */
	public HistoryConfig(String projectPath, String entityPackage, String entityName, String servicePackage, String serviceName, String serviceImplPackage, String serviceImplName, String routerPackage,
			String routerName, String sqlPackage, String sqlName, String sqlAssistPackage, String abstractSqlPackage, String sqlAndParamsPackage, String unitTestPackage, String unitTestName,
			String codeFormat) {
		super();
		this.projectPath = projectPath;
		this.entityPackage = entityPackage;
		this.entityName = entityName;
		this.servicePackage = servicePackage;
		this.serviceName = serviceName;
		this.serviceImplPackage = serviceImplPackage;
		this.serviceImplName = serviceImplName;
		this.routerPackage = routerPackage;
		this.routerName = routerName;
		this.sqlPackage = sqlPackage;
		this.sqlName = sqlName;
		this.sqlAssistPackage = sqlAssistPackage;
		this.abstractSqlPackage = abstractSqlPackage;
		this.sqlAndParamsPackage = sqlAndParamsPackage;
		this.unitTestPackage = unitTestPackage;
		this.unitTestName = unitTestName;
		this.codeFormat = codeFormat;
	}

	/**
	 * 初始化一个所有属性的配置信息
	 * 
	 * @param historyConfigName
	 * @param projectPath
	 * @param entityPackage
	 * @param entityName
	 * @param servicePackage
	 * @param serviceName
	 * @param serviceImplPackage
	 * @param serviceImplName
	 * @param routerPackage
	 * @param routerName
	 * @param sqlPackage
	 * @param sqlName
	 * @param sqlAssistPackage
	 * @param abstractSqlPackage
	 * @param sqlAndParamsPackage
	 * @param codeFormat
	 * @param dbConfig
	 * @param entityConfig
	 * @param serviceConfig
	 * @param serviceImplConfig
	 * @param routerConfig
	 * @param sqlConfig
	 * @param assistConfig
	 * @param abstractSqlConfig
	 * @param sqlAndParamsConfig
	 * @param customConfig
	 * @param customPropertyConfig
	 */
	public HistoryConfig(String historyConfigName, String projectPath, String entityPackage, String entityName, String servicePackage, String serviceName, String serviceImplPackage,
			String serviceImplName, String routerPackage, String routerName, String sqlPackage, String sqlName, String sqlAssistPackage, String abstractSqlPackage, String sqlAndParamsPackage,
			String unitTestPackage, String unitTestName, String codeFormat, DatabaseConfig dbConfig, EntityConfig entityConfig, ServiceConfig serviceConfig, ServiceImplConfig serviceImplConfig,
			RouterConfig routerConfig, SqlConfig sqlConfig, SqlAssistConfig assistConfig, AbstractSqlConfig abstractSqlConfig, SqlAndParamsConfig sqlAndParamsConfig, UnitTestConfig unitTestConfig,
			CustomConfig customConfig, CustomPropertyConfig customPropertyConfig) {
		super();
		this.historyConfigName = historyConfigName;
		this.projectPath = projectPath;
		this.entityPackage = entityPackage;
		this.entityName = entityName;
		this.servicePackage = servicePackage;
		this.serviceName = serviceName;
		this.serviceImplPackage = serviceImplPackage;
		this.serviceImplName = serviceImplName;
		this.routerPackage = routerPackage;
		this.routerName = routerName;
		this.sqlPackage = sqlPackage;
		this.sqlName = sqlName;
		this.sqlAssistPackage = sqlAssistPackage;
		this.abstractSqlPackage = abstractSqlPackage;
		this.sqlAndParamsPackage = sqlAndParamsPackage;
		this.unitTestPackage = unitTestPackage;
		this.unitTestName = unitTestName;
		this.codeFormat = codeFormat;
		this.dbConfig = dbConfig;
		this.entityConfig = entityConfig;
		this.serviceConfig = serviceConfig;
		this.serviceImplConfig = serviceImplConfig;
		this.routerConfig = routerConfig;
		this.sqlConfig = sqlConfig;
		this.assistConfig = assistConfig;
		this.abstractSqlConfig = abstractSqlConfig;
		this.sqlAndParamsConfig = sqlAndParamsConfig;
		this.unitTestConfig = unitTestConfig;
		this.customConfig = customConfig;
		this.customPropertyConfig = customPropertyConfig;
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

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	public String getServiceImplName() {
		return serviceImplName;
	}

	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
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

	public String getSqlAssistPackage() {
		return sqlAssistPackage;
	}

	public void setSqlAssistPackage(String sqlAssistPackage) {
		this.sqlAssistPackage = sqlAssistPackage;
	}

	public String getAbstractSqlPackage() {
		return abstractSqlPackage;
	}

	public void setAbstractSqlPackage(String abstractSqlPackage) {
		this.abstractSqlPackage = abstractSqlPackage;
	}

	public String getSqlAndParamsPackage() {
		return sqlAndParamsPackage;
	}

	public void setSqlAndParamsPackage(String sqlAndParamsPackage) {
		this.sqlAndParamsPackage = sqlAndParamsPackage;
	}

	public String getCodeFormat() {
		return codeFormat;
	}

	public void setCodeFormat(String codeFormat) {
		this.codeFormat = codeFormat;
	}

	public DatabaseConfig getDbConfig() {
		return dbConfig;
	}

	public void setDbConfig(DatabaseConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

	public EntityConfig getEntityConfig() {
		return entityConfig;
	}

	public void setEntityConfig(EntityConfig entityConfig) {
		this.entityConfig = entityConfig;
	}

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	public ServiceImplConfig getServiceImplConfig() {
		return serviceImplConfig;
	}

	public void setServiceImplConfig(ServiceImplConfig serviceImplConfig) {
		this.serviceImplConfig = serviceImplConfig;
	}

	public RouterConfig getRouterConfig() {
		return routerConfig;
	}

	public void setRouterConfig(RouterConfig routerConfig) {
		this.routerConfig = routerConfig;
	}

	public SqlConfig getSqlConfig() {
		return sqlConfig;
	}

	public void setSqlConfig(SqlConfig sqlConfig) {
		this.sqlConfig = sqlConfig;
	}

	public SqlAssistConfig getAssistConfig() {
		return assistConfig;
	}

	public void setAssistConfig(SqlAssistConfig assistConfig) {
		this.assistConfig = assistConfig;
	}

	public AbstractSqlConfig getAbstractSqlConfig() {
		return abstractSqlConfig;
	}

	public void setAbstractSqlConfig(AbstractSqlConfig abstractSqlConfig) {
		this.abstractSqlConfig = abstractSqlConfig;
	}

	public SqlAndParamsConfig getSqlAndParamsConfig() {
		return sqlAndParamsConfig;
	}

	public void setSqlAndParamsConfig(SqlAndParamsConfig sqlAndParamsConfig) {
		this.sqlAndParamsConfig = sqlAndParamsConfig;
	}

	public CustomConfig getCustomConfig() {
		return customConfig;
	}

	public void setCustomConfig(CustomConfig customConfig) {
		this.customConfig = customConfig;
	}

	public CustomPropertyConfig getCustomPropertyConfig() {
		return customPropertyConfig;
	}

	public void setCustomPropertyConfig(CustomPropertyConfig customPropertyConfig) {
		this.customPropertyConfig = customPropertyConfig;
	}

	public String getUnitTestPackage() {
		return unitTestPackage;
	}

	public void setUnitTestPackage(String unitTestPackage) {
		this.unitTestPackage = unitTestPackage;
	}

	public String getUnitTestName() {
		return unitTestName;
	}

	public void setUnitTestName(String unitTestName) {
		this.unitTestName = unitTestName;
	}

	public UnitTestConfig getUnitTestConfig() {
		return unitTestConfig;
	}

	public void setUnitTestConfig(UnitTestConfig unitTestConfig) {
		this.unitTestConfig = unitTestConfig;
	}

	@Override
	public String toString() {
		return "HistoryConfig [historyConfigName=" + historyConfigName + ", projectPath=" + projectPath + ", entityPackage=" + entityPackage + ", entityName=" + entityName + ", servicePackage="
				+ servicePackage + ", serviceName=" + serviceName + ", serviceImplPackage=" + serviceImplPackage + ", serviceImplName=" + serviceImplName + ", routerPackage=" + routerPackage + ", routerName="
				+ routerName + ", sqlPackage=" + sqlPackage + ", sqlName=" + sqlName + ", sqlAssistPackage=" + sqlAssistPackage + ", abstractSqlPackage=" + abstractSqlPackage + ", sqlAndParamsPackage="
				+ sqlAndParamsPackage + ", unitTestPackage=" + unitTestPackage + ", unitTestName=" + unitTestName + ", codeFormat=" + codeFormat + ", dbConfig=" + dbConfig + ", entityConfig=" + entityConfig
				+ ", serviceConfig=" + serviceConfig + ", serviceImplConfig=" + serviceImplConfig + ", routerConfig=" + routerConfig + ", sqlConfig=" + sqlConfig + ", assistConfig=" + assistConfig
				+ ", abstractSqlConfig=" + abstractSqlConfig + ", sqlAndParamsConfig=" + sqlAndParamsConfig + ", unitTestConfig=" + unitTestConfig + ", customConfig=" + customConfig
				+ ", customPropertyConfig=" + customPropertyConfig + "]";
	}

}
