package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostPetRequest extends RequestRestBase {
    public PostPetRequest(){
        requestService = "/pet";
        method = Method.POST;
    }

    public void setJsonBody(String id,
                            String categoryId,
                            String categoryName,
                            String name,
                            String photoUrl,
                            String tagId,
                            String tagName,
                            String status){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/PostPetJson.json");

        jsonBody = jsonBody.replace("$id", id)
                           .replace("$categoryId", categoryId)
                           .replace("$categoryName", categoryName)
                           .replace("$name", name)
                           .replace("$photoUrl", photoUrl)
                           .replace("$tagId", tagId)
                           .replace("$tagName", tagName)
                           .replace("$status", status);
    }
}
