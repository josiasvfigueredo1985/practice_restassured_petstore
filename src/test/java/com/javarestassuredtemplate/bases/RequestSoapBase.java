package com.javarestassuredtemplate.bases;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.enums.AuthenticationType;
import com.javarestassuredtemplate.utils.ExtentReportsUtils;
import com.javarestassuredtemplate.utils.RestAssuredUtils;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

public abstract class RequestSoapBase {
    protected String url = GlobalParameters.URL_DEFAULT;
    protected String xmlBody = null;
    protected Map<String, String> headers = new HashMap<String, String>();
    protected Map<String, String> cookies = new HashMap<String, String>();
    protected AuthenticationType authenticationType = AuthenticationType.NONE;
    protected String authenticatorUser = GlobalParameters.AUTHENTICATOR_USER;
    protected String authenticatorPassword = GlobalParameters.AUTHENTICATOR_PASSWORD;

    public RequestSoapBase(){
        config = RestAssuredConfig.newConfig().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        enableLoggingOfRequestAndResponseIfValidationFails();
        headers.put("Content-Type", "text/xml;charset=UTF-8");
        headers.put("SOAPAction", "");
        //headers.put("token", "Bearer "+GlobalParameters.TOKEN); //<== EXEMPLO DE COMO ADICIONAR TOKEN DEFAULT CASO NECESSÁRIO
    }

    public Response executeRequest() {
        Response response = RestAssuredUtils.executeSoapRequest(url, headers, cookies, xmlBody, authenticatorUser, authenticatorPassword, authenticationType);
        ExtentReportsUtils.addSoapTestInfo(url, headers, cookies, xmlBody, authenticationType, authenticatorUser, authenticatorPassword, response);

        return response;
    }

    public void removeHeader(String header){
        headers.remove(header);
    }

    public void removeCookie(String cookie){
        cookies.remove(cookie);
    }
}
