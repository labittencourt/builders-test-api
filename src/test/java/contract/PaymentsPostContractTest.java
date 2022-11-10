package contract;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import util.GenerateJson;

import java.io.IOException;

import static Constants.ConstantsUrl.PIX_PAYMENT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class PaymentsPostContractTest {

    @Test
    @Description("API returns 200 when contract matches response body")
    public void testPostPaymentsContract() throws IOException {
        GenerateJson generateJson = new GenerateJson();
        String jsonBody = generateJson.generateStringFromResource("src/test/java/Constants/jsonFiles/payments/payments.json");
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(PIX_PAYMENT)
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/successPostPaymentContractSchema.json"));
    }
}
