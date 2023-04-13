package gatling.test.crud;

import gatling.test.common.ResponseStatus;
import io.gatling.http.response.Response;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static gatling.test.example.simulation.PerfTestConfig.*;
import static gatling.test.example.simulation.PerfTestConfig.P95_RESPONSE_TIME_MS;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CreateTaskSimulation extends Simulation
{
    HttpProtocolBuilder httpProtocol = http.baseUrl(BASE_URL)
            .header("Content-Type", "application/json")
            .header("Accept-Encoding", "gzip")
            .check(status().is(ResponseStatus.CREATED));

    ScenarioBuilder createTaskScenario = scenario("Create new task")
            .exec(BaseTaskSimulation.createTaskAction());

    {
        setUp(createTaskScenario.injectOpen(
//                atOnceUsers(250), rampUsers(500).during(Duration.ofMinutes(1)),
                constantUsersPerSec(REQUEST_PER_SECOND).during(Duration.ofMinutes(DURATION_MIN))))
                .protocols(httpProtocol)
                .assertions(global().responseTime().percentile3().lt(P95_RESPONSE_TIME_MS),
                        global().successfulRequests().percent().gt(95.0))
        ;
    }


}
