package com.szmirren.entity;

/**
 * 生成文件的上下文
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class GeneratorContent {

	/** 数据库配置文件 */
	private DatabaseContent database;
	/** 实体类配置信息 */
	private EntityContent entity;
	/** 实体类配置信息 */
	private ServiceContent service;
	/** 实体类配置信息 */
	private ServiceImplContent serviceImpl;
	/** 实体类配置信息 */
	private SQLContent sql;
	/** 实体类配置信息 */
	private RouterContent router;
	/** 实体类配置信息 */
	private UnitTestContent unitTest;
	/** 实体类配置信息 */
	private SqlAssistContent sqlAssist;
	/** 实体类配置信息 */
	private AbstractSqlContent abstractSql;
	/** 实体类配置信息 */
	private SqlAndParamsContent sqlAndParams;
	/** 实体类配置信息 */
	private CustomContent custom;
	/** 实体类配置信息 */
	private CustomPropertyContent customProperty;

	public EntityContent getEntity() {
		return entity;
	}

	public DatabaseContent getDatabase() {
		return database;
	}

	public void setDatabase(DatabaseContent database) {
		this.database = database;
	}

	public void setEntity(EntityContent entity) {
		this.entity = entity;
	}
	public ServiceContent getService() {
		return service;
	}
	public void setService(ServiceContent service) {
		this.service = service;
	}
	public ServiceImplContent getServiceImpl() {
		return serviceImpl;
	}
	public void setServiceImpl(ServiceImplContent serviceImpl) {
		this.serviceImpl = serviceImpl;
	}
	public SQLContent getSql() {
		return sql;
	}
	public void setSql(SQLContent sql) {
		this.sql = sql;
	}
	public RouterContent getRouter() {
		return router;
	}
	public void setRouter(RouterContent router) {
		this.router = router;
	}
	public UnitTestContent getUnitTest() {
		return unitTest;
	}
	public void setUnitTest(UnitTestContent unitTest) {
		this.unitTest = unitTest;
	}
	public SqlAssistContent getSqlAssist() {
		return sqlAssist;
	}
	public void setSqlAssist(SqlAssistContent sqlAssist) {
		this.sqlAssist = sqlAssist;
	}
	public AbstractSqlContent getAbstractSql() {
		return abstractSql;
	}
	public void setAbstractSql(AbstractSqlContent abstractSql) {
		this.abstractSql = abstractSql;
	}
	public SqlAndParamsContent getSqlAndParams() {
		return sqlAndParams;
	}
	public void setSqlAndParams(SqlAndParamsContent sqlAndParams) {
		this.sqlAndParams = sqlAndParams;
	}
	public CustomContent getCustom() {
		return custom;
	}
	public void setCustom(CustomContent custom) {
		this.custom = custom;
	}
	public CustomPropertyContent getCustomProperty() {
		return customProperty;
	}
	public void setCustomProperty(CustomPropertyContent customProperty) {
		this.customProperty = customProperty;
	}
	@Override
	public String toString() {
		return "GeneratorContent [entity=" + entity + ", service=" + service + ", serviceImpl=" + serviceImpl + ", sql=" + sql + ", router="
				+ router + ", unitTest=" + unitTest + ", sqlAssist=" + sqlAssist + ", abstractSql=" + abstractSql + ", sqlAndParams=" + sqlAndParams
				+ ", custom=" + custom + ", customProperty=" + customProperty + "]";
	}

}
