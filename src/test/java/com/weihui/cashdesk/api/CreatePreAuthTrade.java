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

public class CreatePreAuthTrade {
    private WebDriver driver = null;
    private BasePage transferApiPage = null;
    private ReportUtil log = new ReportUtil();
    public CreatePreAuthTrade(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @param hashMap
     * @return void
     * @Title: CreatePreAuthTrade
     * @Description: 预授权接口
     * @author: Lin
     * @throws:
     * @date june 05, 2017 6:43:07 PM
     * @version: V1.0
     */
    public void swithcCashdesk(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("CreatePreAuthTrade"));
        transferApiPage = new BasePage(driver, "预授权", hashMap);
        EnvUtil.testEnv(hashMap, transferApiPage);
        transferApiPage.click("生成请求时间");
        transferApiPage.type("partner_id");
        transferApiPage.click("生成交易订单号");
        String msg = transferApiPage.getText("交易订单号");
        hashMap.put("订单号", msg);//记录订单号
        log.info("订单号："+msg);
        transferApiPage.type("payer_id");
        transferApiPage.type("pay_method");
        EnvUtil.testEnvKey(hashMap, transferApiPage);
        JSUtil.moveToBottom(driver);// 笔记本key8？台式机不能自动滑动？
        transferApiPage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
    }

}
