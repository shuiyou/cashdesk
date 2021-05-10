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

public class CreateHostingWithdraw {
    private WebDriver driver = null;
    private BasePage dePositApiPage = null;
    private ReportUtil log = new ReportUtil();
    public CreateHostingWithdraw(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @return void
     * @throws Exception
     * @Title: swithcCashdesk 托管提现接口
     * @Description: 默认采用跳转收银台
     * @author: Yin
     * @throws:
     * @date Mar 23, 2017 12:20:51 PM
     * @version: V1.0
     */
    public void swithcCashdesk(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("withdraw"));
        dePositApiPage = new BasePage(driver, "托管提现", hashMap);
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
        dePositApiPage.type("withdraw_mode");
        EnvUtil.testEnvKey(hashMap, dePositApiPage);
        JSUtil.moveToBottom(driver);//笔记本key8？台式机不能自动滑动？
        dePositApiPage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
    }

}
