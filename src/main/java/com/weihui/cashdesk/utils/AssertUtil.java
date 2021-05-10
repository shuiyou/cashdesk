package com.weihui.cashdesk.utils;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.weihui.cashdesk.selenium.BrowserUtil;

public class AssertUtil {

    static ReportUtil log = new ReportUtil();

    public static void assertStringContains(WebDriver driver, String exp) {
        String[] expResult = exp.split("\\|");
        boolean ret;
        ret = waitPageResourcesTimeOuts(driver, expResult);
        CheckPoint.checkisTrue(ret);
        BrowserUtil.sleep(1);// 暂停1秒后关闭
    }

    /**
     * @param driver
     * @return void
     * @Title: myAssertEquals 充值和代收用例断言
     * @Description: expResult传入如期结果，second：设置得到网页源代码延时时间
     * @author: han
     * @throws:
     * @date Apr 13, 2017 10:19:32 AM
     * @version: V1.0
     */

    public static boolean assertStringContainsFromPage(WebDriver driver, String exp) {
        String[] expResult = exp.split("\\|");
        boolean ret;
        ret = waitPageResourcesTimeOuts(driver, expResult);
        CheckPoint.checkisTrue(ret);
        BrowserUtil.sleep(1);// 暂停1秒后关闭
        return ret;
    }

    /**
     * @param driver
     * @return void
     * @Title: myAssertEquals 充值和代收用例断言
     * @Description: expResult传入如期结果，second：设置得到网页源代码延时时间
     * @author: Yin
     * @throws:
     * @date Apr 13, 2017 10:19:32 AM
     * @version: V1.0
     */
    public static void assertStringContains(WebDriver driver, HashMap<String, String> hashMap) {
        String[] expResult = hashMap.get("预期结果").split("\\|");
        boolean ret;
        ret = waitPageResourcesTimeOuts(driver, expResult);
        CheckPoint.checkisTrue(ret);
        BrowserUtil.sleep(1);// 暂停1秒后关闭
    }

    /**
     * @param actual
     * @param expected
     * @return void
     * @Title: myAssertEquals
     * @Description: 一般用来做数据库断言
     * @author: Yin
     * @throws:
     * @date Apr 13, 2017 10:39:29 AM
     * @version: V1.0
     */
    public static <Y> void myAssertEquals(Y actual, Y expected) {
        Assert.assertEquals(actual, expected);

    }

    /**
     * @param actual
     * @param expected
     * @param message
     * @return void
     * @Title: myAssertEquals
     * @Description:
     * @author: Yin
     * @throws:
     * @date Apr 13, 2017 10:40:25 AM
     * @version: V1.0
     */
    public static <Y> void myAssertEquals(Y actual, Y expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    /**
     * 断言多个
     *
     * @param driver
     * @param result
     */
    public static boolean waitPageResourcesTimeOuts(WebDriver driver, String[] result) {
        int i = 0;
        int j = 0;
        int second = Integer.parseInt(ConfigUtil.getValue("timeout"));
        boolean flag = false;
        String pageResources;
        while (!flag) {
            BrowserUtil.sleep(1);
            pageResources = BrowserUtil.getPageSource(driver);//1秒刷新页面resource
            for (String exp : result) {
                if (pageResources.contains(exp)) {
                    ++j;
                }
            }
            if (j == result.length) {
                flag = true;//所有断言全部通过退出循环
            }
            if (++i >= second) {//超时强制退出循环，流程结束
                break;
            }
        }
        return flag;
    }


    public static void waitPageResourcesTimeOuts(WebDriver driver, String result) {
        int i = 0;
        int second = Integer.parseInt(ConfigUtil.getValue("timeout"));
        boolean flag = false;
        String pageResources;
        while (!flag) {
            BrowserUtil.sleep(1);
            pageResources = BrowserUtil.getPageSource(driver);//1秒刷新页面resource
            if (pageResources.contains(result)) {
                flag = true;
            }
            if (++i >= second) {//超时强制退出循环，流程结束
                break;
            }
        }
    }


}
