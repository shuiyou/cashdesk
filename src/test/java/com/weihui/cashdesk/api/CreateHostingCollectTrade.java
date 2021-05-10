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

public class CreateHostingCollectTrade {
    private WebDriver driver = null;
    private BasePage collectApiPage = null;
    private ReportUtil log = new ReportUtil();
    public CreateHostingCollectTrade(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * @param hashMap
     * @return void
     * @Title: collectTrade
     * @Description: 托管代收接口
     * @author: Yin
     * @throws:
     * @date Apr 17, 2017 6:23:07 PM
     * @version: V1.0
     */
    public void switchCashdesk(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("collecttrade"));
        collectApiPage = new BasePage(driver, "托管代收", hashMap);
        EnvUtil.testEnv(hashMap, collectApiPage);
        collectApiPage.click("生成请求时间");
        collectApiPage.type("partner_id");
        collectApiPage.click("生成交易订单号");
        String msg = collectApiPage.getText("交易订单号");
        hashMap.put("订单号", msg);//记录订单号
        log.info("订单号："+msg);
        collectApiPage.type("payer_id");
        collectApiPage.type("pay_method");
        EnvUtil.testEnvKey(hashMap, collectApiPage);
        JSUtil.moveToBottom(driver);
        collectApiPage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
    }

}
