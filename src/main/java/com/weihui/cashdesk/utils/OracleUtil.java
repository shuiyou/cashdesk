package com.weihui.cashdesk.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.weihui.cashdesk.selenium.BrowserUtil;

public class OracleUtil {

    private static ReportUtil log = new ReportUtil();
    private static String DBAddress = "10.65.193.11:1521";
    private static String DBName = "whpay";
    private static String userName = null;
    private static String password = null;
    private static DbEnviornment de;

    /**
     * @return String
     * @Title: getVerifyCode
     * @Description:充值验证码
     * @author: Yin
     * @throws:
     * @date Mar 23, 2017 5:10:54 PM
     * @version: V1.0
     */
    public static String getVerifyCode(String phoneNumber, int start, int end) {
        BrowserUtil.sleep(20);
        OracleUtil.userName = "reader";
        OracleUtil.password = "tF1P7IC7oKa6ua";
        String phone = " '%" + phoneNumber + "%'";
        String getCodeSQL = "select to_char(CONTENT) from (select * from mns.t_notify_msg "
                + "where gmt_create > sysdate-1 and target_address like" + phone + "order by gmt_create desc)"
                + "where rownum = 1";
        String code = getData(getCodeSQL, "to_char(CONTENT)").substring(start, end);
        return code;

    }


    /**
     * @param sql
     * @param field
     * @return String
     * @Title: getData
     * @Description:
     * @author: Yin
     * @throws:
     * @date Mar 27, 2017 2:43:32 PM
     * @version: V1.0
     */
    private static String getData(String sql, String field) {
        String data = null;
        try {
            de = new DbEnviornment();
            de.Connect(DBAddress, DBName, userName, password);
            ResultSet rs = DbEnviornment.statement.executeQuery(sql);
            while (rs.next()) {
                data = rs.getString(field);
            }
            rs.close();
            DbEnviornment.statement.close();
            DbEnviornment.con.close();
            return data;
        } catch (SQLException e) {
            log.error("得到数据失败！！！");
        }
        return null;
    }

    /**
     * @param identity
     * @return void
     * @Title: deleteBankCard
     * @Description: 删除银行卡
     * @author: Yin
     * @throws:
     * @date Jun 2, 2017 3:26:43 PM
     * @version: V1.0
     */
    public static void deleteBankCard(String identity) {
        OracleUtil.userName = "MEMBERUSER";
        OracleUtil.password = "MXUfAfINpNrwJz";
        String memberId = getData("select * from member.tm_member_identity where identity= '" + identity + "'", "MEMBER_ID");
        try {
            de = new DbEnviornment();
            de.Connect(DBAddress, DBName, userName, password);
            ResultSet rs = DbEnviornment.statement.executeQuery("delete from member.tr_bank_account where member_id = '" + memberId + "'");
            rs.close();
            DbEnviornment.con.commit();
            DbEnviornment.statement.close();
            DbEnviornment.con.close();
        } catch (SQLException e) {
            log.error("删除异常！！");
        }
    }
}