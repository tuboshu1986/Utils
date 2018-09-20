package com.hb.utils.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

	/**
	 * 返回注解EntityInfo定义的实体类的名称，如果类型没有EntityInfo修饰则返回""
	 * 
	 * @Title: beanName
	 * @Description:
	 * @param obj
	 * @return String
	 * @throws
	 *
	 * @author 黄泊
	 * @date 2018年9月17日
	 */
	public static <T> String beanName(Object obj) {
		return beanName(obj.getClass());
	};

	/**
	 * 返回注解EntityInfo定义的实体类的名称，如果类型没有EntityInfo修饰则返回""
	 * 
	 * @Title: beanName
	 * @Description:
	 * @param clazz
	 * @return String
	 * @throws
	 *
	 * @author 黄泊
	 * @date 2018年9月17日
	 */
	public static <T> String beanName(Class<T> clazz) {
		EntityInfo beanInfo = clazz.getAnnotation(EntityInfo.class);
		if (beanInfo == null)
			return "";
		return beanInfo.name();
	};

	/**
	 * 获取clazz(包含)至lastSuperClazz(包含)的继承链上的所有class，如果lastSuperClazz为null，
	 * 则取默认值Object.class
	 * 
	 * @Title: getClasses
	 * @Description:
	 * @param clazz
	 * @return List<Class<? extends Object>>
	 * @throws
	 *
	 * @author 黄泊
	 * @date 2018年9月10日
	 */
	public static List<Class<? extends Object>> getClasses(
			Class<? extends Object> clazz,
			Class<? extends Object> lastSuperClazz) {
		List<Class<? extends Object>> rst = new ArrayList<Class<? extends Object>>();
		if (clazz == null)
			return rst;
		if (lastSuperClazz == null)
			lastSuperClazz = Object.class;
		rst.add(clazz);
		while (lastSuperClazz != clazz) {
			clazz = clazz.getSuperclass();
			rst.add(clazz);
		}

		return rst;
	}

	/**
	 * 获取属性的指定类型的注解，若指定类型的注解不存在则返回null
	 * 
	 * @Title: getFieldAnnotation
	 * @Description:
	 * @param f
	 * @param annotationClazz
	 * @return T
	 * @throws
	 *
	 * @author 黄泊
	 * @date 2018年9月10日
	 */
	public static <T extends Annotation> T getFieldAnnotation(Field f,
			Class<? extends T> annotationClazz) {
		if (f == null || annotationClazz == null)
			return null;
		try {
			return f.getAnnotation(annotationClazz);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 获取对象属性的值并转换为字符串返回，如果值为空则返回空字符串""
	 * 
	 * @Title: fieldStringValue
	 * @Description:
	 * @param obj
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @return
	 * @throws ApplicationException
	 *             String
	 * @throws
	 *
	 * @author 黄泊
	 * @date 2018年9月10日
	 */
	public static String fieldStringValue(Object obj, String fieldName) {
		try {
			Field f = getField(obj.getClass(), fieldName);
			Object val = stringValue(obj, f);
			if (val == null) {
				return "";
			} else {
				return val.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 属性的字符串值，如果获取失败返回null
	 * 
	 * @Title: stringValue
	 * @Description:
	 * @param obj
	 * @param f
	 * @return String
	 * @throws
	 *
	 * @author 黄泊
	 * @date 2018年9月17日
	 */
	public static String stringValue(Object obj, Field f) {
		try {
			return f.get(obj).toString();
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 获取类型以及类型所有父类的属性
	 * 
	 * @Title: getFields
	 * @Description:
	 * @param clazz
	 * @return List<Field>
	 * @throws
	 *
	 * @author 黄泊
	 * @date 2018年9月17日
	 */
	public static List<Field> getFields(Class<? extends Object> clazz) {
		List<Field> rst = new ArrayList<Field>();
		List<Class<? extends Object>> lst = getClasses(clazz, null);
		for (Class<? extends Object> c : lst) {
			try {
				Field[] fs = c.getDeclaredFields();
				for (Field f : fs) {
					f.setAccessible(true);
					rst.add(f);
				}
			} catch (Exception e) {
			}
		}
		return rst;
	}

	/**
	 * 从clazz和它的父类中获取属性名name指定的属性
	 * 
	 * @Title: getField
	 * @Description:
	 * @param clazz
	 * @param name
	 * @return Field
	 * @throws
	 *
	 * @author 黄泊
	 * @date 2018年9月10日
	 */
	public static Field getField(Class<? extends Object> clazz, String name) {
		Field rst = null;
		List<Class<? extends Object>> lst = getClasses(clazz, null);
		for (Class<? extends Object> c : lst) {
			try {
				rst = c.getDeclaredField(name);
			} catch (Exception e) {
			}
			if (rst != null) {
				rst.setAccessible(true);
				break;
			}

		}
		return rst;
	}

	public static String logString(Object obj) {
		try {
			StringBuffer rst = new StringBuffer("[");
			List<Class<? extends Object>> lst = getClasses(obj.getClass(), null);
			for (int i = lst.size() - 1; i >= 0; i--) {
				Class<? extends Object> c = lst.get(i);
				Field[] fs = c.getDeclaredFields();
				for (Field f : fs) {
					LogInfo logAnno = getFieldAnnotation(f, LogInfo.class);
					String fname = f.getName();
					if ("id".equals(f.getName()))
						rst.append(fname + "=" + fieldStringValue(obj, fname)
								+ ", ");
					else if (logAnno != null)
						rst.append(logAnno.logName() + "="
								+ fieldStringValue(obj, fname) + ", ");
				}
			}
			rst.append("]");
			return rst.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "日志信息生成失败";
		}
	}

}
