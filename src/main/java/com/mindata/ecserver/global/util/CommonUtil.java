package com.mindata.ecserver.global.util;

import com.xiaoleilu.hutool.date.DatePattern;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Array;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mindata.ecserver.global.Constant.*;

/**
 * @author wuweifeng wrote on 2017/11/7.
 */
public class CommonUtil {
    public static Date getNow() {
        return new Date();
    }

    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public static String getNowStr() {
        return DateUtil.formatDateTime(new Date());
    }

    /**
     * 生成uuid
     *
     * @return token
     */
    public static String token() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取总页数
     *
     * @param totalCount 总数量
     * @param pageSize   一页多少
     * @return 结果
     */
    public static Integer getTotalPages(Integer totalCount, Integer pageSize) {
        return totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
    }

    /**
     * 截取double2位
     *
     * @param d d
     * @return 截取后结果
     */
    public static Double cutDouble2(Double d) {
        if (d == null) {
            return 0.00;
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        return Double.parseDouble(df.format(d));
    }

    /**
     * 只接受这样的格式2017-09-3，不能带时分秒
     *
     * @param date 年月日
     * @return 该天的开始
     */
    public static Date beginOfDay(String date) {
        DateFormat dateFormat = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        try {
            return dateFormat.parse(date + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 只接受这样的格式2017-09-3，不能带时分秒
     *
     * @param date 年月日
     * @return 该天的结束
     */
    public static Date endOfDay(String date) {
        DateFormat dateFormat = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        try {
            return dateFormat.parse(date + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取本机计算机名称
     */
    public static String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    /**
     * 获取昨天
     *
     * @return String
     */
    public static String getYesterday() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, -1);
        Date yesterDay = ca.getTime();
        DateFormat dateFormat = new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN);
        return dateFormat.format(yesterDay);
    }

    /**
     * 从一个字符串里截取大的数字
     *
     * @param content content
     * @return String
     */
    public static Integer getNumber(String content) {
        if (StrUtil.isEmpty(content)) {
            return null;
        }
        String[] names = content.split("[\\D]+");
        if (names.length == 1) {
            return Integer.valueOf(names[0]);
        }
        return Integer.valueOf(names[1]);
    }

    /**
     * 获取codeSize表的name
     *
     * @param codeSizeName codeSizeName
     * @return String
     */
    public static Integer cutCodeSizeName(String codeSizeName) {
        Integer codeSize = getNumber(codeSizeName);
        if (codeSize == null) {
            return CODESIZE_OTHER;
        }
        if (codeSize > 2000) {
            return CODESIZE_2000;
        }
        if (codeSize > 501 && codeSize <= 2000) {
            return CODESIZE_501_2000;
        }
        if (codeSize > 151 && codeSize <= 500) {
            return CODESIZE_151_500;
        }
        if (codeSize > 51 && codeSize <= 150) {
            return CODESIZE_51_150;
        }
        if (codeSize <= 50) {
            return CODESIZE_0_50;
        }
        return CODESIZE_OTHER;
    }

    /**
     * 处理手机号
     *
     * @param phone phone
     * @return boolean
     */
    public static String reviseMobile(String phone) {
        if (StrUtil.isNotEmpty(phone)) {
            String regex = "1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}";
            if (phone.contains(",")) {
                String[] phones = phone.split(",");
                for (int i = 0; i < phones.length; i++) {
                    String phoneTemp = phones[i];
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(phoneTemp);
                    boolean flag = m.matches();
                    if (flag) {
                        return phoneTemp;
                    }
                }
            } else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                boolean flag = m.matches();
                if (flag) {
                    return phone;
                }
            }

        }
        return "";
    }

    /**
     * 处理固定电话
     *
     * @param fixedTelephone 固定电话
     * @return boolean
     */
    public static String reviseFixedTelephone(String fixedTelephone) {
        if (StrUtil.isNotEmpty(fixedTelephone)) {
            String regex = "((0\\d{2,3}-?)?\\d{3,4}-?\\d{3,4}(-\\d{1,4})?)|(400-?(\\d{3,4}-?\\d{3,4})(-\\d{1,4})?)";
            if (fixedTelephone.contains(",")) {
                String[] aa = fixedTelephone.split(",");
                for (int i = 0; i < aa.length; i++) {
                    String bb = aa[i];
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(bb);
                    boolean flag = m.matches();
                    if (flag) {
                        return bb;
                    }
                }
            } else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(fixedTelephone);
                boolean flag = m.matches();
                if (flag) {
                    return fixedTelephone;
                }
            }

        }
        return "";
    }

    public static void main(String[] ags) {
        System.out.println(reviseFixedTelephone("18612923210"));
//        System.out.println(Double.valueOf(""));
    }
}
