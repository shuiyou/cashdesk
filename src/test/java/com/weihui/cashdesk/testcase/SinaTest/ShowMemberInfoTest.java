package com.weihui.cashdesk.testcase.SinaTest;

import java.util.HashMap;
import java.util.Iterator;

import com.weihui.cashdesk.testcase.BaseTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.weihui.cashdesk.api.ShowMemberInfo;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.ReadExcel;

public class ShowMemberInfoTest extends BaseTestCase {
    private final String sheetName = "安全站点";


    /**
     *
     * @param hashMap
     */
    @Test(dataProvider = "dp")
    public void switchPersonalCentre(HashMap<String, String> hashMap) {
        getCaseData(hashMap);
        new ShowMemberInfo(driver).getCashdeskUrl(hashMap);
        AssertUtil.assertStringContains(driver, hashMap);

    }

    @DataProvider(name = "dp")
    private Iterator<Object[]> dp() throws Exception {
        return new ReadExcel(sheetName);
    }
}
