package com.szmirren.common;

import java.text.MessageFormat;
import java.util.List;

import com.szmirren.models.AttributeCVF;
import com.szmirren.models.ClassConfig;
import com.szmirren.models.EntityAttribute;

/**
 * 生成工具
 * 
 * @author Mirren
 *
 */
public class ClassUtil {
	private ClassUtil() {
	};

	/**
	 * 获得字符串String
	 * 
	 * @return
	 */
	public static String getEntityStr(EntityAttribute attr) {
		return makeEntityToString(attr);
	}

	/**
	 * 获得实体类,属性列表包名,实体类名,属性数据集合下标0属性类型,1位属性名字
	 * 
	 * @param packageName
	 * @param entityName
	 * @param property
	 * @return
	 */
	private static String makeEntityToString(EntityAttribute attr) {
		String packageName = attr.getEntityPackage();
		List<String> importPackages = attr.getImportPackages();
		String entityName = attr.getEntityName();
		List<AttributeCVF> attrcvf = attr.getAttrs();
		ClassConfig config = attr.getConfig();

		StringBuffer buffer = new StringBuffer();
		buffer.append("package " + packageName + ";\r\n\r\n");
		buffer.append(getImport(importPackages));
		if (config.isSeriz()) {
			buffer.append(
					"public class " + entityName + " implements java.io.Serializable {\r\n    private static final long serialVersionUID = 1L;\r\n");
		} else {
			buffer.append("public class " + entityName + " {\r\n");
		}
		buffer.append(getProperty(attrcvf, config.isComment()));
		if (config.isConstruct()) {
			buffer.append(getConstr(entityName));
		}
		if (config.isConstructJson()) {
			buffer.append(getConstrJson(entityName, attrcvf));
		}
		if (config.isConstructAll()) {
			buffer.append(getConstrAll(entityName, attrcvf));
		}
		if (config.isGetAndSet()) {
			buffer.append(getGetSet(attrcvf));
		}
		if (config.isTojson()) {
			buffer.append(getToJson());
			buffer.append(getToJson(attrcvf));
		}
		if (config.isFormJson()) {
			buffer.append(getFromJson(entityName));
		}
		if (config.isFormMultiMap()) {
			buffer.append(getFromMultiMap(attr.getHistoryConfig().getAssistPackage(), entityName, attrcvf));
		}

		buffer.append("{*addEntity*}\r\n}\r\n");
		return buffer.toString();
	}

	/**
	 * 获得导入空间的字符串
	 * 
	 * @param importSpaces
	 * @return
	 */
	private static String getImport(List<String> importPackages) {
		if (importPackages == null || importPackages.size() == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		for (String str : importPackages) {
			result.append("import " + str + ";\r\n");
		}
		return result.toString();
	}

	/**
	 * 得到属性,
	 * 
	 * @param attrcvf
	 *          表格参数
	 * @param createComment
	 *          是否创建注释
	 * @return
	 */
	private static String getProperty(List<AttributeCVF> attrcvf, boolean createComment) {
		if (attrcvf == null || attrcvf.size() == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		for (AttributeCVF attr : attrcvf) {
			if (!attr.getCheck()) {
				continue;
			}
			result.append("    private " + attr.getJavaTypeValue() + " " + attr.getPropertyName() + ";");
			if (attr.getComment() != null && !("".equals(attr.getComment()))) {
				result.append("//" + attr.getComment() + "\r\n");
			} else {
				result.append("\r\n");
			}
		}
		return result.toString();
	}

	/**
	 * 获得无参构造方法
	 * 
	 * @param cname
	 * @return
	 */
	private static String getConstr(String entityName) {
		return "    public " + entityName + "() {\r\n        super();\r\n    }\r\n";
	}

	/**
	 * 获得json构造方法
	 * 
	 * @param entityName
	 * @return
	 */
	private static String getConstrJson(String entityName, List<AttributeCVF> attrcvf) {
		if (attrcvf == null || attrcvf.size() == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer(String.format("    public %s(JsonObject obj) {\r\n        super();\r\n", entityName));
		for (AttributeCVF attr : attrcvf) {
			if (!attr.getCheck()) {
				continue;
			}
			String temp;
			if (attr.getJavaTypeValue().equalsIgnoreCase("java.time.Instant")) {
				temp = "        this.{0} = obj.getInstant(\"{0}\");\r\n";
			} else if (attr.getJavaTypeValue().equalsIgnoreCase("Double")) {
				temp = "        this.{0} = obj.getDouble(\"{0}\");\r\n";
			} else if (attr.getJavaTypeValue().equalsIgnoreCase("Float")) {
				temp = "        this.{0} = obj.getFloat(\"{0}\");\r\n";
			} else if (attr.getJavaTypeValue().equalsIgnoreCase("Integer")) {
				temp = "        this.{0} = obj.getInteger(\"{0}\");\r\n";
			} else if (attr.getJavaTypeValue().equalsIgnoreCase("Long")) {
				temp = "        this.{0} = obj.getLong(\"{0}\");\r\n";
			} else if (attr.getJavaTypeValue().equalsIgnoreCase("String")) {
				temp = "        this.{0} = obj.getString(\"{0}\");\r\n";
			} else if (attr.getJavaTypeValue().equalsIgnoreCase("JsonObject")) {
				temp = "        this.{0} = obj.getJsonObject(\"{0}\");\r\n";
			} else {
				temp = "        this.{0} = obj.getValue(\"{0}\");\r\n";
			}
			result.append(MessageFormat.format(temp, attr.getPropertyName()));
		}
		result.append("    }\r\n");
		return result.toString();
	}

	/**
	 * 获得带参构造方法;
	 * 
	 * @param str
	 */
	private static String getConstrAll(String entityName, List<AttributeCVF> attrcvf) {
		if (attrcvf == null || attrcvf.size() == 0) {
			return "";
		}
		StringBuffer result = null;
		for (AttributeCVF attr : attrcvf) {
			if (!attr.getCheck()) {
				continue;
			}
			if (result == null) {
				result = new StringBuffer("    public " + entityName + "(");
				result.append(attr.getJavaTypeValue() + " " + attr.getPropertyName());
			} else {
				result.append("," + attr.getJavaTypeValue() + " " + attr.getPropertyName());
			}
		}
		boolean isFrist = true;
		for (AttributeCVF attr : attrcvf) {
			if (!attr.getCheck()) {
				continue;
			}
			if (isFrist) {
				result.append(") {\r\n        super();\r\n");
				result.append("        this." + attr.getPropertyName() + " = " + attr.getPropertyName() + ";\r\n");
				isFrist = false;
			} else {
				result.append("        this." + attr.getPropertyName() + " = " + attr.getPropertyName() + ";\r\n");
			}
		}
		result.append("    }\r\n");
		return result.toString();
	}

	/**
	 * 获得get与set
	 * 
	 * @param str
	 * @return
	 */
	private static String getGetSet(List<AttributeCVF> attrcvf) {
		if (attrcvf == null || attrcvf.size() == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		for (AttributeCVF attr : attrcvf) {
			if (!attr.getCheck()) {
				continue;
			}
			result.append(MessageFormat.format("    public {0} get{1}() '{'\r\n        return this.{2};\r\n    }\r\n\r\n",
					attr.getJavaTypeValue(), StrUtil.fristToUpCase(attr.getPropertyName()), attr.getPropertyName()));
			result.append(MessageFormat.format("    public void set{0}({1} {2}) '{'\r\n", StrUtil.fristToUpCase(attr.getPropertyName()),
					attr.getJavaTypeValue(), attr.getPropertyName()));
			result.append(MessageFormat.format("        this.{0} = {0};\r\n    }\r\n\r\n", attr.getPropertyName()));
		}
		return result.toString();
	}

	/**
	 * 获得toJsonStr方法
	 * 
	 * @return
	 */
	private static String getToJson() {
		StringBuffer result = new StringBuffer(
				"    /**\r\n     * 将本类转换为Json字符串 \r\n     * @return\r\n     */\r\n   public String toJsonStr(){\r\n");
		result.append("        return toJson().toString();\r\n    }\r\n\r\n");
		return result.toString();
	}

	/**
	 * 获得toJson方法
	 * 
	 * @param attrcvf
	 * @return
	 */
	private static String getToJson(List<AttributeCVF> attrcvf) {
		if (attrcvf == null || attrcvf.size() == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer(
				"    /**\r\n     * 将本类转换为JsonObject \r\n     * @return\r\n     */\r\n   public JsonObject toJson(){\r\n");
		result.append("        JsonObject result = new JsonObject();\r\n");
		for (AttributeCVF attr : attrcvf) {
			if (!attr.getCheck()) {
				continue;
			}
			result.append(MessageFormat.format("        result.put(\"{0}\",this.{0});\r\n", attr.getPropertyName()));
		}
		result.append("    return result;\r\n    }\r\n\r\n");
		return result.toString();
	}

	/**
	 * 获得fromJson方法
	 * 
	 * @param entityName
	 * @return
	 */
	private static String getFromJson(String entityName) {
		return MessageFormat.format(
				"    /**\r\n     * 通过json字符串创建一个类 \r\n     * @return\r\n     */\r\n    public static {0} fromJson(String jsonStr)'{'\r\n         return Json.decodeValue(jsonStr, {0}.class);\r\n    }\r\n\r\n",
				entityName);
	}

	/**
	 * 获得fromMultiMap方法
	 * 
	 * @param attr
	 * @return
	 */
	private static String getFromMultiMap(String commomPackage, String entityName, List<AttributeCVF> attrcvf) {
		StringBuffer result = new StringBuffer("    /**\r\n    * 通过MultiMap初始化一个对象\r\n    * @param params\r\n    * @return\r\n    */\r\n");
		result.append("    public static " + entityName + " fromMultiMap(io.vertx.core.MultiMap params) {\r\n");
		result.append(MessageFormat.format("    {0} obj = new {0}();\r\n", entityName));
		for (AttributeCVF attr : attrcvf) {
			String patn;
			if (JavaType.isInteger(attr.getJavaTypeValue())) {
				patn = "    obj.set{0}({2}.StringUtil.getInteger(params.get(\"{1}\")));\r\n";
			} else if (JavaType.isDouble(attr.getJavaTypeValue())) {
				patn = "    obj.set{0}({2}.StringUtil.getDouble(params.get(\"{1}\")));\r\n";
			} else if (JavaType.isLong(attr.getJavaTypeValue())) {
				patn = "    obj.set{0}({2}.StringUtil.getLong(params.get(\"{1}\")));\r\n";
			} else if (JavaType.isDate(attr.getJavaTypeValue())) {
				patn = "    obj.set{0}({2}.StringUtil.getDate(params.get(\"{1}\")));\r\n";
			} else if (attr.getJavaTypeValue().indexOf("Instant") >= 0) {
				patn = "    obj.set{0}({2}.StringUtil.getInstant(params.get(\"{1}\")));\r\n";
			} else if (attr.getJavaTypeValue().indexOf("JsonObject") >= 0) {
				patn = "    obj.set{0}({2}.StringUtil.getJsonObject(params.get(\"{1}\")));\r\n";
			} else if (attr.getJavaTypeValue().indexOf("JsonArray") >= 0) {
				patn = "    obj.set{0}({2}.StringUtil.getJsonArray(params.get(\"{1}\")));\r\n";
			} else {
				patn = "    obj.set{0}(params.get(\"{1}\"));\r\n";
			}
			result.append(MessageFormat.format(patn, StrUtil.fristToUpCase(attr.getPropertyName()), attr.getPropertyName(), commomPackage));
		}
		result.append("    return obj;\r\n");
		result.append("    }\r\n");
		return result.toString();
	}

}
