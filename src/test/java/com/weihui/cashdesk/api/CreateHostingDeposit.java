package com.weihui.cashdesk.api;

import java.util.HashMap;

import com.weihui.cashdesk.utils.ReportUtil;
import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import com.weihui.cashdesk.selenium.JSUtil;
import com.weihui.cashdesk.utils.BasePage;
import com.weihui.cashdesk.utils.ConfigUtil;
import com.weihui.cashdesk.utils.EnvUtil;

public class CreateHostingDeposit {
    private WebDriver driver = null;
    private BasePage dePositApiPage = null;
    private ReportUtil log = new ReportUtil();
    public CreateHostingDeposit(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @param hashMap
     * @return void
     * @Title: swithcCashdesk
     * @Description:
     * @author: Yin
     * @throws:
     * @date May 19, 2017 6:15:02 PM
     * @version: V1.0
     */
    public void swithcCashdesk(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("deposit"));
        dePositApiPage = new BasePage(driver, "托管充值", hashMap);
        EnvUtil.testEnv(hashMap, dePositApiPage);
        dePositApiPage.click("生成请求时间");
        dePositApiPage.type("partner_id");
        dePositApiPage.click("生成交易订单号");
        String msg = dePositApiPage.getText("交易订单号");
        hashMap.put("订单号", msg);//记录订单号
        log.info("订单号："+msg);
        dePositApiPage.type("identity_id");
        dePositApiPage.type("account_type");
        dePositApiPage.type("amount");
        dePositApiPage.type("pay_method");
        EnvUtil.testEnvKey(hashMap, dePositApiPage);
        JSUtil.moveToBottom(driver);
        dePositApiPage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
    }
}
