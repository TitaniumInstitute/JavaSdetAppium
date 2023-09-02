package org.apibase;

import io.restassured.RestAssured;

public class BaseModel {
    public static BaseModel actualModel;
    public static final String BASE_URL = "https://reqres.in/api/";
    //private static String endPoint;

   /* public static String getEndPoint() {
        return BaseModel.endPoint;
    }*/

    /*public void setEndPoint(String endpoint){
        BaseModel.endPoint = endpoint;
        ServiceFactory.setEndPoint(BaseModel.endPoint);
    }*/

   public <TModel extends BaseModel> TModel getInstance(Class<TModel> model){
        Object objModel = RestAssured.get(BASE_URL+ServiceFactory.getEndPoint()).as(model);
        return model.cast(objModel);
    }

    public <TModel extends BaseModel> TModel as(Class<TModel> model){
        try {
            return (TModel) this;
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
