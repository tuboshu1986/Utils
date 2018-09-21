package com.hb.utils.office;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 读取excel，poi实线，来自网络
 * 
 * @date 2018年9月19日 下午6:13:38
 */
public class PoiExcelReader {
	private Workbook workbook;
	private DataFormatter formatter;

	public static void main(String[] args) throws Exception {
		String path = "C:/Users/dell/Desktop/查询登记表（样本）.xlsx";
		PoiExcelReader reader = new PoiExcelReader(path);
		for (DataObject obj : reader.toObjs(DataObject.class)) {
			System.out.println(obj);
		}
	}

	public PoiExcelReader(String path) throws Exception {
		FileInputStream inputStream = new FileInputStream(path);
		workbook = WorkbookFactory.create(inputStream);
		formatter = new DataFormatter();
	}

	public PoiExcelReader(InputStream inputStream) throws Exception {
		workbook = WorkbookFactory.create(inputStream);
		formatter = new DataFormatter();
	}

	public <T> List<T> toObjs(Class<T> clazz) {
		List<T> rst = new ArrayList<T>();
		List<Sheet> sheets = getSheetByClass(clazz);
		for (Sheet s : sheets) {
			String[] headers = this.getStringHeader(workbook.getSheetIndex(s));
			List<String[]> data = this.getStringData(workbook.getSheetIndex(s),
					1);

			ExcelDataMapper<T> dataMapper = new ExcelDataMapper<T>(clazz,
					headers, data, s.getSheetName());
			dataMapper.convert();

			rst.addAll(dataMapper.getRecords());
		}

		return rst;
	}

	/**
	 * 获取指定sheet(0表示第一个sheet)中自limit(包含，0表示第一行)行开始的所有数据，如果没有符合条件的数据则返回null
	 * 
	 * @date 2018年9月20日
	 */
	public List<String[]> getStringData(int sheetIndex, int limit) {
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		int lastRowNum = sheet.getLastRowNum();
		if (lastRowNum < limit) {
			return null;
		}
		List<String[]> sheetData = new ArrayList<String[]>();
		for (int i = limit; i < lastRowNum; i++) {
			sheetData.add(rowToStringArray(sheet.getRow(i)));
		}
		return sheetData;
	}

	private List<Sheet> getSheetByClass(Class<? extends Object> clazz) {
		List<Sheet> rst = new ArrayList<Sheet>();
		ExcelSheetInfo anno = clazz.getAnnotation(ExcelSheetInfo.class);
		if (anno != null) {
			for (String name : anno.sheetName()) {
				Sheet sheet = workbook.getSheet(name);
				if (sheet != null)
					rst.add(sheet);
			}
		}
		return rst;
	}

	public String[] getStringHeader(int sheetIndex) {
		return rowToStringArray(workbook.getSheetAt(sheetIndex).getRow(0));
	}

	public Object[] getObjectHeader(int sheetIndex) {
		return rowToObjectArray(workbook.getSheetAt(sheetIndex).getRow(0));
	}

	private String[] rowToStringArray(Row row) {
		if (row == null)
			return null;
		List<String> data = new ArrayList<String>();
		int lastCellIdx = row.getLastCellNum();
		for (int i = 0; i < lastCellIdx; i++) {
			Cell cell = row.getCell(i);
			data.add(cell == null ? null : formatter.formatCellValue(cell));
		}
		String[] strs = new String[data.size()];
		return trim(data.toArray(strs));
	}

	private Object[] rowToObjectArray(Row row) {
		if (row == null)
			return null;
		List<Object> data = new ArrayList<Object>();
		int lastCellIdx = row.getLastCellNum();
		for (int i = 0; i < lastCellIdx; i++) {
			Cell cell = row.getCell(i);
			data.add(cell == null ? null : getCellValue(cell));
		}
		return data.toArray();
	}

	private String[] trim(String[] strs) {
		if (strs == null || strs.length == 0)
			return strs;
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			if (!StringUtils.isEmpty(str)) {
				strs[i] = str.trim();
			}
		}
		return strs;
	}

	private Object getCellValue(Cell cell) {
		Object val = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING :// 字符串型
				val = cell.getRichStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC :// 数值型
				// 如果是date类型
				if (DateUtil.isCellDateFormatted(cell)) {
					val = cell.getDateCellValue();
				} else {// 纯数字
					val = cell.getNumericCellValue();
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN :// 布尔
				val = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA :// 公式型
				val = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_BLANK :// 空值
				break;
			case Cell.CELL_TYPE_ERROR : // 故障
				break;
		}
		return val;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public DataFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(DataFormatter formatter) {
		this.formatter = formatter;
	}

}
