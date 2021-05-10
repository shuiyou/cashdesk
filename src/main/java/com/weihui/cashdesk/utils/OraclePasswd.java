package com.weihui.cashdesk.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class OraclePasswd {

    public String getPasswd(String username) {
        String jsonString = "{\"READER\":\"tF1P7IC7oKa6ua\"," + "\"ADMUSER\":\"LYryhzD20ddG7A\","
                + "\"ACSUSER\":\"duWbzZL1nIg7tD\"," + "\"AUTHORIZEUSER\":\"dv0hTbVacbG0A4\","
                + "\"AVATARUSER\":\"edNN4Qprm7hqJZ\"," + "\"BASISUSER\":\"Wzc2oEXdBDluSd\","
                + "\"BUSINESSUSER\":\"4XZ4COoPkDgV0s\"," + "\"CASHIERUSER\":\"5Z6bdZFhhHgpZC\","
                + "\"CMFUSER\":\"vsuOjjAV7MgoTt\"," + "\"CSAUSER\":\"qvJTWMLgUGXwdD\","
                + "\"DPMUSER\":\"uYkkbNYaVOxFI6\"," + "\"EWALLETUSER\":\"J3oPfzuGiKff0p\","
                + "\"EXCHUSER\":\"8PNcjyn6uUjPzv\"," + "\"EZFLOWUSER\":\"E5oLD6mBJTO01b\","
                + "\"FFSUSER\":\"n1hcMXSG11W5JZ\"," + "\"FOSUSER\":\"8p3Y4qDEEJCQuC\","
                + "\"GUARDIANUSER\":\"l7p38x7FmS8JxY\"," + "\"LEADUSER\":\"m6YHUMKFTtiRDQ\","
                + "\"LEADUTILUSER\":\"N1gmUSAZhYdzUn\"," + "\"LFLTUSER\":\"j5wkTRPc3cRFfM\","
                + "\"LOGGERUSER\":\"NIh91tAVnHfulI\"," + "\"MARKETINGUSER\":\"LPMXUkFURFQL2W\","
                + "\"MEMBERUSER\":\"MXUfAfINpNrwJz\"," + "\"MKTCUSER\":\"3ujGkwHwTvMyRj\","
                + "\"MNSUSER\":\"wYUQfomjBBNIkP\"," + "\"MONITORUSER\":\"CPpO95paxM3BWp\","
                + "\"PBSDBUSER\":\"6kwYASW022WoGA\"," + "\"PEUSER\":\"rPnFqktZG3ogN4\","
                + "\"PKIUSER\":\"55H9LU3p3JrStJ\"," + "\"POSSUSER\":\"XvyZcaov3snWRJ\","
                + "\"RMSSCHEDULERUSER\":\"Z4gBVqSxloDTrU\"," + "\"RMSUSER\":\"yyVx6WwcbodANr\","
                + "\"TOKENUSER\":\"f4DUDAK5lAy6Q3\"," + "\"UBSUSER\":\"fodMFJbJJPfgrI\","
                + "\"UESUSER\":\"LrfsqRt2cg4XEF\"," + "\"WALLETUSER\":\"HYgtSGzh5Y2bCH\","
                + "\"WAPPUSER\":\"kQPS16Co2iGmOF\"," + "\"WBOSUSER\":\"atgfkmHRttvSdW\","
                + "\"WMASUSER\":\"vdAy7ZdKngry4r\"," + "\"WOPSUSER\":\"4yfV78h6A41cs4\","
                + "\"WQUERY\":\"9r0VSR3kXRMT17\"," + "\"XIAOMIUSER\":\"itzH89JBtX0OoG\","
                + "\"ASSETUSER\":\"yvvtrWFJWBaKgM\"," + "\"AUTHUSER\":\"wQmufuQEWtdT4n\","
                + "\"BILLUSER\":\"LooN1U0M6wxYfM\"," + "\"CCENTERUSER\":\"7XzfeUsHGgwk8T\","
                + "\"CERCNTRUSER\":\"Ljg3HI6lsVjzov\"," + "\"EXCHUSER\":\"iUutc6RmkkDTeC\","
                + "\"FCMUSER\":\"4y6Xhs0X0YYlxz\"," + "\"FINCDPMUSER\":\"2akCiTisKOHiQI\","
                + "\"FOSSUSER\":\"jKym2uGnHAqmmk\"," + "\"FSYNCUSER\":\"9PMjRMyKyqAjHY\","
                + "\"FTSUSER\":\"0L2WGPQNbQP8y0\"," + "\"FUAUSER\":\"2hB0VIcZYS6dAB\","
                + "\"OSERVERUSER\":\"OouFMLfIfhYBHX\"," + "\"PAYMENTMANAGER\":\"gyDdVauynnOFOR\","
                + "\"PAYMENTUSER\":\"EpXmLBg3tRhWlL\"," + "\"PCSUSER\":\"8rHu8M5Bj7uN4r\","
                + "\"PFSUSER\":\"RvFcjv7HpdTPPw\"," + "\"SFCMUSER\":\"F4bGYvPp0Z3Nee\","
                + "\"SMSGATEWAYUSER\":\"3KomYO9Ba5fhGE\"," + "\"VSITEUSER\":\"ZWS0qExAS2WJse\","
                + "\"WMASUSER\":\"vdAy7ZdKngry4r\"," + "\"WHQA\":\"whqa\"," + "\"MAYUNFEI\":\"MAYUNFEI\","
                + "\"MARGIN\":\"margin\"," + "\"CMEMBERUSER\":\"EmVz85KDqmrQ6A\"," + "\"DEBTUSER\":\"rzGoG0AQbjlRN1\","
                + "\"MANAGER\":\"manager\"," + "\"QA\":\"qa\"," + "\"PILOTUSER\":\"xWeh7ya0bGXQ80\"}";
        String Value ;
        JSONObject jsonObject = JSON.parseObject(jsonString, JSONObject.class);
        username = username.toUpperCase();
        Value = jsonObject.get(username).toString();
        return Value;
    }

}
