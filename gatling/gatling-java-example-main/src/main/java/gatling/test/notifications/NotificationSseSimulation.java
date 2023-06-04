package gatling.test.notifications;

import gatling.test.common.Constants;
import gatling.test.common.*;
import gatling.test.common.Properties;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.sse;

public class NotificationSseSimulation extends Simulation {
    ScenarioBuilder scn = scenario("GET Notification SSE").forever()
            .on(exec(sse("Notification SSE").connect(Paths.SSE_NOTIFICATION)
                    .await(Properties.SSE_WORK_TIME)
                    .on(sse.checkMessage("Check notification status")
                            .matching(substring("message"))
                            .check(regex(".*Current datetime.*"))))
                    .pause(
                            Properties.SSE_PAUSE_TIME)
                    .exec(sse(
                            "Close").close()));

    {
        setUp(scn.injectOpen(
                atOnceUsers(
                        Properties.getUsers())))
                .assertions(
                        global()
                                .failedRequests()
                                .percent()
                                .lt(Constants.SUCCESS_RATE))
                .protocols(Constants.getHttpProtocolBuilder())
                .maxDuration(
                        Duration.ofMinutes(Properties.getDuration()));
    }


}

