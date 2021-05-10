package com.weihui.cashdesk.action;

import java.util.HashMap;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.utils.BasePage;

public class CreatePreAuthTradeOpt {
    private WebDriver driver = null;
    private BasePage drawPage = null;

    public CreatePreAuthTradeOpt(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @param data
     * @return void
     * @throws Exception
     * @Title: 预授权
     * @Description: 基本户余额支付
     * @author: Lin
     * @throws:
     * @date Apr 13, 2017 4:33:22 PM
     * @version: V1.0
     */
    public void tradeByBasic(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).basicAccountPayType();
            drawPage = new BasePage(driver, "普通账户", hashMap);
            drawPage.type("预授权支付密码");
            drawPage.click("预授权完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param data
     * @return void
     * @throws Exception
     * @Title: 预授权
     * @Description: 存钱罐余额支付
     * @author: Lin
     * @throws:
     * @date Apr 13, 2017 4:33:22 PM
     * @version: V1.0
     */
    public void tradeBySavingPot(HashMap<String, String> hashMap) {
        try {
            drawPage = new BasePage(driver, "存钱罐账户", hashMap);
            new PayTypeOpt(driver).savindPotPayType();
            drawPage.type("预授权支付密码");
            drawPage.click("预授权完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param data
     * @return void
     * @throws Exception
     * @Title: 预授权
     * @Description: 托管银行余额支付
     * @author: Lin
     * @throws:
     * @date Apr 13, 2017 4:33:22 PM
     * @version: V1.0
     */
    public void tradeByHostingBank(HashMap<String, String> hashMap) {
        try {
            drawPage = new BasePage(driver, "银行账户", hashMap);
            new PayTypeOpt(driver).bankAccountType();
            drawPage.type("预授权支付密码");
            drawPage.click("预授权完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }
}
