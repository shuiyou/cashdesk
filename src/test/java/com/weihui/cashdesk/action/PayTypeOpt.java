package com.weihui.cashdesk.action;

import org.openqa.selenium.WebDriver;
import com.weihui.cashdesk.utils.BasePage;

public class PayTypeOpt {
    private WebDriver driver = null;
    private BasePage cashdeskHomPage = null;

    public PayTypeOpt(WebDriver driver) {
        this.driver = driver;
    }

    public void basicAccountPayType() {
        cashdeskHomPage = new BasePage(driver, "收银台主页");
//	cashdeskHomPage.clickNext("广告窗");
        cashdeskHomPage.click("普通账户");
    }

    public void savindPotPayType() {
        cashdeskHomPage = new BasePage(driver, "收银台主页");
//	cashdeskHomPage.clickNext("广告窗");
        cashdeskHomPage.click("存钱罐账户");
    }

    public void quickPayType() {
        cashdeskHomPage = new BasePage(driver, "收银台主页");
//	cashdeskHomPage.clickNext("广告窗");
        cashdeskHomPage.click("快捷支付");
    }

    public void cardPayType() {
        cashdeskHomPage = new BasePage(driver, "收银台主页");
//	cashdeskHomPage.clickNext("广告窗");
        cashdeskHomPage.click("绑卡支付");//*[@id="J-tabs"]/li[4]
    }

    public void onlineBankType() {
        cashdeskHomPage = new BasePage(driver, "收银台主页");
//	cashdeskHomPage.clickNext("广告窗");
        cashdeskHomPage.click("网银支付");
    }

    public void bankAccountType() {
        cashdeskHomPage = new BasePage(driver, "收银台主页");
//   	cashdeskHomPage.clickNext("广告窗");
        cashdeskHomPage.click("银行账户");
    }

    public void transferPayType() {
        cashdeskHomPage = new BasePage(driver, "收银台主页");
        //cashdeskHomPage.clickNext("广告窗");
        cashdeskHomPage.click("转账");
    }

}
