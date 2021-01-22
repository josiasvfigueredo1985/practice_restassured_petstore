package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DeletePetRequest extends RequestRestBase {
    public DeletePetRequest(int petId){
        requestService = "/pet/"+petId;
        method = Method.DELETE;
    }
}
