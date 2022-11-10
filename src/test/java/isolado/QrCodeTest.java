package isolado;

import Constants.ConstantsQrCode;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static Constants.ConstantsQrCode.*;
import static Constants.ConstantsUrl.PIX_CODE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class QrCodeTest {
    @Test
    @Description("API returns 404 when the call has an invalid QRCode")
    public void testQrCodeNotFound() {
        Map<String, Object> qrcode = new HashMap<>();
        qrcode.put("encoded_value", ConstantsQrCode.QRCODE_NOT_FOUND);

        given()
                .contentType(ContentType.JSON)
                .body(qrcode)
                .when()
                .post(PIX_CODE)
                .then()
                .assertThat()
                .log().all()
                .statusCode(404)
                .body("error", containsString("qrcode_not_found"));
    }

    @Test
    @Description("API returns 400 when the request has an QRCode Empty")
    public void testQrCodeFieldEmpty() {
        Map<String, Object> qrcode = new HashMap<>();
        qrcode.put("encoded_value", ConstantsQrCode.QRCODE_EMPTY);

        given()
                .contentType(ContentType.JSON)
                .body(qrcode)
                .when()
                .post(PIX_CODE)
                .then()
                .assertThat()
                .log().all()
                .statusCode(400)
                .body("error", containsString("Bad Request"))
                .body("errors.encodedValue", containsString("must not be blank"));
    }

    @Test
    @Description("API returns 400 when the request has an QRCode Empty")
    public void testQrCodeInvalidData() {
        Map<String, Object> qrcode = new HashMap<>();
        qrcode.put("encoded_value", ConstantsQrCode.QRCODE_INVALID);

        given()
                .contentType(ContentType.JSON)
                .body(qrcode)
                .when()
                .post(PIX_CODE)
                .then()
                .assertThat()
                .log().all()
                .statusCode(402)
                .body("error", containsString("Bad Request"))
                .body("errors.encodedValue", containsString("must not be blank"));
    }

    @Test
    @Description("API returns 200 when the response body contains total value = 10 and the other parameters with values")
    public void testPostPixCodeCheckDataTotalValue10(){
        Map<String, Object> qrcode = new HashMap<>();
        qrcode.put("encoded_value", QRCODE_VALID_10);

        given()
                .contentType(ContentType.JSON)
                .body(qrcode)
                .when()
                .post(PIX_CODE)
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body("end_to_end", notNullValue())
                .body("conciliation_id", notNullValue())
                .body("total_value", containsString("10"))
                .body("holder.document", notNullValue())
                .body("holder.name", notNullValue())
                .body("holder.key", notNullValue())
                .body("holder.key_type", notNullValue())
                .body("holder.bank.name", notNullValue())
                .body("holder.bank.ispb", notNullValue());
    }

    @Test
    @Description("API returns 200 when the response body contains total value = 20 and the other parameters with values")
    public void testPostPixCodeCheckDataTotalValue20(){
        Map<String, Object> qrcode = new HashMap<>();
        qrcode.put("encoded_value", QRCODE_VALID_20);

        given()
                .contentType(ContentType.JSON)
                .body(qrcode)
                .when()
                .post(PIX_CODE)
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body("end_to_end", notNullValue())
                .body("conciliation_id", notNullValue())
                .body("total_value", containsString("20"))
                .body("holder.document", notNullValue())
                .body("holder.name", notNullValue())
                .body("holder.key", notNullValue())
                .body("holder.key_type", notNullValue())
                .body("holder.bank.name", notNullValue())
                .body("holder.bank.ispb", notNullValue());
    }

    @Test
    @Description("API returns 200 when the response body contains total value = 50 and the other parameters with values")
    public void testPostPixCodeCheckDataTotalValue50(){
        Map<String, Object> qrcode = new HashMap<>();
        qrcode.put("encoded_value", QRCODE_VALID_50);

        given()
                .contentType(ContentType.JSON)
                .body(qrcode)
                .when()
                .post(PIX_CODE)
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body("end_to_end", notNullValue())
                .body("conciliation_id", notNullValue())
                .body("total_value", containsString("50"))
                .body("holder.document", notNullValue())
                .body("holder.name", notNullValue())
                .body("holder.key", notNullValue())
                .body("holder.key_type", notNullValue())
                .body("holder.bank.name", notNullValue())
                .body("holder.bank.ispb", notNullValue());
    }
}
