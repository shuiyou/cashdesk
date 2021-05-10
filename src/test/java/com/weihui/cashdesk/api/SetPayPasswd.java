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

public class SetPayPasswd {
    private WebDriver driver = null;
    private BasePage setPayPasswdPage = null;

    public SetPayPasswd(WebDriver driver) {
        this.driver = driver;
    }

    public String getCashdeskUrl(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("setpaypassword"));
        setPayPasswdPage = new BasePage(driver, "设置支付密码", hashMap);
        EnvUtil.testEnv(hashMap, setPayPasswdPage);
        setPayPasswdPage.click("生成请求时间");
        setPayPasswdPage.type("partner_id");
        setPayPasswdPage.type("identity_id");
        EnvUtil.testEnvKey(hashMap, setPayPasswdPage);
        JSUtil.moveToBottom(driver);
        setPayPasswdPage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
        AssertUtil.waitPageResourcesTimeOuts(driver, "redirect_url");//只能等待页面所需要的元素信息
        String resource = BrowserUtil.getPageSource(driver);
        int beginIndex = resource.indexOf("redirect_url") + 15;
        int endIndex = resource.lastIndexOf("}") - 1;
        return resource.substring(beginIndex, endIndex);

    }

}
