package com.weihui.cashdesk.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.weihui.cashdesk.utils.ReportUtil;


public class MouseUtil {
    protected static ReportUtil log = new ReportUtil();

    /**
     * 鼠标单击
     *
     * @param e
     * @author Yin
     */
    public static void click(WebElement e) {
        e.click();
    }

    /**
     * 鼠标双击
     *
     * @param driver
     * @param e
     * @author Yin
     */
    public static void clickDouble(WebDriver driver, WebElement e) {
        Actions actions = new Actions(driver);
        actions.doubleClick(e).perform();
    }

    /**
     * 鼠标悬停
     *
     * @param driver
     * @param e
     * @author Yin
     */
    public static void clickAndHold(WebDriver driver, WebElement e) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(e).perform();
    }

    /**
     * 鼠标左键单击
     *
     * @param driver
     * @param e
     * @author Yin
     */
    public static void clickLeft(WebDriver driver, WebElement e) {
        Actions actions = new Actions(driver);
        actions.click(e).perform();
    }

    /**
     * 鼠標右键单击
     *
     * @param driver
     * @param e
     * @author Yin
     */
    public static void clickRight(WebDriver driver, WebElement e) {
        Actions actions = new Actions(driver);
        actions.contextClick(e).perform();
    }

    /**
     * 提交表单
     *
     * @param e
     * @author Yin
     */
    public static void submit(WebElement e) {
        e.submit();
    }
}
