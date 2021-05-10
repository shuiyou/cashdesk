package com.weihui.cashdesk.api;

import java.util.HashMap;

import com.weihui.cashdesk.utils.*;
import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import com.weihui.cashdesk.selenium.JSUtil;

public class CreateHostingTransfer {
    private WebDriver driver = null;
    private BasePage transferApiPage = null;
    private ReportUtil log = new ReportUtil();
    public CreateHostingTransfer(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @param hashMap
     * @return void
     * @Title: collectTrade
     * @Description: 托管转账接口
     * @author: han
     * @throws:
     * @date june 05, 2017 6:43:07 PM
     * @version: V1.0
     */
    public void hostingtranfer(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("hostingtransfer"));
        transferApiPage = new BasePage(driver, "转账", hashMap);
        EnvUtil.testEnv(hashMap, transferApiPage);
        transferApiPage.click("生成请求时间");
        transferApiPage.type("partner_id");
        transferApiPage.click("生成交易订单号");
        String msg = transferApiPage.getText("交易订单号");
        hashMap.put("订单号", msg);//记录订单号
        log.info("订单号："+msg);
        transferApiPage.type("payer_identity_id");
        transferApiPage.type("payer_account_type");
        transferApiPage.type("payee_identity_id");
        transferApiPage.type("payee_account_type");
        transferApiPage.type("amount");
        transferApiPage.type("transfer_mode");
        EnvUtil.testEnvKey(hashMap, transferApiPage);
        JSUtil.moveToBottom(driver);// 笔记本key8？台式机不能自动滑动？
        transferApiPage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
    }


}
