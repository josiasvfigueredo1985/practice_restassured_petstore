package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetToken  extends RequestRestBase {
    public GetToken(String usuario, String senha){
        url= GlobalParameters.URL_TOKEN;
        requestService = "token/"+usuario+"/"+senha;
        method = Method.GET;
    }
}
