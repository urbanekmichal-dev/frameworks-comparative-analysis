package gatling.test.sort;

import gatling.test.common.Constants;
import gatling.test.common.*;
import gatling.test.common.Properties;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class MergeSortSimulation extends Simulation {

    private static ChainBuilder mergeSortingRequest(final String fileName) {
        return exec(feed(csv(fileName).batch(1).random()))
                .exec(http("Request sorting")
                        .post(Paths.MERGE_SORT)
                        .asJson()
                        .body(StringBody("""
											  {								 
											   "integerList": #{integerList}
											   }
											   """))
                        .check(status().is(ResponseStatus.OK))
                        .check(jmesPath("integerListSorted").is(session -> session.getString("integerListSorted")))

                );
    }

    ScenarioBuilder scn = scenario("Merge-Sort simulation ").exec(mergeSortingRequest("C:\\Users\\urban\\IdeaProjects\\frameworks-comparative-analysis\\gatling\\gatling-java-example-main\\src\\main\\resources\\test1.csv"));
    {
        setUp(scn.injectOpen(atOnceUsers(Properties.getUsers()))).assertions(global().failedRequests()
                        .percent()
                        .lt(Constants.SUCCESS_RATE))
                .protocols(
                        Constants.getHttpProtocolBuilder())
                .maxDuration(Duration.ofMinutes(
                        Properties.getDuration()));
    }
}
