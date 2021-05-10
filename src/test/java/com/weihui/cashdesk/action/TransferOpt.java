package com.weihui.cashdesk.action;

import java.util.HashMap;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.utils.BasePage;

public class TransferOpt {
    private WebDriver driver = null;
    private BasePage transferPage = null;

    public TransferOpt(WebDriver driver) {
        this.driver = driver;
    }

    public void transferByBasic(HashMap<String, String> hashMap) {
        try {
            transferPage = new BasePage(driver, "普通账户", hashMap);
            transferPage.type("转账支付密码");
            transferPage.click("转账完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    public void transferBySavingPot(HashMap<String, String> hashMap) {
        try {
            transferPage = new BasePage(driver, "存钱罐账户", hashMap);
            transferPage.type("转账支付密码");
            transferPage.click("转账完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    public void transferByBank(HashMap<String, String> hashMap) {
        try {
            transferPage = new BasePage(driver, "银行账户", hashMap);
            transferPage.type("转账支付密码");
            transferPage.click("转账完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

}
