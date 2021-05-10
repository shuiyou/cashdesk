/**
 *
 */
package com.weihui.cashdesk.utils;


/**
 * for remote browser bean
 *
 * @author Young
 */
public class RemoteBrowserBean {
    private String browserName;
    private String version;
    private String platform;
    private String host;
    private boolean javascriptEnabled = true;


    /**
     * @Title:
     * @Description:
     * @param:
     * @author: Yin
     */


    public RemoteBrowserBean(String browser, String version) {
        this.browserName = browser;
        this.version = version;
        this.platform = "LINUX";
        this.host = "http://192.168.99.100:4444/wd/hub";

    }

    public boolean getJavascriptEnabled() {
        return javascriptEnabled;
    }

    public void setJavascriptEnabled(boolean javascriptEnabled) {
        this.javascriptEnabled = javascriptEnabled;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
