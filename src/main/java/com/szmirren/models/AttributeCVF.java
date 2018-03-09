package com.szmirren.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;

public class AttributeCVF {
	private BooleanProperty check = new SimpleBooleanProperty(true);// 是否创建
	private StringProperty conlumn = new SimpleStringProperty();// 列名
	private StringProperty jdbcType = new SimpleStringProperty();// jdbc数据类型
	private ComboBox<String> javaType = new ComboBox<String>();// java数据类型
	private StringProperty propertyName = new SimpleStringProperty();// 属性名称
	private String comment;// 表列的注释
	private int columnSize;// 列的长度
	private String columnDefult;// 默认值
	private boolean nullable;// 是否可以为空

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BooleanProperty checkProperty() {
		return check;
	}

	public Boolean getCheck() {
		return check.get();
	}

	public void setCheck(Boolean check) {
		this.check.set(check);
	}

	public StringProperty conlumnProperty() {
		return conlumn;
	}

	public String getConlumn() {
		return conlumn.get();
	}

	public void setConlumn(String conlumn) {
		this.setPropertyName(conlumn);
		this.conlumn.set(conlumn);
	}

	public StringProperty jdbcTypeProperty() {
		return jdbcType;
	}

	public String getJdbcType() {
		return jdbcType.get();
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType.set(jdbcType);
	}

	public ComboBox<String> getJavaType() {
		return javaType;
	}

	public String getJavaTypeValue() {
		return javaType.getValue();
	}

	public void setJavaType(String javaType) {
		this.javaType.setValue(javaType);

	}

	public String getPropertyName() {
		return propertyName.get();
	}

	public void setPropertyName(String propertyName) {
		this.propertyName.set(propertyName);

	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public String getColumnDefult() {
		return columnDefult;
	}

	public void setColumnDefult(String columnDefult) {
		this.columnDefult = columnDefult;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public AttributeCVF() {
		super();
		javaType.setEditable(true);
		javaType.getItems().addAll("int", "double", "char", "long", "java.util.Date", "java.sql.Date",
				"java.time.LocalDate", "java.time.LocalTime", "java.time.LocalDateTime", "java.util.List<E>",
				"java.util.Set<E>", "java.util.Map<K, V>", "JsonObject", "String", "Double", "Integer", "Long",
				"Object");

	}

	@Override
	public String toString() {
		return "AttributeCVF [check=" + check + ", conlumn=" + conlumn + ", jdbcType=" + jdbcType + ", javaType="
				+ javaType + ", propertyName=" + propertyName + ", comment=" + comment + ", columnSize=" + columnSize
				+ ", columnDefult=" + columnDefult + ", nullable=" + nullable + "]";
	}

}
