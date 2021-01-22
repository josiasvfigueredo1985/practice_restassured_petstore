package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetPetRequest extends RequestRestBase {
    public GetPetRequest(String petId){
        requestService = "/pet/"+petId;
        method = Method.GET;
    }
}
