package com.hb.utils.office;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解表示属性可以导出至excel，且属性导出时的名称
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExcelFieldInfo
{
    String name();
    /**
     * 对应的导入excel表头名称
     * @date 2018年9月20日
     */
    String[] importName();
    
    String adapterClassName() default "";
}
