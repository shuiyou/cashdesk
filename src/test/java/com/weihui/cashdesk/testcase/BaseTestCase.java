package com.weihui.cashdesk.testcase;

import java.io.IOException;
import java.util.HashMap;

import com.weihui.cashdesk.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;

public class BaseTestCase extends ScreenShot {
    private Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver = null; // 设置测试类驱动 完全照抄即可
    protected HashMap<String, String> hashMap = null; // 用例参数 完全照抄即可
    protected String caseType = null;// 指定执行sheet 映射到excel用例里 需要自己更改
    protected String caseId = null;// 指定执行sheet 映射到excel用例里 需要自己更改


    @Parameters({"datasource", "pagexmlpath" ,"jira","jiraName","userName"})
    @BeforeSuite()
    public void beforeSuite(String datasource, String xmlPath, String jira, String jiraName, String userName) {
        ReadExcel.dataPath = System.getProperty("user.dir") +"/测试数据/" + datasource;
        WriteExcel.jira = jira;
        WriteExcel.jiraName = jiraName;
        WriteExcel.userName = userName;
        XmlUtil.xmlPath = System.getProperty("user.dir") +"/src/main/resources/" + xmlPath;
        ReportUtil.logRecord.clear();
        WriteExcel.generateTestOutput();
        TestNGListener.startTime = ConfigUtil.getTime();
    }

    @AfterSuite
    public void afterSuite() throws IOException {}

    @BeforeClass
    public void beforeClass() {
        this.driver = DriverFactory.getInstance().getChromeDriver();
        // this.driver = DriverFactory.getInstance().getFirefoxDriver();
        // RemoteBrowserBean remoteBrowserBean = new RemoteBrowserBean("chrome",
        // "51.0.2704.84");
        // this.driver =
        // DriverFactory.getInstance().getRemoteDriver(remoteBrowserBean);
        super.setDriver(driver);// 设置截图浏览器驱
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @AfterMethod
    public void afterMethod() throws IOException {
        WriteExcel.setCaseRet(hashMap);
        FrameUtil.closeOtherWindow(driver);
    }

    @AfterClass
    public void afterClass() throws IOException {
        BrowserUtil.quit(driver);
    }

    /**
     * @return void
     * @Title: getCaseData
     * @Description: 数据驱动调用，适用一个@test迭代
     * @author: Yin
     * @date Jul 20, 2017 8:54:54 AM
     * @version: V1.0
     */
    public void getCaseData(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
        caseId = hashMap.get("用例编号");
        caseType = hashMap.get("用例场景");
        String mString = caseId + "-" + caseType + "用例开始执行";
        logger.info(mString);
    }

}
