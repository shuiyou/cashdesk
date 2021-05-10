package com.weihui.cashdesk.testcase.SinaTest;

import com.weihui.cashdesk.api.SmerchantUrlGet;
import com.weihui.cashdesk.testcase.BaseTestCase;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.ReadExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by hanxiaodi on 18/9/30.
 */
public class SmerchantUrlGetTest extends BaseTestCase {
	private final String sheetName = "获取子商户链接";


	/**
	 *
	 * @param hashMap
	 */
	@Test(dataProvider = "dp")
	public void switchSmerchantUrl(HashMap<String, String> hashMap) {
		getCaseData(hashMap);
		new SmerchantUrlGet(driver).getSemerchatUrl(hashMap);
		AssertUtil.assertStringContains(driver, hashMap);

	}

	@DataProvider(name = "dp")
	private Iterator<Object[]> dp() throws Exception {
		return new ReadExcel(sheetName);
	}
}
