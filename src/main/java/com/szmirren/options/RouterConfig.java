package com.szmirren.options;

/**
 * Router的配置信息
 * 
 * @author duhua
 *
 */
public class RouterConfig {

	private boolean isGetCount = true;
	private boolean isSelectAll = true;
	private boolean isSelectByPage = true;
	private boolean isSelectObj = true;
	private boolean isSelectId = true;
	private boolean isInsert = true;
	private boolean isUpdate = true;
	private boolean isDelete = true;
	private boolean isInsertBatch = false;
	private boolean isDelOldFile = true;

	private String funGetCount = "get{C}Count";
	private String funSelectAll = "find{C}";
	private String funSelectByPage = "find{C}ByPage";
	private String funSelectObj = "get{C}ByObj";
	private String funSelectId = "get{C}ById";
	private String funInsert = "add{C}";
	private String funUpdate = "updt{C}";
	private String funDelete = "del{C}ById";
	private String funInsertBatch = "addBatch{C}";

	private String bsGetCount = "/{c}/{c}Count";
	private String bsSelectAll = "/{c}/find{C}";
	private String bsSelectByPage = "/{c}/find{C}ByPage";
	private String bsSelectObj = "/{c}/get{C}ByObj";
	private String bsSelectId = "/{c}/get{C}ById/:id";
	private String bsInsert = "/{c}/add{C}";
	private String bsUpdate = "/{c}/updt{C}";
	private String bsDelete = "/{c}/del{C}/:id";
	private String bsInsertBatch = "/{c}/addBatch{C}";

	public RouterConfig() {
		super();
	}

	public RouterConfig(boolean isGetCount, boolean isSelectAll, boolean isSelectByPage, boolean isSelectObj,
			boolean isSelectId, boolean isInsert, boolean isUpdate, boolean isDelete, boolean isInsertBatch,
			boolean isDelOldFile, String funGetCount, String funSelectAll, String funSelectByPage, String funSelectObj,
			String funSelectId, String funInsert, String funUpdate, String funDelete, String funInsertBatch,
			String bsGetCount, String bsSelectAll, String bsSelectByPage, String bsSelectObj, String bsSelectId,
			String bsInsert, String bsUpdate, String bsDelete, String bsInsertBatch) {
		super();
		this.isGetCount = isGetCount;
		this.isSelectAll = isSelectAll;
		this.isSelectByPage = isSelectByPage;
		this.isSelectObj = isSelectObj;
		this.isSelectId = isSelectId;
		this.isInsert = isInsert;
		this.isUpdate = isUpdate;
		this.isDelete = isDelete;
		this.isInsertBatch = isInsertBatch;
		this.isDelOldFile = isDelOldFile;
		this.funGetCount = funGetCount;
		this.funSelectAll = funSelectAll;
		this.funSelectByPage = funSelectByPage;
		this.funSelectObj = funSelectObj;
		this.funSelectId = funSelectId;
		this.funInsert = funInsert;
		this.funUpdate = funUpdate;
		this.funDelete = funDelete;
		this.funInsertBatch = funInsertBatch;
		this.bsGetCount = bsGetCount;
		this.bsSelectAll = bsSelectAll;
		this.bsSelectByPage = bsSelectByPage;
		this.bsSelectObj = bsSelectObj;
		this.bsSelectId = bsSelectId;
		this.bsInsert = bsInsert;
		this.bsUpdate = bsUpdate;
		this.bsDelete = bsDelete;
		this.bsInsertBatch = bsInsertBatch;
	}

	public boolean isDelOldFile() {
		return isDelOldFile;
	}

	public void setDelOldFile(boolean isDelOldFile) {
		this.isDelOldFile = isDelOldFile;
	}

	public boolean isGetCount() {
		return isGetCount;
	}

	public void setGetCount(boolean isGetCount) {
		this.isGetCount = isGetCount;
	}

	public boolean isSelectAll() {
		return isSelectAll;
	}

	public void setSelectAll(boolean isSelectAll) {
		this.isSelectAll = isSelectAll;
	}

	public boolean isSelectByPage() {
		return isSelectByPage;
	}

	public void setSelectByPage(boolean isSelectByPage) {
		this.isSelectByPage = isSelectByPage;
	}

	public boolean isSelectObj() {
		return isSelectObj;
	}

	public void setSelectObj(boolean isSelectObj) {
		this.isSelectObj = isSelectObj;
	}

	public boolean isSelectId() {
		return isSelectId;
	}

	public void setSelectId(boolean isSelectId) {
		this.isSelectId = isSelectId;
	}

	public boolean isInsert() {
		return isInsert;
	}

	public void setInsert(boolean isInsert) {
		this.isInsert = isInsert;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isInsertBatch() {
		return isInsertBatch;
	}

	public void setInsertBatch(boolean isInsertBatch) {
		this.isInsertBatch = isInsertBatch;
	}

	public String getFunGetCount() {
		return funGetCount;
	}

	public void setFunGetCount(String funGetCount) {
		this.funGetCount = funGetCount;
	}

	public String getFunSelectAll() {
		return funSelectAll;
	}

	public void setFunSelectAll(String funSelectAll) {
		this.funSelectAll = funSelectAll;
	}

	public String getFunSelectByPage() {
		return funSelectByPage;
	}

	public void setFunSelectByPage(String funSelectByPage) {
		this.funSelectByPage = funSelectByPage;
	}

	public String getFunSelectObj() {
		return funSelectObj;
	}

	public void setFunSelectObj(String funSelectObj) {
		this.funSelectObj = funSelectObj;
	}

	public String getFunSelectId() {
		return funSelectId;
	}

	public void setFunSelectId(String funSelectId) {
		this.funSelectId = funSelectId;
	}

	public String getFunInsert() {
		return funInsert;
	}

	public void setFunInsert(String funInsert) {
		this.funInsert = funInsert;
	}

	public String getFunUpdate() {
		return funUpdate;
	}

	public void setFunUpdate(String funUpdate) {
		this.funUpdate = funUpdate;
	}

	public String getFunDelete() {
		return funDelete;
	}

	public void setFunDelete(String funDelete) {
		this.funDelete = funDelete;
	}

	public String getFunInsertBatch() {
		return funInsertBatch;
	}

	public void setFunInsertBatch(String funInsertBatch) {
		this.funInsertBatch = funInsertBatch;
	}

	public String getBsGetCount() {
		return bsGetCount;
	}

	public void setBsGetCount(String bsGetCount) {
		this.bsGetCount = bsGetCount;
	}

	public String getBsSelectAll() {
		return bsSelectAll;
	}

	public void setBsSelectAll(String bsSelectAll) {
		this.bsSelectAll = bsSelectAll;
	}

	public String getBsSelectByPage() {
		return bsSelectByPage;
	}

	public void setBsSelectByPage(String bsSelectByPage) {
		this.bsSelectByPage = bsSelectByPage;
	}

	public String getBsSelectObj() {
		return bsSelectObj;
	}

	public void setBsSelectObj(String bsSelectObj) {
		this.bsSelectObj = bsSelectObj;
	}

	public String getBsSelectId() {
		return bsSelectId;
	}

	public void setBsSelectId(String bsSelectId) {
		this.bsSelectId = bsSelectId;
	}

	public String getBsInsert() {
		return bsInsert;
	}

	public void setBsInsert(String bsInsert) {
		this.bsInsert = bsInsert;
	}

	public String getBsUpdate() {
		return bsUpdate;
	}

	public void setBsUpdate(String bsUpdate) {
		this.bsUpdate = bsUpdate;
	}

	public String getBsDelete() {
		return bsDelete;
	}

	public void setBsDelete(String bsDelete) {
		this.bsDelete = bsDelete;
	}

	public String getBsInsertBatch() {
		return bsInsertBatch;
	}

	public void setBsInsertBatch(String bsInsertBatch) {
		this.bsInsertBatch = bsInsertBatch;
	}

}
