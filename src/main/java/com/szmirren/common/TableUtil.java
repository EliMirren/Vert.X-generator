package com.szmirren.common;

import java.util.List;

import com.szmirren.models.AttributeCVF;

/**
 * 表工具
 * 
 * @author Mirren
 *
 */
public class TableUtil {

	/**
	 * 找出主键数据类型
	 * 
	 * @param key
	 * @param attr
	 * @return
	 */
	public static String getParmaryKeyType(String key, List<AttributeCVF> attr) {
		if (attr == null) {
			return "Object";
		}
		for (AttributeCVF item : attr) {
			if (item.getConlumn().equalsIgnoreCase(key)) {
				return item.getJavaTypeValue();
			}
		}
		return "Object";
	}

}
