package com.szmirren.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.szmirren.Main;
import com.szmirren.common.ConfigUtil;
import com.szmirren.common.Constant;
import com.szmirren.common.LanguageKey;
import com.szmirren.common.StrUtil;
import com.szmirren.models.TableAttributeEntity;
import com.szmirren.models.TableAttributeEntityEditingCell;
import com.szmirren.options.EntityConfig;
import com.szmirren.view.AlertUtil;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * 实体类属性控制器
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class SetEntityAttributeController extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());
	/** 首页的控制器 */
	private IndexController indexController;
	/** 存储信息table里面的所有属性 */
	private ObservableList<TableAttributeEntity> tblPropertyValues;

	// =======================控件区域===========================
	@FXML
	private TableView<TableAttributeEntity> tblProperty;
	/** 是否生成 */
	@FXML
	private TableColumn<TableAttributeEntity, Boolean> tdIsCreate;
	/** 列名 */
	@FXML
	private TableColumn<TableAttributeEntity, String> tdColumn;
	/** jdbc数据类型 */
	@FXML
	private TableColumn<TableAttributeEntity, String> tdSqlType;
	/** java数据类型 */
	@FXML
	private TableColumn<TableAttributeEntity, ComboBox<String>> tdJavaType;
	/** java属性名 */
	@FXML
	private TableColumn<TableAttributeEntity, String> tdFiled;

	/** 表的别名 */
	@FXML
	private Label lblTableAlias;
	/** 主键的名称 */
	@FXML
	private Label lblPrimaryKey;
	/** 添加自定义字段 */
	@FXML
	private Label lblAddCustomProperty;
	/** 添加自定义字段类型 */
	@FXML
	private Label lblKey;
	/** 添加自定义字段名称 */
	@FXML
	private Label lblValue;
	/** 使用模板 */
	@FXML
	private Label lblTemplate;

	/** 表的别名输入框 */
	@FXML
	private TextField txtTableAlias;
	/** 主键名称输入框 */
	@FXML
	private TextField txtPrimaryKey;
	/** 自定义类型输入框 */
	@FXML
	private TextField txtKey;
	/** 自定义名称输入框 */
	@FXML
	private TextField txtValue;
	/** 添加自定义属性按钮 */
	@FXML
	private Button btnAddProperty;
	/** 保存配置文件 */
	@FXML
	private Button btnSaveConfig;
	/** 确定按钮 */
	@FXML
	private Button btnConfirm;
	/** 取消按钮 */
	@FXML
	private Button btnCancel;

	/** 如果文件存在就将其覆盖 */
	@FXML
	private CheckBox chkOverrideFile;
	/** 使用模板 */
	@FXML
	private ComboBox<String> cboTemplate;

	/** 右边的pane */
	@FXML
	private AnchorPane apRightPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblProperty.setEditable(true);
		tblProperty.setStyle("-fx-font-size:14px");
		StringProperty property = Main.LANGUAGE.get(LanguageKey.SET_TBL_TIPS);
		String title = property == null ? "可以在右边自定义添加属性..." : property.get();
		tblProperty.setPlaceholder(new Label(title));
		tblPropertyValues = FXCollections.observableArrayList();
		// 设置列的大小自适应
		tblProperty.setColumnResizePolicy(resize -> {
			double width = resize.getTable().getWidth();
			tdIsCreate.setPrefWidth(width * 10 / 1);
			tdColumn.setPrefWidth(width * 10 / 2.25);
			tdSqlType.setPrefWidth(width * 10 / 2.25);
			tdJavaType.setPrefWidth(width * 10 / 2.25);
			tdFiled.setPrefWidth(width * 10 / 2.25);
			return true;
		});
		btnConfirm.widthProperty().addListener(w -> {
			double x = btnConfirm.getLayoutX() + btnConfirm.getWidth() + 10;
			btnCancel.setLayoutX(x);
		});
		// // 设置输入包名自适应
		// lblPackageName.widthProperty().addListener(w -> {
		// double x = lblPackageName.getLayoutX() + lblPackageName.getWidth() + 25;
		// txtPackageName.setLayoutY(x);
		// txtPackageName.setPrefWidth(apRightPane.getWidth() - x);
		// });
		// apRightPane.widthProperty().addListener(w -> {
		// double x = lblPackageName.getLayoutX() + lblPackageName.getWidth() + 25;
		// txtPackageName.setLayoutY(x);
		// txtPackageName.setPrefWidth(apRightPane.getWidth() - x);
		// });
		//
		// // 设置输入类名自适应
		// lblClassName.widthProperty().addListener(w -> {
		// double x = lblClassName.getLayoutX() + lblClassName.getWidth() + 25;
		// txtClassName.setLayoutY(x);
		// txtClassName.setPrefWidth(apRightPane.getWidth() - x);
		// });
		// apRightPane.widthProperty().addListener(w -> {
		// double x = lblClassName.getLayoutX() + lblClassName.getWidth() + 25;
		// txtClassName.setLayoutY(x);
		// txtClassName.setPrefWidth(apRightPane.getWidth() - x);
		// });

	}

	/**
	 * 初始化
	 */
	public void init() {
		LOG.debug("初始化SetEntityAttribute...");
		LOG.debug("初始化SetEntityAttribute->初始化属性...");
		// 添加右键删除属性
		StringProperty property = Main.LANGUAGE.get(LanguageKey.SET_TBL_MENU_ITEM_DELETE);
		String delMenu = property.get() == null ? "删除该属性" : property.get();
		MenuItem item = new MenuItem(delMenu);
		item.setOnAction(event -> {
			TableViewSelectionModel<TableAttributeEntity> model = tblProperty.selectionModelProperty().get();
			StringProperty delConfirmP = Main.LANGUAGE.get(LanguageKey.SET_TBL_MENU_ITEM_DELETE_CONFIRM);
			String delConfirm = delConfirmP.get() == null ? "确定删除该属性吗" : delConfirmP.get();
			boolean isDel = AlertUtil.showConfirmAlert(delConfirm);
			if (isDel) {
				tblPropertyValues.remove(model.getSelectedItem());
			}
		});
		ContextMenu menu = new ContextMenu(item);
		Property<ContextMenu> tblCM = new SimpleObjectProperty<ContextMenu>(menu);
		tblProperty.contextMenuProperty().bind(tblCM);
		// 添加列
		Callback<TableColumn<TableAttributeEntity, String>, TableCell<TableAttributeEntity, String>> cellFactory = (TableColumn<TableAttributeEntity, String> p) -> new TableAttributeEntityEditingCell();
		tdIsCreate.setCellFactory(CheckBoxTableCell.forTableColumn(tdIsCreate));
		tdIsCreate.setCellValueFactory(new PropertyValueFactory<>("create"));

		tdColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
		tdColumn.setCellFactory(cellFactory);
		tdColumn.setOnEditCommit((CellEditEvent<TableAttributeEntity, String> t) -> {
			((TableAttributeEntity) t.getTableView().getItems().get(t.getTablePosition().getRow())).setColumnName(t.getNewValue());
		});

		tdSqlType.setCellValueFactory(new PropertyValueFactory<>("jdbcType"));
		tdSqlType.setCellFactory(cellFactory);
		tdSqlType.setOnEditCommit((CellEditEvent<TableAttributeEntity, String> t) -> {
			System.out.println(t.getNewValue());
			((TableAttributeEntity) t.getTableView().getItems().get(t.getTablePosition().getRow())).setJdbcType(t.getNewValue());
		});
		tdJavaType.setCellValueFactory(new PropertyValueFactory<>("javaType"));
		tdFiled.setCellValueFactory(new PropertyValueFactory<>("field"));
		tdFiled.setCellFactory(cellFactory);
		tdFiled.setOnEditCommit((CellEditEvent<TableAttributeEntity, String> t) -> {
			System.out.println(t.getNewValue());
			((TableAttributeEntity) t.getTableView().getItems().get(t.getTablePosition().getRow())).setField(t.getNewValue());
		});
		tblProperty.setItems(tblPropertyValues);
		LOG.debug("初始化SetEntityAttribute->初始化模板文件名选择...");
		cboTemplate.getItems().addAll(indexController.getTemplateNameItems());
		if (indexController.getTemplateNameItems().contains(Constant.TEMPLATE_NAME_ENTITY)) {
			cboTemplate.setValue(Constant.TEMPLATE_NAME_ENTITY);
		}
		LOG.debug("初始化SetEntityAttribute->初始化配置信息...");
		if (indexController.getHistoryConfig() != null) {
			if (indexController.getHistoryConfig().getEntityConfig() == null) {
				loadConfig(getConfig());
			} else {
				loadConfig(indexController.getHistoryConfig().getEntityConfig());
			}
		} else {
			String configName = indexController.getHistoryConfigName();
			if (StrUtil.isNullOrEmpty(configName)) {
				configName = Constant.DEFAULT;
			}
			loadConfig(getConfig(configName));
		}
		initLanguage();
		LOG.debug("初始化SetEntityAttribute-->成功!");
	}

	/**
	 * 初始化语言
	 */
	private void initLanguage() {
		// lblTips.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_LBL_TIPS));
		// tdClassName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_COMMON_CLASS_NAME));
		// tdPackageName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_COMMON_PACKAGE_NAME));
		// tdTemplate.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_COMMON_TEMPLATE_NAME));
		// lblAddCustomProperty.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_LBL_ADD_CUSTOM_PROPERTY));
		// lblPackageName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_LBL_PACKAGE_NAME));
		// lblClassName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_LBL_CLASS_NAME));
		// btnSaveConfig.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_BTN_SAVE_CONFIG));
		// btnAddProperty.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_BTN_ADD_PROPERTY));
		// btnConfirm.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_BTN_CONFIRM));
		// btnCancel.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_BTN_CANCEL));
		// txtKey.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_TXT_KEY));
		// txtPackageName.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_TXT_PACKAGE_NAME));
		// txtClassName.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_TXT_CLASS_NAME));
		// chkOverrideFile.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_CHK_OVERRIDE_FILE));
	}

	/**
	 * 从数据库中获取配置文件,使用默认值获取
	 * 
	 * @return
	 */
	public EntityConfig getConfig() {
		return getConfig(Constant.DEFAULT);
	}

	/**
	 * 从数据库中获取配置文件
	 * 
	 * @param name
	 * @return
	 */
	public EntityConfig getConfig(String name) {
		LOG.debug("执行从数据库中获取配置文件...");
		try {
			EntityConfig config = ConfigUtil.getEntityConfig(name);
			LOG.debug("执行获取配置文件-->成功!");
			if (config != null) {
				return config;
			}
		} catch (Exception e) {
			LOG.error("执行从数据库中获取配置文件-->失败:", e);
			AlertUtil.showErrorAlert("执行获得配置文件-->失败:" + e);
		}
		return new EntityConfig();
	}

	/**
	 * 获取当前控制器配置文件
	 * 
	 * @param name
	 * @return
	 */
	public EntityConfig getThisConfig() {
		LOG.debug("执行获取当前页面配置文件...");
		// TODO
		EntityConfig config = new EntityConfig();
		LOG.debug("执行获取当前页面配置文件-->成功!");
		return config;
	}

	/**
	 * 加载配置文件到当前页面
	 * 
	 * @param config
	 */
	public void loadConfig(EntityConfig config) {
		LOG.debug("执行加载配置文件到当前页面...");
		tblPropertyValues.clear();
		if (config != null && config.getTableItem() != null) {
			config.getTableItem().forEach(v -> {
				// TableAttributeEntity attribute = new
				// TableAttributeEntity(v.getKey(), v.getPackageName(),
				// v.getClassName(), v.getTemplateValue());
				// attribute.getTemplate().promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_CBO_TEMPLATE));
				// attribute.getTemplate().prefWidthProperty().bind(tdTemplate.widthProperty());
				// attribute.getTemplate().setEditable(true);
				// attribute.getTemplate().getItems().addAll(indexController.getTemplateNameItems());
				// attribute.getTemplate().setValue(v.getTemplateValue());
				// tblPropertyValues.add(attribute);
			});
		}
		chkOverrideFile.setSelected(config.isOverrideFile());
		LOG.debug("执行加载配置文件到当前页面->成功!");
	}

	// =======================事件=================================
	/**
	 * 保存配置
	 * 
	 * @param event
	 */
	public void onSaveConfig(ActionEvent event) {
		LOG.debug("执行将配置文件保存到数据库...");
		try {
			String configName = indexController.getHistoryConfigName();
			if (StrUtil.isNullOrEmpty(configName)) {
				configName = Constant.DEFAULT;
			}
			ConfigUtil.saveEntityConfig(getThisConfig(), configName);
			LOG.debug("执行将配置文件保存到数据库-->成功!");
			AlertUtil.showInfoAlert("保存配置信息成功!");
		} catch (Exception e) {
			LOG.error("执行将配置文件保存到数据库-->失败:", e);
			AlertUtil.showErrorAlert("执行获得配置文件-->失败:" + e);
		}
	}

	/**
	 * 添加自定义属性
	 * 
	 * @param event
	 */
	public void onAddPropertyToTable(ActionEvent event) {
		LOG.debug("执行添加自定义属性...");
		// ComboBox<String> comboBox = new ComboBox<>();
		// comboBox.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.SET_CBO_TEMPLATE));
		// comboBox.prefWidthProperty().bind(tdTemplate.widthProperty());
		// comboBox.setEditable(true);
		// comboBox.getItems().addAll(indexController.getTemplateNameItems());
		// TableAttributeEntity attribute = new
		// TableAttributeEntity(txtKey.getText(),
		// txtPackageName.getText(), txtClassName.getText(), comboBox);
		// tblPropertyValues.add(attribute);
		LOG.debug("添加自定义属性-->成功!");
	}

	/**
	 * 取消关闭该窗口
	 * 
	 * @param event
	 */
	public void onCancel(ActionEvent event) {
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
	public void onConfirm(ActionEvent event) {
		indexController.getHistoryConfig().setEntityConfig(getThisConfig());
		getDialogStage().close();
	}

	// ==================get/set=======================
	/**
	 * 获得首页控制器
	 * 
	 * @return
	 */
	public IndexController getIndexController() {
		return indexController;
	}

	/**
	 * 设置首页控制器
	 * 
	 * @param indexController
	 */
	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

}
