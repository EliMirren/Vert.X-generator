package com.szmirren.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.szmirren.common.ConfigUtil;
import com.szmirren.models.SQLConfig;
import com.szmirren.view.AlertUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SetSQLAttributeController extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());

	private IndexController indexController;// 首页控制器

	@FXML
	private TextField txtInsertBatch;

	@FXML
	private CheckBox chkInsertBatch;
	@FXML
	private CheckBox chkDelOldFile;// 如果存在源文件则删除旧的文件

	@FXML
	private Button btnSuccess;// 确定
	@FXML
	private Button btnSaveConfig;// 保持配置

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	/**
	 * 初始化
	 */
	public void init() {
		SQLConfig config;
		if (indexController.getSqlConfig() != null) {
			config = indexController.getSqlConfig();
		} else {
			config = getConfig("default");
		}
		loadConfig(config);
	}

	/**
	 * 是否插入全部点击事件
	 * 
	 * @param event
	 */
	public void onInsertBatch(ActionEvent event) {
		insertBatchEvent(chkInsertBatch.isSelected());

	}

	public void insertBatchEvent(boolean mode) {
		txtInsertBatch.setDisable(!mode);
	}

	/**
	 * 加载设置信息
	 */
	private void loadConfig(SQLConfig config) {
		if (config == null) {
			return;
		}
		insertBatchEvent(config.isInsertBatch());
		chkDelOldFile.setSelected(config.isDelOldFile());
		chkInsertBatch.setSelected(config.isInsertBatch());
		txtInsertBatch.setText(config.getFunInsertBatch());
	}

	/**
	 * 获得当前页面的设置
	 * 
	 * @return
	 */
	private SQLConfig getThisConfig() {
		boolean isInsertBatch = chkInsertBatch.isSelected();
		String funInsertBatch = txtInsertBatch.getText();
		boolean isDelOldFile = chkDelOldFile.isSelected();
		SQLConfig result = new SQLConfig(isInsertBatch, isDelOldFile, funInsertBatch);
		return result;
	}

	/**
	 * 从数据库中获取配置文件,如果数据库中不存在则创建一个新的对象
	 * 
	 * @return
	 */
	private SQLConfig getConfig(String configName) {
		SQLConfig result;
		try {
			LOG.debug("执行获取配置文件...");
			result = Optional.ofNullable(ConfigUtil.getSQLConfig(configName)).map(conf -> conf).orElse(new SQLConfig());
			LOG.debug("执行获取配置文件-->成功!");
			return result;
		} catch (Exception e) {
			LOG.error("获取配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获取配置文件-->失败:" + e);
		}
		return new SQLConfig();
	}

	/**
	 * 保持配置文件
	 * 
	 * @param event
	 */
	public void onSaveConfig(ActionEvent event) {
		SQLConfig config = getThisConfig();
		try {
			LOG.debug("执行保存配置文件...");
			ConfigUtil.saveSQLConfig(config, "default");
			LOG.debug("保存配置文件-->成功!");
			AlertUtil.showInfoAlert("保存成功!");
		} catch (Exception e) {
			LOG.error("保存配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("保存配置文件-->失败:" + e);
		}
	}

	/**
	 * 确定事件
	 * 
	 * @param event
	 */
	public void onSuccess(ActionEvent event) {
		indexController.setSqlConfig(getThisConfig());
		getDialogStage().close();
	}

	// -----------get/set---------------
	public IndexController getIndexController() {
		return indexController;
	}

	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

}
