package gatling.test.crud;


import gatling.test.common.*;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import java.time.Duration;

import static gatling.test.common.Constants.*;
import static io.gatling.javaapi.core.CoreDsl.*;


public class CreateUpdateDeleteTaskSimulation extends Simulation
{


    ScenarioBuilder createUpdateTaskScenario = scenario("Create update delete task")
            .exec(BaseTaskSimulation.createTaskAction())
            .pause(5,10)
            .exec(BaseTaskSimulation.updateTaskAction());


    {
        setUp(createUpdateTaskScenario.injectOpen(
//                atOnceUsers(100), rampUsers(50).during(Duration.ofMinutes(1)),
                constantUsersPerSec(Properties.getUsers()).during(Duration.ofMinutes(Properties.getDuration()))))
                .protocols(getHttpProtocolBuilder())
                .assertions(global().responseTime().percentile3().lt(Properties.P95_RESPONSE_TIME_MS),
                        global().successfulRequests().percent().gt(95.0))
        ;
    }



}
