package com.weihui.cashdesk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @ClassName: ConfigUtil
 * @Description:
 * @author: Yin
 * @date: Apr 27, 2017 12:03:57 PM
 * @version: V1.0
 */
public class ConfigUtil {
    protected static ReportUtil log = new ReportUtil();
    protected static Logger logger = LogManager.getLogger(ConfigUtil.class);
    protected static String currentDir = System.getProperty("user.dir");

    /**
     * @param config
     * @return Properties
     * @throws IOException
     * @Title: getProperties
     * @Description:
     * @author: Yin
     * @throws:
     * @date May 4, 2017 2:25:31 PM
     * @version: V1.0
     */
    public static Properties getProperties(String config) throws IOException {
        Properties properties = new Properties();
        FileInputStream inStream = new FileInputStream(new File(config));
        try {
            properties.load(inStream);
        } finally {
            inStream.close();
        }
        return properties;
    }

    /**
     * @param apiUrl
     * @return String
     * @Title: getUrl
     * @Description: 切换url
     * @author: Yin
     * @throws:
     * @date May 4, 2017 2:25:19 PM
     * @version: V1.0
     */
    public static String getUrl(String apiUrl) {
        String config = currentDir + "/src/main/resources/config.properties";
        Properties properties = new Properties();
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(new File(config));
            properties.load(inStream);
        } catch (Exception e) {
            log.error("配置文件内容错误或者路径异常");
            logger.error("配置文件内容错误或者路径异常");
        } finally {
            try {
                inStream.close();
            } catch (IOException e) {
            }
        }
        return properties.getProperty(apiUrl);
    }

    /**
     * @param key
     * @return String
     * @Title: getValue
     * @Description:
     * @author: Yin
     * @throws:
     * @date May 4, 2017 2:25:15 PM
     * @version: V1.0
     */
    public static String getValue(String key) {
        String config = currentDir + "/src/main/resources/config.properties";
        Properties properties = new Properties();
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(new File(config));
            properties.load(inStream);
        } catch (Exception e) {
            log.error("配置文件内容错误或者路径异常");
            logger.error("配置文件内容错误或者路径异常");

        } finally {
            try {
                inStream.close();
            } catch (IOException e) {
            }
        }
        return properties.getProperty(key);
    }

    public static String getTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return sf.format(date);
    }


}
