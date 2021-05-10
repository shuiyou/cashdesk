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

public class ModifyVerifyMobile {
    private WebDriver driver = null;
    private BasePage modifyVerifyMobilePage = null;

    public ModifyVerifyMobile(WebDriver driver) {
        this.driver = driver;
    }

    public String getCashdeskUrl(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("modifyverifymobile"));
        modifyVerifyMobilePage = new BasePage(driver, "修改认证手机", hashMap);
        EnvUtil.testEnv(hashMap, modifyVerifyMobilePage);
        modifyVerifyMobilePage.click("生成请求时间");
        modifyVerifyMobilePage.type("partner_id");
        modifyVerifyMobilePage.type("identity_id");
        EnvUtil.testEnvKey(hashMap, modifyVerifyMobilePage);
        JSUtil.moveToBottom(driver);
        modifyVerifyMobilePage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
        AssertUtil.waitPageResourcesTimeOuts(driver, "redirect_url");
        String resource = BrowserUtil.getPageSource(driver);
        int beginIndex = resource.indexOf("redirect_url") + 15;
        int endIndex = resource.lastIndexOf("}") - 1;
        return resource.substring(beginIndex, endIndex);
    }


}
