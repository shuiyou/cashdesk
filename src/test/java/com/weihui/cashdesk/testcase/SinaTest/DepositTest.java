package com.weihui.cashdesk.testcase.SinaTest;


import java.util.HashMap;
import java.util.Iterator;


import com.weihui.cashdesk.testcase.BaseTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.weihui.cashdesk.action.DepositOpt;
import com.weihui.cashdesk.api.CreateHostingDeposit;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.ReadExcel;


public class DepositTest extends BaseTestCase {

    private final String sheetName = "充值";


    @Test(dataProvider = "dp", description = "收银台充值用例")
    public void deposit(HashMap<String, String> hashMap) {
        getCaseData(hashMap);
        new CreateHostingDeposit(driver).swithcCashdesk(hashMap);
        DepositOpt depositOpt = new DepositOpt(driver);

        if (caseType.equals("基本户充值--网银支付")) {
            depositOpt.onLinePay(hashMap);
        } else if (caseType.contains("快捷支付")) {
            depositOpt.quickPay(hashMap);
        } else if (caseType.equals("银行账户充值--绑卡支付")) {
            depositOpt.cardPay(hashMap);
        } else if (caseType.contains("验证码错误")) {
            depositOpt.verifyCodeError(hashMap);
        } else if (caseType.equals("基本户充值--转账")) {
            depositOpt.transferPay(hashMap);
        } else if (caseType.contains("基本户充值--企业网银支付")) {
            depositOpt.basicAccountOnLinePay(hashMap);
        }

        AssertUtil.assertStringContains(driver, hashMap);

    }

    @DataProvider(name = "dp")
    private Iterator<Object[]> dp() throws Exception {
        return new ReadExcel(sheetName);
    }

}
