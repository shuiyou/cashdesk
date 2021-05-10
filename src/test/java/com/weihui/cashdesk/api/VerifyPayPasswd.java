package com.weihui.cashdesk.api;

import java.util.HashMap;

import com.weihui.cashdesk.utils.*;
import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import com.weihui.cashdesk.selenium.JSUtil;

public class VerifyPayPasswd {
    private WebDriver driver = null;
    private BasePage verifyPasswdPage = null;
    private ReportUtil log = new ReportUtil();
    public VerifyPayPasswd(WebDriver driver) {
        this.driver = driver;
    }

    public String getCashdeskUrl(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("validatepaypassword"));
        verifyPasswdPage = new BasePage(driver, "验证支付密码", hashMap);
        EnvUtil.testEnv(hashMap, verifyPasswdPage);
        verifyPasswdPage.click("生成请求时间");
        verifyPasswdPage.type("partner_id");
        verifyPasswdPage.type("identity_id");
        verifyPasswdPage.click("生成请求订单号");
        String msg = verifyPasswdPage.getText("交易订单号");
        hashMap.put("订单号", msg);//记录订单号
        log.info("订单号："+msg);
        EnvUtil.testEnvKey(hashMap, verifyPasswdPage);
        JSUtil.moveToBottom(driver);
        verifyPasswdPage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
        AssertUtil.waitPageResourcesTimeOuts(driver, "redirect_url");
        String resource = BrowserUtil.getPageSource(driver);
        int beginIndex = resource.indexOf("redirect_url") + 15;
        int endIndex = resource.lastIndexOf("}") - 1;
        return resource.substring(beginIndex, endIndex);
    }
}
