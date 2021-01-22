package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetPetStatusRequest extends RequestRestBase {
    public GetPetStatusRequest(String petStatus){
        requestService = "/pet/"+petStatus;
        method = Method.GET;
    }
}
