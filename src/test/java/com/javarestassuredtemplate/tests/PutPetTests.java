package com.javarestassuredtemplate.tests;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.PutPetRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PutPetTests extends TestBase {
    PutPetRequest putPetRequest;
    PostPetTests  criarPets = new PostPetTests();
    String idDisp;

    @Test
    public void atualizaPetComStatusVendido() {
        SoftAssert softAssert = new SoftAssert();

////////Criar Pet com status available

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
        String id = idDisp;
        String categoryId = "10";
        String categoryName = "felinos";
        String name = "Figaro";
        String photoUrl = "http://photodogatito.com/image123.png";
        String tagId = "10";
        String tagName = "macho";
        String status = "sold";// Alterar o status de available para sold

        //Fluxo
        putPetRequest = new PutPetRequest();
        putPetRequest.setJsonBody(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
        Response response = putPetRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), id, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("category.id").toString(), categoryId, "Validação categoryId");
        softAssert.assertEquals(response.body().jsonPath().get("category.name").toString(), categoryName, "Validação categoryName");
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("photoUrls[0]").toString(), photoUrl, "Validação photoUrl");
        softAssert.assertEquals(response.body().jsonPath().get("tags[0].id").toString(), tagId, "Validação tagId");
        softAssert.assertEquals(response.body().jsonPath().get("tags[0].name").toString(), tagName, "Validação tagName");
        softAssert.assertEquals(response.body().jsonPath().get("status").toString(), status, "Validação status");
        softAssert.assertAll();
    }
}
