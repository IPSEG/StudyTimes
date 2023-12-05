package com.ipseg.studyTime.common.utils;

import com.google.common.base.CaseFormat;

import java.util.HashMap;

public class ConvertHashMap extends HashMap<String, Object>  {
    private static final long serialVersionUID = 1L;

    public Object put(String key, Object value) {
        if(key.contains("_"))
            return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key), value);
        else
            return super.put(key, value);
    }
}
