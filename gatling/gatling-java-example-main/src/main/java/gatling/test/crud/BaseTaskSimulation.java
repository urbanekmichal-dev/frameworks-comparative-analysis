package gatling.test.crud;

import gatling.test.common.Paths;
import gatling.test.common.ResponseStatus;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Simulation;
import io.netty.handler.codec.http.HttpResponseStatus;

import static gatling.test.crud.TaskBodyUtils.TASK_DESCRIPTION;
import static gatling.test.crud.TaskBodyUtils.TASK_NAME;


import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class BaseTaskSimulation extends Simulation {



    protected static ChainBuilder createTaskAction() {
        return exec(session -> session.set(TASK_NAME,TaskBodyUtils.generateRandomString())
                .set(TASK_DESCRIPTION,TaskBodyUtils.generateRandomString()))
                .exec(http("Create task")
                        .post(Paths.TASKS).asJson()
                        .body(StringBody(session-> TaskBodyUtils.createTaskBody(
                                session.getString(TASK_NAME),
                                session.getString(TASK_DESCRIPTION))))
                        .check(status().is(ResponseStatus.CREATED)));
    }

}
