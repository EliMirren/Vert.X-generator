package com.szmirren;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.szmirren.common.ConfigUtil;
import com.szmirren.common.TemplateUtil;
import com.szmirren.controller.IndexController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	private static Logger LOG = Logger.getLogger(Main.class.getName());

	@Override
	public void start(Stage primaryStage) throws Exception {
		ConfigUtil.existsConfigDB();// 创建配置文件
		TemplateUtil.existsTemplate();// 创建模板
		URL url = Thread.currentThread().getContextClassLoader().getResource("FXML/Index.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(url);
		Parent root = fxmlLoader.load();
		primaryStage.setResizable(true);
		primaryStage.setTitle("VertX-Generator");
		primaryStage.getIcons().add(new Image("image/icon.png"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		IndexController controller = fxmlLoader.getController();
		controller.setPrimaryStage(primaryStage);
	}

	public static void main(String[] args) {
		URL logResource = Thread.currentThread().getContextClassLoader().getResource("config/log4j.properties");
		PropertyConfigurator.configure(logResource);
		try {
			LOG.debug("运行VertX-Generator...");
			launch(args);
			LOG.debug("关闭VertX-Generator!!!");
		} catch (Exception e) {
			LOG.error(e);
		}
	}
}