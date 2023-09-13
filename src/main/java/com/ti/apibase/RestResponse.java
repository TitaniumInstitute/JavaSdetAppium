package com.ti.apibase;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.apache.http.HttpStatus;

public class RestResponse<T>implements IRestResponse<T> {
    private T data;
    private ResponseOptions<Response> response;
    private Exception e;

    public RestResponse(Class<T> t, ResponseOptions<Response> response) {
        this.response = response;
        try{
            this.data = t.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            throw new RuntimeException("There should be a default constructor in the Response POJO");
        }
    }

    @Override
    public T getBody() {
        try {
            data = (T) response.getBody().jsonPath().getList(".",data.getClass());
        }catch (Exception e) {
            this.e=e;
        }
        return data;
    }

    public T getSingleBody() {
        try {
            data = (T) response.getBody();
        }catch (Exception e) {
            this.e=e;
        }
        return data;
    }

    @Override
    public String getContent() {
        return response.getBody().asString();
    }

    @Override
    public int getStatusCode() {
        return response.getStatusCode();
    }

    @Override
    public boolean isSuccessful() {
        int code = response.getStatusCode();
        if(code <= HttpStatus.SC_RESET_CONTENT) return true;
        return false;
    }

    @Override
    public String getStatusDescription() {
        return response.getStatusLine();
    }

    @Override
    public ResponseOptions<Response> getResponse() {
        return response;
    }

    @Override
    public Exception getException() {
        return e;
    }
}
