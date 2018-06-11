package com.szmirren.options;

import java.util.ArrayList;
import java.util.List;

import com.szmirren.common.Constant;
import com.szmirren.models.TableAttributeEntity;
import com.szmirren.models.TableAttributeKeyValue;

/**
 * 实体类的配置文件
 * 
 * @author Mirren
 *
 */
public class EntityConfig {
	/** 设置的tableItem */
	private List<TableAttributeEntity> tableItem = new ArrayList<>();
	/** 生成模板的名字 */
	private String templateName = Constant.TEMPLATE_NAME_ENTITY;
	/** 是否覆盖原文件 */
	private boolean overrideFile = true;

	public EntityConfig() {
		super();
	}

	public List<TableAttributeEntity> getTableItem() {
		return tableItem;
	}

	public void setTableItem(List<TableAttributeEntity> tableItem) {
		this.tableItem = tableItem;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public boolean isOverrideFile() {
		return overrideFile;
	}

	public void setOverrideFile(boolean overrideFile) {
		this.overrideFile = overrideFile;
	}

	@Override
	public String toString() {
		return "EntityConfig [tableItem=" + tableItem + ", templateName=" + templateName + ", overrideFile=" + overrideFile + "]";
	}

}
