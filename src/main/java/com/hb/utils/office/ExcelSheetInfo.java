package com.hb.utils.office;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述类对应的excel的sheet的信息
 * 
 * @date 2018年9月20日 下午4:45:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExcelSheetInfo {
	int sheetIndex() default -1;
	/**
	 * 对应sheet的名称
	 * 
	 * @date 2018年9月20日
	 */
	String[] sheetName();

	/**
	 * 如果sheet的名称是数据类型，则将sheet的名称存于所修饰的类型的此属性中
	 * 
	 * @date 2018年9月21日
	 */
	String dataTypeFieldName() default "";

	/**
	 * sheet的名称和数据类型映射关系，偶数项的值映射为随后的基数项的值，如：["aaa-1","a","aaa-2","a","bbb","b"]
	 * 表示"aaa-1"和"aaa-2"映射为"a"，"bbb"映射为"b"
	 * 
	 * @date 2018年9月21日
	 */
	String[] dataTypeMapper() default {};
}
