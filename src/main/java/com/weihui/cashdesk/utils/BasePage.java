package com.weihui.cashdesk.utils;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.weihui.cashdesk.selenium.AttributeUtil;
import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.selenium.InputUtil;
import com.weihui.cashdesk.selenium.JSUtil;
import com.weihui.cashdesk.selenium.MouseUtil;


/**
 * @author 尹全旺
 */
public class BasePage {
    protected WebDriver driver;
    private HashMap<String, Locator> locatorMap;
    private HashMap<String, String> dataMap;
    protected ReportUtil log = new ReportUtil();
    private Logger logger = LogManager.getLogger(this.getClass());
    private String pageName = null;

    public BasePage(WebDriver driver, String pageName) {
        this.driver = driver;
        this.pageName = pageName;
        try {
            locatorMap = XmlUtil.readXMLDocument(XmlUtil.xmlPath, pageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BasePage(WebDriver driver, String pageName, HashMap<String, String> hashMap) {
        this.driver = driver;
        this.pageName = pageName;
        try {
            locatorMap = XmlUtil.readXMLDocument(XmlUtil.xmlPath, pageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataMap = hashMap;
    }

    /**
     * @param locatorName
     * @return void
     * @Title: type
     * @Description: 传入locator映射到page.xml和excel参数列标题
     * @author: Yin
     * @throws:
     * @date Apr 26, 2017 1:54:09 PM
     * @version: V1.0
     */
    public void type(String locatorName) {
        WebElement e = findElement(driver, locatorName);
        String value;
        if (dataMap.containsKey(locatorName)) {
            value = dataMap.get(locatorName);
            InputUtil.clearText(e);
            InputUtil.inputText(e, value);
            log.info(locatorName + ":" + value);
            logger.info(locatorName + ":" + value);
        }
    }

    /**
     * @param locatorName
     * @param value
     * @return void
     * @Title: type
     * @Description: 手动传入文本值
     * @author: Yin
     * @throws:
     * @date Apr 26, 2017 1:53:44 PM
     * @version: V1.0
     */
    public void type(String locatorName, String value) {
        WebElement e = findElement(driver, locatorName);
        InputUtil.clearText(e);
        InputUtil.inputText(e, value);
        log.info(locatorName + ":" + value);
        logger.info(locatorName + ":" + value);
    }

    /**
     * @param locatorName
     * @return String
     * @Title: getText
     * @Description: 得到当前标签value值
     * @author: Yin
     * @throws:
     * @date May 4, 2017 12:42:30 PM
     * @version: V1.0
     */
    public String getText(String locatorName) {
        WebElement e = findElement(driver, locatorName);
        return AttributeUtil.getAttribute(e, "value");
    }

    /**
     * @param locatorName
     * @return String
     * @Title: getValue
     * @Description: 得到标签包含的key值
     * @author: Yin
     * @date Jul 24, 2017 5:54:27 PM
     * @version: V1.0
     */
    public String getValue(String locatorName) {
        WebElement e = findElement(driver, locatorName);
        return AttributeUtil.getText(e);
    }

    /**
     * @param locatorName
     * @return void
     * @Title: click
     * @Description: 没有元素直接抛异常，正常流程，直接执行下一个test
     * @author: Yin
     * @throws:
     * @date Apr 25, 2017 3:02:30 PM
     * @version: V1.0
     */
    public void click(String locatorName) {
        WebElement e = findElement(driver, locatorName);
        MouseUtil.click(e);
        log.info("点击" + locatorName);
        logger.info("点击" + locatorName);
    }



    /**
     * @param driver
     * @param locatorName
     * @return WebElement
     * @Title: findElement
     * @Description: 定位元素，无 返回null
     * @author: Yin
     * @throws:
     * @date May 4, 2017 12:43:19 PM
     * @version: V1.0
     */
    private WebElement findElement(WebDriver driver, final String locatorName) {
        long waittime = getLocator(locatorName).getWaitSec();
        BrowserUtil.implicitlyWait(driver, waittime);
        return getElement(driver, locatorName);
    }

    /**
     * @param driver
     * @param locatorName
     * @return WebElement
     * @Title: getElement
     * @Description: 自适应定位元素，7种
     * @author: Yin
     * @throws:
     * @date May 4, 2017 12:43:41 PM
     * @version: V1.0
     */
    private WebElement getElement(WebDriver driver, String locatorName) {
        WebElement e = null;
        Locator locator = getLocator(locatorName);
        try {
            switch (locator.getByType()) {
                case xpath:
                    e = driver.findElement(By.xpath(locator.getElement()));
                    break;
                case id:
                    e = driver.findElement(By.id(locator.getElement()));
                    break;
                case name:
                    e = driver.findElement(By.name(locator.getElement()));
                    break;
                case cssSelector:
                    e = driver.findElement(By.cssSelector(locator.getElement()));
                    break;
                case className:
                    e = driver.findElement(By.className(locator.getElement()));
                    break;
                case tagName:
                    e = driver.findElement(By.tagName(locator.getElement()));
                    break;
                case linkText:
                    e = driver.findElement(By.linkText(locator.getElement()));
                    break;
                case partialLinkText:
                    e = driver.findElement(By.partialLinkText(locator.getElement()));
                    break;
                default:
                    e = driver.findElement(By.xpath(locator.getElement()));
            }
            JSUtil.waitElementToBeDisplayed(driver, e);
            while (!JSUtil.waitForJStoLoad(driver)) {}
            JSUtil.scrollToElement(e, driver);
        } catch (Exception e1) {
            String msg = locatorName + "元素定位失败,请检查" + pageName + "页面" + locatorName + "元素定位信息！";
            log.error(msg);
            logger.error(msg);
            ReportUtil.logRecord.add(msg);
            throw new NoSuchElementException();
        }
        return e;
    }

    /**
     * @param locatorName
     * @return Locator
     * @Title: getLocator
     * @Description: 获取元素对象信息，对应page.xml key
     * @author: Yin
     * @throws:
     * @date May 4, 2017 12:43:57 PM
     * @version: V1.0
     */
    private Locator getLocator(String locatorName) {
        Locator locator = null;
        if (locatorMap != null) {
            if (!locatorMap.containsKey(locatorName)) {
                String msg = XmlUtil.xmlPath + this.pageName + "页面没有" + locatorName + "元素信息";
                log.error(msg);
                logger.error(msg);
                ReportUtil.logRecord.add(msg);
            } else {
                locator = locatorMap.get(locatorName);
            }
        }
        return locator;
    }
}
