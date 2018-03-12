package com.szmirren.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.szmirren.common.ConfigUtil;
import com.szmirren.common.DBUtil;
import com.szmirren.common.StrUtil;
import com.szmirren.models.AttributeCVF;
import com.szmirren.models.ClassConfig;
import com.szmirren.models.DatabaseConfig;
import com.szmirren.models.EntityAttribute;
import com.szmirren.view.AlertUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class SetAttributeController extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());

	private IndexController indexController;// 首页控制器

	// 存储信息table里面的所有属性
	ObservableList<AttributeCVF> attributeCVF;// 表的属性
	@FXML
	private CheckBox chkUnlineCamel;// 去除下划线并驼峰命名
	@FXML
	private CheckBox chkSerializable;// 序列化
	@FXML
	private CheckBox chkGetAndSet;// get与set
	@FXML
	private CheckBox chkConstruct;// 无参构造方法
	@FXML
	private CheckBox chkConstructAll;// 全参构造方法
	@FXML
	private CheckBox chkConstructJson;// json构造方法
	@FXML
	private CheckBox chkToJson;// toJosn方法
	@FXML
	private CheckBox chkFromJson;// formJson方法
	@FXML
	private CheckBox chkformMultiMap;// formJson方法
	@FXML
	private CheckBox chkComment;// 属性注解
	@FXML
	private CheckBox chkEntityAdd;// 属性注解
	@FXML
	private CheckBox chkDelOldFile;// 属性注解

	@FXML
	private TextField txtPrimaryKey;// 主键
	@FXML
	private TextField txtCustomType;// 自定义类型
	@FXML
	private TextField txtCustomName;// 自定义名字
	@FXML
	private TextField txtTableAlias;// 表别名

	@FXML
	private Button btnSuccess;// 确定按钮
	@FXML
	private Button btnCancel;// 取消按钮
	@FXML
	private Button btnAddToTableView;// 添加属性到表格中按钮
	@FXML
	private Button btnSaveConfig;// 保存配置信息按钮

	@FXML
	public TableView<AttributeCVF> tblEntityProperty;

	@FXML
	private TableColumn<AttributeCVF, Boolean> tdCheck;
	@FXML
	private TableColumn<AttributeCVF, String> tdColumn;
	@FXML
	private TableColumn<AttributeCVF, String> tdJDBCType;
	@FXML
	private TableColumn<AttributeCVF, String> tdJAVAType;
	@FXML
	private TableColumn<AttributeCVF, String> tdPropertyName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblEntityProperty.setEditable(true);
		tblEntityProperty.setStyle("-fx-font-size:14px");
		tblEntityProperty.setPlaceholder(new Label("正在加载属性..."));

	}

	/**
	 * 初始化
	 */
	public void init() {
		LOG.debug("初始化修改属性页...");
		// 存储数据库指定数据库,修改属性时用
		DatabaseConfig selectedDatabaseConfig = indexController.getSelectedDatabaseConfig();
		// 记录存储的表名,修改属性时用
		String selectedTableName = indexController.getTableName();
		try {
			LOG.debug("获得表的所有列并初始化成类的属性...");
			if (indexController.getCtEntityAttribute() != null) {
				EntityAttribute entityAttribute = indexController.getCtEntityAttribute();
				attributeCVF = FXCollections.observableList(entityAttribute.getAttrs());
				txtTableAlias.setText(entityAttribute.getTableAlias());
				txtPrimaryKey.setText(entityAttribute.getPrimaryKey());
				loadClassConfig(entityAttribute.getConfig());
			} else {
				attributeCVF = getAttributeCVFs(selectedDatabaseConfig, selectedTableName);
				initTablePrimaryKey(selectedDatabaseConfig, selectedTableName);
				try {
					LOG.debug("初始化创建类配置信息...");
					ClassConfig classConfig = getClassConfig("default");
					indexController.setClassConfig(classConfig);
					loadClassConfig(classConfig);
					LOG.debug("初始化创建类配置信息-->成功!");
				} catch (Exception e) {
					LOG.error("初始化创建类配置信息-->失败:" + e);
				}
			}
			LOG.debug("获得表的所有列并初始化成类的属性成功!");
		} catch (Exception e) {
			AlertUtil.showErrorAlert("加载属性列失败!失败原因:\r\n" + e.getCause() + e.getMessage());
			LOG.error("获得表的所有列并初始化成类的属性失败!!!" + e);
		}

		tdCheck.setCellFactory(CheckBoxTableCell.forTableColumn(tdCheck));
		tdCheck.setCellValueFactory(new PropertyValueFactory<>("check"));
		tdColumn.setCellValueFactory(new PropertyValueFactory<>("conlumn"));
		tdJDBCType.setCellValueFactory(new PropertyValueFactory<>("jdbcType"));
		tdJDBCType.setCellFactory(TextFieldTableCell.forTableColumn());
		tdJDBCType.setOnEditCommit(event -> {
			if ("".equals(event.getNewValue())) {
				event.getTableView().getItems().get(event.getTablePosition().getRow()).setJdbcType(null);
			} else {
				event.getTableView().getItems().get(event.getTablePosition().getRow()).setJdbcType(event.getNewValue());
			}
		});
		tdJAVAType.setCellValueFactory(new PropertyValueFactory<>("javaType"));
		tdPropertyName.setCellValueFactory(new PropertyValueFactory<>("propertyName"));
		tdPropertyName.setCellFactory(TextFieldTableCell.forTableColumn());
		tdPropertyName.setOnEditCommit(event -> {
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setPropertyName(event.getNewValue());
		});

		// 是否将字符驼峰命名;
		if (chkUnlineCamel.isSelected()) {
			toCamel();
		} else {
			notCamel();
		}

		LOG.debug("初始化属性页成功!");
	}

	/**
	 * 获得数据库列并初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public ObservableList<AttributeCVF> getAttributeCVFs(DatabaseConfig config, String tableName) throws Exception {
		List<AttributeCVF> attributeCVFs = DBUtil.getTableColumns(config, tableName);
		return FXCollections.observableList(attributeCVFs);
	}

	/**
	 * 初始化主键
	 */
	public void initTablePrimaryKey(DatabaseConfig selectedDatabaseConfig, String selectedTableName) {
		try {
			LOG.debug("执行获取表的主键列...");
			String key = DBUtil.getTablePrimaryKey(selectedDatabaseConfig, selectedTableName);
			if (key != null) {
				txtPrimaryKey.setText(key);
				LOG.debug("获取表的主键列成功!");
			} else {
				txtPrimaryKey.setPromptText("注意:你选择的表没有主键!");
				LOG.debug("获取表的主键列失败,当前表不存在主键!");
			}
		} catch (Exception e) {
			AlertUtil.showErrorAlert("获得主键失败!失败原因:\r\n" + e.getMessage());
			LOG.error("获取表的主键列失败!!!" + e);
		}

	}

	/**
	 * 是否将java属性设置为驼峰命名
	 * 
	 * @param event
	 */
	public void unlineCamel(ActionEvent event) {
		if (chkUnlineCamel.isSelected()) {
			toCamel();
		} else {
			notCamel();
		}
	}

	/**
	 * 设置属性为帕斯卡
	 */
	public void toCamel() {
		if (attributeCVF == null) {
			return;
		}
		tblEntityProperty.getItems().clear();
		for (AttributeCVF attr : attributeCVF) {
			attr.setPropertyName(StrUtil.unlineToCamel(attr.getPropertyName()));
			tblEntityProperty.getItems().add(attr);
		}
	}

	/**
	 * 设置属性名跟列名相同
	 */
	public void notCamel() {
		if (attributeCVF == null) {
			return;
		}
		ObservableList<AttributeCVF> item = attributeCVF;
		tblEntityProperty.getItems().clear();
		for (AttributeCVF attr : item) {
			if (attr.getConlumn() == null || "".equals(attr.getConlumn())) {
				attr.setPropertyName(StrUtil.fristToLoCase(attr.getPropertyName()));
			} else {
				attr.setPropertyName(StrUtil.fristToLoCase(attr.getConlumn()));
			}
			tblEntityProperty.getItems().add(attr);
		}

	}

	/**
	 * 将属性添加到属性表
	 * 
	 * @param event
	 */
	public void addToTable(ActionEvent event) {
		LOG.debug("执行添加自定义属性...");
		AttributeCVF attribute = new AttributeCVF();
		attribute.setJavaType(txtCustomType.getText());
		attribute.setPropertyName(txtCustomName.getText());
		this.attributeCVF.add(attribute);
		tblEntityProperty.getItems().add(attribute);
		LOG.debug("添加自定义属性成功!");
	}

	/**
	 * 保存配置信息事件
	 * 
	 * @param event
	 */
	public void onSaveConfig(ActionEvent event) {
		LOG.debug("执行保存配置信息...");
		ClassConfig config = getThisClassConfig();
		try {
			ConfigUtil.saveClassConfig(config, "default");
			indexController.setClassConfig(config);
			AlertUtil.showInfoAlert("保存配置信息成功!");
			LOG.debug("保存配置信息-->成功!");
		} catch (Exception e) {
			AlertUtil.showErrorAlert("保存配置信息-->失败:" + e);
			LOG.error("保存配置信息-->失败:" + e);
		}
	}

	/**
	 * 获得实体类配置文件
	 * 
	 * @param configName
	 * @return
	 */
	private ClassConfig getClassConfig(String configName) {
		try {
			LOG.debug("执行获得配置文件...");
			ClassConfig result = Optional.ofNullable(ConfigUtil.getClassConfig(configName)).map(conf -> conf).orElse(new ClassConfig());
			LOG.debug("执行获得配置文件-->成功!");
			return result;
		} catch (Exception e) {
			LOG.error("执行获得配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("执行获得配置文件-->失败:" + e);
		}
		return new ClassConfig();
	};

	/**
	 * 获得实体类配置信息
	 * 
	 * @return
	 */
	private ClassConfig getThisClassConfig() {
		boolean seriz = chkSerializable.isSelected();
		boolean unlineCamel = chkUnlineCamel.isSelected();
		boolean getAndSet = chkGetAndSet.isSelected();
		boolean construct = chkConstruct.isSelected();
		boolean constructAll = chkConstructAll.isSelected();
		boolean constructJson = chkConstructJson.isSelected();
		boolean tojson = chkToJson.isSelected();
		boolean formJson = chkFromJson.isSelected();
		boolean formMultimap = chkformMultiMap.isSelected();
		boolean comment = chkComment.isSelected();
		boolean entityAdd = chkEntityAdd.isSelected();
		boolean delOldFile = chkDelOldFile.isSelected();
		ClassConfig config = new ClassConfig(seriz, unlineCamel, getAndSet, construct, constructAll, constructJson, tojson, formJson,
				formMultimap, comment, entityAdd, delOldFile);
		return config;
	}

	/**
	 * 加载实体类配置信息
	 * 
	 * @param classConfig
	 */
	private void loadClassConfig(ClassConfig classConfig) {
		chkSerializable.setSelected(classConfig.isSeriz());
		chkUnlineCamel.setSelected(classConfig.isUnlineCamel());
		chkGetAndSet.setSelected(classConfig.isGetAndSet());
		chkConstruct.setSelected(classConfig.isConstruct());
		chkConstructAll.setSelected(classConfig.isConstructAll());
		chkConstructJson.setSelected(classConfig.isConstructJson());
		chkToJson.setSelected(classConfig.isTojson());
		chkFromJson.setSelected(classConfig.isFormJson());
		chkformMultiMap.setSelected(classConfig.isFormMultiMap());
		chkComment.setSelected(classConfig.isComment());
		chkEntityAdd.setSelected(classConfig.isEntityAdd());
		chkDelOldFile.setSelected(classConfig.isDelOldFile());
	}

	/**
	 * 取消关闭该窗口
	 * 
	 * @param event
	 */
	public void cancel(ActionEvent event) {
		boolean result = AlertUtil.showConfirmAlert("如果取消全部的设置都将回复到默认值,确定取消吗?");
		if (result) {
			getDialogStage().close();
		}
	}

	/**
	 * 确定事件
	 * 
	 * @param event
	 */
	public void success(ActionEvent event) {
		EntityAttribute attr = new EntityAttribute();
		attr.setTableAlias(txtTableAlias.getText());
		attr.setPrimaryKey(txtPrimaryKey.getText());
		attr.setAttrs(attributeCVF);
		attr.setConfig(getThisClassConfig());
		indexController.setCtEntityAttribute(attr);
		getDialogStage().close();
	}

	// -----------------------get/set--------------------------------
	public String getPrimaryKey() {
		return txtPrimaryKey.getText();
	}

	public IndexController getIndexController() {
		return indexController;
	}

	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

}
