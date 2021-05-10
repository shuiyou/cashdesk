package com.weihui.cashdesk.selenium;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;


public class AlertUtil {

    Logger logger = LogManager.getLogger(AlertUtil.class);

    /**
     * 处理弹出框：点击确定
     *
     * @param driver
     * @author Yin
     */
    public static void alertConfirm(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        try {
            alert.accept();
            //	log.info("点击js弹出框确定");
        } catch (Exception notFindAlert) {
            //    	log.error("当前页面没有js弹出框");
        }
    }

    /**
     * 处理弹出框：点击取消
     *
     * @param driver
     * @author Yin
     */
    public static void alertDismiss(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        try {
            alert.dismiss();
            //log.info("点击js弹出框取消");
        } catch (Exception notFindAlert) {
            //  	log.error("没有js弹出框");
        }
    }

    /**
     * 得到相应弹出框里的提示信息
     *
     * @param driver
     * @return
     * @author Yin
     */
    public static String getAlertText(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String value = null;
        try {
            value = alert.getText();
            //	log.info("得到js弹出框值: "+value);
        } catch (Exception notFindAlert) {
            //   	log.error("js弹出框没有值");
        }
        return value;
    }

    /**
     * 弹出框输入相应value
     *
     * @param driver
     * @param value
     * @author Yin
     */
    public static void inputAlinputAlertTextertText(WebDriver driver, String value) {
        Alert alert = driver.switchTo().alert();
        try {
            alert.sendKeys(value);
            //   log.info("js弹出框输入值: "+value);
        } catch (Exception notFindAlert) {
            //    log.error("没有js弹出框");
        }
    }
}
