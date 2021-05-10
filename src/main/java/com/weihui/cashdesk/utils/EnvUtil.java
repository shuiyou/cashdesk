package com.weihui.cashdesk.utils;

import java.util.HashMap;

import com.weihui.cashdesk.selenium.BrowserUtil;


public class EnvUtil {

    /**
     *
     * @param locatorName
     * @param photoNumber
     * @param basePage
     * @param start
     * @param end
     */
    public static void getVerifyCode(String locatorName, String photoNumber, BasePage basePage, int start, int end) {
        String testEnv = ConfigUtil.getValue("envflag");
        if (testEnv.equals("test")) {
            String code = OracleUtil.getVerifyCode(photoNumber, start, end);
            basePage.type(locatorName, code);
        } else if (testEnv.equals("pro")) {
            basePage.type(locatorName, "");
            BrowserUtil.sleep(60);
        }
    }

    /**
     * 环境切换
     *
     * @param hashMap
     * @param basePage
     * @return void
     * @Title: testEnv
     * @Description:
     * @author: Yin
     * @throws:
     * @date Apr 18, 2017 9:33:51 AM
     * @version: V1.0
     */
    public static void testEnv(HashMap<String, String> hashMap, BasePage basePage) {
        String testEnv = ConfigUtil.getValue("envflag");
        if (testEnv.equals("test")) {
            basePage.click("测试环境");
            if (hashMap.get("用例场景").contains("快捷")) {
                if (hashMap.containsKey("identity_id")) {
                    OracleUtil.deleteBankCard(hashMap.get("identity_id"));
                } else if (hashMap.containsKey("payer_id")) {
                    OracleUtil.deleteBankCard(hashMap.get("payer_id"));
                }
            }
        } else {
            basePage.click("生产环境");
        }
    }

    /**
     * 最后必須移動到底部
     *
     * @param hashMap
     * @param basePage
     * @return void
     * @Title: testEnvKey
     * @Description:
     * @author: Yin
     * @throws:
     * @date Apr 14, 2017 4:05:26 PM
     * @version: V1.0
     */
    public static void testEnvKey(HashMap<String, String> hashMap, BasePage basePage) {
        String testEnv = ConfigUtil.getValue("envflag");
        if (testEnv.equals("test")) {
            basePage.click("测试环境加密公钥");
        } else {
            basePage.click("生产环境加密公钥");
        }
    }
}
