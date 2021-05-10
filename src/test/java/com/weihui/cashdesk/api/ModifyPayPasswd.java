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

public class ModifyPayPasswd {
    private WebDriver driver = null;
    private BasePage modifyPayPasswd = null;

    public ModifyPayPasswd(WebDriver driver) {
        this.driver = driver;
    }

    public String getCashdeskUrl(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("modifypaypassword"));
        modifyPayPasswd = new BasePage(driver, "修改支付密码", hashMap);
        EnvUtil.testEnv(hashMap, modifyPayPasswd);
        modifyPayPasswd.click("生成请求时间");
        modifyPayPasswd.type("partner_id");
        modifyPayPasswd.type("identity_id");
        EnvUtil.testEnvKey(hashMap, modifyPayPasswd);
        JSUtil.moveToBottom(driver);
        modifyPayPasswd.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
        AssertUtil.waitPageResourcesTimeOuts(driver, "redirect_url");
        String resource = BrowserUtil.getPageSource(driver);
        int beginIndex = resource.indexOf("redirect_url") + 15;
        int endIndex = resource.lastIndexOf("}") - 1;
        return resource.substring(beginIndex, endIndex);
    }
}
