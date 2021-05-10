package com.weihui.cashdesk.action;

import java.util.HashMap;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.utils.BasePage;

public class HostingWithDrawOpt {
    private WebDriver driver = null;
    private BasePage drawPage = null;

    public HostingWithDrawOpt(WebDriver driver) {
        this.driver = driver;
    }

    public void hostingWithdraw(HashMap<String, String> hashMap) {
        try {
            drawPage = new BasePage(driver, "托管提现主界面", hashMap);
            drawPage.click("广告窗");
            drawPage.type("支付密码");
            drawPage.click("完成支付");

        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }
}
