package userManagement;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class jsonSchemaValidation {

    @Test
    public void jsonSchemaValidation() {
        File schema = new File("resources/ExpectedSchema.json");
        given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_24d7a6cb968c4215aa918d6f655d7cad ")
                .when()
                .get("https://reqres.in/api/users?page=234")
                .then()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
}
