package com.weihui.cashdesk.api;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import org.openqa.selenium.WebDriver;
import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import com.weihui.cashdesk.selenium.JSUtil;
import com.weihui.cashdesk.utils.BasePage;
import com.weihui.cashdesk.utils.ConfigUtil;
import com.weihui.cashdesk.utils.EnvUtil;

public class ShowMemberInfo {
    private WebDriver driver = null;
    private BasePage showMemberInfosPage = null;
    private String need_to_decode = null;           //商户信息展示所需的URL信息
    private String getUrl = null;                  //个人中心网址
    private final String redirectUrl = "redirect_url";


    public ShowMemberInfo(WebDriver driver) {
        this.driver = driver;
    }

    public String getCashdeskUrl(HashMap<String, String> hashMap) {
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("showMemberinfossina"));
        showMemberInfosPage = new BasePage(driver, "sina页面展示商户信息", hashMap);
        EnvUtil.testEnv(hashMap, showMemberInfosPage);
        showMemberInfosPage.click("生成请求时间");
        showMemberInfosPage.type("partner_id");
        showMemberInfosPage.type("identity_id");
        showMemberInfosPage.type("resp_method");
        EnvUtil.testEnvKey(hashMap, showMemberInfosPage);
        JSUtil.moveToBottom(driver);
        showMemberInfosPage.click("发送请求");
        FrameUtil.switchToWindow(driver, "", 3);
        //获取需要URL解密内容
        need_to_decode = showMemberInfosPage.getValue("pre");
        //跳转界面页面去解密URL
        BrowserUtil.openUrl(driver, ConfigUtil.getUrl("UncodeUrl"));
        showMemberInfosPage = new BasePage(driver, "解码url", hashMap);
        showMemberInfosPage.type("decodeContent", need_to_decode);
        showMemberInfosPage.click("UrlDecode解码");
        //获取解密后的jasonString
        getUrl = showMemberInfosPage.getValue("shwoDecodeContent");
        HashMap map = JSON.parseObject(getUrl, HashMap.class);
        String dString = (String) map.get(redirectUrl);
        //获取jason串中的URL 去打开
        BrowserUtil.openUrl(driver, dString);
        String resource = BrowserUtil.getPageSource(driver);
        return resource;


    }

}
