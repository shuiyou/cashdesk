package com.weihui.cashdesk.selenium;

import org.openqa.selenium.WebElement;
import com.weihui.cashdesk.utils.ReportUtil;


public class InputUtil {

    protected static ReportUtil log = new ReportUtil();

    /**
     * 相应元素输入相应值
     *
     * @param e
     * @param value
     * @author Yin
     */
    public static void inputText(WebElement e, String value) {
        e.sendKeys(value);
    }

    /**
     * 清除文本内容
     *
     * @param e
     * @throws Exception
     * @author Yin
     */
    public static void clearText(WebElement e) {
        e.clear();
    }

}
