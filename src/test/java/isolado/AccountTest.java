package isolado;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static Constants.ConstantsAccountId.*;
import static Constants.ConstantsUrl.ACCOUNT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class AccountTest {

    @Test
    public void testGetAccountIdInvalid() {
        given()
                .get(ACCOUNT + ACCOUNT_ID_INVALID)
                .then()
                .log().all()
                .statusCode(404)
                .body("error", containsString("qrcode_not_found"));
    }

    @Test
    public void testGetAccountIdEmpty() {
        given()
                .get(ACCOUNT + "")
                .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    @Description("API returns 200 when the response body contains name = Candidato and the other parameters with values")
    public void testGetAccountID01Success() {
        given()
                .get(ACCOUNT + ACCOUNT_ID_VALID_01)
                .then()
                .log().all()
                .statusCode(200)
                .body("account_id", notNullValue())
                .body("document", notNullValue())
                .body("name", containsString("Candidato"))
                .body("amount", notNullValue())
                .body("bank.name", notNullValue())
                .body("bank.ispb", notNullValue());
    }

    @Test
    public void testGetAccountID02Success() {
        given()
                .get(ACCOUNT + ACCOUNT_ID_VALID_02)
                .then()
                .log().all()
                .statusCode(200)
                .body("account_id", notNullValue())
                .body("document", notNullValue())
                .body("name", containsString("Luke Skywalkers"))
                .body("amount", notNullValue())
                .body("bank.name", notNullValue())
                .body("bank.ispb", notNullValue());
    }

    @Test
    public void testGetAccountID03Success() {
        given()
                .get(ACCOUNT + ACCOUNT_ID_VALID_03)
                .then()
                .log().all()
                .statusCode(200)
                .body("account_id", notNullValue())
                .body("document", notNullValue())
                .body("name", containsString("Darth Vader"))
                .body("amount", notNullValue())
                .body("bank.name", notNullValue())
                .body("bank.ispb", notNullValue());
    }
}
