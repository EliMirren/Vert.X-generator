package com.szmirren.controller;

/**
 * 页面枚举类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public enum FXMLPage {
	/** 数据库连接页面 */
	CONNECTION("FXML/Connection.fxml"),
	/** 数据库修改页面 */
	UPDATE_CONNECTION("FXML/UpdateConnection.fxml"),
	/** 配置信息页面 */
	HISTORY_CONFIG("FXML/HistoryConfig.fxml"),
	/** 实体类属性设置页面 */
	SET_ATTRIBUTE("FXML/SetAttribute.fxml"),
	/** Router设置页面 */
	SET_ROUTER_ATTRIBUTE("FXML/SetRouterAttribute.fxml"),
	/** SQL设置页面 */
	SET_SQL_ATTRIBUTE("FXML/SetSQLAttribute.fxml"),
	/** SQLAssist设置页面 */
	SET_ASSIST_ATTRIBUTE("FXML/SetAssist.fxml"),
	/** 使用说明页面 */
	ABOUT("FXML/About.fxml"),
	/** 设置页面 */
	SETTING("FXML/Setting.fxml");

	private String fxml;

	FXMLPage(String fxml) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return this.fxml;
	}

}
