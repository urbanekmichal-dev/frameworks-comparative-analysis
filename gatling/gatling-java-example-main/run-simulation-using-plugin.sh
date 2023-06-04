#!/usr/bin/env bash
   mvn gatling:test -DbaseUrl=http://localhost:8080/api/tasks -DdurationMin=1 -DrequestPersecond=10 -Dgatling.simulationClass=gatling.test.example.simulation.ExampleSimulation

>mvn gatling:test -DbaseUrl=http://localhost:8082/ -DdurationMin=1 -DrequestPersecond=200 -Dgatling.simulationClass=gatling.test.crud.CreateTaskSimulation
