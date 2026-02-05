package userManagement;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import core.StatusCode;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postRequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class postUsers {

    private static FileInputStream fileInputStream;

    private static FileInputStream fileInputStreamMethod(String requestBodyFileName) {
        try {
            fileInputStream = new FileInputStream(
                    new File(System.getProperty("user.dir") + "/resources/TestData/" + requestBodyFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileInputStream;
    }

    @Test
    public void validatePostWithString() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithString executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePutWithString() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body("{\"name\":\"sidharth\",\"job\":\"LeadSDET\"}")
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePutWithString executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePatchWithString() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body("{\"name\":\"morpheus\"}")
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithString executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithJsonFile() throws IOException {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(IOUtils.toString(fileInputStreamMethod("postRequestBody.json")))
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePatchWithJsonFile() throws IOException {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(IOUtils.toString(fileInputStreamMethod("patchRequestBody.json")))
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePutWithJsonFile() throws IOException {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(IOUtils.toString(fileInputStreamMethod("putRequestBody.json")))
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePutWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPojo() {

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPojoListString() {

        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        postRequest.setLanguages(listLanguage);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojoListString executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPojoListObject() {

        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");

        cityRequest cityRequests1 = new cityRequest();
        cityRequests1.setName("bangalore");
        cityRequests1.setTemperature("30");
        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("delhi");
        cityRequests2.setTemperature("40");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequests1);
        cityRequests.add(cityRequests2);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        postRequest.setLanguages(listLanguage);
        postRequest.setCityRequestBody(cityRequests);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojoListObject executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePutWithPojo() {

        postRequestBody putRequest = new postRequestBody();
        putRequest.setJob("Santosh");
        putRequest.setName("Salesforce QA");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(putRequest)
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePutWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }
    @Test
    public void validatePatchWithPojo() throws IOException {
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setName("morpheus");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePatchWithResponsePojo() throws IOException {
        String name = "morpheus";
        String updatedAt ="2026-02-02T13:48:48.414Z";
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setName(name);
        patchRequest.setUpdatedAt(updatedAt);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println("name:" + responseBody.getName());
        System.out.println("Updated At: " + responseBody.getUpdatedAt());
        assertEquals(responseBody.getName(), name);
        assertEquals(responseBody.getUpdatedAt(), updatedAt);
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test(description = "Validate the POST method response with POJO")
    public void validatePostWithResponsePojoListObject() {

        String name = "bangalore";
        String temperature = "30";
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");

        cityRequest cityRequests1 = new cityRequest();
        cityRequests1.setName(name);
        cityRequests1.setTemperature(temperature);
        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("delhi");
        cityRequests2.setTemperature("40");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequests1);
        cityRequests.add(cityRequests2);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        postRequest.setLanguages(listLanguage);
        postRequest.setCityRequestBody(cityRequests);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println(responseBody.getCityRequestBody().get(0).getName());
        System.out.println(responseBody.getCityRequestBody().get(0).getTemperature());
        System.out.println(responseBody.getLanguages());
        assertEquals (responseBody.getCityRequestBody().get(0).getName(), name);
        assertEquals(responseBody.getCityRequestBody().get(0).getTemperature(), temperature);
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojoListObject executed successfully");
        System.out.println(response.getBody().asString());
    }

}
