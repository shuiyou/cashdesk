package com.weihui.cashdesk.action;

import java.util.HashMap;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.weihui.cashdesk.api.CreateHostingDeposit;
import com.weihui.cashdesk.api.FindPayPasswd;
import com.weihui.cashdesk.api.FindVerifyPhone;
import com.weihui.cashdesk.api.ModifyPayPasswd;
import com.weihui.cashdesk.api.ModifyVerifyMobile;
import com.weihui.cashdesk.api.SetPayPasswd;
import com.weihui.cashdesk.api.VerifyPayPasswd;
import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.utils.BasePage;
import com.weihui.cashdesk.utils.EnvUtil;

public class AuthMsgOpt {

    private WebDriver driver = null;
    private BasePage securityMsgPage = null;

    public AuthMsgOpt(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @param hashMap
     * @return void
     * @Title: modifyPhone
     * @Description: 修改认证手机
     * @author: Yin
     * @throws:
     * @date May 22, 2017 4:33:18 PM
     * @version: V1.0
     */
    public void modifyPhone(HashMap<String, String> hashMap) {
        try {
            String url = new ModifyVerifyMobile(driver).getCashdeskUrl(hashMap);
            BrowserUtil.openUrl(driver, url);
            securityMsgPage = new BasePage(driver, "修改认证手机", hashMap);
            securityMsgPage.click("短信获取1");
            EnvUtil.getVerifyCode("验证码1", hashMap.get("手机号"), securityMsgPage, 4, 10);
            securityMsgPage.click("下一步");
            securityMsgPage.type("新手机号");
            securityMsgPage.click("短信获取2");
            EnvUtil.getVerifyCode("验证码2", hashMap.get("新手机号"), securityMsgPage, 4, 10);
            securityMsgPage.click("完成");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }

    }

    /**
     * @param hashMap
     * @return void
     * @Title: findPhone
     * @Description: 找回认证手机
     * @author: Yin
     * @throws:
     * @date May 22, 2017 5:15:09 PM
     * @version: V1.0
     */
    public void findPhone(HashMap<String, String> hashMap) {
        try {
            String url = new FindVerifyPhone(driver).getCashdeskUrl(hashMap);
            BrowserUtil.openUrl(driver, url);
            securityMsgPage = new BasePage(driver, "找回认证手机", hashMap);
            securityMsgPage.type("支付密码");
            securityMsgPage.click("下一步1");
            securityMsgPage.type("身份证号码");
            securityMsgPage.click("下一步2");
            securityMsgPage.type("新手机号");
            securityMsgPage.click("短信获取");
            EnvUtil.getVerifyCode("验证码", hashMap.get("新手机号"), securityMsgPage, 4, 10);
            securityMsgPage.click("完成");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: modifyPasswd
     * @Description: 修改支付密码
     * @author: Yin
     * @throws:
     * @date May 22, 2017 5:15:22 PM
     * @version: V1.0
     */
    public void modifyPasswd(HashMap<String, String> hashMap) {
        try {
            String url = new ModifyPayPasswd(driver).getCashdeskUrl(hashMap);
            BrowserUtil.openUrl(driver, url);
            securityMsgPage = new BasePage(driver, "修改支付密码", hashMap);
            securityMsgPage.type("支付密码");
            securityMsgPage.type("新密码");
            securityMsgPage.type("确认密码", hashMap.get("新密码"));
            securityMsgPage.click("完成");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: findPasswd
     * @Description: 找回支付密码
     * @author: Yin
     * @throws:
     * @date May 22, 2017 5:28:40 PM
     * @version: V1.0
     */
    public void findPasswd(HashMap<String, String> hashMap) {
        try {
            String url = new FindPayPasswd(driver).getCashdeskUrl(hashMap);
            BrowserUtil.openUrl(driver, url);
            securityMsgPage = new BasePage(driver, "找回支付密码", hashMap);
            securityMsgPage.click("短信获取");
            EnvUtil.getVerifyCode("验证码", hashMap.get("手机号"), securityMsgPage, 4, 10);
            securityMsgPage.click("下一步1");
            securityMsgPage.type("身份证号码");
            securityMsgPage.click("下一步2");
            securityMsgPage.type("新密码");
            securityMsgPage.type("确认密码", hashMap.get("新密码"));
            securityMsgPage.click("完成");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: verifyPasswd
     * @Description: 验证支付密码
     * @author: Yin
     * @throws:
     * @date May 22, 2017 5:40:32 PM
     * @version: V1.0
     */
    public void verifyPasswd(HashMap<String, String> hashMap) {
        try {
            String url = new VerifyPayPasswd(driver).getCashdeskUrl(hashMap);
            BrowserUtil.openUrl(driver, url);
            securityMsgPage = new BasePage(driver, "验证支付密码", hashMap);
            securityMsgPage.type("支付密码");
            securityMsgPage.click("完成");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }
    }

    /**
     * @param hashMap
     * @return void
     * @Title: setPasswd
     * @Description:设置支付密码
     * @author: Yin
     * @throws:
     * @date May 23, 2017 9:58:16 AM
     * @version: V1.0
     */
    public void setPasswd(HashMap<String, String> hashMap) {
        try {
            String url = new SetPayPasswd(driver).getCashdeskUrl(hashMap);
            BrowserUtil.openUrl(driver, url);
            securityMsgPage = new BasePage(driver, "设置支付密码", hashMap);
            securityMsgPage.type("手机号");
            securityMsgPage.click("短信获取");
            EnvUtil.getVerifyCode("验证码", hashMap.get("手机号"), securityMsgPage, 4, 10);
            securityMsgPage.type("支付密码");
            securityMsgPage.type("确认密码", hashMap.get("支付密码"));
            securityMsgPage.click("完成");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }

    }

    /**
     * @param hashMap
     * @return void
     * @Title: setPasswdByTrade
     * @Description: 通过托管充值设置交易密码
     * @author: Yin
     * @throws:
     * @date May 23, 2017 10:16:11 AM
     * @version: V1.0
     */
    public void setPasswdByTrade(HashMap<String, String> hashMap) {
        try {
            new CreateHostingDeposit(driver).swithcCashdesk(hashMap);
            securityMsgPage = new BasePage(driver, "设置支付密码", hashMap);
            securityMsgPage.type("手机号");
            securityMsgPage.click("短信获取");
            EnvUtil.getVerifyCode("验证码", hashMap.get("手机号"), securityMsgPage, 4, 10);
            securityMsgPage.type("支付密码");
            securityMsgPage.type("确认密码", hashMap.get("支付密码"));
            securityMsgPage.click("完成");
        } catch (NoSuchElementException e) {
            throw new AssertionError("测试流程所必需的元素定位失败，用例被迫终止");
        }


    }

}
