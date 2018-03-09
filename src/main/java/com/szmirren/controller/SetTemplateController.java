package com.szmirren.controller;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.szmirren.common.ConfigUtil;
import com.szmirren.models.CommonName;
import com.szmirren.models.TemplateConfig;
import com.szmirren.view.AlertUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * 模板控制器
 * 
 * @author Mirren
 *
 */
public class SetTemplateController extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());
	private IndexController indexController;// 首页的控制器
	@FXML
	private ComboBox<String> cboEntityAdd;
	@FXML
	private ComboBox<String> cboDao;
	@FXML
	private ComboBox<String> cboDaoAdd;
	@FXML
	private ComboBox<String> cboBiz;
	@FXML
	private ComboBox<String> cboBizAdd;
	@FXML
	private ComboBox<String> cboWebRouter;
	@FXML
	private ComboBox<String> cboWebRouterAdd;
	@FXML
	private ComboBox<String> cboSQL;
	@FXML
	private ComboBox<String> cboSQLNonId;
	@FXML
	private ComboBox<String> cboAddSQL;
	@FXML
	private ComboBox<String> cboAssist;
	@FXML
	private ComboBox<String> cboAssistCondition;
	@FXML
	private ComboBox<String> cboAbstractSQL;
	@FXML
	private ComboBox<String> cboSqlAndParams;
	@FXML
	private ComboBox<String> cboPropertyValue;
	@FXML
	private Button btnSaveConfig;// 保存按钮
	@FXML
	private Button btnSuccess;// 确定按钮

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			LOG.debug("初始化模板页面...");
			List<String> defultItem = new ArrayList<>();
			List<String> customItem = new ArrayList<>();
			Files.list(Paths.get(CommonName.TEMPLATE_DIR_NAME.getValue())).forEach(path -> {
				String fileName = path.getFileName().toString();
				if ((fileName.contains("Temp") || fileName.contains("temp"))) {
					if ((fileName.contains("Custom") || fileName.contains("custom"))) {
						customItem.add(fileName);
					} else {
						defultItem.add(fileName);
					}
				}
			});
			customItem.addAll(defultItem);
			cboEntityAdd.getItems().addAll(customItem);
			cboDao.getItems().addAll(customItem);
			cboDaoAdd.getItems().addAll(customItem);
			cboBiz.getItems().addAll(customItem);
			cboBizAdd.getItems().addAll(customItem);
			cboWebRouter.getItems().addAll(customItem);
			cboWebRouterAdd.getItems().addAll(customItem);
			cboSQL.getItems().addAll(customItem);
			cboSQLNonId.getItems().addAll(customItem);
			cboAddSQL.getItems().addAll(customItem);
			cboAssist.getItems().addAll(customItem);
			cboAssistCondition.getItems().addAll(customItem);
			cboAbstractSQL.getItems().addAll(customItem);
			cboAbstractSQL.getItems().add("Temp-Defult-Abstract{*DBType*}.txt");
			cboSqlAndParams.getItems().addAll(customItem);
			cboPropertyValue.getItems().addAll(customItem);
			LOG.debug("初始化模板页面-->成功!");
		} catch (Exception e) {
			LOG.error("初始化模板页面-->失败:" + e);
			AlertUtil.showErrorAlert("初始化模板页面-->失败:" + e);
		}

	}

	public void init() {
		TemplateConfig templateConfig;
		if (indexController.getTemplateConfig() != null) {
			templateConfig = indexController.getTemplateConfig();
		} else {
			templateConfig = getTemplateConfig("default");
		}
		loadTemplateConfig(templateConfig);
	}

	/**
	 * 保存配置文件点击事件
	 * 
	 * @param event
	 */
	public void onSaveConfig(ActionEvent event) {
		TemplateConfig config = getThisTemplateConfig();
		try {
			LOG.debug("执行保存模板配置...");
			ConfigUtil.saveTemplateConfig(config, "default");
			AlertUtil.showInfoAlert("保存模板配置成功!");
			LOG.debug("执行保存模板配置-->成功!");
		} catch (Exception e) {
			LOG.error("执行保存模板配置-->失败:" + e);
			AlertUtil.showErrorAlert("执行保存模板配置-->失败:" + e);
		}
	}

	/**
	 * 确定按钮点击事件
	 * 
	 * @param event
	 */
	public void onSuccess(ActionEvent event) {
		indexController.setTemplateConfig(getThisTemplateConfig());
		closeDialogStage();
	}

	/**
	 * 加载配置文件
	 * 
	 * @param config
	 */
	public void loadTemplateConfig(TemplateConfig config) {
		if (config == null) {
			return;
		}
		cboEntityAdd.setValue(config.getEntityAddName());
		cboDao.setValue(config.getDaoName());
		cboDaoAdd.setValue(config.getDaoAddName());
		cboBiz.setValue(config.getBizName());
		cboBizAdd.setValue(config.getBizAddName());
		cboWebRouter.setValue(config.getWebRouterName());
		cboWebRouterAdd.setValue(config.getWebRouterAddName());
		cboSQL.setValue(config.getSqlName());
		cboSQLNonId.setValue(config.getSqlNonIdName());
		cboAddSQL.setValue(config.getAddSQLName());
		cboAssist.setValue(config.getAssistName());
		cboAssistCondition.setValue(config.getAssistConditionName());
		cboAbstractSQL.setValue(config.getAbstractSQLName());
		cboSqlAndParams.setValue(config.getSqlAndParamsName());
		cboPropertyValue.setValue(config.getSqlPropertyValueName());
	}

	/**
	 * 获得当前类配置文件
	 * 
	 * @return
	 */
	public TemplateConfig getThisTemplateConfig() {
		String entityAddName = cboEntityAdd.getValue();
		String daoName = cboDao.getValue();
		String daoAddName = cboDaoAdd.getValue();
		String bizName = cboBiz.getValue();
		String bizAddName = cboBizAdd.getValue();
		String webRouterName = cboWebRouter.getValue();
		String webRouterAddName = cboWebRouterAdd.getValue();
		String sqlName = cboSQL.getValue();
		String sqlNonIdName = cboSQLNonId.getValue();
		String addSQLName = cboAddSQL.getValue();
		String assistName = cboAssist.getValue();
		String assistConditionName = cboAssistCondition.getValue();
		String abstractSQLName = cboAbstractSQL.getValue();
		String sqlAndParamsName = cboSqlAndParams.getValue();
		String sqlPropertyValueName = cboPropertyValue.getValue();
		return new TemplateConfig(entityAddName, daoName, daoAddName, bizName, bizAddName, webRouterName,
				webRouterAddName, sqlName, sqlNonIdName, addSQLName, assistName, assistConditionName, abstractSQLName,
				sqlAndParamsName, sqlPropertyValueName);
	}

	/**
	 * 获得配置文件
	 * 
	 * @param configName
	 * @return
	 */
	public TemplateConfig getTemplateConfig(String configName) {
		try {
			LOG.debug("获得模板配置文件...");
			TemplateConfig result = ConfigUtil.getTemplateConfig(configName);
			LOG.debug("获得模板配置文件-->成功");
			return Optional.ofNullable(result).map(conf -> conf).orElse(new TemplateConfig());
		} catch (Exception e) {
			LOG.error("获得模板配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获得模板配置文件-->失败:" + e);
		}
		return new TemplateConfig();
	}

	public IndexController getIndexController() {
		return indexController;
	}

	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

}
