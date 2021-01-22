package com.javarestassuredtemplate.utils;

import com.javarestassuredtemplate.enums.AuthenticationType;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.net.URI;
import java.util.Map;

public class RestAssuredUtils {
    public static Response executeRestRequest(String url,
                                              String requestService,
                                              Method method,
                                              Map<String,String> headers,
                                              Map<String,String> cookies,
                                              Map<String,String> queryParameters,
                                              String jsonBody,
                                              String authenticatorUser,
                                              String authenticatorPassword,
                                              AuthenticationType authenticationType){

        RequestSpecification requestSpecification = RestAssured.given();

        for (Map.Entry<String, String> header : headers.entrySet()){
            requestSpecification.headers(header.getKey(), header.getValue());
        }

        for (Map.Entry<String, String> cookie : cookies.entrySet()){
            requestSpecification.cookies(cookie.getKey(), cookie.getValue());
        }

        for (Map.Entry<String, String> parameter : queryParameters.entrySet()){
            requestSpecification.queryParams(parameter.getKey(), parameter.getValue());
        }

        if(jsonBody !=null){
            requestSpecification.body(jsonBody);
        }

        switch (authenticationType){
            case BASIC:
                requestSpecification.auth().basic(authenticatorUser, authenticatorPassword);
                break;
            case PREEMPTIVE:
                requestSpecification.auth().preemptive().basic(authenticatorUser, authenticatorPassword);
                break;
            case NONE:
                break;
            default:
                try {
                    throw new Exception("Authentication type not implemented");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return requestSpecification.request(method, URI.create(url+requestService));
    }

    public static Response executeSoapRequest(String url,
                                              Map<String, String> headers,
                                              Map<String, String> cookies,
                                              String xmlBody,
                                              String authenticatorUser,
                                              String authenticatorPassword,
                                              AuthenticationType authenticationType) {

        RequestSpecification requestSpecification = RestAssured.given();

        for (Map.Entry<String, String> header : headers.entrySet()){
            requestSpecification.headers(header.getKey(), header.getValue());
        }

        for (Map.Entry<String, String> cookie : cookies.entrySet()){
            requestSpecification.cookies(cookie.getKey(), cookie.getValue());
        }

        if(xmlBody !=null){
            requestSpecification.body(xmlBody);
        }

        switch (authenticationType){
            case BASIC:
                requestSpecification.auth().basic(authenticatorUser, authenticatorPassword);
                break;
            case PREEMPTIVE:
                requestSpecification.auth().preemptive().basic(authenticatorUser, authenticatorPassword);
                break;
            case NONE:
                break;
            default:
                try {
                    throw new Exception("Authentication type not implemented");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return requestSpecification.request(Method.POST, URI.create(url));
    }
}
