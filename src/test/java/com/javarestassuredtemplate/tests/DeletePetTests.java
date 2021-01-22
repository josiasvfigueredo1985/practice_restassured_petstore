package com.javarestassuredtemplate.tests;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.DeletePetRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeletePetTests extends TestBase {
    DeletePetRequest deletePetRequest;
    PostPetTests  criarPets = new PostPetTests();
    String idDisp;

    @Test
    public void deletarPetInserido(){
        SoftAssert softAssert = new SoftAssert();

////////Criar Pet

        // Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;

        // Fluxo
        criarPets.inserirPetStatusDisponível();
        int responseCode = criarPets.code;
        idDisp = criarPets.id;

        //Asserções
        Assert.assertEquals(responseCode, statusCodeEsperado);
////////

        //Parâmetros
        int petId=Integer.parseInt(idDisp);

        //Fluxo
        deletePetRequest = new DeletePetRequest(petId);
        Response response = deletePetRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("code"), 200, "Validação code");
        softAssert.assertEquals(response.body().jsonPath().get("type").toString(), "unknown", "Validação type");
        softAssert.assertEquals(response.body().jsonPath().get("message"), Integer.toString(petId), "Validação message");
        softAssert.assertAll();
    }
}