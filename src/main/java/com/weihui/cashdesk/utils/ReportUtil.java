package com.weihui.cashdesk.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.Reporter;

/**
 * @author 尹全旺
 */
public class ReportUtil {

    public static List<String> logRecord = new ArrayList<>();


    /*********************************************************************************************
     * 写log和报告操作
     *********************************************************************************************/
    /**
     * 写日志和报告
     *
     * @param comm
     */
    public void log(String... comm) {
        String time = ConfigUtil.getTime();
        if (comm.length == 0) {
            Reporter.log("[" + time + "] ");
        } else {
            Reporter.log("[" + time + "] " + comm[0] );
        }
    }

    public void info(String comm) {
        String time = ConfigUtil.getTime();
        Reporter.log( "[" + time + "] [INFO] " + comm);
    }

    public void debug(String comm) {
        String time = ConfigUtil.getTime();
        Reporter.log("[" + time + "] [DEBUG] " + comm);
    }

    /**
     * 写错误日志和报告
     *
     * @param comm
     */
    public void error(String comm) {
        String time = ConfigUtil.getTime();
        Reporter.log("[" + time + "] [ERROR] " + comm);
    }

    /**
     * 写警告日志和报告
     *
     * @param comm
     */
    public void warn(String comm) {
        String time = ConfigUtil.getTime();
        Reporter.log("[" + time + "] [WARN] " + comm);
    }


    /**
     * 写醒目的标题
     *
     * @param comm
     */
    public void title(String comm) {
        Reporter.log(comm);
    }


    public static String getTestName(Object[] parameters) {
        HashMap<String,String> hashMap = (HashMap<String, String>) parameters[0];
        return hashMap.get("用例编号")+"-"+hashMap.get("用例场景");
    }
}
