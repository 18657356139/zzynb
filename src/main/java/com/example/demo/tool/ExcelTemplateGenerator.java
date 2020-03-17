package com.example.demo.tool;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelTemplateGenerator {
    private static final Pattern PATTERN = Pattern.compile("#\\{[0-9]+\\}");

    String template;
    InputStream inputStream;
    List<Map<Integer, String>> dataList;


    public ExcelTemplateGenerator(String template, InputStream is) {
        this.template = template;
        this.inputStream = is;
    }

    public String excute(int sheetNum, int startRow, int endRow) {
        List<Integer> dataNoList = parseTemplate();
        dataList = getdata(inputStream, dataNoList, sheetNum, startRow, endRow);
        return generateResultContent();
    }

    private String generateResultContent() {
        Matcher matcher = PATTERN.matcher((template));
        StringBuffer sb = new StringBuffer();
        for (Map<Integer, String> map : dataList) {
            Set<Integer> numbers = map.keySet();
            String temp = template;
            for (int i : numbers) {
                temp = temp.replace("#{" + i + "}", map.get(i));
            }
            sb.append(temp + System.lineSeparator());
        }
        return sb.toString();
    }

    private List<Integer> parseTemplate() {
        Matcher matcher = PATTERN.matcher((template));
        List<Integer> dataNoList = new ArrayList<>();
        while (matcher.find()) {
            String num = matcher.group().replace("#{", "").replace("}", "");
            dataNoList.add(Integer.parseInt(num));
        }
        return dataNoList;
    }

    private List<Map<Integer, String>> getdata(InputStream is, List<Integer> dataNolist,
                                               int sheetnum, int startRow, int endRow) {

        if (dataNolist == null || dataNolist.size() <= 0) {
            System.err.println("Must has dataNolist");
        }
        if (startRow < 0 || endRow < 0) {
            System.err.println("Start row or end row must >=0");
        }
        boolean isEndRow = true;
        if (endRow == 0) {
            isEndRow = false;
        }
        HSSFWorkbook wb = createWorkBook(is);
        List<Map<Integer, String>> resultList = new ArrayList<>();
        HSSFSheet sheet = wb.getSheetAt(sheetnum);
        if (sheet == null) {
            return null;
        }
        int rowNum = 0;
        for (Row row : sheet) {
            if (rowNum < startRow) {
                rowNum++;
                continue;
            }
            if (isEndRow && rowNum > endRow) {
                break;
            }
            Map<Integer, String> dataMap = new HashMap<>();
            for (Integer i : dataNolist) {
                dataMap.put(i, getCellValue(row, i));
            }
            rowNum++;
            resultList.add(dataMap);
        }
        return resultList;
    }

    public HSSFWorkbook createWorkBook(InputStream is) {
        if (is == null) {
            return null;
        }
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    private String getCellValue(Row row, int i) {
        Cell cell = row.getCell(i);
        if (cell == null) {
            return null;
        }
        String cellValue = null;

        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }

}
