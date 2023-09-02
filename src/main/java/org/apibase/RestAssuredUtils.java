package org.apibase;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static org.apibase.ConfigFactory.getDefaultConfig;

public class RestAssuredUtils {
    public static RequestSpecification request;

    public RestAssuredUtils(String baseUri, Map<String, String> headers){
        //RequestSpecBuilder builder = new RequestSpecBuilder();
        ServiceFactory.setBuilder(new RequestSpecBuilder());

        RequestSpecBuilder builder = ServiceFactory.getBuilder();
        builder.setBaseUri(baseUri);
        builder.setConfig(getDefaultConfig());
        builder.addHeaders(headers);
        //var requestSpec = builder.build();

        ServiceFactory.setRequestSpec(builder);
        //request = ServiceFactory.getRequestSpec().spec(builder.build());
        request = RestAssured.given().spec(ServiceFactory.getBuilder().build());
    }

    public static ResponseOptions<Response> getItems(String endpoint){
        return request.get(endpoint);
    }

    public static ResponseOptions<Response> getItems(){
        return request.get(ServiceFactory.getEndPoint());
    }

    public static ResponseOptions<Response> getWithPathParameter(String endpoint, String key, String value){
        request.pathParam(key,value);
        return request.get(endpoint);
    }

    public static ResponseOptions<Response> getWithPathParameters(String endpoint, Map<String, String> pathParams){
        request.pathParams(pathParams);
        return request.get(endpoint);
    }

    public static ResponseOptions<Response> getWithQueryParameter(String endpoint, String queryParams){
        request.queryParam(queryParams);
        return request.get(endpoint);
    }

    public static ResponseOptions<Response> getWithQueryParameters(String endpoint, Map<String,?> queryParams){
        request.queryParams(queryParams);
        return request.get(endpoint);
    }

    public static ResponseOptions<Response> postWithBodyAndPathParams(String endpoint, Map<String, String> pathParams, Map<String, String> body) {
        request.pathParams(pathParams);
        request.body(body);
        return request.post(endpoint);
    }

    public static ResponseOptions<Response> postItem(String endpoint){
        return request.post(endpoint);
    }

    public static ResponseOptions<Response> postItem(){
        return request.post(ServiceFactory.getEndPoint());
    }

    public static ResponseOptions<Response> postWithBody(String endpoint, Map<String, String> body) {
        request.body(body);
        return request.post(endpoint);
    }

    public static ResponseOptions<Response> postWithBody(Map<String, String> body) {
        request.body(body);
        return request.post(ServiceFactory.getEndPoint());
    }

    public static ResponseOptions<Response> deleteWithPathParam(String endpoint, String key, String value){
        request.pathParam(key,value);
        return request.delete(endpoint);

    }

    public static ResponseOptions<Response> deleteWithPathParams(String endpoint, Map<String, ?> pathParams){
        request.pathParams(pathParams);
        return request.delete(endpoint);

    }

    public static ResponseOptions<Response> deleteItem(String endpoint){
        return request.delete(endpoint);
    }

    public static ResponseOptions<Response> deleteItem(){
        return request.delete(ServiceFactory.getEndPoint());
    }

    public static ResponseOptions<Response> putItem() {
        return request.put(ServiceFactory.getEndPoint());
    }
    public static ResponseOptions<Response> putWithBody(String endpoint, Map<String, String> body) {
        request.body(body);
        return request.put(endpoint);
    }

    public static ResponseOptions<Response> putWithBody(Map<String, String> body) {
        request.body(body);
        return request.put(ServiceFactory.getEndPoint());
    }

    public static ResponseOptions<Response> putWithBodyAndPathParams(String endpoint, Map<String, String> pathParams, Map<String, String> body) {
        request.pathParams(pathParams);
        request.body(body);
        return request.put(endpoint);
    }
}
