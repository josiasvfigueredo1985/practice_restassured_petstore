package com.javarestassuredtemplate.tests;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.GetPetRequest;
import com.javarestassuredtemplate.requests.GetPetStatusRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class GetPetTests extends TestBase {

    GetPetRequest getPetRequest;
    GetPetStatusRequest getPetStatusRequest;
    PostPetTests  criarPets = new PostPetTests();
    String idDisp;
    String idPend;
    String idVend;

    @Test
    public void buscarPetDisponível(){
        SoftAssert softAssert = new SoftAssert();

////////Criar Pet disponível

        // Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;

        // Fluxo
        criarPets.inserirPetStatusDisponível();
        int responseCodedisponivel = criarPets.code;
        idDisp = criarPets.id;

////////

        //Parâmetros
        String petId = idDisp;
        String categoryId = "10";
        String categoryName = "felinos";
        String name = "Figaro";
        String photoUrl = "http://photodogatito.com/image123.png";
        String tagId = "10";
        String tagName = "macho";
        String status = "available";

        //Fluxo
        getPetRequest = new GetPetRequest(petId);
        Response response = getPetRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseCodedisponivel, statusCodeEsperado);
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), petId, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("category.id").toString(), categoryId, "Validação categoryId");
        softAssert.assertEquals(response.body().jsonPath().get("category.name").toString(), categoryName, "Validação categoryName");
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name,  "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("photoUrls[0]").toString(), photoUrl, "Validação photoUrl");
        softAssert.assertEquals(response.body().jsonPath().get("tags[0].id").toString(), tagId, "Validação tagId");
        softAssert.assertEquals(response.body().jsonPath().get("tags[0].name").toString(), tagName, "Validação tagName");
        softAssert.assertEquals(response.body().jsonPath().get("status").toString(), status, "Validação status");
        softAssert.assertAll();
    }

    @Test
    public void buscarPetPendente(){
        SoftAssert softAssert = new SoftAssert();

////////Criar pet pendente

        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;

        // Fluxo
        criarPets.inserirPetStatusPendente();
        int responseCodePendente = criarPets.code;
        idPend = criarPets.id;


////////

        //Parâmetros
        String petId = idPend;
        String categoryId = "10";
        String categoryName = "felinos";
        String name = "Matilda";
        String photoUrl = "http://photodogatito.com/image123.png";
        String tagId = "10";
        String tagName = "fêmea";
        String status = "pending";

        //Fluxo
        getPetRequest = new GetPetRequest(petId);
        Response response = getPetRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseCodePendente, statusCodeEsperado);
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), petId, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("category.id").toString(), categoryId, "Validação categoryId");
        softAssert.assertEquals(response.body().jsonPath().get("category.name").toString(), categoryName, "Validação categoryName");
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name,  "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("photoUrls[0]").toString(), photoUrl, "Validação photoUrl");
        softAssert.assertEquals(response.body().jsonPath().get("tags[0].id").toString(), tagId, "Validação tagId");
        softAssert.assertEquals(response.body().jsonPath().get("tags[0].name").toString(), tagName, "Validação tagName");
        softAssert.assertEquals(response.body().jsonPath().get("status").toString(), status, "Validação status");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void buscarPetVendido(){
        SoftAssert softAssert = new SoftAssert();

/////// Criar pet vendido

        // Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;

        // Fluxo
        criarPets.inserirPetStatusVendido();
        int responseCodeVendido = criarPets.code;
        idVend = criarPets.id;

////////

        //Parâmetros
        String petId = idVend;
        String categoryId = "10";
        String categoryName = "felinos";
        String name = "Paçoca";
        String photoUrl = "http://photodogatito.com/image123.png";
        String tagId = "10";
        String tagName = "macho";
        String status = "sold";

        //Fluxo
        getPetRequest = new GetPetRequest(petId);
        Response response = getPetRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseCodeVendido, statusCodeEsperado);
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), petId, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("category.id").toString(), categoryId, "Validação categoryId");
        softAssert.assertEquals(response.body().jsonPath().get("category.name").toString(), categoryName, "Validação categoryName");
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name,  "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("photoUrls[0]").toString(), photoUrl, "Validação photoUrl");
        softAssert.assertEquals(response.body().jsonPath().get("tags[0].id").toString(), tagId, "Validação tagId");
        softAssert.assertEquals(response.body().jsonPath().get("tags[0].name").toString(), tagName, "Validação tagName");
        softAssert.assertEquals(response.body().jsonPath().get("status").toString(), status, "Validação status");
        softAssert.assertAll();
    }

////////Busca pets por Status

    @Test
    public void buscarPetsStatusDisponíveis(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;
        String petStatus = "findByStatus?status=";
        String status = "available";

        //Fluxo
        getPetStatusRequest = new GetPetStatusRequest(petStatus+status);
        Response response = getPetStatusRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        Assert.assertTrue(response.body().jsonPath().getString("status").contains(status),"Validação Status " +status);
        softAssert.assertAll();
    }

    @Test
    public void buscarPetsStatusPendentes(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;
        String petStatus = "findByStatus?status=";
        String status = "pending";

        //Fluxo
        getPetStatusRequest = new GetPetStatusRequest(petStatus+status);
        Response response = getPetStatusRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        Assert.assertTrue(response.body().jsonPath().getString("status").contains(status),"Validação Status" +status);
        softAssert.assertAll();
    }

    @Test
    public void buscarPetsStatusVendidos(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;
        String petStatus = "findByStatus?status=";
        String status = "sold";

        //Fluxo
        getPetStatusRequest = new GetPetStatusRequest(petStatus+status);
        Response response = getPetStatusRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        Assert.assertTrue(response.body().jsonPath().getString("status").contains(status),"Validação Status"+status);
        softAssert.assertAll();
    }
}
