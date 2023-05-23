package gatling.test.crypto;

import gatling.test.common.*;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;
import java.time.LocalDateTime;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class EncryptDecryptSimulation extends Simulation {
    private static final String ENCRYPT_PROPERTY_DATA = "data";
    private static final String FILE_NAME_ATTRIBUTE = "fileName";

//    private static final String TEST_MESSAGE = Utils.generateString(1000000);


    ScenarioBuilder scn = scenario("Encrypt - decrypt simulation").exec(
                    session -> session.set(ENCRYPT_PROPERTY_DATA, Utils.generateString(1000000)))
            .exec(http("Encrypt message")
                    .post(Paths.ENCRYPT)
                    .asJson()
                    .body(StringBody("{\"data\": \"#{data}\"}"))
                    .check(status().is(ResponseStatus.OK))
                    .check(jmesPath("fileName").saveAs(FILE_NAME_ATTRIBUTE))

            ).pause(5, 10)
            .exec(http("Decrypt message")
                    .get(session -> Paths.DECRYTP + "/" + session.getString(FILE_NAME_ATTRIBUTE))
                    .check(status().is(ResponseStatus.OK))

                  //  .check(jmesPath("data").is(session -> session.getString(ENCRYPT_PROPERTY_DATA)))
            );

    {
        setUp(scn.injectOpen(atOnceUsers(250), rampUsers(500).during(Duration.ofMinutes(1)),
                constantUsersPerSec(30).during(Duration.ofMinutes(2)))).assertions(global().failedRequests()
                        .percent()
                        .lt(Constants.SUCCESS_RATE))
                .protocols(
                        Constants.getHttpProtocolBuilder());
    }
}
