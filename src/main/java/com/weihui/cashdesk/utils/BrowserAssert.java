package com.weihui.cashdesk.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.weihui.cashdesk.selenium.BrowserUtil;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author
 * @version 创建时间：2017年1月16日 下午2:36:21
 */
public class BrowserAssert {
    protected static ReportUtil log = new ReportUtil();

    /**
     * @param driver
     * @return boolean
     * @Title: alertIsPresent
     * @Description: 断言当前页面是否有js弹出框
     * @author: Yin
     * @throws:
     * @date May 4, 2017 3:35:59 PM
     * @version: V1.0
     */
    public static boolean alertIsPresent(WebDriver driver) {
        boolean ret = false;
        try {
            driver.switchTo().alert();
            ret = true;
        } catch (NoAlertPresentException e) {
        }
        return ret;
    }

    /**
     * @param element
     * @return boolean
     * @Title: ElementIsEnabled
     * @Description:
     * @author: Yin
     * @throws:
     * @date May 4, 2017 3:36:24 PM
     * @version: V1.0
     */
    public static boolean elementIsEnabled(WebElement element) {
        boolean ret = false;
        try {
            element.isEnabled();
            //    log.info(element+"元素被使能");
            ret = true;
        } catch (Exception e) {
            log.error(element + "元素被禁止");
        }
        return ret;
    }

    /**
     * @param checkBox
     * @return boolean
     * @Title: chekBoxIsSelected
     * @Description:
     * @author: Yin
     * @throws:
     * @date May 4, 2017 3:36:32 PM
     * @version: V1.0
     */
    public static boolean chekBoxIsSelected(WebElement checkBox) {
        boolean ret = false;
        try {
            checkBox.isSelected();
            //    log.info(checkBox+"复选框已勾选");
            ret = true;
        } catch (Exception e) {
            log.error(checkBox + "没勾选");
        }
        return ret;
    }

    /**
     * @param element
     * @return boolean
     * @Title: elementIsVisible
     * @Description:
     * @author: Yin
     * @throws:
     * @date May 4, 2017 3:36:54 PM
     * @version: V1.0
     */
    public static void elementTimeOuts(WebElement element) {
        int i = 0;
        int second = 6;
        while (!element.isDisplayed()) {
            BrowserUtil.sleep(1);
            if (++i >= second) {
                break;
            }
        }
    }

    /**
     * @param driver
     * @param value
     * @return boolean
     * @Title: elementExist
     * @Description: 断言页面是否还有改元素 xpath定位方式
     * @author: Yin
     * @throws:
     * @date May 4, 2017 3:37:07 PM
     * @version: V1.0
     */
    public static boolean elementExist(WebDriver driver, String value) {
        boolean ret = false;
        try {
            driver.findElement(By.xpath(value));
            ret = true;
        } catch (NoSuchElementException e) {
        }
        return ret;
    }


    private boolean waitElementToBeDisplayed(WebDriver driver, final WebElement element) {
        boolean wait = false;
        if (element == null) {
            return wait;
        }
        try {
            wait = new WebDriverWait(driver, Integer.parseInt(ConfigUtil
                    .getValue("timeout")))
                    .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver d) {
                            return element.isDisplayed();
                        }
                    });
        } catch (Exception e) {
            System.out.println(element.toString() + " is not displayed");
        }
        return wait;
    }


}
