package gatling.test.notifications;

import gatling.test.common.Constants;
import gatling.test.common.Paths;
import gatling.test.common.Properties;
import gatling.test.common.ResponseStatus;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;


public class NotificationSimulation extends Simulation {

    ScenarioBuilder scenarioBuilder = scenario("Single notification simulation")
            .forever()
            .on(
                    exec(
                            http("Get Notification")
                                    .get(Paths.SINGLE_NOTIFICATION)
                                    .check(
                                            status().is(ResponseStatus.OK)
                                    )));

    {
        setUp(scenarioBuilder.injectOpen(atOnceUsers(Properties.getNotificationUsers()))).assertions(global().failedRequests()
                        .percent()
                        .lt(Constants.SUCCESS_RATE))
                .protocols(Constants.getHttpProtocolBuilder())
                .maxDuration(
                        Duration.ofMinutes(Properties.getNotificationTime()));
    }
}

