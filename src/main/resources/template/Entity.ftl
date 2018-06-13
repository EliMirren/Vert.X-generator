package ${content.entity.classPackage};

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;

/**
 * ${content.entity.tableName}实体类
 * 
 * @author 
 *
 */
public class ${content.entity.className} {
	<#list content.entity.attrs as item> 
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>
	/**
	 * 实例化
	 */
	public ${content.entity.className!}() {
		super();
	}
	/**
	 * 实例化
	 * 
	 * @param obj
	 */
	public ${content.entity.className!}(JsonObject obj) {
		this();
		<#list content.entity.attrs as item> 
		<#if item.javaType  == "String">
		if (obj.getValue("${item.field}") instanceof String) {
			this.${item.fset}((String) obj.getValue("${item.field}"));
		}
		<#elseif item.javaType  ==  "Integer">
		if (obj.getValue("${item.field}") instanceof Number) {
			this.${item.fset}(((Number) obj.getValue("${item.field}")).intValue());
		}
		<#elseif item.javaType  ==  "Long">
		if (obj.getValue("${item.field}") instanceof Number) {
			this.${item.fset}(((Number) obj.getValue("${item.field}")).longValue());
		}
		<#elseif item.javaType  ==  "Double">
		if (obj.getValue("${item.field}") instanceof Number) {
			this.${item.fset}(((Number) obj.getValue("${item.field}")).doubleValue());
		}
		<#else>
		this.${item.fset}(obj.getValue("${item.field}"));
		</#if>
		</#list>
	}
	/**
	 * 实例化
	 * 
	 * @param params
	 */
	public ${content.entity.className!}(MultiMap params) {
		this();
		<#list content.entity.attrs as item> 
		<#if item.javaType  ==  "Integer">
		this.${item.fset}(new Integer(params.get("${item.field}")));
		<#elseif item.javaType  ==  "Long">
		this.${item.fset}(new Long(params.get("${item.field}")));
		<#elseif item.javaType  ==  "Double">
		this.${item.fset}(new Double(params.get("${item.field}")));
		<#else>
		this.${item.fset}(params.get("${item.field}"));
		</#if>
		</#list>
	}
	<#list content.entity.attrs as item> 
	
	/**
	 * 获取${item.field}
	 * 
	 * @return
	 */
	public ${item.javaType} ${item.fget}() {
		return ${item.field};
	}

	/**
	 * 设置${item.field}
	 * 
	 * @param ${item.field}
	 */
	public void setTableItem(${item.javaType} ${item.field}) {
		this.${item.field} = ${item.field};
	}
	</#list>

	@Override
	public String toString() {
		return "${content.entity.className!} [<#list content.entity.attrs as item>${item.field}=" + ${item.field} + " <#if item?has_next>,</#if> </#list>]";
	
	}
	
	
}
