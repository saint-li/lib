package com.saint.lib.util;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * `Author: Administrator
 * Time: 2018/9/5 10:06
 * ReadMe:
 */
public class TimeUtil {
    public SimpleDateFormat dateFormat;

    public final String PATTERN_yMdHms = "yyyy-MM-dd HH:mm:ss";
    public final String PATTERN_yMdHmsS = "yyyyMMddHHmmssSSS";
    public final String PATTERN_yMdHm = "yyyy-MM-dd HH:mm";
    public final String PATTERN_yMd = "yyyy.MM.dd";
    public final String PATTERN_Hms = "HH:mm:ss";
    public final String PATTERN_Hm = "HH:mm";
    public final String PATTERN_H = "HH";
    public final String PATTERN_m = "mm";
    public final String PATTERN_MdHm = "MM/dd HH:mm";
    public final String PATTERN_y_M_d = "yyyy-MM-dd";
    public final String PATTERN_yM = "yyyy年M月";
    public final String PATTERN_M_dHm = "MM-dd HH:mm";
    private DateFormat sdfYMd = SimpleDateFormat.getDateInstance(1);

    private static TimeUtil instance;

    public static TimeUtil getInstance() {
        if (instance == null) {
            synchronized (TimeUtil.class) {
                if (instance == null) instance = new TimeUtil();
            }
        }
        return instance;
    }

    private TimeUtil() {
        dateFormat = new SimpleDateFormat(PATTERN_yMdHms, Locale.getDefault());
    }

    public String formatYM(long timeMillis) {
        if (timeMillis <= 0) return "";
        dateFormat.applyPattern(PATTERN_yM);
        return dateFormat.format(timeMillis);
    }

    public String formatMillisecond(long timeMillis) {
        if (timeMillis <= 0) return "";
        dateFormat.applyPattern(PATTERN_yMdHmsS);
        return dateFormat.format(timeMillis);
    }

    public String formatYMd(long timeMillis) {
        if (timeMillis <= 0) return "";
        return sdfYMd.format(timeMillis);
    }

    /**
     * @param timeStr yyyy-MM-dd
     * @return 时间戳
     */
    public long parseYM(String timeStr) {
        if (TextUtils.isEmpty(timeStr)) return 0;
        try {
            dateFormat.applyPattern(PATTERN_y_M_d);
            return dateFormat.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String formatMDHM(long timeMillis) {
        if (timeMillis <= 0) return "";
        dateFormat.applyPattern(PATTERN_M_dHm);
        return dateFormat.format(timeMillis);
    }


    public String formatDay(long timeMillis) {
        if (timeMillis <= 0) return "";
        dateFormat.applyPattern(PATTERN_y_M_d);
        return dateFormat.format(timeMillis);
    }

    public String formatTimeSecond(long timeSecond) {
        if (timeSecond > 0) {
            dateFormat.applyPattern(PATTERN_yMdHms);
            return dateFormat.format(timeSecond * 1000);
        }
        return "";
    }

    public String formatTime(long timeMillis) {
        if (timeMillis > 0) {
            dateFormat.applyPattern(PATTERN_yMdHms);
            return dateFormat.format(timeMillis);
        }
        return "";
    }

    public String formatTimeHms(long timeMillis) {
        if (timeMillis > 0) {
            dateFormat.applyPattern(PATTERN_Hms);
            return dateFormat.format(timeMillis);
        }
        return "";
    }

    public String formatTimeMdHms(long timeSecond) {
        if (timeSecond > 0) {
            dateFormat.applyPattern(PATTERN_MdHm);
            return dateFormat.format(timeSecond * 1000);
        }
        return "";
    }

    public String formatTimeMdHm(long timeSecond) {
        if (timeSecond > 0) {
            dateFormat.applyPattern(PATTERN_MdHm);
            return dateFormat.format(timeSecond * 1000);
        }
        return "";
    }

    public String formatTimeYMD(long timeMillis) {
        if (timeMillis > 0) {
            dateFormat.applyPattern(PATTERN_yMd);
            return dateFormat.format(timeMillis);
        }
        return "";
    }

    public String formatTimeHm(long timeMillis) {
        if (timeMillis > 0) {
            dateFormat.applyPattern(PATTERN_Hm);
            return dateFormat.format(timeMillis);
        }
        return "";
    }

    public String formatTimeH(long timeMillis) {
        if (timeMillis > 0) {
            dateFormat.applyPattern(PATTERN_H);
            return dateFormat.format(timeMillis);
        }
        return "";
    }

    public String formatTimem(long timeMillis) {
        if (timeMillis > 0) {
            dateFormat.applyPattern(PATTERN_m);
            return dateFormat.format(timeMillis);
        }
        return "";
    }

    public long toTimestamp(String timeStr) {
        if (TextUtils.isEmpty(timeStr)) return 0;
        return Timestamp.valueOf(timeStr).getTime();
    }

    public String formatTime(String pattern, long timeMillis) {
        if (timeMillis <= 0) return "";
        dateFormat.applyPattern(pattern);
        return dateFormat.format(timeMillis);
    }

    public String getTime(Date date) {//可根据需要自行截取数据显示
        dateFormat.applyPattern(PATTERN_Hm);
        return dateFormat.format(date);
    }

    public long parseHmTime(String timeHm) {//可根据需要自行截取数据显示
        try {
            dateFormat.applyPattern(PATTERN_Hm);
            return dateFormat.parse(timeHm).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCurrentHour() {
        Date date = new Date();
        return date.getHours();
    }

    public int getCurrentMin() {
        Date date = new Date();
        return date.getMinutes();
    }

    public long toTimeMillis(String timeyMdHm) {
        try {
            dateFormat.applyPattern(PATTERN_yMdHm);
            return dateFormat.parse(timeyMdHm).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param timeStr yyyy-MM-dd HH:mm:ss
     * @return 时间戳
     */
    public long parseTime(String timeStr) {
        if (TextUtils.isEmpty(timeStr)) return 0;
        try {
            dateFormat.applyPattern(PATTERN_yMdHms);
            return dateFormat.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param timeStr yyyy-MM-dd
     * @return 时间戳
     */
    public long parseTimeYMd(String timeStr) {
        if (TextUtils.isEmpty(timeStr)) return 0;
        try {
            dateFormat.applyPattern(PATTERN_y_M_d);
            return dateFormat.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
