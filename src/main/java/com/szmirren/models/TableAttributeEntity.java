package com.szmirren.models;

import com.szmirren.entity.FieldAttribute;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;

/**
 * Table的key与packageName属性
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class TableAttributeEntity {
	/** 是否创建 */
	private BooleanProperty create = new SimpleBooleanProperty(true);
	/** 列名 */
	private StringProperty columnName = new SimpleStringProperty();
	/** jdbc数据类型 */
	private StringProperty jdbcType = new SimpleStringProperty();
	/** java数据类型 */
	private ComboBox<String> javaType = new ComboBox<String>();
	/** 属性名称 */
	private StringProperty field = new SimpleStringProperty();

	public TableAttributeEntity() {
		super();
		initComboBox();
	}

	public TableAttributeEntity(FieldAttribute attribute) {
		super();
		initComboBox();
	}

	public void initComboBox() {
		javaType.setEditable(true);
		javaType.getItems().addAll("int", "double", "char", "long", "java.util.Date", "java.sql.Date", "java.time.LocalDate", "java.time.LocalTime", "java.time.LocalDateTime", "java.util.List<E>",
				"java.util.Set<E>", "java.util.Map<K, V>", "JsonObject", "String", "Double", "Integer", "Long", "Object");
	}

	public BooleanProperty getCreate() {
		return create;
	}

	public void setCreate(BooleanProperty create) {
		this.create = create;
	}

	public StringProperty getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName.setValue(columnName);
	}

	public void setColumnName(StringProperty columnName) {
		this.columnName = columnName;
	}

	public StringProperty getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType.setValue(jdbcType);
	}

	public void setJdbcType(StringProperty jdbcType) {
		this.jdbcType = jdbcType;
	}

	public ComboBox<String> getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType.setValue(javaType);
	}

	public void setJavaType(ComboBox<String> javaType) {
		this.javaType = javaType;
	}

	public StringProperty getField() {
		return field;
	}

	public void setField(String field) {
		this.field.setValue(field);
	}

	public void setField(StringProperty field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "TableAttributeEntity [create=" + create + ", columnName=" + columnName + ", jdbcType=" + jdbcType + ", javaType=" + javaType + ", field=" + field + "]";
	}

}
