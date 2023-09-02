package com.ti.restassured.commands;

import com.ti.restassured.dao.AnotherUser;
import com.ti.restassured.dao.User;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import com.fasterxml.jackson.databind.*;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

public class MapperExample {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    void objectMappingOne(){
        User user = RestAssured.get(BASE_URL + "users/macournoyer")
                .as(User.class);

        Assert.assertEquals(user.getLogin(), "macournoyer");
        Assert.assertEquals(user.getId(), 22);
        Assert.assertEquals(user.getPublicRepos(), 65);
    }

    @Test
    void ObjectMappingRelyingOnMapperType(){
        User user = RestAssured.get(BASE_URL + "users/macournoyer")
                .as(User.class, ObjectMapperType.JACKSON_2);

        Assert.assertEquals(user.getLogin(), "macournoyer");
    }

    @Test
    void objectMappingUsingSpecifiedMapper(){
        io.restassured.mapper.ObjectMapper mapper = new Jackson2Mapper(new Jackson2ObjectMapperFactory(){
            @Override
            public ObjectMapper create(Type type, String s){
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }
        });

        AnotherUser user = RestAssured.get(BASE_URL + "users/macournoyer")
                .as(AnotherUser.class, mapper);
        Assert.assertEquals(user.getLogin(), "macournoyer");
    }

    @Test
    void objectMappingUsingCustomMapper(){
        AnotherUser user = RestAssured.get(BASE_URL + "users/macournoyer")
                .as(AnotherUser.class, getMapperLambda());
        Assert.assertEquals(user.getLogin(), "macournoyer");
    }

    private Jackson2Mapper getMapper(){
        return new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(Type type, String s) {
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }
        });
    }

    private Jackson2Mapper getMapperLambda(){
        return new Jackson2Mapper((type, s)-> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        });
    }
}
