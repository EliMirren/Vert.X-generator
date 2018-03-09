package com.szmirren.controller;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.szmirren.common.ConfigUtil;
import com.szmirren.common.CreateFileUtil;
import com.szmirren.common.DBUtil;
import com.szmirren.common.StrUtil;
import com.szmirren.models.AttributeCVF;
import com.szmirren.models.BizConfig;
import com.szmirren.models.ClassConfig;
import com.szmirren.models.CommonName;
import com.szmirren.models.DaoConfig;
import com.szmirren.models.DatabaseConfig;
import com.szmirren.models.EntityAttribute;
import com.szmirren.models.HistoryConfig;
import com.szmirren.models.RouterConfig;
import com.szmirren.models.SQLConfig;
import com.szmirren.models.TemplateConfig;
import com.szmirren.view.AlertUtil;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

public class IndexController extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());
	// 存储数据库指定数据库,修改属性时用
	private DatabaseConfig selectedDatabaseConfig;
	private DatabaseConfig updateOfDatabaseConfig;

	// 表与实体类的属性,表名-包名-类名在执行是时添加到本属性
	private EntityAttribute ctEntityAttribute;

	// 记录存储的表名,修改属性时用
	private String selectedTableName;
	// 标记属性是否修改false为没有修改
	private boolean changeInfo = false;
	// 模板的配置
	private TemplateConfig templateConfig;
	// 类的配置文件;
	private ClassConfig classConfig;
	// dao的配置文件
	private DaoConfig daoConfig;
	// biz的配置文件
	private BizConfig bizConfig;
	// router的配置文件
	private RouterConfig routerConfig;
	// sql的配置文件
	private SQLConfig sqlConfig;

	private String entityNamePlace;// 实体类默认的占位符
	private String daoNamePlace;// dao默认占位符
	private String bizNamePlace;// biz默认占位符
	private String routerNamePlace;// router默认占位符
	private String sqlNamePlace;// sql默认占位符

	// -----------------事件-------------------------

	@FXML
	private Label lblConnection;// 数据库连接
	@FXML
	private Label lblConfig;// 配置信息
	@FXML
	private Label lblSetTemplate;// 模板选择
	@FXML
	private Label lblInstructions;// 使用说明
	@FXML
	private Label lblAbout;// 关于

	@FXML
	private TreeView<String> tvDataBase;// 数据树列表
	@FXML
	private TextField txtProjectPath;// 存放目录

	@FXML
	private TextField txtTableName;// 数据库表名
	@FXML
	private TextField txtEntityPackage;// 实体类包名
	@FXML
	private TextField txtDaoPackage;// dao包名
	@FXML
	private TextField txtBizPackage;// biz包名
	@FXML
	private TextField txtRouterPackage;// router包名
	@FXML
	private TextField txtSqlPackage;// SQL包名
	@FXML
	private TextField txtAssistPackage;// Assist包名
	@FXML
	private TextField txtAbstractSqlPackage;// AbstractSql包名
	@FXML
	private TextField txtSqlParamsPackage;// SqlParams包名

	@FXML
	private TextField txtEntityName;// 实体类类名
	@FXML
	private TextField txtDaoName;// dao类名
	@FXML
	private TextField txtBizName;// biz类名
	@FXML
	private TextField txtRouterName;// router类名
	@FXML
	private TextField txtSqlName;// SQL类名
	@FXML
	private TextField txtAssistName;// Assist类名
	@FXML
	private TextField txtAbstractSqlName;// AbstractSql类名
	@FXML
	private TextField txtSqlParamsName;// SqlParams类名

	@FXML
	private Button btnSelectFile;// 选择根目录按钮
	@FXML
	private Button btnRunCreate;// 执行创建
	@FXML
	private Button btnSaveConfig;// 保存配置文件
	@FXML
	private Button btnSetEntity;// 实体类配置按钮;
	@FXML
	private Button btnSetDao;// 到设置按钮;
	@FXML
	private Button btnSetBiz;// biz设置按钮;
	@FXML
	private Button btnSetRouter;// router设置按钮;
	@FXML
	private Button btnSetSql;// SQL设置按钮;

	@FXML
	private CheckBox chkCreateEntity;// 是否创建实体类
	@FXML
	private CheckBox chkCreateDao;// 是否创建Dao
	@FXML
	private CheckBox chkCreateBiz;// 是否创建service
	@FXML
	private CheckBox chkCreateRouter;// 是否创建路由
	@FXML
	private CheckBox chkJsonKeyIsCamel;// 是否将表中列名带下划线的改为驼峰命名

	@FXML
	private ComboBox<String> cboCodeFormat;// 字符编码格式

	@FXML
	private ProgressBar probCreateAll;// 生成进度条

	@FXML
	private Label lblRunCreateAll;// 提示文字进度条

	private String runCreateText = "正在生成 {t} ...";// 提示文字的默认文字

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOG.debug("初始化首页...");

		// 初始化图标连接与配置信息
		ImageView lblConnImage = new ImageView("image/computer.png");
		lblConnImage.setFitHeight(40);
		lblConnImage.setFitWidth(40);
		lblConnection.setGraphic(lblConnImage);
		lblConnection.setOnMouseClicked(event -> {
			ConnectionController controller = (ConnectionController) loadFXMLPage("新建数据库连接", FXMLPage.CONNECTION, false);
			controller.setIndexController(this);
			controller.showDialogStage();

		});
		ImageView lblConfImage = new ImageView("image/config.png");
		lblConfImage.setFitHeight(40);
		lblConfImage.setFitWidth(40);
		lblConfig.setGraphic(lblConfImage);
		lblConfig.setOnMouseClicked(enent -> {
			HistoryConfigController controller = (HistoryConfigController) loadFXMLPage("配置信息管理", FXMLPage.HISTORY_CONFIG, false);
			controller.setIndexController(this);
			controller.showDialogStage();
		});

		ImageView lblSetTempImage = new ImageView("image/template-set.png");
		lblSetTempImage.setFitHeight(40);
		lblSetTempImage.setFitWidth(40);
		lblSetTemplate.setGraphic(lblSetTempImage);
		lblSetTemplate.setOnMouseClicked(enent -> {
			SetTemplateController controller = (SetTemplateController) loadFXMLPage("模板管理", FXMLPage.SET_TEMPLATE, false);
			controller.setIndexController(this);
			controller.showDialogStage();
			controller.init();
		});

		ImageView lblInstructionsImage = new ImageView("image/instructions.png");
		lblInstructionsImage.setFitHeight(40);
		lblInstructionsImage.setFitWidth(40);
		lblInstructions.setGraphic(lblInstructionsImage);
		lblInstructions.setOnMouseClicked(enent -> {
			AboutController controller = (AboutController) loadFXMLPage("使用说明", FXMLPage.ABOUT, false);
			controller.showDialogStage();
		});
		cboCodeFormat.setEditable(true);
		cboCodeFormat.getItems().addAll("UTF-8", "GBK", "UTF-16", "UTF-32", "GB2312", "GB18030", "ISO-8859-1");
		cboCodeFormat.setValue("UTF-8");
		LOG.debug("初始化首页成功!");
		LOG.debug("加载左侧数据库树与事件....");
		// 加载右边数据库树与事件
		tvDataBase.setShowRoot(false);
		tvDataBase.setRoot(new TreeItem<>());
		Callback<TreeView<String>, TreeCell<String>> defaultCellFactory = TextFieldTreeCell.forTreeView();
		tvDataBase.setCellFactory((TreeView<String> tv) -> {
			TreeCell<String> cell = defaultCellFactory.call(tv);
			cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				int level = tvDataBase.getTreeItemLevel(cell.getTreeItem());
				TreeCell<String> treeCell = (TreeCell<String>) event.getSource();
				TreeItem<String> treeItem = treeCell.getTreeItem();
				if (level == 1) {
					final ContextMenu contextMenu = new ContextMenu();
					MenuItem item0 = new MenuItem("打开连接");
					item0.setOnAction(event1 -> {
						LOG.debug("执行打开数据库连接....");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							List<String> tables = DBUtil.getTableNames(selectedConfig);
							if (tables != null && tables.size() > 0) {
								ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
								children.clear();
								for (String tableName : tables) {
									TreeItem<String> newTreeItem = new TreeItem<>();
									ImageView imageView = new ImageView("image/table.png");
									imageView.setFitHeight(16);
									imageView.setFitWidth(16);
									newTreeItem.setGraphic(imageView);
									newTreeItem.setValue(tableName);
									children.add(newTreeItem);
								}
							}
						} catch (Exception e) {
							AlertUtil.showErrorAlert(e.getMessage());
							LOG.error("打开连接失败!!!" + e);
						}
					});
					MenuItem item1 = new MenuItem("关闭连接");
					item1.setOnAction(event1 -> {
						treeItem.getChildren().clear();
					});
					MenuItem item3 = new MenuItem("修改连接");
					item3.setOnAction(event1 -> {
						updateOfDatabaseConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						if (updateOfDatabaseConfig != null) {
							LOG.debug("打开修改数据库连接窗口...");
							UpdateConnection controller = (UpdateConnection) loadFXMLPage("修改数据库连接", FXMLPage.UPDATE_CONNECTION, false);
							controller.setIndexController(this);
							controller.init();
							controller.showDialogStage();

						}
					});
					MenuItem item2 = new MenuItem("删除连接");
					item2.setOnAction(event1 -> {
						if (!AlertUtil.showConfirmAlert("确定删除该连接吗")) {
							return;
						}
						LOG.debug("执行删除数据库链接...");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							ConfigUtil.deleteDatabaseConfig(selectedConfig.getConnName());
							this.loadTVDataBase();
						} catch (Exception e) {
							AlertUtil.showErrorAlert("删除数据库连接失败: " + e.getMessage());
							LOG.error("删除数据库连接失败!!!" + e);
						}
					});

					MenuItem itemCreateAll = new MenuItem("全库生成");
					itemCreateAll.setOnAction(event1 -> {
						if (StrUtil.isNullOrEmpty(txtProjectPath.getText())) {
							AlertUtil.showWarnAlert("生成的路径不能为空");
							return;
						}
						if (!AlertUtil.showConfirmAlert("确定当前数据库里面所有的表都生成吗?")) {
							return;
						}
						LOG.debug("执行全库生成...");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						createAllTable(selectedConfig);
					});
					contextMenu.getItems().addAll(itemCreateAll, item0, item1, item3, item2);
					cell.setContextMenu(contextMenu);
				}
				// 加载所有表
				if (event.getClickCount() == 2) {
					if (treeItem == null) {
						return;
					}
					treeItem.setExpanded(true);
					if (level == 1) {
						LOG.debug("加载所有表....");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							List<String> tables = DBUtil.getTableNames(selectedConfig);
							if (tables != null && tables.size() > 0) {
								ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
								children.clear();
								// 获得树节点
								for (String tableName : tables) {
									TreeItem<String> newTreeItem = new TreeItem<>();
									ImageView imageView = new ImageView("image/table.png");
									imageView.setFitHeight(18);
									imageView.setFitWidth(18);
									newTreeItem.setGraphic(imageView);
									newTreeItem.setValue(tableName);
									children.add(newTreeItem);
								}
							}
							LOG.debug("加载所有表成功!");
						} catch (Exception e) {
							AlertUtil.showErrorAlert(e.getMessage());
							LOG.error("加载所有表失败!!!" + e);
						}
					} else if (level == 2) {
						LOG.debug("将表的数据加载到数据面板...");
						String tableName = treeCell.getTreeItem().getValue();
						selectedDatabaseConfig = (DatabaseConfig) treeItem.getParent().getGraphic().getUserData();
						selectedTableName = tableName;
						txtTableName.setText(tableName);
						txtEntityName.setText(entityNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
						txtDaoName.setText(daoNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
						txtBizName.setText(bizNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
						txtRouterName.setText(routerNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
						txtSqlName.setText(sqlNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
						LOG.debug("将表的数据加载到数据面板成功!");
					}
				}
			});
			return cell;
		});
		// 加载左边数据库树

		try {
			loadTVDataBase();
			LOG.debug("加载所有数据库到左侧树集成功!");
		} catch (Exception e1) {
			AlertUtil.showErrorAlert(e1.getMessage());
			LOG.error("加载所有数据库到左侧树集失败!!!" + e1);
		}
		try {
			// 加载首页配置信息
			LOG.debug("执行查询默认配置信息并加载到首页...");
			loadIndexConfigInfo("default");// 查询使用有默认的配置,如果有就加载
			loadPlace();// 设置默认的占位符名字
			LOG.debug("加载配置信息到首页成功!");
		} catch (Exception e) {
			AlertUtil.showErrorAlert("加载配置失败!失败原因:\r\n" + e.getMessage());
			LOG.error("加载配置信息失败!!!" + e);
		}

	}

	/**
	 * 加载数据库到树集
	 * 
	 * @throws Exception
	 */
	public void loadTVDataBase() throws Exception {
		TreeItem<String> rootTreeItem = tvDataBase.getRoot();
		rootTreeItem.getChildren().clear();
		List<DatabaseConfig> item = null;
		item = ConfigUtil.getDatabaseConfig();
		for (DatabaseConfig dbConfig : item) {
			TreeItem<String> treeItem = new TreeItem<String>();
			treeItem.setValue(dbConfig.getConnName());
			ImageView dbImage = new ImageView("image/database.png");
			dbImage.setFitHeight(20);
			dbImage.setFitWidth(20);
			dbImage.setUserData(dbConfig);
			treeItem.setGraphic(dbImage);
			rootTreeItem.getChildren().add(treeItem);
		}
	}

	/**
	 * 选择项目文件
	 * 
	 * @param event
	 */
	public void selectFile(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = directoryChooser.showDialog(super.getPrimaryStage());
		if (file != null) {
			txtProjectPath.setText(file.getPath());
			LOG.debug("选择文件项目目录:" + file.getPath());
		}
	}

	/**
	 * 是否创建实体类点击事件
	 * 
	 * @param event
	 */
	public void onCreateEntity(ActionEvent event) {
		createEntityEvent(chkCreateEntity.isSelected());
	}

	/**
	 * 实体类事件源
	 * 
	 * @param mode
	 */
	private void createEntityEvent(boolean mode) {
		chkJsonKeyIsCamel.setVisible(!mode);
		txtEntityName.setDisable(!mode);
		txtEntityPackage.setDisable(!mode);
		btnSetEntity.setDisable(!mode);
	}

	/**
	 * 是否创建dao事件
	 * 
	 * @param event
	 */
	public void onCreateDao(ActionEvent event) {
		createDaoEvent(chkCreateDao.isSelected());
	}

	/**
	 * dao事件源
	 * 
	 * @param mode
	 */
	private void createDaoEvent(boolean mode) {
		txtDaoPackage.setDisable(!mode);
		txtDaoName.setDisable(!mode);
	}

	/**
	 * 是否创建Biz的点击事件
	 * 
	 * @param event
	 */
	public void onCreateBiz(ActionEvent event) {
		createBizEnvet(chkCreateBiz.isSelected());
	}

	/**
	 * biz事件源
	 * 
	 * @param mode
	 */
	private void createBizEnvet(boolean mode) {
		txtBizPackage.setDisable(!mode);
		txtBizName.setDisable(!mode);
	}

	/**
	 * biz模式Router点击事件
	 * 
	 * @param event
	 */
	public void onBizRouter(ActionEvent event) {
		chkCreateBiz.setSelected(false);
	}

	/**
	 * web模式Router的点击事件
	 * 
	 * @param event
	 */
	public void onWebRouter(ActionEvent event) {
		chkCreateBiz.setSelected(true);
	}

	/**
	 * 是否创建router的点击事件
	 * 
	 * @param event
	 */
	public void onCreateRouter(ActionEvent event) {
		createRouterEnvet(chkCreateRouter.isSelected());
	}

	/**
	 * router事件源
	 * 
	 * @param mode
	 */
	private void createRouterEnvet(boolean mode) {
		txtRouterPackage.setDisable(!mode);
		txtRouterName.setDisable(!mode);
	}

	/**
	 * 修改实体属性
	 *
	 * @param event
	 */
	public void onEntity(ActionEvent event) {
		if (selectedTableName == null) {
			AlertUtil.showWarnAlert("请先选择数据库表!打开左侧数据库双击表名便可加载...");
			return;
		}
		SetAttributeController controller = (SetAttributeController) loadFXMLPage("修改实体类属性", FXMLPage.SET_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 修改dao属性
	 * 
	 * @param event
	 */
	public void onSetDao(ActionEvent event) {
		SetDaoAttributeController controller = (SetDaoAttributeController) loadFXMLPage("dao层设置", FXMLPage.SET_DAO_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 修改biz属性
	 * 
	 * @param event
	 */
	public void onSetBiz(ActionEvent event) {
		SetBizAttributeController controller = (SetBizAttributeController) loadFXMLPage("biz层设置,biz包括BizRouter", FXMLPage.SET_BIZ_ATTRIBUTE,
				false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 修改Router属性
	 * 
	 * @param event
	 */
	public void onSetRouter(ActionEvent event) {
		SetRouterAttributeController controller = (SetRouterAttributeController) loadFXMLPage("Router设置", FXMLPage.SET_ROUTER_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 修改SQL属性
	 * 
	 * @param event
	 */
	public void onSetSQL(ActionEvent event) {
		SetSQLAttributeController controller = (SetSQLAttributeController) loadFXMLPage("SQL设置", FXMLPage.SET_SQL_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();

	}

	/**
	 * 保存配置文件
	 * 
	 * @param event
	 */
	public void saveConfig(ActionEvent event) {
		LOG.debug("执行保存配置文件...");
		// 检查是否类名是否存在占位符
		boolean indexOf = StrUtil.indexOf("{c}", txtEntityName.getText(), txtDaoName.getText(), txtBizName.getText(), txtRouterName.getText(),
				txtSqlName.getText());
		if (!indexOf) {
			AlertUtil.showWarnAlert("所以类名里面必须包含用于替换表名的占位符: {c}");
			return;
		}

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("保存当前配置");
		dialog.setContentText("请输入配置名称:\r\n(表名不在保存范围内必须通过数据库加载!!!)");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			String name = result.map(n -> n).orElse("null");
			try {
				HistoryConfig config = getHistoryConfig(name);
				ConfigUtil.saveHistoryConfig(config);
				AlertUtil.showInfoAlert("保存配置成功!");
				LOG.debug("保存配置成功!");
			} catch (Exception e) {
				AlertUtil.showErrorAlert("保存配置失败!失败原因:\r\n" + e.getMessage());
				LOG.error("保存配置失败!!!" + e);
			}
		}
	}

	/**
	 * 执行创建
	 * 
	 * @param event
	 */
	public void runCreate(ActionEvent event) {
		try {
			if (StrUtil.isNullOrEmpty(txtProjectPath.getText())) {
				AlertUtil.showWarnAlert("生成的路径不能为空");
				return;
			}
			if (StrUtil.isNullOrEmpty(txtTableName.getText())) {
				AlertUtil.showWarnAlert("请双击左侧数据选择想要生成的表,或者在左侧右键全库生成!");
				return;
			}
			LOG.debug("执行创建文件...");
			CreateFileUtil.CreateEntity(getHistoryConfig(), getEntityAttribute());
			CreateFileUtil.createAbstractSQL(getHistoryConfig());
			CreateFileUtil.createSqlAssist(getHistoryConfig());
			CreateFileUtil.createSQLAndParams(getHistoryConfig());
			CreateFileUtil.createSQL(getHistoryConfig(), getEntityAttribute());
			CreateFileUtil.createSqlAssistCondition(getHistoryConfig());
			CreateFileUtil.createDao(getHistoryConfig(), getEntityAttribute());
			CreateFileUtil.createBiz(getHistoryConfig(), getCtEntityAttribute());
			CreateFileUtil.createRouter(getHistoryConfig(), getCtEntityAttribute());
			ctEntityAttribute = null;
			AlertUtil.showInfoAlert("创建文件成功!");
		} catch (Exception e) {
			AlertUtil.showErrorAlert("创建文件失败:" + e);
			LOG.error("执行创建文件-->失败:" + e);
		}
	}

	/**
	 * 将数据库中所有的表创建
	 * 
	 * @param databaseConfig
	 */
	public void createAllTable(DatabaseConfig databaseConfig) {
		try {
			List<String> tables = DBUtil.getTableNames(databaseConfig);
			if (tables.size() == 0) {
				AlertUtil.showWarnAlert("当前数据库不存在表");
			}
			double progIndex = 1.0 / tables.size();
			probCreateAll.setVisible(true);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					for (int i = 0; i < tables.size(); i++) {
						updateProgress(progIndex * (i + 1), 1.0);
						updateMessage(runCreateText.replace("{t}", tables.get(i)));
						try {
							runCreateAll(databaseConfig, tables.get(i));
						} catch (Exception e) {
							AlertUtil.showErrorAlert("全库生成失败:" + e);
							LOG.error("执行全库创建-->失败:" + e);
						}
					}
					updateMessage("创建成功!");
					LOG.debug("执行全库生成-->成功");
					return null;
				}
			};
			probCreateAll.progressProperty().bind(task.progressProperty());
			lblRunCreateAll.textProperty().bind(task.messageProperty());
			new Thread(task).start();
		} catch (Exception e) {
			AlertUtil.showErrorAlert("创建文件失败:" + e);
			LOG.error("执行创建文件-->失败:" + e);
		}

	}

	/**
	 * 执行创建所有文件
	 * 
	 * @param dbConfig
	 * @param tableName
	 * @throws Exception
	 */
	public void runCreateAll(DatabaseConfig dbConfig, String tableName) throws Exception {
		String primaryKey = null;
		EntityAttribute entityAttribute = new EntityAttribute();
		entityAttribute.setTableName(tableName);
		entityAttribute.setEntityPackage(txtEntityPackage.getText());
		entityAttribute.setEntityName(entityNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
		LOG.debug("获取表的主键...");
		primaryKey = DBUtil.getTablePrimaryKey(dbConfig, tableName);
		entityAttribute.setPrimaryKey(primaryKey);
		LOG.debug("获取类的生成配置...");
		ClassConfig config = getThisClassConfig();
		entityAttribute.setConfig(config);
		LOG.debug("获取表与类的属性...");
		List<AttributeCVF> columns = DBUtil.getTableColumns(dbConfig, tableName);
		if (chkCreateEntity.isSelected()) {
			if (config.isUnlineCamel()) {
				for (AttributeCVF temp : columns) {
					temp.setPropertyName(StrUtil.unlineToCamel(temp.getPropertyName()));
				}
			}
		} else {
			if (chkJsonKeyIsCamel.isSelected()) {
				for (AttributeCVF temp : columns) {
					temp.setPropertyName(StrUtil.unlineToCamel(temp.getPropertyName()));
				}
			}
		}
		entityAttribute.setAttrs(columns);
		String jUPackg = CommonName.JSON_UTIL_PACKAGE.getValue();
		String jOPackg = CommonName.JSON_OBJECT_PACKAGE.getValue();
		String jOName = CommonName.JSON_OBJECT_NAME.getValue();

		// 引入json包
		entityAttribute.setImportPackages(Arrays.asList(new String[]{jUPackg, String.join(".", jOPackg, jOName)}));

		// TODO

		HistoryConfig historyConfig = getHistoryConfig();
		historyConfig.setDaoName(daoNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
		historyConfig.setBizName(bizNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
		historyConfig.setRouterName(routerNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
		historyConfig.setSqlName(sqlNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
		historyConfig.setEntityName(entityNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)));
		historyConfig.setDbType(dbConfig.getDbType());
		CreateFileUtil.CreateEntity(historyConfig, entityAttribute);
		CreateFileUtil.createAbstractSQL(historyConfig);
		CreateFileUtil.createSqlAssist(historyConfig);
		CreateFileUtil.createSQLAndParams(historyConfig);
		CreateFileUtil.createSQL(historyConfig, entityAttribute);
		CreateFileUtil.createSqlAssistCondition(historyConfig);
		CreateFileUtil.createDao(historyConfig, entityAttribute);
		CreateFileUtil.createBiz(historyConfig, entityAttribute);
		CreateFileUtil.createRouter(historyConfig, entityAttribute);

	}

	/**
	 * 获得配置信息
	 * 
	 * @return
	 */
	public HistoryConfig getHistoryConfig() {
		return getHistoryConfig("default");
	}

	/**
	 * 获得配置信息
	 * 
	 * @param name
	 * @return
	 */
	public HistoryConfig getHistoryConfig(String name) {
		String projectPath = txtProjectPath.getText();
		String entityPackage = txtEntityPackage.getText();
		String entityName = txtEntityName.getText();
		String daoPackage = txtDaoPackage.getText();
		String daoName = txtDaoName.getText();
		String bizPackage = txtBizPackage.getText();
		String bizName = txtBizName.getText();
		String routerPackage = txtRouterPackage.getText();
		String routerName = txtRouterName.getText();
		String sqlPackage = txtSqlPackage.getText();
		String sqlName = txtSqlName.getText();
		String assistPackage = txtAssistPackage.getText();
		String abstractSqlPackage = txtAbstractSqlPackage.getText();
		String sqlParamsPackage = txtSqlParamsPackage.getText();
		boolean isCreateEntity = chkCreateEntity.isSelected();
		String codeFormat = cboCodeFormat.getValue();
		boolean isCreateDao = chkCreateDao.isSelected();
		boolean isCreateBiz = chkCreateBiz.isSelected();
		boolean isCreateRouter = chkCreateRouter.isSelected();
		boolean isJsonToCamel = chkJsonKeyIsCamel.isSelected();
		HistoryConfig config = new HistoryConfig(name, projectPath, entityPackage, entityName, daoPackage, daoName, bizPackage, bizName,
				routerPackage, routerName, sqlPackage, sqlName, assistPackage, abstractSqlPackage, sqlParamsPackage, codeFormat, isCreateEntity,
				isCreateDao, isCreateBiz, isCreateRouter, isJsonToCamel);
		if (selectedDatabaseConfig != null) {
			config.setDbType(selectedDatabaseConfig.getDbType());
		}
		config.setDaoConfig(getThisDaoConfig());
		config.setBizConfig(getThisBizConfig());
		config.setRouterConfig(getThisRouterConfig());
		config.setSqlConfig(getThisSQLConfig());
		config.setTemplateConfig(getThisTemplateConfig());
		return config;
	}

	/**
	 * 加载首页配置文件
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void loadIndexConfigInfo(String name) throws Exception {
		HistoryConfig config = ConfigUtil.getHistoryConfigByName(name);
		if (config == null) {
			return;
		}
		txtProjectPath.setText(config.getProjectPath());
		txtEntityPackage.setText(config.getEntityPackage());
		if (txtEntityName.getText().contains("{c}")) {
			txtEntityName.setText(config.getEntityName());
		}
		txtDaoPackage.setText(config.getDaoPackage());
		if (txtDaoName.getText().contains("{c}")) {
			txtDaoName.setText(config.getDaoName());
		}
		txtBizPackage.setText(config.getBizPackage());
		if (txtBizName.getText().contains("{c}")) {
			txtBizName.setText(config.getBizName());
		}
		txtRouterPackage.setText(config.getRouterPackage());
		if (txtRouterName.getText().contains("{c}")) {
			txtRouterName.setText(config.getRouterName());
		}
		txtSqlPackage.setText(config.getSqlPackage());
		if (txtSqlName.getText().contains("{c}")) {
			txtSqlName.setText(config.getSqlName());
		}
		txtAssistPackage.setText(config.getAssistPackage());
		txtAbstractSqlPackage.setText(config.getAbstractSqlPackage());
		txtSqlParamsPackage.setText(config.getSqlParamsPackage());
		chkCreateEntity.setSelected(config.isCreateEntity());
		createEntityEvent(config.isCreateEntity());
		chkCreateDao.setSelected(config.isCreateDao());
		createDaoEvent(config.isCreateDao());
		chkCreateBiz.setSelected(config.isCreateBiz());
		createBizEnvet(config.isCreateBiz());
		chkCreateRouter.setSelected(config.isCreateRouter());
		createRouterEnvet(config.isCreateRouter());
		chkJsonKeyIsCamel.setSelected(config.isJsonToCamel());
		if (config.isJsonToCamel() == false) {
			chkJsonKeyIsCamel.setSelected(false);
		}
		cboCodeFormat.setValue(config.getCodeFormat());
		daoConfig = config.getDaoConfig();
		bizConfig = config.getBizConfig();
		routerConfig = config.getRouterConfig();
		sqlConfig = config.getSqlConfig();
		loadPlace();// 加载配置是设置占位符
	}

	/**
	 * 加载默认名字
	 */
	private void loadPlace() {
		entityNamePlace = txtEntityName.getText();
		daoNamePlace = txtDaoName.getText();
		bizNamePlace = txtBizName.getText();
		routerNamePlace = txtRouterName.getText();
		sqlNamePlace = txtSqlName.getText();
	}

	/**
	 * 或者表的属性与实体类配置等信息
	 * 
	 * @return
	 */
	private EntityAttribute getEntityAttribute() {
		LOG.debug("获得表与类属性...");
		if (ctEntityAttribute != null) {
			ctEntityAttribute.setTableName(txtTableName.getText());
			ctEntityAttribute.setEntityPackage(txtEntityPackage.getText());
			ctEntityAttribute.setEntityName(txtEntityName.getText());
		} else {
			try {
				String primaryKey = null;
				ctEntityAttribute = new EntityAttribute();
				ctEntityAttribute.setTableName(txtTableName.getText());
				ctEntityAttribute.setEntityPackage(txtEntityPackage.getText());
				ctEntityAttribute.setEntityName(txtEntityName.getText());
				LOG.debug("获取表的主键...");
				primaryKey = DBUtil.getTablePrimaryKey(selectedDatabaseConfig, selectedTableName);
				ctEntityAttribute.setPrimaryKey(primaryKey);
				LOG.debug("获取类的生成配置...");
				ClassConfig config = getThisClassConfig();
				ctEntityAttribute.setConfig(config);
				LOG.debug("获取表与类的属性...");
				List<AttributeCVF> columns = DBUtil.getTableColumns(selectedDatabaseConfig, selectedTableName);
				if (chkCreateEntity.isSelected()) {
					if (config.isUnlineCamel()) {
						for (AttributeCVF temp : columns) {
							temp.setPropertyName(StrUtil.unlineToCamel(temp.getPropertyName()));
						}
					}
				} else {
					if (chkJsonKeyIsCamel.isSelected()) {
						for (AttributeCVF temp : columns) {
							temp.setPropertyName(StrUtil.unlineToCamel(temp.getPropertyName()));
						}
					}
				}
				ctEntityAttribute.setAttrs(columns);
			} catch (Exception e) {
				AlertUtil.showErrorAlert("获取表与类属性!原因:\r\n" + e.getMessage());
				LOG.error("获取表与类属性-->失败:" + e);
			}

		}
		String jUPackg = CommonName.JSON_UTIL_PACKAGE.getValue();
		String jOPackg = CommonName.JSON_OBJECT_PACKAGE.getValue();
		String jOName = CommonName.JSON_OBJECT_NAME.getValue();

		// 引入json包
		ctEntityAttribute.setImportPackages(Arrays.asList(new String[]{jUPackg, String.join(".", jOPackg, jOName)}));
		LOG.debug("获取表与类属性-->成功!!!");
		return ctEntityAttribute;
	}

	private ClassConfig getThisClassConfig() {
		if (classConfig != null) {
			return classConfig;
		}
		try {
			ClassConfig config = Optional.ofNullable(ConfigUtil.getClassConfig("default")).map(conf -> conf).orElse(new ClassConfig());
			return config;
		} catch (Exception e) {
			LOG.error("获取entity配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获取entity配置文件-->失败:" + e);
		}

		return new ClassConfig();
	}

	/**
	 * 获得daoConfig如果当前的daoConfig为空,查询default,如果default为空新建一个配置文件
	 * 
	 * @return
	 */
	private DaoConfig getThisDaoConfig() {
		if (daoConfig != null) {
			return daoConfig;
		}
		try {
			LOG.debug("执行获取dao配置文件...");
			DaoConfig result = Optional.ofNullable(ConfigUtil.getDaoConfig("default")).map(conf -> conf).orElse(new DaoConfig());
			LOG.debug("执行获取dao配置文件-->成功!");
			return result;
		} catch (Exception e) {
			LOG.error("获取dao配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获取dao配置文件-->失败:" + e);
		}
		return new DaoConfig();
	}

	/**
	 * 获得bizConfig如果当前的bizConfig为空,查询default,如果default为空新建一个配置文件
	 * 
	 * @return
	 */
	private BizConfig getThisBizConfig() {
		if (bizConfig != null) {
			return bizConfig;
		}
		try {
			LOG.debug("执行获取配biz置文件...");
			BizConfig result = Optional.ofNullable(ConfigUtil.getBizConfig("default")).map(conf -> conf).orElse(new BizConfig());
			LOG.debug("执行获取biz配置文件-->成功!");
			return result;
		} catch (Exception e) {
			LOG.error("获取biz配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获取biz配置文件-->失败:" + e);
		}
		return new BizConfig();
	}

	/**
	 * 获得routerConfig如果当前的routerConfig为空,查询default,如果default为空新建一个配置文件
	 * 
	 * @return
	 */
	private RouterConfig getThisRouterConfig() {
		if (routerConfig != null) {
			return routerConfig;
		}
		try {
			LOG.debug("执行获取router配置文件...");
			RouterConfig result = Optional.ofNullable(ConfigUtil.getRouterConfig("default")).map(conf -> conf).orElse(new RouterConfig());
			LOG.debug("执行获取router配置文件-->成功!");
			return result;
		} catch (Exception e) {
			LOG.error("获取router配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获取router配置文件-->失败:" + e);
		}
		return new RouterConfig();
	}

	/**
	 * 获得sqlConfig如果当前的sqlConfig为空,查询default,如果default为空新建一个配置文件
	 * 
	 * @return
	 */
	private SQLConfig getThisSQLConfig() {
		if (sqlConfig != null) {
			return sqlConfig;
		}
		try {
			LOG.debug("执行获取SQL配置文件...");
			SQLConfig result = Optional.ofNullable(ConfigUtil.getSQLConfig("default")).map(conf -> conf).orElse(new SQLConfig());
			LOG.debug("执行获取SQL配置文件-->成功!");
			return result;
		} catch (Exception e) {
			LOG.error("获取SQL配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获取SQL配置文件-->失败:" + e);
		}
		return new SQLConfig();
	}

	private TemplateConfig getThisTemplateConfig() {
		if (templateConfig != null) {
			return templateConfig;
		}
		try {
			LOG.debug("执行获取SQL配置文件...");
			TemplateConfig result = Optional.ofNullable(ConfigUtil.getTemplateConfig("default")).map(conf -> conf).orElse(new TemplateConfig());
			LOG.debug("执行获取SQL配置文件-->成功!");
			return result;
		} catch (Exception e) {
			LOG.error("获取SQL配置文件-->失败:" + e);
			AlertUtil.showErrorAlert("获取SQL配置文件-->失败:" + e);
		}
		return new TemplateConfig();
	};

	// -----------------------get/set-------------------------------

	public String getTableName() {
		return txtTableName.getText();
	}

	public boolean isChangeInfo() {
		return changeInfo;
	}

	public void setChangeInfo(boolean changeInfo) {
		this.changeInfo = changeInfo;
	}

	public DatabaseConfig getSelectedDatabaseConfig() {
		return selectedDatabaseConfig;
	}

	public void setSelectedDatabaseConfig(DatabaseConfig selectedDatabaseConfig) {
		this.selectedDatabaseConfig = selectedDatabaseConfig;
	}

	public String getSelectedTableName() {
		return selectedTableName;
	}

	public void setSelectedTableName(String selectedTableName) {
		this.selectedTableName = selectedTableName;
	}

	public DatabaseConfig getUpdateOfDatabaseConfig() {
		return updateOfDatabaseConfig;
	}

	public void setUpdateOfDatabaseConfig(DatabaseConfig updateOfDatabaseConfig) {
		this.updateOfDatabaseConfig = updateOfDatabaseConfig;
	}

	public ClassConfig getClassConfig() {
		return classConfig;
	}

	public void setClassConfig(ClassConfig classConfig) {
		this.classConfig = classConfig;
	}

	public DaoConfig getDaoConfig() {
		return daoConfig;
	}

	public void setDaoConfig(DaoConfig daoConfig) {
		this.daoConfig = daoConfig;
	}

	public BizConfig getBizConfig() {
		return bizConfig;
	}

	public void setBizConfig(BizConfig bizConfig) {
		this.bizConfig = bizConfig;
	}

	public RouterConfig getRouterConfig() {
		return routerConfig;
	}

	public void setRouterConfig(RouterConfig routerConfig) {
		this.routerConfig = routerConfig;
	}

	public SQLConfig getSqlConfig() {
		return sqlConfig;
	}

	public void setSqlConfig(SQLConfig sqlConfig) {
		this.sqlConfig = sqlConfig;
	}

	public EntityAttribute getCtEntityAttribute() {
		return ctEntityAttribute;
	}

	public void setCtEntityAttribute(EntityAttribute ctEntityAttribute) {
		this.ctEntityAttribute = ctEntityAttribute;
	}

	public TemplateConfig getTemplateConfig() {
		return templateConfig;
	}

	public void setTemplateConfig(TemplateConfig templateConfig) {
		this.templateConfig = templateConfig;
	}

}
