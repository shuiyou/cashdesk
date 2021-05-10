package com.weihui.cashdesk.testcase.SinaTest;

import java.util.HashMap;
import java.util.Iterator;

import com.weihui.cashdesk.testcase.BaseTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.weihui.cashdesk.action.CreatePreAuthTradeOpt;
import com.weihui.cashdesk.api.CreatePreAuthTrade;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.ReadExcel;

public class CreatePreAuthTradeTest extends BaseTestCase {

    private final String sheetName = "预授权";


    /**
     * @return void
     * @Title: createPreAuthTrade
     * @Description:
     * @author: Lin
     * @throws:
     * @date Jul 21, 2017 11:09:18 AM
     * @version: V1.0
     */
    @Test(dataProvider = "dp")
    public void createPreAuthTrade(HashMap<String, String> hashMap) {
        getCaseData(hashMap);
        new CreatePreAuthTrade(driver).swithcCashdesk(hashMap);
        CreatePreAuthTradeOpt cpatOpt = new CreatePreAuthTradeOpt(driver);

        if (caseType.contains("基本户")) {
            cpatOpt.tradeByBasic(hashMap);
        } else if (caseType.contains("存钱罐")) {
            cpatOpt.tradeBySavingPot(hashMap);
        } else if (caseType.contains("托管银行")) {
            cpatOpt.tradeByHostingBank(hashMap);
        }

        AssertUtil.assertStringContains(driver, hashMap);
    }

    @DataProvider(name = "dp")
    private Iterator<Object[]> dp() throws Exception {
        return new ReadExcel(sheetName);
    }


}
