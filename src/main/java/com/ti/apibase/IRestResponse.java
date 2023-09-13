package com.ti.apibase;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public interface IRestResponse<T>{
    public T getBody();

    public T  getSingleBody();
    public String getContent();

    public int getStatusCode();

    public boolean isSuccessful();

    public String getStatusDescription();

    public ResponseOptions<Response> getResponse();

    public Exception getException();
}
