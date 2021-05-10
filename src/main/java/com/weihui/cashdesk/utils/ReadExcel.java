package com.weihui.cashdesk.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author 尹全旺
 */
public class ReadExcel implements Iterator<Object[]> {
    private String excelName;
    private Workbook wb;
    private InputStream is;
    private Sheet sheet;
    private Row row;
    private int curRowNum = 2;
    private int rowNum;
    private int colNum;
    private String[] columnName;
    private int[] runCase;
    private int realRow;
    public static String dataPath;

    /**
     * 可以指定xlsx，sheet
     * @param sheetName
     * @param comm
     */
    public ReadExcel(String sheetName, String... comm) {
        if (comm.length == 0) {
            excelName = dataPath;
        } else if (comm.length == 1) {
            excelName = System.getProperty("user.dir") + "/测试数据/" + comm[0];
        }
        try {
            is = new FileInputStream(excelName);
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheet = wb.getSheet(sheetName);
        runCase = getRunCaseId();
        readExcel();
    }

    /**
     * 读excel
     */
    private void readExcel() {
        row = sheet.getRow(1);
        rowNum = sheet.getPhysicalNumberOfRows();
        colNum = row.getPhysicalNumberOfCells();// 最后3列不读取
        columnName = new String[colNum];
        int count = 0;
        Iterator<Cell> heads = row.cellIterator();
        while (heads.hasNext()) {
            Cell cell = heads.next();
            columnName[count] = cell.getRichStringCellValue().toString();
            count++;
        }
        try {
            is.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 得到待运行case
     * @return
     */
    private int[] getRunCaseId() {
        String[] caseId ;
        int[] runCaseId ;
        row = sheet.getRow(0);
        Cell cell = row.getCell(1);
        if (assertCell(cell)) {
            caseId = cell.getRichStringCellValue().toString().split("\\|");
            runCaseId = new int[caseId.length];
            for (int i = 0; i < caseId.length; i++) {
                runCaseId[i] = Integer.parseInt(caseId[i]) + 1;
            }
        } else {
            runCaseId = new int[1];
            runCaseId[0] = 0;
        }
        return runCaseId;
    }

    private boolean assertCell(Cell cell) {
        boolean ret = false;
        if (cell.getRichStringCellValue().toString() == "") {} 
        else {
            ret = true;
        }
        return ret;
    }

    /**
     * 判断是否还有可迭代对象
     * @return
     */
    @Override
    public boolean hasNext() {
        boolean ret = false;
        int length = runCase.length;
        if (runCase[0] == 0) {
            realRow = this.curRowNum;
            if (this.curRowNum >= rowNum) {
                ret = false;
            } else {
                ret = true;
            }
        } else {
            for (; this.curRowNum - 2 < length; ) {
                realRow = runCase[this.curRowNum - 2];
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * 跌代对象
     * @return
     */
    @Override
    public Object[] next() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Object temp;
        Row row = sheet.getRow(this.realRow);
        int endscols = 3;
        for (int i = 0; i < colNum - endscols; i++) {
            Cell cell = row.getCell(i);
            vaildCellType(cell);
            if (assertCell(cell)) {
                temp = row.getCell(i).getRichStringCellValue().toString();
                map.put(columnName[i], temp);
            }
        }
        this.curRowNum++;
        Object[] object = new Object[1];
        object[0] = map;
        return object;

    }
    /**
     * 检验cell类型 如果不是String 设置为String类型
     * @return
     */

    public static void vaildCellType(Cell cell){
        if(cell.getCellType()!=Cell.CELL_TYPE_STRING){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }else {

        }
    }




    @Override
    public void remove() {
    }
}
