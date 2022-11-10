package isolado;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import util.GenerateJson;

import java.io.IOException;

import static Constants.ConstantsUrl.PIX_PAYMENT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class PaymentTest {

    @Test
    @Description("API returns 200 when all data from Account and Pix/Codes are valid")
    public void testSuccessPostPaymentsWhenAllDataIsValid() throws IOException {
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
                .statusCode(200);
    }

    @Test
    @Description("API returns 400 when some data is missing")
    public void testFailurePostPaymentsWhenSomeDataIsMissing() throws IOException {
        GenerateJson generateJson = new GenerateJson();
        String jsonBody = generateJson.generateStringFromResource("src/test/java/Constants/jsonFiles/payments/paymentInvalidResquest.json");
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(PIX_PAYMENT)
                .then()
                .assertThat()
                .log().all()
                .statusCode(400)
                .body("error", containsString("invalid_request"))
                .body("errors.conciliationId", containsString("must not be blank"));
    }

    @Test
    @Description("API returns 422 when payment failed")
    public void testFailurePostPaymentsFailed() throws IOException {
        GenerateJson generateJson = new GenerateJson();
        String jsonBody = generateJson.generateStringFromResource("src/test/java/Constants/jsonFiles/payments/paymentMissSomeData.json");
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(PIX_PAYMENT)
                .then()
                .assertThat()
                .log().all()
                .statusCode(422)
                .body("error", containsString("payment_failed"));
    }

    @Test
    @Description("API returns 404 when insufficient balance")
    public void testFailurePostPaymentsInsufficientBalance() throws IOException {
        GenerateJson generateJson = new GenerateJson();
        String jsonBody = generateJson.generateStringFromResource("src/test/java/Constants/jsonFiles/payments/paymentInsufficientBalance.json");
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(PIX_PAYMENT)
                .then()
                .assertThat()
                .log().all()
                .statusCode(404)
                .body("error", containsString("insufficient_balance"));
    }
}
