package com.szmirren.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.szmirren.common.ConfigUtil;
import com.szmirren.common.DBUtil;
import com.szmirren.models.DBTypeName;
import com.szmirren.models.DatabaseConfig;
import com.szmirren.view.AlertUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConnectionController extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());
	private IndexController indexController;
	@FXML
	private TextField txtConnName;
	@FXML
	private TextField txtConnURL;
	@FXML
	private TextField txtListenPort;
	@FXML
	private TextField txtDBName;
	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtUserPwd;
	@FXML
	private ComboBox<String> cboDBType;
	@FXML
	private ComboBox<String> cboDBCoding;
	@FXML
	private Button btnTestConn;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnSave;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOG.debug("初始化数据库连接窗口....");
		// 初始化下拉列表
		cboDBType.getItems().addAll(DBTypeName.MYSQL.getValue(), DBTypeName.POSTGRE_SQL.getValue(),
				DBTypeName.ORACLE.getValue(), DBTypeName.SQL_SERVER.getValue());
		cboDBCoding.getItems().addAll("utf8", "utf16", "utf32", "utf8mb4", "gb2312", "gbk", "ascii");
		cboDBCoding.setValue("utf8");
		LOG.debug("初始化数据库连接成功!");
	}

	/**
	 * 保存连接
	 * 
	 * @param event
	 */
	public void saveConnection(ActionEvent event) {
		LOG.debug("执行保存数据库连接...");
		DatabaseConfig config = getDatabaseConfig();
		if (config == null) {
			LOG.debug("连接数据库的数据为null,取消保存操作!!!");
			return;
		}
		try {
			ConfigUtil.saveDatabaseConfig(config.getConnName(), config);
			getDialogStage().close();
			indexController.loadTVDataBase();
			LOG.debug("保存数据库连接成功!");
		} catch (Exception e) {
			AlertUtil.showErrorAlert(e.getMessage());
			LOG.error("保存数据库连接失败!!!" + e);
		}

	}

	/**
	 * 关闭当前窗口
	 * 
	 * @param event
	 */
	public void onCancel(ActionEvent event) {
		closeDialogStage();
	}

	/**
	 * 测试连接
	 * 
	 * @param event
	 */
	public void testConnection(ActionEvent event) {
		LOG.debug("执行测试数据库连接...");
		DatabaseConfig config = getDatabaseConfig();
		if (config == null) {
			LOG.debug("连接数据库的数据为null,取消测试操作!!!");
			return;
		}
		try {
			DBUtil.getConnection(config);
			AlertUtil.showInfoAlert("连接成功!");
			LOG.debug("数据库测试连接成功!");
		} catch (Exception e) {
			AlertUtil.showErrorAlert("连接失败" + e.getMessage());
			LOG.error("数据库连接测试失败!!!" + e);
		}
	}

	/**
	 * 获得连接的所有字段
	 * 
	 * @return
	 */
	public DatabaseConfig getDatabaseConfig() {
		String connName = txtConnName.getText().trim();
		String connURL = txtConnURL.getText().trim();
		String listenPort = txtListenPort.getText().trim();
		String dbName = txtDBName.getText().trim();
		String userName = txtUserName.getText().trim();
		String userPwd = txtUserPwd.getText().trim();
		String dbType = cboDBType.getValue();
		String encoding = cboDBCoding.getValue();
		boolean isEmpty = validata(connName, connURL, listenPort, dbName, userName, dbType, encoding);
		if (isEmpty) {
			DatabaseConfig config = new DatabaseConfig(connName, connURL, listenPort, dbName, userName, userPwd, dbType,
					encoding);
			return config;
		} else {
			AlertUtil.showWarnAlert("除了密码以外所有属性都为必需填与选择");
			return null;
		}

	}

	/**
	 * 验证所有属性是否已经填写
	 * 
	 * @param str
	 * @return
	 */
	public boolean validata(String... str) {
		for (String string : str) {
			if (string == null || "".equals(string)) {
				return false;
			}
		}
		return true;
	}

	// ----------------------get/set----------------------------
	public IndexController getIndexController() {
		return indexController;
	}

	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

	public TextField getTxtConnName() {
		return txtConnName;
	}

	public void setTxtConnName(TextField txtConnName) {
		this.txtConnName = txtConnName;
	}

	public TextField getTxtConnURL() {
		return txtConnURL;
	}

	public void setTxtConnURL(TextField txtConnURL) {
		this.txtConnURL = txtConnURL;
	}

	public TextField getTxtListenPort() {
		return txtListenPort;
	}

	public void setTxtListenPort(TextField txtListenPort) {
		this.txtListenPort = txtListenPort;
	}

	public TextField getTxtDBName() {
		return txtDBName;
	}

	public void setTxtDBName(TextField txtDBName) {
		this.txtDBName = txtDBName;
	}

	public TextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(TextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public TextField getTxtUserPwd() {
		return txtUserPwd;
	}

	public void setTxtUserPwd(TextField txtUserPwd) {
		this.txtUserPwd = txtUserPwd;
	}

	public ComboBox<String> getCboDBType() {
		return cboDBType;
	}

	public void setCboDBType(ComboBox<String> cboDBType) {
		this.cboDBType = cboDBType;
	}

	public ComboBox<String> getCboDBCoding() {
		return cboDBCoding;
	}

	public void setCboDBCoding(ComboBox<String> cboDBCoding) {
		this.cboDBCoding = cboDBCoding;
	}

	public Button getBtnTestConn() {
		return btnTestConn;
	}

	public void setBtnTestConn(Button btnTestConn) {
		this.btnTestConn = btnTestConn;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

}
