package com.weihui.cashdesk.api;

import com.alibaba.fastjson.JSON;
import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import com.weihui.cashdesk.selenium.JSUtil;
import com.weihui.cashdesk.utils.BasePage;
import com.weihui.cashdesk.utils.ConfigUtil;
import com.weihui.cashdesk.utils.EnvUtil;
import com.weihui.cashdesk.utils.ScreenShot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Created by hanxiaodi on 18/9/30.
 */
public class SmerchantUrlGet
{
	private WebDriver driver = null;
	private BasePage smerchantUrlGetPage = null;
	private String getUrl = null;                  //子商户网址


	public SmerchantUrlGet(WebDriver driver) {
		this.driver = driver;
	}

	public String getSemerchatUrl(HashMap<String, String> hashMap) {
		BrowserUtil.openUrl(driver, ConfigUtil.getUrl("smerchanturlget"));
		smerchantUrlGetPage = new BasePage(driver, "获取子商户链接", hashMap);
		smerchantUrlGetPage.type( "请求地址" );
		smerchantUrlGetPage.click("生成请求时间");
		smerchantUrlGetPage.type("partner_id");
		smerchantUrlGetPage.type("identity_id");
		smerchantUrlGetPage.click("生成UID");
		smerchantUrlGetPage.type( "member_type" );
		smerchantUrlGetPage.type( "url_type" );
		EnvUtil.testEnvKey(hashMap, smerchantUrlGetPage);
		JSUtil.moveToBottom(driver);
		smerchantUrlGetPage.click("发送请求");
		FrameUtil.switchToWindow(driver, "", 3);
		//获取需要URL解密内容
		getUrl = smerchantUrlGetPage.getValue("pre");
//		System.out.println(getUrl.substring( 4 ));
		HashMap map = JSON.parseObject(getUrl.substring( 5 ), HashMap.class);
		String dString = (String) map.get("url");
		//获取jason串中的URL 去打开
		BrowserUtil.openUrl(driver, dString);
		String code = new ScreenShot().getVerifycode( driver,driver.findElement( By.id( "captchaImg" )));
		smerchantUrlGetPage.type( "imgCaptcha" );



		String resource = BrowserUtil.getPageSource(driver);
		return resource;


	}
}
