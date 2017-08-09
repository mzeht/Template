package com.wpmac.template.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: wpmac  Date: 2017/6/2 Time: 下午8:23
 * @email: mzeht8303@gamil.com
 */
public class WeekUtils {

    public static String getWeekFormNumbers(int number) {
        String weeks = "";
        if (number >= 128) {
            number = number - 128;
            weeks = "周六" +" ";
        }
        if (number >= 64) {
            number -= 64;
            weeks += "周五" + " ";
        }
        if (number >= 32) {
            number -= 32;
            weeks += "周四" + " ";
        }
        if (number >= 16) {
            number -= 16;
            weeks += "周三" + " ";
        }
        if (number >= 8) {
            number -= 8;
            weeks += "周二" + " ";
        }
        if (number >= 4) {
            number -= 4;
            weeks += "周一" + " ";
        }
        if (number >= 2) {
            number -= 2;
            weeks += "周日" + " ";
        }
        return weeks;

    }

    public static int setWeekNumberFormMap(Map<String, Boolean> map) {
        int number=0;
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Boolean isCheced = (Boolean) entry.getValue();
            if (!isCheced)
                continue;
            if (key.equals("周日"))
                number+=2;
            if (key.equals("周一"))
                number+=4;
            if (key.equals("周二"))
                number+=8;
            if (key.equals("周三"))
                number+=16;
            if (key.equals("周四"))
                number+=32;
            if (key.equals("周五"))
                number+=64;
            if (key.equals("周六"))
                number+=128;


        }

        return number;
    }
}
