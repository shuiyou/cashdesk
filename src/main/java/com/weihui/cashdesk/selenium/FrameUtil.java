package com.weihui.cashdesk.selenium;

import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.weihui.cashdesk.utils.ReportUtil;

public class FrameUtil {
    protected static ReportUtil log = new ReportUtil();
    static Logger logger = LogManager.getLogger(FrameUtil.class);

    /**
     * 切換frame
     *
     * @param driver
     * @param e
     * @author Yin
     */
    public static void switchToFrame(WebDriver driver, WebElement e) {
        try {
            driver.switchTo().frame(e);
        } catch (Exception noSuchFrame) {
            log.error("跳转frame失败");
            logger.error("跳转frame失败");
        }

    }

    /**
     * @param driver
     * @param iframe
     * @author Yin
     */
    public static void switchToFrame(WebDriver driver, String iframe) {
        try {
            driver.switchTo().frame(iframe);
        } catch (Exception e) {
            log.error("跳转frame失败");
            logger.error("跳转frame失败");
        }
    }

    /**
     * 切回默認窗口
     *
     * @param driver
     * @author Yin
     */
    public static void switchToDefaultFrame(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            log.error("跳转默认frame失败");
            logger.error("跳转frame失败");
        }
    }

    /**
     * @param driver
     * @param windowTitle
     * @return boolean
     * @Title: switchToWindow
     * @Description:根据标题切换窗口
     * @author: Yin
     * @throws:
     * @date Apr 26, 2017 9:47:05 AM
     * @version: V1.0
     */
    public static void switchToWindow(WebDriver driver, String windowTitle, int second) {
        BrowserUtil.sleep(second);
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle)) {
                    continue;
                } else {
                    driver.switchTo().window(s);
                    if (driver.getTitle().equals(windowTitle)) {
                        break;
                    } else {
                        continue;
                    }
                }
            }
        } catch (NoSuchWindowException e) {
            log.error("跳转至: " + windowTitle + " 页面失败!" + e.fillInStackTrace());
            logger.error("跳转至: " + windowTitle + " 页面失败!" + e.fillInStackTrace());

        }
    }

    /**
     * @param driver
     * @param title
     * @return boolean
     * @Title: closeOtherWindowByTitle
     * @Description: 根据标题关掉窗口
     * @author: Yin
     * @throws:
     * @date Apr 26, 2017 9:46:25 AM
     * @version: V1.0
     */
    public static void closeOtherWindowByTitle(WebDriver driver, String title) {
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle)) {
                    continue;
                } else {
                    driver.switchTo().window(s);
                    if (driver.getTitle().contains(title)) {
                        driver.close();
                        break;
                    } else {
                        continue;
                    }
                }
            }
            driver.switchTo().window(currentHandle);
        } catch (NoSuchWindowException e) {
            log.error("Window: " + title + " cound not found!" + e.fillInStackTrace());
            logger.error("Window: " + title + " cound not found!" + e.fillInStackTrace());
        }
    }

    /**
     * @param driver
     * @return void
     * @Title: closeOtherWindowByTitle
     * @Description: 保留当前url 关闭其余url
     * @author: Yin
     * @throws:
     * @date May 12, 2017 1:58:36 PM
     * @version: V1.0
     */
    public static void closeOtherWindow(WebDriver driver) {
        String title = null;
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle)) {
                    continue;
                } else {
                    driver.switchTo().window(s);
                    title = driver.getTitle();
                    driver.close();
                    driver.switchTo().window(currentHandle);
                }
            }

        } catch (NoSuchWindowException e) {
            log.error("Window: " + title + " cound not found!" + e.fillInStackTrace());
            logger.error("Window: " + title + " cound not found!" + e.fillInStackTrace());
        }
    }

}
