package com.weihui.cashdesk.api;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import com.weihui.cashdesk.selenium.JSUtil;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.BasePage;
import com.weihui.cashdesk.utils.ConfigUtil;
import com.weihui.cashdesk.utils.EnvUtil;

public class FindPayPasswd {
    private WebDriver driver = null;
    private BasePage findPayPasswd = null;

    public FindPayPasswd(WebDriver driver) {
        this.driver = driver;
    }

    public String getCashdeskUrl(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("findpaypassword"));
        findPayPasswd = new BasePage(driver, "找回支付密码", hashMap);
        EnvUtil.testEnv(hashMap, findPayPasswd);
        findPayPasswd.click("生成请求时间");
        findPayPasswd.type("partner_id");
        findPayPasswd.type("identity_id");
        EnvUtil.testEnvKey(hashMap, findPayPasswd);
        JSUtil.moveToBottom(driver);
        findPayPasswd.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
        AssertUtil.waitPageResourcesTimeOuts(driver, "redirect_url");//等待url加载完成
        String resource = BrowserUtil.getPageSource(driver);
        int beginIndex = resource.indexOf("redirect_url") + 15;
        int endIndex = resource.lastIndexOf("}") - 1;
        return resource.substring(beginIndex, endIndex);
    }
}
