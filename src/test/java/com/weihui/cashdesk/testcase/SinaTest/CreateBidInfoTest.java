package com.weihui.cashdesk.testcase.SinaTest;


import com.weihui.cashdesk.api.CreateBidInfo;
import com.weihui.cashdesk.testcase.BaseTestCase;
import com.weihui.cashdesk.utils.AssertUtil;
import com.weihui.cashdesk.utils.ReadExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by hanxiaodi on 17/12/19.
 */
public class CreateBidInfoTest extends BaseTestCase
{
	private final String sheetName = "创建标的";


	/**
	 *
	 * @param hashMap
	 */
	@Test(dataProvider = "dp")
	public void CreateBidInfoTest(HashMap<String, String> hashMap) {

		/********************** 数据部分 *********************************/
		getCaseData(hashMap);
		new CreateBidInfo(driver).createbid(hashMap);
		AssertUtil.assertStringContains(driver, hashMap);
	}

	@DataProvider(name = "dp")
	private Iterator<Object[]> dp() throws Exception {
		return new ReadExcel(sheetName);
	}
}
