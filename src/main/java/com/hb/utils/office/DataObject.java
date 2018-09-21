package com.hb.utils.office;

@ExcelSheetInfo(sheetName = {"QQ邮箱", "QQ群", "QQ号码"}, 
	dataTypeFieldName="dt", 
	dataTypeMapper = {})
public class DataObject {
	@ExcelFieldInfo(importName = {"年份", "年度"}, name = "年度")
	private String val1;
	@ExcelFieldInfo(importName = {"时间", "结果反馈时间"}, name = "结果反馈时间")
	private String val2;
	@ExcelFieldInfo(importName = {"qq号码", "号码"}, name = "号码")
	private String val3;
	@ExcelFieldInfo(importName = {"来源单位"}, name = "来源单位")
	private String val4;
	
	private String dt;

	public DataObject(){
		
	}
	
	public DataObject(String val1, String val2, String val3, String val4) {
		super();
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
		this.val4 = val4;
	}

	@Override
	public String toString() {
		return "DataObject [val1=" + val1 + ", val2=" + val2 + ", val3=" + val3
				+ ", val4=" + val4 + ", dt=" + dt + "]";
	}

	public String getVal1() {
		return val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	public String getVal2() {
		return val2;
	}
	public void setVal2(String val2) {
		this.val2 = val2;
	}
	public String getVal3() {
		return val3;
	}
	public void setVal3(String val3) {
		this.val3 = val3;
	}
	public String getVal4() {
		return val4;
	}
	public void setVal4(String val4) {
		this.val4 = val4;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

}
