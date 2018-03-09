package com.szmirren.controller;

public enum FXMLPage {
	CONNECTION("FXML/Connection.fxml"),
	UPDATE_CONNECTION("FXML/UpdateConnection.fxml"),
	HISTORY_CONFIG("FXML/HistoryConfig.fxml"),
	SET_ATTRIBUTE("FXML/SetAttribute.fxml"),
	SET_DAO_ATTRIBUTE("FXML/SetDaoAttribute.fxml"),
	SET_BIZ_ATTRIBUTE("FXML/SetBizAttribute.fxml"),
	SET_ROUTER_ATTRIBUTE("FXML/SetRouterAttribute.fxml"),
	SET_SQL_ATTRIBUTE("FXML/SetSQLAttribute.fxml"),
	SET_TEMPLATE("FXML/SetTemplates.fxml"),
	ABOUT("FXML/About.fxml"),
	;

	private String fxml;

	FXMLPage(String fxml) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return this.fxml;
	}

}
