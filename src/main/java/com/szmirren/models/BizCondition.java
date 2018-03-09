package com.szmirren.models;

/**
 * 用于判断biz生成的条件类
 * 
 * @author Mirren
 *
 */
public class BizCondition {

	private String jsonPrimary;// json属性 主键 既json.getXXX("x");
	private String jsonPrimaryEqNull;// json属性主键等于null;
	private String jsonPrimaryNeqNull;// json属性主键不等于null;

	private String clzPrimary;// 实体类属性 主键;
	private String clzPrimaryEqNull;// 实体类属性主键等于null;
	private String clzPrimaryNeqNull;// 实体类属性主键不等于null ;

	private String jsonAttr;// json属性既json.getXXX("x");
	private String jsonPeqNull;// json属性等于null
	private String jsonPneqNull;// json属性不等于null
	private String jsonLenLt;// json属性长度小于列长度
	private String jsonLenLte;// json属性长度小于等于列长度
	private String jsonLenGt;// json属性长度大于列长度
	private String jsonLenGte;// json属性长度大于等于列长度

	private String clzAttr;// json属性既json.getXXX();
	private String clzPeqNull;// 实体类属性等于null
	private String clzPneqNull;// 实体类属性不等于null
	private String clzLenLt;// 实体类属性长度小于列长度
	private String clzLenLte;// 实体类属性长度小于等于列长度
	private String clzLenGt;// 实体类属性长度大于列长度
	private String clzLenGte;// 实体类属性长度大于等于列长度

	public BizCondition() {
		super();
	}

	public BizCondition(String jsonPrimary, String jsonPrimaryEqNull, String jsonPrimaryNeqNull, String clzPrimary,
			String clzPrimaryEqNull, String clzPrimaryNeqNull, String jsonAttr, String jsonPeqNull, String jsonPneqNull,
			String jsonLenLt, String jsonLenLte, String jsonLenGt, String jsonLenGte, String clzAttr, String clzPeqNull,
			String clzPneqNull, String clzLenLt, String clzLenLte, String clzLenGt, String clzLenGte) {
		super();
		this.jsonPrimary = jsonPrimary;
		this.jsonPrimaryEqNull = jsonPrimaryEqNull;
		this.jsonPrimaryNeqNull = jsonPrimaryNeqNull;
		this.clzPrimary = clzPrimary;
		this.clzPrimaryEqNull = clzPrimaryEqNull;
		this.clzPrimaryNeqNull = clzPrimaryNeqNull;
		this.jsonAttr = jsonAttr;
		this.jsonPeqNull = jsonPeqNull;
		this.jsonPneqNull = jsonPneqNull;
		this.jsonLenLt = jsonLenLt;
		this.jsonLenLte = jsonLenLte;
		this.jsonLenGt = jsonLenGt;
		this.jsonLenGte = jsonLenGte;
		this.clzAttr = clzAttr;
		this.clzPeqNull = clzPeqNull;
		this.clzPneqNull = clzPneqNull;
		this.clzLenLt = clzLenLt;
		this.clzLenLte = clzLenLte;
		this.clzLenGt = clzLenGt;
		this.clzLenGte = clzLenGte;
	}

	public BizCondition(String jsonPrimary, String jsonPrimaryEqNull, String jsonPrimaryNeqNull, String clzPrimary,
			String clzPrimaryEqNull, String clzPrimaryNeqNull, StringBuilder jsonAttr, StringBuilder jsonPeqNull,
			StringBuilder jsonPneqNull, StringBuilder jsonLenLt, StringBuilder jsonLenLte, StringBuilder jsonLenGt,
			StringBuilder jsonLenGte, StringBuilder clzAttr, StringBuilder clzPeqNull, StringBuilder clzPneqNull,
			StringBuilder clzLenLt, StringBuilder clzLenLte, StringBuilder clzLenGt, StringBuilder clzLenGte) {
		super();
		this.jsonPrimary = jsonPrimary;
		this.jsonPrimaryEqNull = jsonPrimaryEqNull;
		this.jsonPrimaryNeqNull = jsonPrimaryNeqNull;
		this.clzPrimary = clzPrimary;
		this.clzPrimaryEqNull = clzPrimaryEqNull;
		this.clzPrimaryNeqNull = clzPrimaryNeqNull;
		this.jsonAttr = jsonAttr.toString();
		this.jsonPeqNull = jsonPeqNull.toString();
		this.jsonPneqNull = jsonPneqNull.toString();
		this.jsonLenLt = jsonLenLt.toString();
		this.jsonLenLte = jsonLenLte.toString();
		this.jsonLenGt = jsonLenGt.toString();
		this.jsonLenGte = jsonLenGte.toString();
		this.clzAttr = clzAttr.toString();
		this.clzPeqNull = clzPeqNull.toString();
		this.clzPneqNull = clzPneqNull.toString();
		this.clzLenLt = clzLenLt.toString();
		this.clzLenLte = clzLenLte.toString();
		this.clzLenGt = clzLenGt.toString();
		this.clzLenGte = clzLenGte.toString();
	}

	public String getJsonPrimary() {
		return jsonPrimary;
	}

	public void setJsonPrimary(String jsonPrimary) {
		this.jsonPrimary = jsonPrimary;
	}

	public String getJsonPrimaryEqNull() {
		return jsonPrimaryEqNull;
	}

	public void setJsonPrimaryEqNull(String jsonPrimaryEqNull) {
		this.jsonPrimaryEqNull = jsonPrimaryEqNull;
	}

	public String getJsonPrimaryNeqNull() {
		return jsonPrimaryNeqNull;
	}

	public void setJsonPrimaryNeqNull(String jsonPrimaryNeqNull) {
		this.jsonPrimaryNeqNull = jsonPrimaryNeqNull;
	}

	public String getClzPrimary() {
		return clzPrimary;
	}

	public void setClzPrimary(String clzPrimary) {
		this.clzPrimary = clzPrimary;
	}

	public String getClzPrimaryEqNull() {
		return clzPrimaryEqNull;
	}

	public void setClzPrimaryEqNull(String clzPrimaryEqNull) {
		this.clzPrimaryEqNull = clzPrimaryEqNull;
	}

	public String getClzPrimaryNeqNull() {
		return clzPrimaryNeqNull;
	}

	public void setClzPrimaryNeqNull(String clzPrimaryNeqNull) {
		this.clzPrimaryNeqNull = clzPrimaryNeqNull;
	}

	public String getJsonAttr() {
		return jsonAttr;
	}

	public void setJsonAttr(String jsonAttr) {
		this.jsonAttr = jsonAttr;
	}

	public String getJsonPeqNull() {
		return jsonPeqNull;
	}

	public void setJsonPeqNull(String jsonPeqNull) {
		this.jsonPeqNull = jsonPeqNull;
	}

	public String getJsonPneqNull() {
		return jsonPneqNull;
	}

	public void setJsonPneqNull(String jsonPneqNull) {
		this.jsonPneqNull = jsonPneqNull;
	}

	public String getJsonLenLt() {
		return jsonLenLt;
	}

	public void setJsonLenLt(String jsonLenLt) {
		this.jsonLenLt = jsonLenLt;
	}

	public String getJsonLenLte() {
		return jsonLenLte;
	}

	public void setJsonLenLte(String jsonLenLte) {
		this.jsonLenLte = jsonLenLte;
	}

	public String getJsonLenGt() {
		return jsonLenGt;
	}

	public void setJsonLenGt(String jsonLenGt) {
		this.jsonLenGt = jsonLenGt;
	}

	public String getJsonLenGte() {
		return jsonLenGte;
	}

	public void setJsonLenGte(String jsonLenGte) {
		this.jsonLenGte = jsonLenGte;
	}

	public String getClzAttr() {
		return clzAttr;
	}

	public void setClzAttr(String clzAttr) {
		this.clzAttr = clzAttr;
	}

	public String getClzPeqNull() {
		return clzPeqNull;
	}

	public void setClzPeqNull(String clzPeqNull) {
		this.clzPeqNull = clzPeqNull;
	}

	public String getClzPneqNull() {
		return clzPneqNull;
	}

	public void setClzPneqNull(String clzPneqNull) {
		this.clzPneqNull = clzPneqNull;
	}

	public String getClzLenLt() {
		return clzLenLt;
	}

	public void setClzLenLt(String clzLenLt) {
		this.clzLenLt = clzLenLt;
	}

	public String getClzLenLte() {
		return clzLenLte;
	}

	public void setClzLenLte(String clzLenLte) {
		this.clzLenLte = clzLenLte;
	}

	public String getClzLenGt() {
		return clzLenGt;
	}

	public void setClzLenGt(String clzLenGt) {
		this.clzLenGt = clzLenGt;
	}

	public String getClzLenGte() {
		return clzLenGte;
	}

	public void setClzLenGte(String clzLenGte) {
		this.clzLenGte = clzLenGte;
	}

}
