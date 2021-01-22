package com.javarestassuredtemplate.tests;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.PostPetRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Random;

public class PostPetTests extends TestBase {
    PostPetRequest postPetRequest;
    SoftAssert softAssert = new SoftAssert();

    String id;
    int code;
    @Test
    public void inserirPetStatusDisponível() {

// Insere pet status disponível//
        Random random = new Random();
        int id1 = random.nextInt(99);
        String idDisp = Integer.toString(id1);

        //Parâmetros
        id = idDisp;
        int statusCodeEsperado = HttpStatus.SC_OK;
        String categoryId = "10";
        String categoryName = "felinos";
        String name = "Figaro";
        String photoUrl = "http://photodogatito.com/image123.png";
        String tagId = "10";
        String tagName = "macho";
        String status = "available";

        //Fluxo
        postPetRequest = new PostPetRequest();
        postPetRequest.setJsonBody(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
        Response response = postPetRequest.executeRequest();
        code = response.statusCode();

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
        @Test
        public void inserirPetStatusPendente() {

            // Insere pet status pendente//
            Random random = new Random();
            int id2 = random.nextInt(99);
            String idPend = Integer.toString(id2);


            //Parâmetros
            id = idPend;
            int statusCodeEsperado = HttpStatus.SC_OK;
            String categoryId = "10";
            String categoryName = "felinos";
            String name = "Matilda";
            String photoUrl = "http://photodogatito.com/image123.png";
            String tagId = "10";
            String tagName = "fêmea";
            String status = "pending";

            //Fluxo
            postPetRequest = new PostPetRequest();
            postPetRequest.setJsonBody(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
            Response response = postPetRequest.executeRequest();
            code = response.statusCode();

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

    @Test
    public void inserirPetStatusVendido() {
// Insere pet status vendido//

        Random random = new Random();
        int id3 = random.nextInt(99);
        String idVend = Integer.toString(id3);

        //Parâmetros
        id = idVend;
        int statusCodeEsperado = HttpStatus.SC_OK;
        String categoryId = "10";
        String categoryName = "felinos";
        String name = "Paçoca";
        String photoUrl = "http://photodogatito.com/image123.png";
        String tagId = "10";
        String tagName = "macho";
        String status = "sold";

        //Fluxo
        postPetRequest = new PostPetRequest();
        postPetRequest.setJsonBody(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
        Response response = postPetRequest.executeRequest();
        code = response.statusCode();

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

