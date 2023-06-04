package gatling.test.crud;

import gatling.test.common.*;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CreateTaskSimulation extends Simulation
{
    HttpProtocolBuilder httpProtocol = http.baseUrl(Properties.getBaseUrl())
            .header("Content-Type", "application/json")
            .header("Accept-Encoding", "gzip")
            .check(status().is(ResponseStatus.CREATED));

    ScenarioBuilder createTaskScenario = scenario("Create new task")
            .exec(BaseTaskSimulation.createTaskAction());

    {
        setUp(createTaskScenario.injectOpen(
//                atOnceUsers(100), rampUsers(50).during(Duration.ofMinutes(1)),
                constantUsersPerSec(Properties.getUsers()).during(Duration.ofMinutes(Properties.getDuration()))))
                .protocols(httpProtocol)
                .assertions(global().responseTime().percentile3().lt(Properties.P95_RESPONSE_TIME_MS),
                        global().successfulRequests().percent().gt(95.0))
        ;
    }


}
