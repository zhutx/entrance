package com.xier.sesame.attence.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by wuyb on 2017/4/6.
 */
public class DefaultGroupType {

    public final static int ALL_GROUP = 0;
    public final static int VISITOR_GROUP = 1;

    private final static HashMap<Integer, String> DEFAULT_GROUP_MAP = new LinkedHashMap<>();

    static {

        DEFAULT_GROUP_MAP.put(ALL_GROUP, "全员组");
        DEFAULT_GROUP_MAP.put(VISITOR_GROUP, "访客组");

    }

    public static HashMap<Integer, String> getDefaultGroupMap() {
        return DEFAULT_GROUP_MAP;
    }

    public static String getDefaultGroupTypeStr(Integer i) {
        if (i != null && DEFAULT_GROUP_MAP.containsKey(i)) {
            return DEFAULT_GROUP_MAP.get(i);
        }
        return "";
    }
}
