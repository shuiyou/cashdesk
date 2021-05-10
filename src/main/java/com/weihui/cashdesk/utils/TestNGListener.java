package com.weihui.cashdesk.utils;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class TestNGListener extends TestListenerAdapter {
    Logger logger = LogManager.getLogger(this.getClass());
    public static boolean passCaseRet = false;
    public static String startTime = null;
    public static String endTime = null;
    public static int successNum = 0;
    public static int failNum = 0;
    public static int skipNum = 0;

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        String name =ReportUtil.getTestName(tr.getParameters());
        logger.info(name+"用例PASS");
        passCaseRet = true;
        ++successNum;
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        passCaseRet = false;
        String name =ReportUtil.getTestName(tr.getParameters());
        logger.info(name+"用例FAIL");
        takeScreenShot(tr,name);
        ++failNum;
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        String name =ReportUtil.getTestName(tr.getParameters());
        takeScreenShot(tr,name);
        logger.info(name+"用例SKIP");
        ++skipNum;
    }


    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
    }

    private void takeScreenShot(ITestResult tr,String name) {
        ScreenShot b = (ScreenShot) tr.getInstance();
        b.takeScreenShot(name);

    }

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);

    }

}
