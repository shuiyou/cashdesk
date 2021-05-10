package com.weihui.cashdesk.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.weihui.cashdesk.utils.Locator.ByType;

public class XmlUtil {
    public static String xmlPath;

    /**
     * @param path
     * @param pageName
     * @return HashMap<String,Locator>
     * @Title: readXMLDocument
     * @Description: 读取xml中Locator元素对象信息
     * @author: Yin
     * @throws:
     * @date May 4, 2017 2:25:51 PM
     * @version: V1.0
     */
    public static HashMap<String, Locator> readXMLDocument(String path, String pageName) {
        ReportUtil log = new ReportUtil();
        HashMap<String, Locator> locatorMap = new HashMap<>();
        locatorMap.clear();
        File file = new File(path);
        if (!file.exists()) {
            log.error("xmlPath路径非法 " + path);
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {

        }

        getLocator(document, "测试工具公共参数", locatorMap);
        getLocator(document, pageName, locatorMap);
        return locatorMap;
    }

    /**
     * @param document
     * @param pageName
     * @param locatorMap
     * @return void
     * @Title: getLocator
     * @Description:
     * @author: Yin
     * @throws:
     * @date May 22, 2017 11:02:33 AM
     * @version: V1.0
     */
    private static void getLocator(Document document, String pageName, HashMap<String, Locator> locatorMap) {
        Element root = document.getRootElement();
        for (Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
            Element page = (Element) i.next();
            if (page.attribute(0).getValue().equalsIgnoreCase(pageName)) {
                for (Iterator<?> l = page.elementIterator(); l.hasNext(); ) {
                    String type = null;
                    String timeOut = ConfigUtil.getValue("timeout");
                    String value = null;
                    String locatorName;
                    Element locator = (Element) l.next();
                    for (Iterator<?> j = locator.attributeIterator(); j.hasNext(); ) {
                        Attribute attribute = (Attribute) j.next();
                        if (attribute.getName().equals("type")) {
                            type = attribute.getValue();
                        } else if (attribute.getName().equals("timeOut")) {
                            timeOut = attribute.getValue();
                        } else if (attribute.getName().equals("value")) {
                            value = attribute.getValue();
                        }
                    }
                    Locator temp = new Locator(value, Long.parseLong(timeOut) - 15,
                            getByType(type));
                    locatorName = locator.getText();
                    locatorMap.put(locatorName, temp);
                }
                continue;
            }

        }
    }

    /**
     * @param type
     * @return ByType
     * @Title: getByType
     * @Description: 匹配定位方式
     * @author: Yin
     * @throws:
     * @date Mar 31, 2017 6:41:30 PM
     * @version: V1.0
     */
    public static ByType getByType(String type) {
        ByType byType = ByType.xpath;
        if (type == null || type.equalsIgnoreCase("xpath")) {
            byType = ByType.xpath;
        } else if (type.equalsIgnoreCase("id")) {
            byType = ByType.id;
        } else if (type.equalsIgnoreCase("linkText")) {
            byType = ByType.linkText;
        } else if (type.equalsIgnoreCase("name")) {
            byType = ByType.name;
        } else if (type.equalsIgnoreCase("className")) {
            byType = ByType.className;
        } else if (type.equalsIgnoreCase("css")) {
            byType = ByType.cssSelector;
        } else if (type.equalsIgnoreCase("partialLinkText")) {
            byType = ByType.partialLinkText;
        } else if (type.equalsIgnoreCase("tagName")) {
            byType = ByType.tagName;
        }
        return byType;
    }
}
