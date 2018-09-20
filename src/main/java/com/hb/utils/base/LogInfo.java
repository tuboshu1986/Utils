package com.hb.utils.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如果属性加上该注解，则属性应当包含在日志之中
 * 
 * @date 2018年9月10日 下午5:11:41
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface LogInfo
{
    /**
     * 属性中文名称
     */
    public String logName();
}
