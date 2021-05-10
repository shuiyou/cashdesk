package com.weihui.cashdesk.utils;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

    private static int caseNum = 1;
    private static String path = System.getProperty("user.dir") + "/test-output/TestDetails.xlsx";
    private static String currentDir = System.getProperty("user.dir");
    public static String jira;
    public static String jiraName;
    public static String userName;

    public static void generateTestOutput() {
        try {
            ZipCompressor.delAllFile(currentDir + "/test-output");
            File file;
            file = new File(currentDir + "/test-output");
            file.mkdir();
            file = new File(currentDir + "/TestZip");
            file.mkdir();
            file = new File(currentDir + "/test-output/失败截图");
            file.mkdir();
            createExcel(path);
            testInfo(path);
            testDetails(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setInfo(String[] str) throws IOException {
        FileInputStream is = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(is);
        OutputStream out = new FileOutputStream(path);
        String passTotal = TestNGListener.successNum + "";
        String failTotal = TestNGListener.failNum + "";
        String caseTotal = TestNGListener.successNum + TestNGListener.skipNum + TestNGListener.failNum + "";
        String[] title;
        title = new String[]{str[0], caseTotal};
        setData(wb, 2, title);

        title = new String[]{str[1], passTotal};
        setData(wb, 3, title);

        title = new String[]{str[2], failTotal};
        setData(wb, 4, title);

        title = new String[]{TestNGListener.startTime, TestNGListener.endTime};
        setData(wb, 5, title);

        wb.write(out);
        out.flush();
        out.close();
        wb.close();
        is.close();

    }

    private static void setData(Workbook wb, int rowNum, String[] data) {
        Sheet sheet = wb.getSheet("测试概况");
        CellStyle cellStyle = setCellstyle(wb, HorizontalAlignment.CENTER, "宋体", (short) 12, IndexedColors.WHITE.getIndex());
        Row row = sheet.getRow(rowNum);
        for (int i = 1; i < data.length + 1; i++) {
            if (rowNum == 3) {
                Cell cell = row.getCell(5);
                cell.setCellStyle(cellStyle);
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数 
                int caseTotal = TestNGListener.successNum + TestNGListener.skipNum + TestNGListener.failNum;//   
                String num = "0.00%";
                if (caseTotal != 0) {
                    num = df.format((float) TestNGListener.successNum / (caseTotal) * 100) + "%";//返回的是String类型
                }
                cell.setCellValue(num);
            }
            Cell cell = row.getCell(i * 2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(data[i - 1]);
        }

    }

    public static void setCaseRet(HashMap<String, String> hashMap) throws IOException {
        FileInputStream is = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(is);
        OutputStream out = new FileOutputStream(path);
        Sheet sheet = wb.getSheet("测试详情");
        Cell cell;
        CellStyle cellStyle;
        Row row = sheet.createRow(caseNum);
        caseNum++;

        String caseID = hashMap.get("用例编号");
        String caseType = hashMap.get("用例场景");
        String expect = hashMap.get("预期结果");
        String outTradeOrder = "";
        String caseRet = "FALSE";
        if (TestNGListener.passCaseRet) {
            caseRet = "PASS";
        }
        if (hashMap.containsKey("订单号")) {
            outTradeOrder = hashMap.get("订单号");
            hashMap.remove("订单号");
        }
        hashMap.remove("用例编号");
        hashMap.remove("用例场景");
        hashMap.remove("预期结果");
        String errorLog = " ";
        if (ReportUtil.logRecord != null) {
            int i = 1;
            for (String log : ReportUtil.logRecord) {
                errorLog += i + "、" + log + "\n";
                i++;
            }
            ReportUtil.logRecord.clear();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String key : hashMap.keySet()) {
            String value = key + "=" + hashMap.get(key) + ", ";
            stringBuffer.append(value);
        }
        String caseData = stringBuffer.substring(0, stringBuffer.length() - 2);
        String[] title = {caseID, caseType, caseData, expect, outTradeOrder, caseRet, errorLog};
        cellStyle = setCellstyle(wb, HorizontalAlignment.CENTER, "宋体", (short) 11, IndexedColors.WHITE.getIndex());
        for (int i = 0; i < title.length; i++) {
            if (title[i] == "FALSE") {
                cellStyle = setCellstyle(wb, HorizontalAlignment.CENTER, "宋体", (short) 11, IndexedColors.RED.getIndex());
            }
            if (i == title.length - 1) {
                cellStyle = setCellstyle(wb, HorizontalAlignment.LEFT, "宋体", (short) 11, IndexedColors.WHITE.getIndex());
            }
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title[i]);

        }


        wb.write(out);
        out.flush();
        out.close();
        wb.close();
        is.close();
    }


    private static void createExcel(String path) throws IOException {
        Workbook wb = new XSSFWorkbook();//创建工作簿对象
        wb.createSheet("测试概况");
        wb.createSheet("测试详情");
        FileOutputStream fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        wb.close();
        fileOut.close();
    }


    private static void testInfo(String path) throws IOException {
        FileInputStream is = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(is);
        OutputStream out = new FileOutputStream(path);
        Sheet sheet = wb.getSheet("测试概况");
        CellRangeAddress cra;
        Row row;
        Cell cell;
        CellStyle cellStyle;

        row = sheet.createRow(0);
        row.setHeight((short) 600);
        cellStyle = setCellstyle(wb, HorizontalAlignment.CENTER, "黑体", (short) 17, IndexedColors.WHITE.getIndex());//设置格式
        cra = new CellRangeAddress(0, 0, 0, 5);//  合并单元格
        sheet.addMergedRegion(cra);
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("测试报告总概况");

        row = sheet.createRow(1);
        row.setHeight((short) 550);
        cellStyle = setCellstyle(wb, HorizontalAlignment.CENTER, "宋体", (short) 15, IndexedColors.GREEN.getIndex());
        cra = new CellRangeAddress(1, 1, 0, 5);
        sheet.addMergedRegion(cra);
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("测试概况");

        cellStyle = setCellstyle(wb, HorizontalAlignment.CENTER, "宋体", (short) 12, IndexedColors.WHITE.getIndex());

        String[] row2 = {"项目名称", "用例总数", "通过率"};
        row = sheet.createRow(2);
        row.setHeight((short) 500);
        for (int i = 1; i < 6; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            sheet.setColumnWidth(i, 6000);
            if (i == 1) {
                cell.setCellValue(row2[0]);
            } else if (i == 3) {
                cell.setCellValue(row2[1]);
            } else if (i == 5) {
                cell.setCellValue(row2[2]);
            }
        }
        cra = new CellRangeAddress(2, 5, 0, 0);
        sheet.addMergedRegion(cra);
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        sheet.setColumnWidth(0, 8000);
        cell.setCellValue("微汇金融支付结算测试组UI自动化测试");

        String[] row3 = {"JIRA任务号", "通过总数"};
        row = sheet.createRow(3);
        row.setHeight((short) 500);
        for (int i = 1; i < 5; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            if (i == 1) {
                cell.setCellValue(row3[0]);
            } else if (i == 3) {
                cell.setCellValue(row3[1]);
            }
        }
        cra = new CellRangeAddress(3, 5, 5, 5);
        sheet.addMergedRegion(cra);
        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("100%");

        String[] row4 = {"执行者", "失败总数"};
        row = sheet.createRow(4);
        row.setHeight((short) 500);
        for (int i = 1; i < 5; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            if (i == 1) {
                cell.setCellValue(row4[0]);
            } else if (i == 3) {
                cell.setCellValue(row4[1]);
            }
        }

        String[] row5 = {"开始时间", "结束时间"};
        row = sheet.createRow(5);
        row.setHeight((short) 500);
        for (int i = 1; i < 5; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            if (i == 1) {
                cell.setCellValue(row5[0]);
            } else if (i == 3) {
                cell.setCellValue(row5[1]);
            }
        }


        wb.write(out);
        out.flush();
        out.close();
        wb.close();
        is.close();
    }

    private static void testDetails(String path) throws IOException {
        String[] title = {"用例ID", "用例场景", "用例数据", "预期结果", "订单号", "测试结果", "错误日志"};
        FileInputStream is = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(is);
        OutputStream out;
        Sheet sheet = wb.getSheet("测试详情");
        Cell cell;
        CellStyle cellStyle;

        cellStyle = setCellstyle(wb, HorizontalAlignment.CENTER, "宋体", (short) 13, IndexedColors.LIGHT_GREEN.getIndex());
        Row row = sheet.createRow(0);

        for (int i = 0; i < title.length; i++) {
            if (i == 2) {
                sheet.setColumnWidth(i, 20000);
            } else if (i == 0 || i == 5) {
                sheet.setColumnWidth(i, 3000);
            } else if (i == title.length - 1) {
                sheet.setColumnWidth(i, 10000);
            } else {
                sheet.setColumnWidth(i, 4000);
            }
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title[i]);
        }

        out = new FileOutputStream(path);
        wb.write(out);
        out.flush();
        out.close();
        wb.close();
        is.close();
    }

    /**
     * @param alignment
     * @param fontName
     * @param fontSize
     * @param shontColor
     * @return
     */
    private static CellStyle setCellstyle(Workbook wb, HorizontalAlignment alignment, String fontName, short fontSize, short shontColor) {
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(alignment);
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);//设置字体大小 
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(shontColor);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

}
