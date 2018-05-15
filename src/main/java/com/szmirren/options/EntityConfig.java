package com.szmirren.options;

/**
 * 实体类的配置文件
 * 
 * @author Mirren
 *
 */
public class EntityConfig {
	private boolean seriz = false;
	private boolean unlineCamel = true;
	private boolean getAndSet = true;
	private boolean construct = true;
	private boolean constructAll = true;
	private boolean constructJson = true;
	private boolean tojson = true;
	private boolean formJson = true;
	private boolean formMultiMap = true;
	private boolean comment = true;
	private boolean entityAdd = false;
	private boolean delOldFile = true;

	public EntityConfig() {
		super();
	}

	public EntityConfig(boolean seriz, boolean unlineCamel, boolean getAndSet, boolean construct, boolean constructAll, boolean constructJson,
			boolean tojson, boolean formJson, boolean formMultiMap, boolean comment, boolean entityAdd, boolean delOldFile) {
		super();
		this.seriz = seriz;
		this.unlineCamel = unlineCamel;
		this.getAndSet = getAndSet;
		this.construct = construct;
		this.constructAll = constructAll;
		this.constructJson = constructJson;
		this.tojson = tojson;
		this.formJson = formJson;
		this.formMultiMap = formMultiMap;
		this.comment = comment;
		this.entityAdd = entityAdd;
		this.delOldFile = delOldFile;
	}

	public boolean isDelOldFile() {
		return delOldFile;
	}

	public void setDelOldFile(boolean delOldFile) {
		this.delOldFile = delOldFile;
	}

	public boolean isSeriz() {
		return seriz;
	}

	public void setSeriz(boolean seriz) {
		this.seriz = seriz;
	}

	public boolean isUnlineCamel() {
		return unlineCamel;
	}

	public void setUnlineCamel(boolean unlineCamel) {
		this.unlineCamel = unlineCamel;
	}

	public boolean isGetAndSet() {
		return getAndSet;
	}

	public void setGetAndSet(boolean getAndSet) {
		this.getAndSet = getAndSet;
	}

	public boolean isConstruct() {
		return construct;
	}

	public void setConstruct(boolean construct) {
		this.construct = construct;
	}

	public boolean isConstructAll() {
		return constructAll;
	}

	public void setConstructAll(boolean constructAll) {
		this.constructAll = constructAll;
	}

	public boolean isConstructJson() {
		return constructJson;
	}

	public void setConstructJson(boolean constructJson) {
		this.constructJson = constructJson;
	}

	public boolean isTojson() {
		return tojson;
	}

	public void setTojson(boolean tojson) {
		this.tojson = tojson;
	}

	public boolean isFormJson() {
		return formJson;
	}

	public void setFormJson(boolean formJson) {
		this.formJson = formJson;
	}

	public boolean isFormMultiMap() {
		return formMultiMap;
	}

	public void setFormMultiMap(boolean formMultiMap) {
		this.formMultiMap = formMultiMap;
	}

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public boolean isEntityAdd() {
		return entityAdd;
	}

	public void setEntityAdd(boolean entityAdd) {
		this.entityAdd = entityAdd;
	}

	@Override
	public String toString() {
		return "ClassConfig [seriz=" + seriz + ", unlineCamel=" + unlineCamel + ", getAndSet=" + getAndSet + ", construct=" + construct
				+ ", constructAll=" + constructAll + ", constructJson=" + constructJson + ", tojson=" + tojson + ", formJson=" + formJson
				+ ", comment=" + comment + "]";
	}

}
