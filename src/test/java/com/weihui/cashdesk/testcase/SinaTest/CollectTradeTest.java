package com.weihui.cashdesk.testcase.SinaTest;

import java.util.HashMap;
import java.util.Iterator;

import com.weihui.cashdesk.testcase.BaseTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.weihui.cashdesk.action.CollectTradeOpt;
import com.weihui.cashdesk.api.CreateHostingCollectTrade;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.ReadExcel;

public class CollectTradeTest extends BaseTestCase {
    private final String sheetName = "代收";


    @Test(dataProvider = "dp", description = "收银台代收用例")
    public void collect(HashMap<String, String> hashMap) {
        getCaseData(hashMap);
        new CreateHostingCollectTrade(driver).switchCashdesk(hashMap);
        CollectTradeOpt collectTradeOpt = new CollectTradeOpt(driver);

        if (caseType.equals("代收--银行账户支付")) {
            collectTradeOpt.bankAccountPay(hashMap);
        } else if (caseType.equals("代收-基本户支付")) {
            collectTradeOpt.basicAccountPay(hashMap);
        } else if (caseType.equals("代收-存钱罐支付")) {
            collectTradeOpt.savingPotAccountPay(hashMap);
        } else if (caseType.equals("代收--快捷支付")) {
            collectTradeOpt.quickPay(hashMap);
        } else if (caseType.equals("代收--绑卡支付")) {
            collectTradeOpt.cardPay(hashMap);
        } else if (caseType.equals("代收--存钱罐支付--支付密码错误")) {
            collectTradeOpt.savingPotPayPsdError(hashMap);
        }

        AssertUtil.assertStringContains(driver, hashMap);

    }


    @DataProvider(name = "dp")
    private Iterator<Object[]> dp() throws Exception {
        return new ReadExcel(sheetName);
    }
}
