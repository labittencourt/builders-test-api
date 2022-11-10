package contract;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static Constants.ConstantsQrCode.QRCODE_VALID_10;
import static Constants.ConstantsUrl.PIX_CODE;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PixCodePostContractTest {

    @Test
    public void testPostPixCodeContract(){
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
                .body(matchesJsonSchemaInClasspath("schema/sucessPostPixCodeContractSchema.json"));
        }
    }