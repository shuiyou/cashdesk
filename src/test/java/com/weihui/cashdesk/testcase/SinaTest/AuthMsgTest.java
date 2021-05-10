package com.weihui.cashdesk.testcase.SinaTest;

import java.util.HashMap;
import java.util.Iterator;

import com.weihui.cashdesk.testcase.BaseTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.weihui.cashdesk.action.AuthMsgOpt;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.ReadExcel;

public class AuthMsgTest extends BaseTestCase {

    private final String sheetName = "认证信息";


    /**
     *
     * @param hashMap
     */
    @Test(dataProvider = "dp")
    public void authMsg(HashMap<String, String> hashMap) {
        getCaseData(hashMap);
        if (caseType.contains("修改认证手机")) {
            new AuthMsgOpt(driver).modifyPhone(hashMap);
        } else if (caseType.contains("找回认证手机")) {
            new AuthMsgOpt(driver).findPhone(hashMap);
        } else if (caseType.contains("修改支付密码")) {
            new AuthMsgOpt(driver).modifyPasswd(hashMap);
        } else if (caseType.contains("找回支付密码")) {
            new AuthMsgOpt(driver).findPasswd(hashMap);
        } else if (caseType.contains("验证支付密码")) {
            new AuthMsgOpt(driver).verifyPasswd(hashMap);
        } else if (caseType.contains("设置支付密码")) {
            new AuthMsgOpt(driver).setPasswd(hashMap);
        } else if (caseType.contains("交易设置支付密码")) {
            new AuthMsgOpt(driver).setPasswdByTrade(hashMap);
        }

        AssertUtil.assertStringContains(driver, hashMap);
    }

    @DataProvider(name = "dp")
    private Iterator<Object[]> dp() throws Exception {
        return new ReadExcel(sheetName);
    }
}
