package com.merkone.quiz.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vescudero
 */
public class UtilJson {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilJson.class.getName());

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error processing json", e);
            throw new RuntimeException(e);
        }
    }

}
