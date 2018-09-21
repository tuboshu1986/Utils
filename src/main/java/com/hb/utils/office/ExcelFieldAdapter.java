package com.hb.utils.office;

public abstract class ExcelFieldAdapter {
	/**
	 * 传入值obj，转换，将结果存储在result中，result不得为空，如果转换成功返回true，否则返回false
	 */
	public abstract boolean adapter(Object obj, Object result);
}
