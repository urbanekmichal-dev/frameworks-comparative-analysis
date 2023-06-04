package gatling.test.notifications;

import gatling.test.common.Constants;
import gatling.test.common.*;
import gatling.test.common.Properties;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class NotificationWithDelaySimulation extends Simulation {
    ScenarioBuilder scenarioBuilder = scenario("Single notification with delay")
            .forever()
            .on(
                    exec(
                            http("Get Notification with delay")
                                    .get(Paths.DELAYED_NOTIFICATION)
                                    .check(
                                            status().is(ResponseStatus.OK)
                                    )));

    {
        setUp(scenarioBuilder.injectOpen(atOnceUsers(Properties.getUsers()))).assertions(global().failedRequests()
                        .percent()
                        .lt(Constants.SUCCESS_RATE))
                .protocols(Constants.getHttpProtocolBuilder())
                .maxDuration(
                        Duration.ofMinutes(Properties.getDuration()));
    }
}
