package com.wy.shopping.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wy.vo.User;

public class Util {

    private static final String SEP1 = "#";
    private static final String SEP2 = "|";

    private static final String SEP3 = "=";

    public static boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }

    public static <T> boolean isEmpty(T[] array) {
        return ((array == null) || (array.length) == 0);
    }

    public static List<User> StringToList(String listText) {
        String b = listText.substring(1, listText.length() - 1);
        String c[] = b.split("User");
        List<User> list = new ArrayList<User>();
        for (int s = 0; s < c.length; s++) {
            User u = new User();
            u = u.toObject(c[s]);
            if (u != null) {
               list.add(u);
            }
        }
        return list;
    }

    public static Map<String, Object> StringToMap(String mapText) {
        if (mapText == null || mapText.equals("")) {
            return null;
        }
        mapText = mapText.substring(1);
        Map<String, Object> map = new HashMap<String, Object>();

        String[] text = mapText.split("\\" + SEP2); // 转换为数组

        for (String str : text) {

            String[] keyText = str.split(SEP3); // 转换key与value的数组
            if (keyText.length < 1) {
                continue;
            }
            String key = keyText[0]; // key
            String value = keyText[1]; // value
            if (value.charAt(0) == 'M') {
                Map<?, ?> map1 = StringToMap(value);
                map.put(key, map1);
            } else if (value.charAt(0) == 'L') {
                List<?> list = StringToList(value);
                map.put(key, list);
            } else {
                map.put(key, value);
            }
        }
        return map;
    }
}
