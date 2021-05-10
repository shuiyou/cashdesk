package com.weihui.cashdesk.api;

import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import com.weihui.cashdesk.selenium.JSUtil;
import com.weihui.cashdesk.utils.BasePage;
import com.weihui.cashdesk.utils.ConfigUtil;
import com.weihui.cashdesk.utils.EnvUtil;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Created by hanxiaodi on 17/12/19.
 */
public class CreateBidInfo
{
	private WebDriver driver = null;
	private BasePage createBidInfoPage = null;



	public CreateBidInfo(WebDriver driver) {
		this.driver = driver;
	}

	public void createbid(HashMap<String, String> hashMap) {
		BrowserUtil.openUrl(driver, ConfigUtil.getUrl("CreateBidInfo"));
		createBidInfoPage = new BasePage(driver, "创建标的页面", hashMap);
		EnvUtil.testEnv(hashMap, createBidInfoPage);
		createBidInfoPage.click("生成请求时间");
		createBidInfoPage.click( "生成商户标的号");
		String bid_no = createBidInfoPage.getText("生成商户标的号");
		createBidInfoPage.type("partner_id");
		createBidInfoPage.type( "summary" );
		createBidInfoPage.type("web_site_name");
		createBidInfoPage.click("生成标的名称");
		createBidInfoPage.type("标的类型");
		createBidInfoPage.type("标的金额");
		createBidInfoPage.type("年化收益率");
		createBidInfoPage.type("借款期限");
		createBidInfoPage.type("还款方式");
		createBidInfoPage.type("协议类型");
		createBidInfoPage.type("标的产品类型");
		createBidInfoPage.type("推荐机构");
		createBidInfoPage.type("限定最低投标份数");
		createBidInfoPage.type("限定每份投标金额");
		createBidInfoPage.type("限定最多投标金额");
		createBidInfoPage.type("限定最少投标金额");
		createBidInfoPage.type("标的URL");
		createBidInfoPage.click("生成标的开始时间");
		createBidInfoPage.click("生成还款日期");
		createBidInfoPage.type("担保方式");
		createBidInfoPage.type("borrower_info_list");
		createBidInfoPage.type("担保人列表");
		createBidInfoPage.type("MONTH_FIXED_DAY");
		createBidInfoPage.type("int_pay_day");
		createBidInfoPage.type("扩展信息");
		createBidInfoPage.type("华瑞募集开始日期");
		createBidInfoPage.type("华瑞募集结束日期");
		createBidInfoPage.type("华瑞担保方账户");
		createBidInfoPage.type("华瑞经销商账户");
		createBidInfoPage.type("华瑞委托还款账户");
		createBidInfoPage.type("委托还款人会员标识类型");
		createBidInfoPage.type("委托还款人会员标识");
		createBidInfoPage.type("经销商会员标识类型");
		createBidInfoPage.type("经销商会员标识");
		createBidInfoPage.type("担保方会员标识类型");
		createBidInfoPage.type("担保方会员标识");
		EnvUtil.testEnvKey(hashMap, createBidInfoPage);
		JSUtil.moveToBottom(driver);
		createBidInfoPage.click("发送请求");
		FrameUtil.switchToWindow(driver, "", 3);


	}
}
