package com.hb.utils.office;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hb.utils.base.BeanUtils;

public class CommonExportExcel
{

    /**
     * 对象转数组
     * 
     * @Title: toStringArr
     * @Description:
     * @param data
     * @return List<String[]>
     * @throws
     *
     * @author 黄泊
     * @date 2018年9月17日
     */
    public static <T> List<String[]> toStringArr(List<T> data)
    {
        List<String[]> rst = new ArrayList<String[]>();
        if (data == null || data.size() == 0)
            return rst;

        LinkedHashMap<Field, String> header = getExcelHeaderMap(data.get(0));
        for (T obj : data)
        {
            String[] recordDataArr = new String[header.size()];
            int index = 0;
            Iterator<Field> iterator = header.keySet().iterator();
            while (iterator.hasNext())
            {
                Field f = iterator.next();
                recordDataArr[index++] = BeanUtils.stringValue(obj, f);
            }
            rst.add(recordDataArr);
        }

        return rst;
    }

    /**
     * 获取excel头部
     * 
     * @Title: header
     * @Description:
     * @param t
     * @return String[]
     * @throws
     *
     * @author 黄泊
     * @date 2018年9月17日
     */
    public static String[] header(Object t)
    {
        LinkedHashMap<Field, String> headerMap = getExcelHeaderMap(t);
        int index = 0;
        String[] rst = new String[headerMap.size()];
        Iterator<Field> iterator = headerMap.keySet().iterator();
        while (iterator.hasNext())
        {
            Field f = iterator.next();
            rst[index++] = headerMap.get(f);
        }
        return rst;
    }

    /**
     * 获取类型属性与excel列名的映射
     * 
     * @Title: getExcelHeader
     * @Description:
     * @param clazz
     * @return Map<String,String> key为属性名，value为excel列名
     * @throws
     *
     * @author 黄泊
     * @date 2018年9月17日
     */
    public static LinkedHashMap<Field, String> getExcelHeaderMap(Object t)
    {
        Class<? extends Object> clazz = t.getClass();
        LinkedHashMap<Field, String> rst = new LinkedHashMap<Field, String>();
        List<Field> fs = BeanUtils.getFields(clazz);
        for (Field f : fs)
        {
            ExcelFieldInfo efi = BeanUtils.getFieldAnnotation(f, ExcelFieldInfo.class);
            if (efi == null)
                continue;
            rst.put(f, efi.name());
        }
        return rst;
    }

    /**
     * 生成导出excel的流
     * 
     * @param titles sheet数组
     * @param headerList 列名数组
     * @param dataList 正文数组
     */
    public static <T> InputStream exportExcel(List<T> data, String title)
    {
        List<String> titles = new ArrayList<String>();
        List<String[]> headerList = new ArrayList<>();
        List<List<String[]>> dataList = new ArrayList<List<String[]>>();

        if (data == null || data.size() == 0)
        {
            titles.add("无数据");
            headerList.add(new String[]{ "无数据" });
            dataList.add(Arrays.asList(new String[][] { new String[] { "无数据" } }));
        }
        else
        {
            titles.add(title);
            headerList.add(header(data.get(0)));
            dataList.add(toStringArr(data));
        }

        return new CommonExportExcel().exportExcel(titles, headerList, dataList);
    }

    /**
     * 生成导出excel的流
     * 
     * @param titles sheet数组
     * @param headerList 列名数组
     * @param dataList 正文数组
     */
    public InputStream exportExcel(List<String> titles, List<String[]> headerList, List<List<String[]>> dataList)
    {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        for (int i = 0; i < titles.size(); i++)
        {
            String title = titles.get(i);
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet(title);

            // 产生表格标题行
            String[] headers = headerList.get(i);
            HSSFRow row = sheet.createRow(0);
            for (int j = 0; j < headers.length; j++)
            {
                HSSFCell cell = row.createCell(j);
                HSSFRichTextString text = new HSSFRichTextString(headers[j]);
                cell.setCellValue(text);
            }

            List<String[]> dataset = dataList.get(i);
            for (int j = 0; j < dataset.size(); j++)
            {
                row = sheet.createRow(j + 1);
                String[] records = dataset.get(j);
                for (int k = 0; k < records.length; k++)
                {
                    HSSFCell cell = row.createCell(k);
                    HSSFRichTextString text = new HSSFRichTextString(records[k]);
                    cell.setCellValue(text);
                }
            }
        }

        InputStream inputStream = null;

        try
        {
            workbook.write(out);
            inputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 生成导出excel的流
     * 
     * @param titles sheet数组
     * @param headerList 列名数组
     * @param dataList 正文数组
     */
    public InputStream exportExcelXlsx(List<String> titles, List<String[]> headerList, List<List<String[]>> dataList)
    {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 声明一个工作薄
        Workbook workbook = new XSSFWorkbook();

        for (int i = 0; i < titles.size(); i++)
        {
            String title = titles.get(i);
            // 生成一个表格
            Sheet sheet = workbook.createSheet(title);

            // 产生表格标题行
            String[] headers = headerList.get(i);
            Row row = sheet.createRow(0);
            for (int j = 0; j < headers.length; j++)
            {
                Cell cell = row.createCell(j);
                RichTextString text = new XSSFRichTextString(headers[j]);
                cell.setCellValue(text);
            }

            List<String[]> dataset = dataList.get(i);
            for (int j = 0; j < dataset.size(); j++)
            {
                row = sheet.createRow(j + 1);
                String[] records = dataset.get(j);
                for (int k = 0; k < records.length; k++)
                {
                    Cell cell = row.createCell(k);
                    RichTextString text = new XSSFRichTextString(records[k]);
                    cell.setCellValue(text);
                }
            }
        }

        InputStream inputStream = null;

        try
        {
            workbook.write(out);
            inputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return inputStream;
    }
}
