package com.javarestassuredtemplate.bases;

import static io.restassured.RestAssured.*;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.enums.AuthenticationType;
import com.javarestassuredtemplate.utils.ExtentReportsUtils;
import com.javarestassuredtemplate.utils.RestAssuredUtils;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.*;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class RequestRestBase {
    protected String url = GlobalParameters.URL_DEFAULT;
    protected String requestService = null;
    protected Method method = null;
    protected String jsonBody = null;
    protected Map<String, String> headers = new HashMap<String, String>();
    protected Map<String, String> cookies = new HashMap<String, String>();
    protected Map<String, String> queryParameters = new HashMap<String, String>();
    protected AuthenticationType authenticationType = AuthenticationType.NONE;
    protected String authenticatorUser = GlobalParameters.AUTHENTICATOR_USER;
    protected String authenticatorPassword = GlobalParameters.AUTHENTICATOR_PASSWORD;

    public RequestRestBase(){
        config = RestAssuredConfig.newConfig().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        enableLoggingOfRequestAndResponseIfValidationFails();
        headers.put("content-type", "application/json");
        //headers.put("Authorization", "Bearer "+GlobalParameters.TOKEN); //<== EXEMPLO DE COMO ADICIONAR TOKEN DEFAULT CASO NECESSÁRIO
    }

    public Response executeRequest() {
        Response response = RestAssuredUtils.executeRestRequest(url, requestService, method, headers, cookies, queryParameters, jsonBody, authenticatorUser, authenticatorPassword, authenticationType);
        ExtentReportsUtils.addRestTestInfo(url, requestService, method.toString(), headers, cookies, queryParameters, jsonBody, authenticationType, authenticatorUser, authenticatorPassword, response);

        return response;
    }

    public void removeHeader(String header){
        headers.remove(header);
    }

    public void removeCookie(String cookie){
        cookies.remove(cookie);
    }

    public void removeQueryParameters(String parameter){
        queryParameters.remove(parameter);
    }

    public void setMehtod(Method method){
        this.method = method;
    }
}
