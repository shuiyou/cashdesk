package com.weihui.cashdesk.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.weihui.cashdesk.utils.ConfigUtil;
import com.weihui.cashdesk.utils.ReportUtil;

public class JSUtil {
    protected static ReportUtil log = new ReportUtil();

    /**
     * @param driver
     * @return void
     * @Title: moveToBottom
     * @Description: 移动到页面最底部
     * @author: Yin
     * @throws:
     * @date Mar 23, 2017 9:40:34 AM
     * @version: V1.0
     */
    public static void moveToBottom(WebDriver driver, int second) {
        BrowserUtil.sleep(second);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * 默认不延时
     * @param driver
     */
    public static void moveToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * js点击
     * @param driver
     * @param element
     */
    public static void click(WebDriver driver, WebElement element) {
        if (element.isEnabled() && element.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


    /**
     * @param driver
     * @param pix
     * @return void
     * @Title: moveToPosition
     * @Description: 移动到指定的坐标(相对当前的坐标移动)
     * @author: Yin
     * @throws:
     * @date Mar 23, 2017 9:42:34 AM
     * @version: V1.0
     */
    public static void moveToOpposite(WebDriver driver, int pix) {
        String tmp = "window.scrollBy(0," + pix + ")";
        ((JavascriptExecutor) driver).executeScript(tmp);
    }

    /**
     * @param driver
     * @param pix
     * @return void
     * @Title: moveToAbsolute
     * @Description:
     * @author: Yin
     * @throws:
     * @date Mar 23, 2017 10:06:26 AM
     * @version: V1.0
     */
    public static void moveToAbsolute(WebDriver driver, int pix, int second) {
        BrowserUtil.sleep(second);
        String tmp = "window.scrollBy(0," + pix + ")";
        ((JavascriptExecutor) driver).executeScript(tmp);
    }

    /**
     * @param driver
     * @param e
     * @param
     * @throws Exception
     * @author Yin
     */
    public static void typeQuick(WebDriver driver, WebElement e, String value) {
        //log.info("type value is:  " + values);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=\"" + value + "\"", e);

    }

    /**
     * @param driver
     * @param e
     * @param text
     * @author Yin
     */
    protected void setRichTextBox(WebDriver driver, WebElement e, String text) {
        log.info("type value is:  " + text);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = \"" + text + "\"", e);
    }

    /**
     * @param driver
     * @param e
     * @param text
     * @return
     * @author Yin
     */
    protected String getRichTextBox(WebDriver driver, WebElement e, String text) {
        log.info("type value is:  " + text);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String result = (String) js.executeScript("arguments[0].getInnerHTML()", e);
        log.info("the resule is: " + result);
        return result;
    }


    /**
     * 窗口滑动至相应元素
     *
     * @param e
     * @param driver
     * @author Yin
     */
    public static void scrollToElement(WebElement e, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", e);
    }

    /**
     * @param driver
     * @return boolean
     * @Title: waitForJStoLoad
     * @Description: 智能等待js代码加载完毕，避免手工处理拖动条问题
     * @author: Yin
     * @date Apr 26, 2017 10:21:35 AM
     * @version: V1.0
     */
    public static boolean waitForJStoLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ConfigUtil.getValue("timeout")));
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) js.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    /**
     * 只能等待元素页面可见
     * @param driver
     * @param element
     */
    public static void waitElementToBeDisplayed(WebDriver driver, final WebElement element) {
        try {
            new WebDriverWait(driver, Integer.parseInt(ConfigUtil
                    .getValue("timeout")))
                    .until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return element.isDisplayed();
                        }
                    });
        } catch (Exception e) {
            System.out.println(element.toString() + " is not displayed");
        }

    }
}
