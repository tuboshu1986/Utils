package com.hb.utils.office;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hb.utils.base.BeanUtils;

/**
 * 数组和对象的映射，将数组的数据映射到T，或将T的数据映射到数组
 * 
 * @date 2018年9月20日 下午4:54:41
 */
public class ExcelDataMapper<T> {
	private Class<T> clazz;
	private List<T> records;
	private String[] headers;
	private List<String[]> arrays;
	private String sheetName;

	public static void main(String[] args) throws Exception {
	}

	public ExcelDataMapper(Class<T> clazz) {
		this.clazz = clazz;
	}

	public ExcelDataMapper(Class<T> clazz, List<T> records, String sheetName) {
		this(clazz);
		this.records = records;
		this.sheetName = sheetName;
	}

	public ExcelDataMapper(Class<T> clazz, String[] headers,
			List<String[]> arrays, String sheetName) {
		this(clazz);
		this.headers = headers;
		this.arrays = arrays;
		this.sheetName = sheetName;
	}

	public void convert() {
		if (records == null)
			arrayToObjs();
		else
			objsToArray();
	}

	private void objsToArray() {
		if (this.getRecords() == null)
			return;
		List<Field> fs = excelFields();
		List<String[]> rst = new ArrayList<String[]>();
		for (T obj : records) {
			String[] arr = new String[fs.size()];
			for (int i = 0; i < fs.size(); i++) {
				arr[i] = BeanUtils.stringValue(obj, fs.get(i));
			}
			rst.add(arr);
		}
		this.arrays = rst;
		this.headers = fieldToHeader(fs);
		this.setDataTypeForSheetName();
	}

	private List<Field> excelFields() {
		List<Field> rst = new ArrayList<Field>();
		List<Field> fs = BeanUtils.getFields(clazz);
		for (Field f : fs) {
			if (f.getAnnotation(ExcelFieldInfo.class) != null) {
				rst.add(f);
			}
		}
		return rst;
	}

	private String[] fieldToHeader(List<Field> fs) {
		String[] headers = new String[fs.size()];
		for (int i = 0; i < fs.size(); i++) {
			headers[i] = BeanUtils.getFieldAnnotation(fs.get(i),
					ExcelFieldInfo.class).name();
		}
		return headers;
	}

	/**
	 * 数组数据转换为对象
	 * 
	 * @date 2018年9月21日
	 */
	private void arrayToObjs() {
		if (this.getHeaders() == null || this.arrays == null)
			return;
		Map<Integer, Field> mapper = headerMapper();
		List<T> rst = new ArrayList<T>();
		for (String[] strs : arrays) {
			T obj = rowToObj(strs, mapper);
			if (obj == null)
				continue;
			rst.add(obj);
		}
		this.records = rst;
		this.setDataTypeForObj();
	}

	private void setDataTypeForSheetName() {
		ExcelSheetInfo sheetInfo = clazz.getAnnotation(ExcelSheetInfo.class);
		if (!StringUtils.isEmpty(sheetName)
				|| sheetInfo.sheetName().length == 0)
			return;
		sheetName = sheetInfo.sheetName()[0];
	}

	private void setDataTypeForObj() {
		ExcelSheetInfo sheetInfo = clazz.getAnnotation(ExcelSheetInfo.class);
		Field f = BeanUtils.getField(clazz, sheetInfo.dataTypeFieldName());
		if (StringUtils.isEmpty(sheetInfo.dataTypeFieldName())
				|| StringUtils.isEmpty(sheetName) || f == null)
			return;
		String mapName = mappedDataType(sheetInfo, sheetName);
		if (mapName != null) {
			setRecordsVal(f, mapName);
		} else {
			setRecordsVal(f, sheetName);
		}
	}

	private String mappedDataType(ExcelSheetInfo sheetInfo, String name) {
		String[] dtMapper = sheetInfo.dataTypeMapper();
		for (int i = 0; i < dtMapper.length; i += 2) {
			if (name.equals(dtMapper[i])) {
				return dtMapper[i + 1];
			}
		}
		return null;
	}
	private void setRecordsVal(Field f, Object val) {
		if (this.getRecords() != null) {
			for (T obj : this.getRecords()) {
				try {
					f.set(obj, val);
				} catch (Exception e) {
				}
			}
		}
	}

	private T rowToObj(String[] arr, Map<Integer, Field> mapper) {
		T obj = newObj();
		if (obj == null)
			return null;
		for (int i = 0; i < arr.length; i++) {
			try {
				Field f = mapper.get(i);
				if (f == null)
					continue;
				BeanUtils.setValue(obj, f, arr[i]);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return obj;
	}

	private T newObj() {
		try {
			return this.clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<Integer, Field> headerMapper() {
		Map<Integer, Field> mapper = new HashMap<Integer, Field>();
		List<Field> fs = BeanUtils.getFields(clazz);
		for (Field field : fs) {
			ExcelFieldInfo fieldInfo = BeanUtils.getFieldAnnotation(field,
					ExcelFieldInfo.class);
			if (fieldInfo == null)
				continue;
			for (int i = 0; i < headers.length; i++) {
				String hname = headers[i];
				if (contains(fieldInfo.importName(), hname)) {
					mapper.put(i, field);
					break;
				}
			}
		}
		return mapper;
	}

	private boolean contains(String[] arr, String str) {
		if (arr == null || str == null)
			return false;
		for (String string : arr) {
			if (str.equals(string))
				return true;
		}
		return false;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public List<String[]> getArrays() {
		return arrays;
	}

	public void setArrays(List<String[]> arrays) {
		this.arrays = arrays;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

}
