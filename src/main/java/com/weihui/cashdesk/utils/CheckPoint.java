package com.weihui.cashdesk.utils;


import org.testng.Assert;
import org.testng.Reporter;

public class CheckPoint {

    /**
     * @return
     * @TODO：检查 int 型数字
     * @author
     * @parameter @param actual 实际结果
     * @parameter @param expected 预期结果
     * @parameter @param comment 备注信息，可写可不写
     */
    public static void checkIntNum(int actual, int expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
            Assert.fail("检查点检查出错误");
        }
    }

    /**
     * @return
     * @TODO： 检查String类型，实际字符串与预期结果是否一致
     * @author
     * @parameter @param actual 实际结果
     * @parameter @param expected 预期结果
     * @parameter @param comment 备注信息，可写可不写
     */
    public static void checkString(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
            Assert.fail("检查点检查出错误");
        }
    }

    /**
     * @return
     * @TODO： 检查String类型，实际字符串中是否包含指定字段，存在为正确，不存在为错误
     * @author
     * @parameter @param actual 实际结果
     * @parameter @param expected 预期结果
     * @parameter @param comment 备注信息，可写可不写
     */
    public static void checkStringContains(String actual, String expected) {
        try {
            Assert.assertEquals(true, actual.contains(expected));
        } catch (AssertionError e) {
            Assert.fail("检查点检查出错误");
        }
    }

    /**
     * @return
     * @TODO：检查String类型，实际字符串中是否存在指定字段，存在为错，不存在为正确
     * @author
     * @parameter @param actual 实际结果
     * @parameter @param expected 预期结果
     * @parameter @param comment 备注信息，可写可不写
     */
    public static void checkStringNotContains(String actual, String expected) {
        try {
            Assert.assertEquals(false, actual.contains(expected));
        } catch (AssertionError e) {
            Assert.fail("检查点检查出错误");
        }
    }

    /**
     * @return
     * @TODO： double类型校验
     * @author
     * @parameter @param actual 实际结果
     * @parameter @param expected 期望结果
     * @parameter @param comment 备注信息，可写可不写
     */
    public static void checkDouble(double actual, double expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
            Assert.fail("检查点检查出错误");
        }
    }

    /**
     * @return
     * @TODO：检查实际double字符串中是否包含指定double字段，实际结果为空时，不校验
     * @author
     * @parameter @param actual 实际结果
     * @parameter @param expected 预期结果
     * @parameter @param comment 备注信息，可写可不写
     */
    public static void checkDoubleNotNull(double actual, double expected) {
        if (actual != 0 && expected != 0) {
            try {
                Assert.assertEquals(actual, expected);
            } catch (AssertionError e) {
                Assert.fail("检查点检查出错误");
            }
        }
    }

    /**
     * @return
     * @TODO：检查boolean类型实际结果是否为真
     * @author
     * @parameter @param actual 需要对比的条件，布尔类型
     * @parameter @param comment 备注信息，可写可不写
     */
    public static void checkisTrue(boolean actual) {
        try {
            Assert.assertEquals(true, actual);
        } catch (AssertionError e) {
            Assert.fail("预期结果与实际结果不一致，断言失败");
            ReportUtil.logRecord.add("预期结果与实际结果不一致，断言失败");
        }
    }

    /**
     * @return
     * @TODO：检查boolean类型
     * @author
     * @parameter @param actual 实际结果
     * @parameter @param expected 预期结果
     * @parameter @param comment 备注信息，可写可不写
     */
    public static void checkBoolean(boolean actual, boolean expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
            Assert.fail("检查点检查出错误");
        }
    }


    /**
     * @return
     * @TODO：Check里的专用打印格式模板
     * @author
     * @parameter @param comm 备注信息
     * @parameter @param expected 预期结果
     * @parameter @param result 结果
     */
    private static void ptFormat(String comm, String expected, String result) {
        String ptmsg;
        ptmsg = "┌───────────────────────┤ＣＨＥＣＫ　ＰＯＩＮＴ├───────────────────────┐<br>";
        ptmsg = ptmsg + "[预期结果]：" + comm + "<br>";
        if (result.contains("PASS")) {
            ptmsg = ptmsg + "└──────────────────────────────────────────────────────────────────┘";
            ptmsg = "<P style=\"font-size:1.1em;color:#1C9340\"><b>" + ptmsg + "</b></P>";
            Reporter.log(ptmsg);
        }
    }

}


