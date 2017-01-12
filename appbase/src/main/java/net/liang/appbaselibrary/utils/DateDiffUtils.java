package net.liang.appbaselibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lenovo on 2016/11/2.
 * 计算年份之间相差几年
 */

public class DateDiffUtils {
    //计算两个日期相差年数
    public static int yearDateDiff(String startDate, String endDate){
        Calendar calBegin = Calendar.getInstance(); //获取日历实例
        Calendar calEnd = Calendar.getInstance();
        calBegin.setTime(TimeUtils.string2Date(startDate,new SimpleDateFormat("yyyy年MM月"))); //字符串按照指定格式转化为日期
        calEnd.setTime(TimeUtils.string2Date(endDate,new SimpleDateFormat("yyyy年MM月")));
        return calEnd.get(Calendar.YEAR) - calBegin.get(Calendar.YEAR);
    }
}
