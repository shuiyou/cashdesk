package com.weihui.cashdesk.utils;

/**
 * @ClassName: Locator
 * @Description:
 * @author: Yin
 * @date: Mar 31, 2017 6:38:28 PM
 * @version: V1.0
 */
public class Locator {
    private String element;
    private long waitSec;
    private ByType byType;

    public enum ByType {
        xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
    }

    public Locator() {

    }

    public Locator(String element) {
        this.element = element;
        this.waitSec = 3;
        this.byType = ByType.xpath;
    }

    public Locator(String element, long waitSec) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = ByType.xpath;
    }

    public Locator(String element, long waitSec, ByType byType) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = byType;
    }


    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public long getWaitSec() {
        return waitSec;
    }

    public void setWaitSec(long waitSec) {
        this.waitSec = waitSec;
    }

    public ByType getByType() {
        return byType;
    }

    public void setByType(ByType byType) {
        this.byType = byType;
    }


}
