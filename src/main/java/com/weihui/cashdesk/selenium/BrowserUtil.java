package com.weihui.cashdesk.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import com.weihui.cashdesk.utils.BrowserAssert;
import com.weihui.cashdesk.utils.ReportUtil;

public class BrowserUtil {
    protected static ReportUtil log = new ReportUtil();

    /**
     * 打开相应网址
     *
     * @param driver
     * @param url
     * @author Yin
     */
    public static void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    /**
     * 关闭页面
     *
     * @param driver
     * @author Yin
     */
    public static void closeUrl(WebDriver driver) {
        driver.close();
    }

    /**
     * @param driver
     * @author Yin
     */
    public static void quit(WebDriver driver) {
        driver.quit();
    }

    /**
     * @param driver
     * @author Yin
     */
    public static void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    /**
     * @param driver
     * @author Yin
     */
    public static void forword(WebDriver driver) {
        driver.navigate().forward();
    }

    /**
     * @param driver
     * @author Yin
     */
    public static void back(WebDriver driver) {
        driver.navigate().back();
    }

    /**
     * 得到网页源代码
     *
     * @param driver
     * @return
     * @author Yin
     */
    public static String getPageSource(WebDriver driver) {
        String resource = "";
        if (BrowserAssert.alertIsPresent(driver)) {
            AlertUtil.alertConfirm(driver);
        } else {
            resource = driver.getPageSource();
        }
        return resource;
    }

    /**
     * 浏览器最大化
     *
     * @param driver
     * @author Yin
     */
    public static void maxmizeBrowser(WebDriver driver) {
        driver.manage().window().maximize();
//        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(ConfigUtil.getValue("timeout")), TimeUnit.SECONDS);
    }

    /**
     * 设置延时时间
     *
     * @param second
     * @author Yin
     */
    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 设置页面加载时间
     *
     * @param driver
     * @param second
     * @author Yin
     */
    private static void pageLoadTime(WebDriver driver, long second) {
        driver.manage().timeouts().pageLoadTimeout(second, TimeUnit.SECONDS);
    }

    /**
     * 设置隐式等待时间
     *
     * @param driver
     * @param second
     * @author Yin
     */
    public static void implicitlyWait(WebDriver driver, long second) {
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }

    /**
     * 初始化浏览器相关设置
     *
     * @param driver
     * @param url
     * @param pagTime
     * @param impTime
     * @author Yin
     */
    public static void browserInit(WebDriver driver, String url, long pagTime, long impTime) {
        maxmizeBrowser(driver);
        openUrl(driver, url);
        implicitlyWait(driver, impTime);
        pageLoadTime(driver, pagTime);
    }

    public static void browserInit(WebDriver driver, long pagTime, long impTime) {
        maxmizeBrowser(driver);
        implicitlyWait(driver, impTime);
        pageLoadTime(driver, pagTime);
    }


}
