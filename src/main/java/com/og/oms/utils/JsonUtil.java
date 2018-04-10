package com.og.oms.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象转化为json字符串
     * @param object
     * @return
     */
    public static String objectToString(Object object){
        try {
            return mapper.writeValueAsString(object);
        } catch(JsonProcessingException e) {
            logger.error("", e);
            return"";
        }
    }
}
