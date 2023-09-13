package com.ti.apibase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class ServiceFactory {
    private static ThreadLocal<String> endPoint = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<RequestSpecification> requestSpec = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<RequestSpecBuilder> builder = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<ResponseOptions<Response>> response =  ThreadLocal.withInitial(() -> null);

    public static void setEndPoint(String apiEndPoint) {
        endPoint.set(apiEndPoint);
    }

    public static String getEndPoint() {
        return endPoint.get();
    }

    public static void setRequestSpec(RequestSpecBuilder requestSpecBuilder) {
        requestSpec.set(requestSpecBuilder.build());
    }

    public static RequestSpecification getRequestSpec() {
        return requestSpec.get();
    }

    public static void setResponse(ResponseOptions<Response> res){
        response.set(res);
    }

    public static ResponseOptions<Response> getResponse(){
        return response.get();
    }

    public static RequestSpecBuilder getBuilder() {
        return builder.get();
    }

    public static void setBuilder(RequestSpecBuilder requestSpecBuilderbuilder) {
        builder.set(requestSpecBuilderbuilder);
    }
}
