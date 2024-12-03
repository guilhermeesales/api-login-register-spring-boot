package com.guilhermesales.apiloginregistro.simulations;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class getSimulation1 extends Simulation {

    // Configuração do protocolo HTTP
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080") // Base URL da API
            .acceptHeader("application/json"); // Cabeçalho padrão

    // Definição do cenário
    ScenarioBuilder scenario = scenario("getAllCourses")
            .exec(http("get courses") // Nome da requisição
                    .get("/services/cursos/get-courses") // Endpoint da API
                    .check(status().is(200))); // Valida se o status é 200

    {
        // Configuração de carga: 5000 usuários simultâneos
        setUp(
                scenario.injectOpen(
                        atOnceUsers(5000) // 5000 usuários simultâneos
                )
        ).protocols(httpProtocol);
    }
}
