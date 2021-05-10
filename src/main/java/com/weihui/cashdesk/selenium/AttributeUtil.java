package com.weihui.cashdesk.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.weihui.cashdesk.utils.ReportUtil;


public class AttributeUtil {

    protected static ReportUtil log = new ReportUtil();

    /**
     * 获取元素某属性的值
     *
     * @param e
     * @param attributeName
     * @return 返回String
     * @author Yin
     */
    public static String getAttribute(WebElement e, String attributeName) {
        String value = e.getAttribute(attributeName);
        return value;
    }

    /**
     * 获取元素文本
     *
     * @param e
     * @author Yin
     */
    public static String getText(WebElement e) {
        String text = e.getText();
        return text;
    }

    /**
     * 获取当前网页标题
     *
     * @return 返回String
     * @author Yin
     */
    public static String getTitle(WebDriver driver) {
        String title = driver.getTitle();
        return title;
    }

    /**
     * 获取当前url
     *
     * @return url
     * @author Yin
     */
    public static String getUrl(WebDriver driver) {
        String url = driver.getCurrentUrl();
        return url;
    }
}
