package gatling.test.crud;

import gatling.test.common.*;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Simulation;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;


public class BaseTaskSimulation extends Simulation {
    public static final String TASK_NAME = "name";
    public static final String TASK_DESCRIPTION = "description";
    public static final String TASK_ID = "taskId";

    protected static ChainBuilder createTaskAction() {
        return exec(session -> session.set(TASK_NAME,TaskBodyUtils.generateRandomString())
                .set(TASK_DESCRIPTION,TaskBodyUtils.generateRandomString()))
                .exec(http("Create task")
                        .post(Paths.TASKS).asJson()
                        .body(StringBody(session-> TaskBodyUtils.createTaskBody(
                                session.getString(TASK_NAME),
                                session.getString(TASK_DESCRIPTION))))
                        .check(status().is(ResponseStatus.CREATED))
                        .check(jmesPath("taskId").saveAs("taskId"))
                );
    }

    protected static ChainBuilder updateTaskAction(){
        return	exec(session -> session.set(TASK_DESCRIPTION,TaskBodyUtils.generateRandomString())
                .set(TASK_DESCRIPTION,TaskBodyUtils.generateRandomString()))
                .exec(http("Update task description")
                        .put(session -> Paths.TASKS+"/"+session.getString(TASK_ID))
                        .asJson()
                        .body(StringBody(session -> TaskBodyUtils.createTaskBody(
                                session.getString(TASK_NAME),
                                session.getString(TASK_DESCRIPTION))))
                        .check(status().is(ResponseStatus.OK))
                );
    }


}
