package com.demo.utils;

/**
 * �ַ���������
 * @author 86152
 *
 */
public class StringUtils {
	/**
	 * �ж��Ƿ�Ϊ��
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
	 * �ж��Ƿ�Ϊ��
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
