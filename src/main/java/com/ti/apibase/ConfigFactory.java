package com.ti.apibase;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.*;
import io.restassured.filter.log.LogDetail;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

public class ConfigFactory {

    public static RestAssuredConfig getDefaultConfig(){

        ResponseValidationFailureListener failListener = (reqSpec, resSpec, response)->
                System.out.printf("API failure: response status was %s and the body contains: %s",
                        response.getStatusCode(),response.body().asPrettyString());

        return RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL))
                .failureConfig(FailureConfig.failureConfig().failureListeners(failListener))
                .redirect(RedirectConfig.redirectConfig().maxRedirects(1))
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory(getDefaultMapper()));
    }

    private static Jackson2ObjectMapperFactory getDefaultMapper(){
        return (type, s)-> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            om.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
            return om;
        };
    }
}
