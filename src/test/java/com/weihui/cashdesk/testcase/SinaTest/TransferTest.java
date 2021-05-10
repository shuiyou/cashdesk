package com.weihui.cashdesk.testcase.SinaTest;

import java.util.HashMap;
import java.util.Iterator;

import com.weihui.cashdesk.testcase.BaseTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.weihui.cashdesk.action.TransferOpt;
import com.weihui.cashdesk.api.CreateHostingTransfer;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.ReadExcel;

public class TransferTest extends BaseTestCase {

    private final String sheetName = "转账";


    /**
     *
     * @param hashMap
     */
    @Test(dataProvider = "dp")
    public void transfer(HashMap<String, String> hashMap) {

        /********************** 数据部分 *********************************/
        getCaseData(hashMap);
        String payerAccountType = hashMap.get("payer_account_type");

        new CreateHostingTransfer(driver).hostingtranfer(hashMap);
        TransferOpt transferPage = new TransferOpt(driver);

        if (payerAccountType.contains("BASIC")) {
            transferPage.transferByBasic(hashMap);
        } else if (payerAccountType.contains("BANK")) {
            transferPage.transferByBank(hashMap);
        } else if (payerAccountType.contains("SAVING_POT")) {
            transferPage.transferBySavingPot(hashMap);
        }
        AssertUtil.assertStringContains(driver, hashMap);
    }

    @DataProvider(name = "dp")
    private Iterator<Object[]> dp() throws Exception {
        return new ReadExcel(sheetName);
    }

}
