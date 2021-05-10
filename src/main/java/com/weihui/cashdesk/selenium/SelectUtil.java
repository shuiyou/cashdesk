package com.weihui.cashdesk.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.weihui.cashdesk.utils.ReportUtil;

public class SelectUtil {
    protected static ReportUtil log = new ReportUtil();

    /**
     * 根据value定位相应下拉列表元素
     *
     * @param e
     * @param value
     * @throws Exception
     * @author Yin
     */
    public static void select(WebElement e, String value) {
        Select select = new Select(e);
        try {
            select.selectByValue(value);
        } catch (Exception notByValue) {
            select.selectByVisibleText(value);
        }
    }

    /**
     * 根据index定位相应下拉列表元素
     *
     * @param e
     * @param index
     * @throws Exception
     * @author Yin
     */
    public static void select(WebElement e, int index) {
        Select select = new Select(e);
        try {
            select.selectByIndex(index);
        } catch (Exception notByValue) {
            log.error("索引值非法: " + index);
        }
    }
}
