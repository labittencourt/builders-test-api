package contract;

import org.junit.jupiter.api.Test;

import static Constants.ConstantsUrl.ACCOUNT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class AccountGetContractTest {
    @Test
    public void testGetAccountContract() {
        given()
                .get(ACCOUNT + "62f87af90ba501353df77b95")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/successGetContractSchema.json"));
    }
}
