package com.weihui.cashdesk.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    private ReportUtil log = new ReportUtil();
    private String chromedriver;
    private String firefoxdriver;
    private Properties p = null;
    private String IEDriverServer;
    private String EDGEDriver;
    private String currentDir = System.getProperty("user.dir");
    private String config = currentDir + "/src/main/resources/config.properties";
    private String OSType = System.getProperty("os.name");// 得到当前操作系统

    static WebDriver driver = null;
    public static DriverFactory driverfactory;

    public enum BrowserType {
        firefox, chrome, ie, edge, safari
    }

    /**
     * @return DriverFactory
     * @Title: getInstance
     * @Description: 得到唯一对象
     * @author: Yin
     * @throws:
     * @date Mar 30, 2017 1:40:41 PM
     * @version: V1.0
     */
    public static DriverFactory getInstance() {
        if (driverfactory == null) {
            synchronized (DriverFactory.class) {
                driverfactory = new DriverFactory();
            }
        }
        return driverfactory;
    }

    /**
     * @return WebDriver
     * @Title: getFirefoxDriver
     * @Description: 得到火狐浏览器
     * @author: Yin
     * @throws:
     * @date Mar 30, 2017 1:40:32 PM
     * @version: V1.0
     */
    public synchronized WebDriver getFirefoxDriver() {
        try {
            p = ConfigUtil.getProperties(config);

        } catch (Exception e) {
            log.error("can not find firefox process");
        }
        if (p != null) {
            if (!OSType.contains("Mac")) {
                WindowsUtils.killByName("geckodriver.exe");
                firefoxdriver = p.getProperty("firefoxdriver");
            } else {
                firefoxdriver = p.getProperty("MAC_firefoxdriver");
            }
        }
        firefoxdriver = currentDir + "/" + firefoxdriver;
        System.setProperty("webdriver.gecko.driver", firefoxdriver);
        driver = new FirefoxDriver();
        return driver;

    }

    /**
     * @return WebDriver
     * @Title: getIEDriver
     * @Description: 得到IE浏览器
     * @author: Yin
     * @throws:
     * @date Mar 30, 2017 1:40:28 PM
     * @version: V1.0
     */
    public synchronized WebDriver getIEDriver() {
        try {
            p = ConfigUtil.getProperties(config);
            WindowsUtils.killByName("IEDriverServer.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (p != null) {
            IEDriverServer = p.getProperty("IEDriverServer");
        }
        System.setProperty("webdriver.ie.driver", IEDriverServer);
    /*
	 * String PROXY = "http://proxy:8083"; 设置代理 org.openqa.selenium.Proxy
	 * proxy = new org.openqa.selenium.Proxy();
	 * proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
	 * 
	 * DesiredCapabilities ds = DesiredCapabilities.internetExplorer();
	 * ds.setCapability(InternetExplorerDriver.
	 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	 * ds.setCapability("ignoreProtectedModeSettings", true);
	 * ds.setCapability(CapabilityType.PROXY, proxy); driver = new
	 * InternetExplorerDriver(ds);
	 */
        driver = new InternetExplorerDriver();
        return driver;
    }

    public synchronized WebDriver getChromeDriver() {

        try {
            p = ConfigUtil.getProperties(config);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        if (p != null) {
            if (!OSType.contains("Mac")) {
                WindowsUtils.killByName("chromedriver.exe");
                chromedriver = p.getProperty("chromedriver");
            } else {
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("pkill chromedriver");
                } catch (IOException e) {
                    System.out.println("关闭程序异常");
                }
                chromedriver = p.getProperty("MAC_chromedriver");
            }
        }

        System.setProperty("webdriver.chrome.driver", chromedriver);
        ChromeOptions options = new ChromeOptions();
      /*  options.addArguments("enable-automation");
        options.addArguments("--disable-infobars");*/
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
        options.addArguments("--test-type", "--start-maximized");
        driver = new ChromeDriver(options);
        return driver;
    }

    public synchronized WebDriver getRemoteDriver(RemoteBrowserBean remoteBrowserBean) {
        DesiredCapabilities capability = null;
        if (remoteBrowserBean.getBrowserName().contains("firefox")) {
            capability = DesiredCapabilities.firefox();
        } else if (remoteBrowserBean.getBrowserName().contains("chrome")) {
            capability = DesiredCapabilities.chrome();
            capability.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
        } else {
            capability = DesiredCapabilities.internetExplorer();
        }

        capability.setBrowserName(remoteBrowserBean.getBrowserName());
        capability.setVersion(remoteBrowserBean.getVersion());
        capability.setCapability("platform", remoteBrowserBean.getPlatform());
        capability.setJavascriptEnabled(remoteBrowserBean.getJavascriptEnabled());
        try {
            driver = new RemoteWebDriver(new URL(remoteBrowserBean.getHost()), capability);
            // log.info(remoteBrowserBean.getBrowserName()+"start success");
        } catch (MalformedURLException e) {
            // log.info(remoteBrowserBean.getBrowserName()+"launch failed");
        }
        return driver;
    }

    /**
     * @return WebDriver
     * @Title: getEDGEDriver
     * @Description:
     * @author: Yin
     * @throws:
     * @date Mar 30, 2017 1:43:31 PM
     * @version: V1.0
     */
    public synchronized WebDriver getEDGEDriver() {
        try {
            p = ConfigUtil.getProperties(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (p != null) {
            WindowsUtils.killByName("MicrosoftWebDriver.exe");
            EDGEDriver = p.getProperty("EDGEDriver");


        }
        System.setProperty("webdriver.edge.driver", EDGEDriver);

	 /*String PROXY =
	 "https://raw.githubusercontent.com/seveniruby/gfwlist2pac/master/test/proxy.pac";
	 Proxy proxy = new org.openqa.selenium.Proxy();
	 proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
	 DesiredCapabilities capabilities = DesiredCapabilities.edge();
	 EdgeOptions options = new EdgeOptions();
	 options.setPageLoadStrategy("normal");
	 capabilities.setCapability(EdgeOptions.CAPABILITY, options);
	 capabilities.setCapability(CapabilityType.PROXY, proxy);
	 driver = new EdgeDriver(capabilities);*/

        driver = new EdgeDriver();
        return driver;
    }

    /**
     * @return BrowserType
     * @Title: getBrowserType
     * @Description:
     * @author: Yin
     * @throws:
     * @date Mar 30, 2017 1:40:12 PM
     * @version: V1.0
     */
    public BrowserType getBrowserType() {

        if (driver instanceof FirefoxDriver) {
            return BrowserType.firefox;
        } else if (driver instanceof ChromeDriver) {
            return BrowserType.chrome;
        } else if (driver instanceof InternetExplorerDriver) {
            return BrowserType.ie;
        } else if (driver instanceof EdgeDriver) {
            return BrowserType.edge;
        } else if (driver instanceof SafariDriver) {
            return BrowserType.safari;
        } else {
            return null;
        }

    }
}
