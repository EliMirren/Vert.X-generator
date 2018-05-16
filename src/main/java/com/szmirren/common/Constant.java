package com.szmirren.common;

import com.szmirren.Main;

/**
 * 工具需要用到的常量词
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public interface Constant {
	/** default */
	static final String DEFAULT = "default";
	/** language */
	static final String LANGUAGE = "language";
	/** 模板的文件夹名称 */
	static final String TEMPLATE_DIR_NAME = "template";
	/** 刷新模板文件夹 */
	static final String TEMPLATE_DIR_REFRESH = "刷新模板文件夹";
	/** Service模板的默认名字 */
	static final String TEMPLATE_NAME_SERVICE = "Service.ftl";
	/** ServiceImpl模板的默认名字 */
	static final String TEMPLATE_NAME_SERVICE_IMPL = "ServiceImpl.ftl";
	/** Service模板的默认名字 */
	static final String TEMPLATE_NAME_ROUTER = "Router.ftl";
	/** ServiceImpl模板的默认名字 */
	static final String TEMPLATE_NAME_SQL = "SQL.ftl";
	/** SqlAssist模板的默认名字 */
	static final String TEMPLATE_NAME_SQL_ASSIST = "SqlAssist.ftl";
	/** Abstract模板的默认名字 */
	static final String TEMPLATE_NAME_ABSTRACT_SQL = Main.LANGUAGE.get(LanguageKey.SET_ABSTRACT_AUTOMATIC).get();
	/** SqlAndParams模板的默认名字 */
	static final String TEMPLATE_NAME_SQL_AND_PARAMS = "SqlAndParams.ftl";

}
