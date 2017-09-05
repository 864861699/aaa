package com.sixi.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公用方法
 *
 * @Author 艾翔
 * @Date 2017/8/28 14:42
 */
public class Fn {
    private final static Pattern intPaddern = Pattern.compile("^[+-]?[0-9]+$");

    /**
     * 判断字符串是否可以转int
     *
     * @param str 字符串
     * @return
     */
    public static Boolean isInt(String str) {
        if (str == null) {
            return false;
        }

        return intPaddern.matcher(str).find();
    }

    /**
     * 判断对象是否可以转int
     *
     * @param o
     * @return
     */
    public static Boolean isInt(Object o) {
        if (o == null) {
            return false;
        }
        String str = o.toString();

        Matcher mer = intPaddern.matcher(str);
        return mer.find();
    }

    /**
     * 对象转int(默认是对象的toString())，失败返回 failureVal
     *
     * @param o
     * @param failureVal
     * @return
     */
    public static Integer toInt(Object o, int failureVal) {
        if (o == null) {
            return failureVal;
        }
        String str = o.toString();

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return failureVal;
        }
    }

    /**
     * 对象转int(默认是对象的toString())，失败返回 0
     *
     * @param o
     * @return
     */
    public static Integer toInt(Object o) {
        if (o == null) {
            return 0;
        }
        String str = o.toString();
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * 字符串重新组合 “1,2,a,3,4” -> 1,2,3,4
     *
     * @param t
     * @return
     */
    public static String clearNoInt(String t) {
        String[] arr = t.split(",");
        List<String> list = new ArrayList<String>();
        for (String val : arr) {
            if (isInt(val)) {
                list.add(val);
            }
        }

        return String.join(",", list);
    }

    /**
     * 过滤字符串前后空格，支持全角
     *
     * @param str
     * @return 若为null，返回空字符串
     */
    public static String trim(String str) {
        if (str == null) {
            return "";
        }
        //第1个：全角空格 encodeURI('　')=%E3%80%80
        //第2个：半角空格 encodeURI(' ')=%20
        //第3个：特殊空格 encodeURI(' ')=%C2%A0
        while (str.startsWith("　") || str.startsWith(" ") || str.startsWith(" ")) {// 全角空格
            str = str.substring(1, str.length()).trim();
        }
        while (str.endsWith("　") || str.endsWith(" ") || str.endsWith(" ")) {// 全角空格
            str = str.substring(0, str.length() - 1).trim();
        }
        return str;
    }

    /**
     * 过滤字符串前后空格，支持全角
     *
     * @param o
     * @return 若为null，返回空字符串
     */
    public static String trim(Object o) {
        if (o == null) {
            return "";
        }
        return trim(o.toString());
    }

    public static String md5(String s) {
        return DigestUtils.md5Hex(s);
    }

    public static String md5_16(String s) {
        return DigestUtils.md5Hex(s).substring(8, 24);
    }

    /**
     * 获取当前登录人id
     *
     * @return
     */
    public static Integer getLoginUserId() {
        Integer userid = Session.getSession("userid");
        return Fn.toInt(userid);
    }

    /**
     * 获取当前登录人一级部门
     *
     * @return
     */
    public static Integer getLoginClass1() {
        Integer class1 = Session.getSession("class1");
        return Fn.toInt(class1);
    }

    /**
     * 获取当前登录人二级部门
     *
     * @return
     */
    public static Integer getLoginClass2() {
        Integer class2 = Session.getSession("class2");
        return Fn.toInt(class2);
    }

    /**
     * 对象转字符串，当null时，返回空字符串，并去除前后空格
     *
     * @param o
     * @return
     */
    public static String toString(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }

    /**
     * 对象转double，失败返回0
     *
     * @param o
     * @return
     */
    public static Double toDouble(Object o) {
        if (o == null) {
            return 0d;
        }
        String str = o.toString();

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            return 0d;
        }
    }

    /**
     * 判断字符串是否可以转 指定的时间类型
     *
     * @param strDate
     * @param dateFormat 时间格式
     * @return
     */
    public static Boolean isDateFormat(String strDate, String dateFormat) {
        if (strDate == null) {
            return false;
        }

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        format.setLenient(false);
        try {
            format.parse(strDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 字符串传时间类型带
     *
     * @param datastr 传入时间字符串 如:2016-01-01,2016-01-01 00:00:00
     * @return 字符串转时间类型, 如果转换成功返回类型为时间类型：2016-01-01 00：00：00，转换失败返回null
     */
    public static Timestamp toTimestamp(String datastr) {
        datastr = datastr.length() == 10 ? datastr + " 00:00:00" : datastr;
        if (!Fn.isDateFormat(datastr, "yyyy-MM-dd HH:mm:ss")) {
            return null;
        }
        return Timestamp.valueOf(datastr);
    }

    /**
     * 获取当前时间字符串格式 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }


//    /**
//     * 集合转化分页 2016年9月10日 下午5:57:04
//     *
//     * @param list
//     *            需分页的集合
//     * @param pageIndex
//     *            页码
//     * @param pageSize
//     *            每页记录数
//     * @return 返回Page对象，Page对象的getList()返回结果是经过筛选后的当前pageIndex页的记录
//     */
//    public static <T> Page<T> listToPage(Collection<T> list, int pageIndex, int pageSize) {
//        if (pageIndex <= 0) {
//            pageIndex = 1;
//        }
//        if (pageSize <= 0) {
//            pageSize = 1;
//        }
//
//        int rowTotal = list.size();// 总条数
//        int pageTotal = rowTotal / pageSize;// 总页数
//        int startIndex = (pageIndex - 1) * pageSize;// 所在页码的开始索引
//        int endIndex = startIndex + pageSize;// 所在页码的结束索引
//
//        // 若最后一页不满pageSize条
//        if (rowTotal % pageSize > 0) {
//            pageTotal++;
//        }
//
//        List<T> tmpResult = new ArrayList<>();// 分页后的结果集
//
//        int itIndex = 0;// 迭代器索引
//        Iterator<T> it = list.iterator();
//        while (it.hasNext()) {
//            if (itIndex >= endIndex) {
//                break;// 找完后退出
//            }
//
//            T val = (T) it.next();
//            // 若页码在指的开始索引和结束索引，则加入结果集
//            if (startIndex <= itIndex && itIndex < endIndex) {
//                tmpResult.add(val);
//            }
//            itIndex++;
//        }
//        Page<T> result=new Page<>(tmpResult);
//        return result;
//    }
}
