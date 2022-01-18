package helpers;

import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {

    public static String videoUrl(String sessionId) {

        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class);
        String username = mobileConfig.user();
        String password = mobileConfig.key();
        String videoUrl = mobileConfig.url();

        return given()
                .auth().basic(username, password)
                .when()
                .get(videoUrl + sessionId + ".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .path("automation_session.video_url");
    }
}