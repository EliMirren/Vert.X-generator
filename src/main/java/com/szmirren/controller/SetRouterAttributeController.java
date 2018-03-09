package com.szmirren.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.szmirren.common.ConfigUtil;
import com.szmirren.models.RouterConfig;
import com.szmirren.view.AlertUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SetRouterAttributeController extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());

	private IndexController indexController;// 首页控制器

	@FXML
	private CheckBox chkGetCount;// 创建查询全部
	@FXML
	private CheckBox chkSelectAll;// 创建查询全部
	@FXML
	private CheckBox chkSelectByPage;// 创建查询全部
	@FXML
	private CheckBox chkSelectObj;// 创建查询对象
	@FXML
	private CheckBox chkSelectId;// 创建查询id
	@FXML
	private CheckBox chkInsert;// 创建插入
	@FXML
	private CheckBox chkUpdate;// 创建更新
	@FXML
	private CheckBox chkDelete;// 创建删除
	@FXML
	private CheckBox chkInsertBatch;// 创建删除
	@FXML
	private CheckBox chkDelOldFile;// 如果存在源文件则删除旧的文件

	@FXML
	private TextField txtFunGetCount;
	@FXML
	private TextField txtFunSelectAll;
	@FXML
	private TextField txtFunSelectByPage;
	@FXML
	private TextField txtFunSelectObj;
	@FXML
	private TextField txtFunSelectId;
	@FXML
	private TextField txtFunInsert;
	@FXML
	private TextField txtFunUpdate;
	@FXML
	private TextField txtFunDelete;
	@FXML
	private TextField txtFunInsertBatch;

	@FXML
	private TextField txtBsGetCount;
	@FXML
	private TextField txtBsSelectAll;
	@FXML
	private TextField txtBsSelectByPage;
	@FXML
	private TextField txtBsSelectObj;
	@FXML
	private TextField txtBsSelectId;
	@FXML
	private TextField txtBsInsert;
	@FXML
	private TextField txtBsUpdate;
	@FXML
	private TextField txtBsDelete;
	@FXML
	private TextField txtBsInsertBatch;

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
		RouterConfig config;
		if (indexController.getRouterConfig() != null) {
			config = indexController.getRouterConfig();
		} else {
			config = getConfig("default");
		}
		loadConfig(config);
	}

	/**
	 * 创建数据总行数的的点击事件
	 * 
	 * @param event
	 */
	public void onGetCount(ActionEvent event) {
		getCountEvent(chkGetCount.isSelected());
	}

	/**
	 * 数据总行数的点击事件源
	 * 
	 * @param mode
	 */
	public void getCountEvent(boolean mode) {
		txtFunGetCount.setDisable(!mode);
		txtBsGetCount.setDisable(!mode);
	}

	/**
	 * 是否创建查询全部事件
	 * 
	 * @param event
	 */
	public void onSelectAll(ActionEvent event) {
		selectAllEvent(chkSelectAll.isSelected());
	}

	/**
	 * 是否创建查询全部事件源
	 * 
	 * @param mode
	 */
	public void selectAllEvent(boolean mode) {
		txtFunSelectAll.setDisable(!mode);
		txtBsSelectAll.setDisable(!mode);
	}

	/**
	 * 创建分页查询的点击事件
	 * 
	 * @param event
	 */
	public void onSelectByPage(ActionEvent event) {
		selectByPageEvent(chkSelectByPage.isSelected());
	}

	/**
	 * 创建分页查询的点击事件源
	 * 
	 * @param mode
	 */
	public void selectByPageEvent(boolean mode) {
		txtFunSelectByPage.setDisable(!mode);
		txtBsSelectByPage.setDisable(!mode);
	}

	/**
	 * 是否创建查询对象事件
	 * 
	 * @param event
	 */
	public void onSelectObj(ActionEvent event) {
		selectObjEvent(chkSelectObj.isSelected());
	}

	/**
	 * 是否创建查询对象事件源
	 * 
	 * @param mode
	 */
	public void selectObjEvent(boolean mode) {
		txtFunSelectObj.setDisable(!mode);
		txtBsSelectObj.setDisable(!mode);
	}

	/**
	 * 是否创建查询对象事件
	 * 
	 * @param event
	 */
	public void onSelectId(ActionEvent event) {
		selectIdEvent(chkSelectId.isSelected());
	}

	/**
	 * 是否创建查询对象事件源
	 * 
	 * @param mode
	 */
	public void selectIdEvent(boolean mode) {
		txtFunSelectId.setDisable(!mode);
		txtBsSelectId.setDisable(!mode);
	}

	/**
	 * 是否创建插入事件
	 * 
	 * @param event
	 */
	public void onInsert(ActionEvent event) {
		insertEvent(chkInsert.isSelected());
	}

	/**
	 * 是否创建插入事件源
	 * 
	 * @param mode
	 */
	public void insertEvent(boolean mode) {
		txtFunInsert.setDisable(!mode);
		txtBsInsert.setDisable(!mode);

	}

	/**
	 * 是否创建修改事件
	 * 
	 * @param event
	 */
	public void onUpdate(ActionEvent event) {
		updateEvent(chkUpdate.isSelected());
	}

	/**
	 * 是否创建修改事件源
	 * 
	 * @param mode
	 */
	public void updateEvent(boolean mode) {
		txtFunUpdate.setDisable(!mode);
		txtBsUpdate.setDisable(!mode);
	}

	/**
	 * 是否创建删除事件
	 * 
	 * @param event
	 */
	public void onDelete(ActionEvent event) {
		deleteEvent(chkDelete.isSelected());
	}

	/**
	 * 是否创建删除事件源
	 * 
	 * @param mode
	 */
	public void deleteEvent(boolean mode) {
		txtFunDelete.setDisable(!mode);
		txtBsDelete.setDisable(!mode);
	}

	/**
	 * 是否创建附加内容事件
	 * 
	 * @param event
	 */
	public void onInsertBatch(ActionEvent event) {
		insertBatchEvent(chkInsertBatch.isSelected());
	}

	/**
	 * 是否创建附加内容事件源
	 * 
	 * @param mode
	 */
	public void insertBatchEvent(boolean mode) {
		txtFunInsertBatch.setDisable(!mode);
		txtBsInsertBatch.setDisable(!mode);
	}

	/**
	 * 保持配置文件
	 * 
	 * @param event
	 */
	public void onSaveConfig(ActionEvent event) {
		RouterConfig config = getThisConfig();
		try {
			LOG.debug("执行保存配置文件...");
			ConfigUtil.saveRouterConfig(config, "default");
			LOG.debug("保存配置文件-->成功!");
			AlertUtil.showInfoAlert("保存成功!");
		} catch (Exception e) {
			LOG.error("保存配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("保存配置文件-->失败:" + e);
		}
	}

	/**
	 * 加载设置信息
	 */
	private void loadConfig(RouterConfig config) {
		if (config == null) {
			return;
		}
		chkGetCount.setSelected(config.isGetCount());
		getCountEvent(config.isGetCount());
		chkSelectAll.setSelected(config.isSelectAll());
		selectAllEvent(config.isSelectAll());
		chkSelectByPage.setSelected(config.isSelectByPage());
		selectByPageEvent(config.isSelectByPage());
		chkSelectObj.setSelected(config.isSelectObj());
		selectObjEvent(config.isSelectObj());
		chkSelectId.setSelected(config.isSelectId());
		selectIdEvent(config.isSelectId());
		chkInsert.setSelected(config.isInsert());
		insertEvent(config.isInsert());
		chkUpdate.setSelected(config.isUpdate());
		updateEvent(config.isUpdate());
		chkDelete.setSelected(config.isDelete());
		deleteEvent(config.isDelete());
		chkInsertBatch.setSelected(config.isInsertBatch());
		insertBatchEvent(config.isInsertBatch());
		chkDelOldFile.setSelected(config.isDelOldFile());

		txtFunGetCount.setText(config.getFunGetCount());
		txtFunSelectAll.setText(config.getFunSelectAll());
		txtFunSelectByPage.setText(config.getFunSelectByPage());
		txtFunSelectObj.setText(config.getFunSelectObj());
		txtFunSelectId.setText(config.getFunSelectId());
		txtFunInsert.setText(config.getFunInsert());
		txtFunUpdate.setText(config.getFunUpdate());
		txtFunDelete.setText(config.getFunDelete());
		txtFunInsertBatch.setText(config.getFunInsertBatch());

		txtBsGetCount.setText(config.getBsGetCount());
		txtBsSelectAll.setText(config.getBsSelectAll());
		txtBsSelectByPage.setText(config.getBsSelectByPage());
		txtBsSelectObj.setText(config.getBsSelectObj());
		txtBsSelectId.setText(config.getBsSelectId());
		txtBsInsert.setText(config.getBsInsert());
		txtBsUpdate.setText(config.getBsUpdate());
		txtBsDelete.setText(config.getBsDelete());
		txtBsInsertBatch.setText(config.getBsInsertBatch());
	}

	/**
	 * 获得当前页面的设置
	 * 
	 * @return
	 */
	private RouterConfig getThisConfig() {
		boolean isGetCount = chkGetCount.isSelected();
		boolean isSelectAll = chkSelectAll.isSelected();
		boolean isSelectByPage = chkSelectByPage.isSelected();
		boolean isSelectObj = chkSelectObj.isSelected();
		boolean isSelectId = chkSelectId.isSelected();
		boolean isInsert = chkInsert.isSelected();
		boolean isUpdate = chkUpdate.isSelected();
		boolean isDelete = chkDelete.isSelected();
		boolean isInsertBatch = chkInsertBatch.isSelected();
		boolean isDelOldFile = chkDelOldFile.isSelected();

		String funGetCount = txtFunGetCount.getText();
		String funSelectAll = txtFunSelectAll.getText();
		String funSelectByPage = txtFunSelectByPage.getText();
		String funSelectObj = txtFunSelectObj.getText();
		String funSelectId = txtFunSelectId.getText();
		String funInsert = txtFunInsert.getText();
		String funUpdate = txtFunUpdate.getText();
		String funDelete = txtFunDelete.getText();
		String funInsertBatch = txtFunInsertBatch.getText();

		String bsGetCount = txtBsGetCount.getText();
		String bsSelectAll = txtBsSelectAll.getText();
		String bsSelectByPage = txtBsSelectByPage.getText();
		String bsSelectObj = txtBsSelectObj.getText();
		String bsSelectId = txtBsSelectId.getText();
		String bsInsert = txtBsInsert.getText();
		String bsUpdate = txtBsUpdate.getText();
		String bsDelete = txtBsDelete.getText();
		String bsInsertBatch = txtBsInsertBatch.getText();

		RouterConfig result = new RouterConfig(isGetCount, isSelectAll, isSelectByPage, isSelectObj, isSelectId,
				isInsert, isUpdate, isDelete, isInsertBatch, isDelOldFile, funGetCount, funSelectAll, funSelectByPage,
				funSelectObj, funSelectId, funInsert, funUpdate, funDelete, funInsertBatch, bsGetCount, bsSelectAll,
				bsSelectByPage, bsSelectObj, bsSelectId, bsInsert, bsUpdate, bsDelete, bsInsertBatch);
		return result;
	}

	/**
	 * 从数据库中获取配置文件,如果数据库中不存在则创建一个新的对象
	 * 
	 * @return
	 */
	private RouterConfig getConfig(String configName) {
		RouterConfig result;
		try {
			LOG.debug("执行获取配置文件...");
			result = Optional.ofNullable(ConfigUtil.getRouterConfig(configName)).map(conf -> conf)
					.orElse(new RouterConfig());
			LOG.debug("执行获取配置文件-->成功!");
			return result;
		} catch (Exception e) {
			LOG.error("获取配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获取配置文件-->失败:" + e);
		}
		return new RouterConfig();
	}

	/**
	 * 确定事件
	 * 
	 * @param event
	 */
	public void onSuccess(ActionEvent event) {
		indexController.setRouterConfig(getThisConfig());
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
