package com.wy.shopping.utils;

import java.util.List;

public class Util {
    public static boolean isEmpty(List<?> list){
        return (list == null || list.size() == 0);
    }
    
    public static <T>boolean isEmpty(T [] array){
        return ((array == null) || (array.length) == 0);
    }
}
