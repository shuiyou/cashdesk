package com.weihui.cashdesk.utils;

import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


/**
 * Created by hanxiaodi on 17/12/25.
 */
public class InitalPararm
{
	public WebDriver driver;
	private Logger logger = LogManager.getLogger(this.getClass());
	private BasePage BasisPage = null;



	private void  initalPararm(String merchantId,String parmkey,String value){
		BrowserUtil.openUrl(driver, ConfigUtil.getUrl("BASISLogin"));
		BasisPage =  new BasePage(driver, "BASIS页面");
		BasisPage.type( "loginname","hanxiaodi" );
		BasisPage.type("password","#QAZ2WSX");
		BasisPage.click( "登陆" );
		FrameUtil.switchToWindow(driver, "", 3);
		BasisPage.click( "新增商户配置" );
		BasisPage.type("商户号",merchantId);
		BasisPage.type( "参数key" ,parmkey);
		BasisPage.type("参数值",value);
		BasisPage.click( "保存" );
	}

}
