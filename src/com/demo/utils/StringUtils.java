package com.demo.utils;

/**
 * 字符串工具类
 * @author 86152
 *
 */
public class StringUtils {
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	
	public static boolean isEmpty(String str) {
		if(null==str||"".equals(str.trim())) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * 判断是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(null!=str&&"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}

}
