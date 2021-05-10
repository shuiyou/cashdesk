package com.weihui.cashdesk.action;

import java.util.HashMap;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import com.weihui.cashdesk.utils.BasePage;
import com.weihui.cashdesk.utils.EnvUtil;

public class CollectTradeOpt {
    private WebDriver driver = null;
    private BasePage collectTradePage = null;

    public CollectTradeOpt(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @param hashMap
     * @return void
     * @Title: bankAccountPay
     * @Description: 代收，银行账户支付
     * @author: Yin
     * @throws:
     * @date Jul 19, 2017 6:01:38 PM
     * @version: V1.0
     */
    public void bankAccountPay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).bankAccountType();
            collectTradePage = new BasePage(driver, "银行账户", hashMap);
            collectTradePage.type("代收支付密码");
            collectTradePage.click("代收完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: basicAccountPay
     * @Description: 代收，基本户支付
     * @author: Yin
     * @date Jul 20, 2017 9:23:23 AM
     * @version: V1.0
     */
    public void basicAccountPay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).basicAccountPayType();
            collectTradePage = new BasePage(driver, "普通账户", hashMap);
            collectTradePage.type("代收支付密码");
            collectTradePage.click("代收完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: savingPotAccountPay
     * @Description: 代收，存钱罐支付
     * @author: Yin
     * @throws:
     * @date Jul 20, 2017 9:26:21 AM
     * @version: V1.0
     */
    public void savingPotAccountPay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).savindPotPayType();
            collectTradePage = new BasePage(driver, "存钱罐账户", hashMap);
            collectTradePage.type("代收支付密码");
            collectTradePage.click("代收完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: quickPay
     * @Description: 代收快捷支付
     * @author: Yin
     * @throws:
     * @date Jul 20, 2017 9:31:29 AM
     * @version: V1.0
     */
    public void quickPay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).quickPayType();
            collectTradePage = new BasePage(driver, "快捷支付", hashMap);
            collectTradePage.type("储蓄卡卡号");
            collectTradePage.click("下一步");
            collectTradePage.type("手机号");
            collectTradePage.click("短信获取");
            EnvUtil.getVerifyCode("验证码", hashMap.get("手机号"), collectTradePage, 0, 6);
            collectTradePage.type("代收支付密码");
            collectTradePage.click("完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }

    }

    /**
     * @param hashMap
     * @return void
     * @Title: cardPay
     * @Description: 代收-绑卡支付
     * @author: Yin
     * @throws:
     * @date Jul 20, 2017 9:46:25 AM
     * @version: V1.0
     */
    public void cardPay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).cardPayType();
            collectTradePage = new BasePage(driver, "绑卡支付", hashMap);
            collectTradePage.click("短信获取");
            EnvUtil.getVerifyCode("验证码", hashMap.get("手机号"), collectTradePage, 0, 6);
            collectTradePage.type("代收支付密码");
            collectTradePage.click("代收完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: savingPotPayPsdError
     * @Description: 代收--支付密码错误
     * @author: Yin
     * @throws:
     * @date Jul 20, 2017 9:59:57 AM
     * @version: V1.0
     */
    public void savingPotPayPsdError(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).savindPotPayType();
            collectTradePage = new BasePage(driver, "存钱罐账户", hashMap);
            collectTradePage.type("代收支付密码", "111111");
            collectTradePage.click("代收完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }
}
