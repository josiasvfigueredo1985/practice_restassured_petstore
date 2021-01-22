package com.javarestassuredtemplate.steps;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.requests.GetToken;
import io.restassured.response.Response;

public class AutenticacaoSteps {
    public static void gerarToken(String usuario, String senha){
        GetToken getToken = new GetToken(usuario, senha);
        Response response = getToken.executeRequest();
        GlobalParameters.setToken(response.body().jsonPath().get("token").toString());
    }
}
