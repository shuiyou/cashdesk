package com.weihui.cashdesk.utils;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.TestAttribute;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.testng.xml.XmlSuite;


public class TestReport implements IReporter {
    private String currentDir = System.getProperty("user.dir");
    //生成的路径以及文件名
    private static final String OUTPUT_FOLDER = "test-output/";
    private static final String FILE_NAME = "TestReport.html";
    private String OSType = System.getProperty("os.name");//得到当前操作系统
    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        TestNGListener.endTime = ConfigUtil.getTime();
        String[] n = {WriteExcel.jiraName, WriteExcel.jira, WriteExcel.userName};
        try {
            WriteExcel.setInfo(n);
        } catch (IOException e) {
            e.printStackTrace();
        }


        init();
        boolean createSuiteNode = false;
        if (suites.size() > 1) {
            createSuiteNode = true;
        }
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            //如果suite里面没有任何用例，直接跳过，不在报告里生成
            if (result.size() == 0) {
                continue;
            }
            //统计suite下的成功、失败、跳过的总用例数
            int suiteFailSize = 0;
            int suitePassSize = 0;
            int suiteSkipSize = 0;
            ExtentTest suiteTest = null;
            //存在多个suite的情况下，在报告中将同一个一个suite的测试结果归为一类，创建一级节点。
            if (createSuiteNode) {
                suiteTest = extent.createTest(suite.getName()).assignCategory(suite.getName());
            }
            boolean createSuiteResultNode = false;
            if (result.size() > 1) {
                createSuiteResultNode = true;
            }
            Date suiteStartTime = null, suiteEndTime = new Date();
            for (ISuiteResult r : result.values()) {
                ExtentTest resultNode;
                ITestContext context = r.getTestContext();
                if (createSuiteResultNode) {
                    //没有创建suite的情况下，将在SuiteResult的创建为一级节点，否则创建为suite的一个子节点。
                    if (null == suiteTest) {
                        resultNode = extent.createTest(context.getName());
                    } else {
                        resultNode = suiteTest.createNode(context.getName());
                    }
                } else {
                    resultNode = suiteTest;
                }
                if (resultNode != null) {
                    resultNode.assignCategory(suite.getName(), context.getName());
                    if (suiteStartTime == null) {
                        suiteStartTime = context.getStartDate();
                    }
                    suiteEndTime = context.getEndDate();
                    resultNode.getModel().setStartTime(context.getStartDate());
                    resultNode.getModel().setEndTime(context.getEndDate());
                    //统计SuiteResult下的数据
                    int passSize = context.getPassedTests().size();
                    int failSize = context.getFailedTests().size();
                    int skipSize = context.getSkippedTests().size();
                    suitePassSize += passSize;
                    suiteFailSize += failSize;
                    suiteSkipSize += skipSize;
                    if (failSize > 0) {
                        resultNode.getModel().setStatus(Status.FAIL);
                    }
                    resultNode.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;", passSize, failSize, skipSize));
                }
                buildTestNodes(resultNode, context.getFailedTests(), Status.FAIL);
                buildTestNodes(resultNode, context.getSkippedTests(), Status.SKIP);
                buildTestNodes(resultNode, context.getPassedTests(), Status.PASS);
            }
            if (suiteTest != null) {
                suiteTest.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;", suitePassSize, suiteFailSize, suiteSkipSize));
                suiteTest.getModel().setStartTime(suiteStartTime == null ? new Date() : suiteStartTime);
                suiteTest.getModel().setEndTime(suiteEndTime);
                if (suiteFailSize > 0) {
                    suiteTest.getModel().setStatus(Status.FAIL);
                }
            }

        }
        extent.flush();

        String Recipients; // 邮件收件人地址
        String mailTitle = "收银台自动化测试报告" + TestNGListener.startTime;
        String reportTitle = "测试概况";
        Recipients = ConfigUtil.getValue("recipients");

        StringBuffer sb2 = new StringBuffer();
        sb2.append(
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        sb2.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n");
        sb2.append("<meta http-equiv=\"Content-Type\"content=\"text/html; charset=gbk\" />\n");
        sb2.append("<title>" + reportTitle + "</title>\n");
        sb2.append("</head>\n");
        sb2.append("<body>\n");
        sb2.append("<div id=\"content\">\n");
        sb2.append("<div id=\"report_title\">\n");
        sb2.append(
                "<div id=\"logo\"><img src=\"http://www.webdriver.org/template/time_6th_travel/src/logo.png\"></img></div>\n");
        sb2.append("<div style=\"clear:both\"><div id=\"time\">\n");
        sb2.append("<div style=\"pading-top:20px\">开始时间：" + TestNGListener.startTime
                + "</div>\n");
        sb2.append(
                "<div>结束时间：" + TestNGListener.endTime + " </div>\n");
        sb2.append("</div>\n");

        sb2.append("</div>\n");
        sb2.append("</div>");
        sb2.append("<div  id=\"header_right\" >\n");
        sb2.append("<div id=\"title\">\n");
        sb2.append("<div style=\"text-align:center;color:#F00;font-size:18px\">" + reportTitle + "</div>\n");
        sb2.append("</div>\n");
        sb2.append("</div>\n");
        sb2.append("<div id=\"report_total\">\n");
        sb2.append(
                "<div>(<span style=\"color:green\">绿色字体pass用例</span><span style=\"color:green\">        </span><span style=\"color:red\">红色字体failed用例</span></div>\n");
        sb2.append(
                "<table width=\"100%\" style=\"width:100%;text-align:left;border-collapse:collapse;border-spacing:0;font-size:12px;\" >\n");
        sb2.append("<tr>\n"
                + "<td  style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\" width=\"18%\"><div align=\"center\">用例总数</div></td>\n");
        sb2.append(
                "<td   style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\" width=\"22%\" align=\"center\"><div align=\"center\">通过数（pass）</div></td>\n");
        sb2.append(
                "<td  style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\"  width=\"21%\" align=\"center\"><div align=\"center\">失败数(failed)</div></td>\n");
        sb2.append(
                "<td  style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\"  width=\"21%\" align=\"center\"><div align=\"center\">跳过数(skip)</div></td>\n");
        sb2.append(
                "<td   style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\" width=\"18%\" align=\"center\"><div align=\"center\">通过率</div></td>\n</tr>\n");
        sb2.append(
                " <tr >\n<td style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\" ><div align=\"center\">");
        sb2.append("<label style=\"color:blue\" id=\"total_num\">");
        sb2.append(TestNGListener.successNum + TestNGListener.failNum + TestNGListener.skipNum);
        sb2.append("</label>");
        sb2.append(
                "</div></td>\n<td style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\"><div align=\"center\">");
        sb2.append("<label style=\"color:green\" id=\"passed_num\"" + "value=\"" + TestNGListener.successNum + "\">");
        sb2.append(TestNGListener.successNum);
        sb2.append("</label>");
        sb2.append(
                "</div></td>\n <td style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\"><div align=\"center\">");
        sb2.append("<label style=\"color:red\" id=\"failed_num\"" + "value=\"" + TestNGListener.failNum + "\">");
        sb2.append(TestNGListener.failNum);
        sb2.append("</label>");
        sb2.append(
                "</div></td>\n<td style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\"><div align=\"center\">");
        sb2.append("<label style=\"color:gray\" id=\"skiped_num\"" + "value=\"" + TestNGListener.skipNum + "\">");
        sb2.append(TestNGListener.skipNum);
        sb2.append("</label>");
        sb2.append(
                "</div></td>\n<td style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\"><div align=\"center\">");
        DecimalFormat dfDecimalFormat1 = new DecimalFormat("######0.00");
        float total1 = TestNGListener.successNum + TestNGListener.failNum + TestNGListener.skipNum;
        float s1 = TestNGListener.successNum / total1;
        sb2.append(dfDecimalFormat1.format(s1 * 100));
        sb2.append("%");
        sb2.append("</div></td>\n</tr>\n</table>\n");

        sb2.append(
                "<table style=\"width:100%;text-align:left;border-collapse:collapse;border-spacing:0;font-size:10px;\">\n");
        sb2.append(
                "<tr>\n<td  style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\" width=\"2%\" class=\"SEQ\">序号</td>\n");
        sb2.append(
                "<td   style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\" width=\"17%\" class=\"case_funtcion_td\">功能模块</td>\n");
        sb2.append(
                "<td  style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\" width=\"22%\" class=\"case_name_td\">用例名称</td>\n");
        sb2.append(
                "<td  style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\"  width=\"3%\"  align=\"left\">测试结果</td>\n");
        sb2.append(
                "<td  style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\"  width=\"28%\" class=\"case_result_beizhu_td\">备注</td>\n");
        sb2.append(
                "<td  style=\"border:1.5px;border-color:gray; word-break:break-all;border-style: solid;height:20px;\" width=\"28%\" class=\"case_time\">耗时</td>\n");
        sb2.append("</tr>\n");


        sb2.append("</table>\n");
        // --跳过结果--end
        sb2.append("</table>\n</div>\n");
        sb2.append("</div>\n"
                + "<div id=\"footer\" style=\"font-size:14px\" >技术支持：Copyright © 2017 微汇金融&新浪支付.Inc</div>");
        sb2.append("</body>\n</html>\n");
        SendMail sendMail = new SendMail();
        String smtpUserName;
        String smtpPassWord;
        String smtpHost;
        String smtpPort = "";


        smtpUserName = ConfigUtil.getValue("smtpUserName");
        smtpPassWord = ConfigUtil.getValue("smtpPassWord");
        smtpHost = ConfigUtil.getValue("smtpHost");

        String Log = currentDir + "/TestZip/Log.zip";
        String output = currentDir + "/TestZip/TestOutPut.zip";

        new ZipCompressor(Log).compress(currentDir + "/logs");
        new ZipCompressor(output).compress(currentDir + "/test-output");

        sendMail.attachfile(Log);
        sendMail.attachfile(output);

        sendMail.sendmessage(smtpUserName, smtpPassWord, smtpHost, smtpPort, smtpUserName, Recipients, mailTitle,
                sb2.toString());
    }


    private void init() {
        //文件夹不存在的话进行创建
        File reportDir = new File(OUTPUT_FOLDER);
        if (!reportDir.exists() && !reportDir.isDirectory()) {
            reportDir.mkdir();
        }
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setDocumentTitle("UI自动化测试报告");
        htmlReporter.config().setReportName("收银台自动化测试报告");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        //设置点击效果：.node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}
        //设置系统信息样式：.card-panel.environment  th:first-child{ width:30%;}
        htmlReporter.config().setCSS(".node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}  .card-panel.environment  th:first-child{ width:30%;}");
        // 移除按键监听事件
        htmlReporter.config().setJS("$(window).off(\"keydown\");");
        //设置静态文件的DNS 如果cdn.rawgit.com访问不了，可以设置为：ResourceCDN.EXTENTREPORTS 或者ResourceCDN.GITHUB
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
       /* // 设置系统信息
        Properties properties = System.getProperties();
        extent.setSystemInfo("os.name",properties.getProperty("os.name","未知"));
        extent.setSystemInfo("os.arch",properties.getProperty("os.arch","未知"));
        extent.setSystemInfo("os.version",properties.getProperty("os.version","未知"));
        extent.setSystemInfo("java.version",properties.getProperty("java.version","未知"));
        extent.setSystemInfo("java.home",properties.getProperty("java.home","未知"));
        extent.setSystemInfo("user.name",properties.getProperty("user.name","未知"));
        extent.setSystemInfo("user.dir",properties.getProperty("user.dir","未知"));*/
    }

    private void buildTestNodes(ExtentTest extenttest, IResultMap tests, Status status) {
        //存在父节点时，获取父节点的标签
        String[] categories = new String[0];
        if (extenttest != null) {
            List<TestAttribute> categoryList = extenttest.getModel().getCategoryContext().getAll();
            categories = new String[categoryList.size()];
            for (int index = 0; index < categoryList.size(); index++) {
                categories[index] = categoryList.get(index).getName();
            }
        }

        ExtentTest test;

        if (tests.size() > 0) {
            //调整用例排序，按时间排序
            Set<ITestResult> treeSet = new TreeSet<ITestResult>(new Comparator<ITestResult>() {
                @Override
                public int compare(ITestResult o1, ITestResult o2) {
                    return o1.getStartMillis() < o2.getStartMillis() ? -1 : 1;
                }
            });
            treeSet.addAll(tests.getAllResults());
            for (ITestResult result : treeSet) {
                Object[] parameters = result.getParameters();
                String name="";
                Map<String,String>  hashMap = new HashMap<String,String>();
                hashMap = (HashMap<String, String>) parameters[0];
                if(hashMap.containsKey("用例场景")) {
                    name = hashMap.get("用例编号")+"-"+hashMap.get("用例场景");
                }
                if (name.length() > 0) {
                    if (name.length() > 50) {
                        name = name.substring(0, 49) + "...";
                    }
                } else {
                    name = result.getMethod().getMethodName();
                }
                if (extenttest == null) {
                    test = extent.createTest(name);
                } else {
                    //作为子节点进行创建时，设置同父节点的标签一致，便于报告检索。
                    test = extenttest.createNode(name).assignCategory(categories);
                }
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                List<String> outputList = Reporter.getOutput(result);
                for (String output : outputList) {
                    //将用例的log输出报告中
                    test.debug(output.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
                }
                if (result.getThrowable() != null) {
                    String path;
                    if (OSType.contains("Mac")) {
                        path = System.getProperty("user.dir").replace("\\", "/");
                        path = path + "/test-output/失败截图/" + name + ".png";
                    } else {
                        path = System.getProperty("user.dir") + "\\test-output\\失败截图\\" + name + ".png";
                    }
                    try {
                        test.addScreenCaptureFromPath(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    private void write(File file, File newFile) throws IOException {
        File newFiles = new File(newFile, file.getName());
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(newFiles));
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }

    }



}


