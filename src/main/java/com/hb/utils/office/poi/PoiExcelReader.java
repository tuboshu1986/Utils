package com.hb.utils.office.poi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.hb.utils.array.ArrayUtils;

/**
 * 读取excel，poi实线，来自网络
 * 
 * @date 2018年9月19日 下午6:13:38
 */
public class PoiExcelReader {
	private Workbook workbook;
	private DataFormatter formatter;

	public PoiExcelReader(String path) throws Exception {
		FileInputStream inputStream = new FileInputStream(path);
		workbook = WorkbookFactory.create(inputStream);
		formatter = new DataFormatter();
	}

	public PoiExcelReader(InputStream inputStream) throws Exception {
		workbook = WorkbookFactory.create(inputStream);
		formatter = new DataFormatter();
	}

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
		for (Cell cell : row) {
			data.add(formatter.formatCellValue(cell));
		}
		String[] strs = new String[data.size()];
		return data.toArray(strs);
	}

	private Object[] rowToObjectArray(Row row) {
		if (row == null)
			return null;
		List<Object> data = new ArrayList<Object>();
		for (Cell cell : row) {
			data.add(getCellValue(cell));
		}
		return data.toArray();
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

	public static void main(String[] args) throws Exception {
		String path = "C:/Users/dell/Desktop/话单/新建 Microsoft Excel 工作表1.xlsx";
		PoiExcelReader reader = new PoiExcelReader(path);
		System.out.println(ArrayUtils.toString(reader.getObjectHeader(0)));
		List<String[]> data = reader.getStringData(0, 1);
		for (String[] strings : data) {
			System.out.println(ArrayUtils.toString(strings));
		}
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
