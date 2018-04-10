package com.og.oms.utils;

import java.util.List;
import java.util.HashSet;

public class CommUtil {
	
	/**
     * 判断数组中是否有重复的值
     */
    public static boolean cheakIsRepeat(Object[] array) {
        HashSet<Object> hashSet = new HashSet<Object>();
        for (int i = 0; i < array.length; i++) {
            hashSet.add(array[i]);
        }
        if (hashSet.size() == array.length) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * 判断列表中是否有重复的值
     */
    public static boolean cheakListIsRepeat(List<String> array) {
        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < array.size(); i++) {
            hashSet.add(array.get(i));
        }
        if (hashSet.size() == array.size()) {
            return false;
        } else {
            return true;
        }
    }
}
