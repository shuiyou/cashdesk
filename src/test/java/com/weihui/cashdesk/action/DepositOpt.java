package com.weihui.cashdesk.action;

import java.util.HashMap;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.FrameUtil;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.BasePage;
import com.weihui.cashdesk.utils.EnvUtil;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class DepositOpt {

    private WebDriver driver = null;
    private BasePage depositPage = null;

    public DepositOpt(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @return void
     * @throws Exception
     * @Title: dePosit
     * @Description: 绑卡支付
     * @author: Yin
     * @throws:
     * @date Apr 13, 2017 4:33:22 PM
     * @version: V1.0
     */
    public void cardPay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).cardPayType();
            depositPage = new BasePage(driver, "绑卡支付", hashMap);
            depositPage.click("短信获取");
            EnvUtil.getVerifyCode("验证码", hashMap.get("手机号"), depositPage, 0, 6);
            depositPage.type("充值支付密码");
            depositPage.click("充值完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: quickPay
     * @Description: 快捷支付
     * @author: Yin
     * @throws:
     * @date Apr 17, 2017 3:10:37 PM
     * @version: V1.0
     */
    public void quickPay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).quickPayType();
            depositPage = new BasePage(driver, "快捷支付", hashMap);
            depositPage.type("储蓄卡卡号");
            depositPage.click("下一步");
            depositPage.click( "用户协议" );
            if (AssertUtil.assertStringContainsFromPage(driver,"新浪支付快捷支付服务协议")==true){
                depositPage.click( "关闭协议" );
                depositPage.type("手机号");
                depositPage.click("短信获取");
                EnvUtil.getVerifyCode("验证码", hashMap.get("手机号"), depositPage, 0, 6);
                depositPage.type("充值支付密码");
                depositPage.click("完成支付");
            }else {
                System.out.println("协议缺失");
            }

        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }

    }


    /**
     * @param hashMap
     * @return void
     * @Title: onLinePay
     * @Description: 网银支付
     * @author: Yin
     * @throws:
     * @date Jul 19, 2017 3:23:52 PM
     * @version: V1.0
     */
    public void onLinePay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).onlineBankType();
            depositPage = new BasePage(driver, "网银支付", hashMap);
            depositPage.click("测试银行");
            depositPage.click("去网银充值");
            FrameUtil.switchToWindow(driver, "", 5);// 传入"“默认往后面的网页跳转
            depositPage.click("验签成功");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: verifyCOdeError
     * @Description:验证码错误
     * @author: Yin
     * @throws:
     * @date Jul 19, 2017 5:34:25 PM
     * @version: V1.0
     */
    public void verifyCodeError(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).cardPayType();
            depositPage = new BasePage(driver, "绑卡支付", hashMap);
            depositPage.click("短信获取");
            BrowserUtil.sleep(5);
            depositPage.type("验证码", "111111");
            depositPage.type("充值支付密码");
            depositPage.click("充值完成支付");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }


    /**
     * @param hashMap
     * @return void
     * @Title: basicAccountOnLineTransfer
     * @Description: 基本户充值--企业网银支付  企业会员
     * @throws:
     * @date Jul 21, 2017 3:39:50 PM
     * @version: V1.0
     */
    public void basicAccountOnLinePay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).onlineBankType();
            depositPage = new BasePage(driver, "网银支付", hashMap);
            depositPage.click("企业测试银行");
            depositPage.click("去网银充值");
            FrameUtil.switchToWindow(driver, "", 5);// 传入"“默认往后面的网页跳转
            depositPage.click("验签成功");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: transferPay
     * @Description:基本户充值-转账，企业会员
     * @throws:
     * @date Jul 21, 2017 3:52:25 PM
     * @version: V1.0
     */
    public void transferPay(HashMap<String, String> hashMap) {
        try {
            new PayTypeOpt(driver).transferPayType();
            depositPage = new BasePage(driver, "网银转账", hashMap);
            depositPage.click("中国工商银行");
            depositPage.click("下一步");
            depositPage.click("网银转账下一步");
            AssertUtil.assertStringContains(driver, "0200041619200014090");
            depositPage.click("去网银转账");
            FrameUtil.switchToWindow(driver, "中国工商银行中国网站",3);
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }


}
