package com.guilhermesales.apiloginregistro.simulations;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class getSimulation2 extends Simulation {

    // Definindo o protocolo HTTP
    HttpProtocolBuilder httpProtocolBuilder = http
            .baseUrl("http://localhost:8080")  // URL base do servidor
            .acceptHeader("application/json");  // Definindo o cabeçalho de aceitação

    // Definindo o cenário
    ScenarioBuilder scenario = scenario("Cadastro de Cursos")
            .exec(http("Cadastro de cursos")
                    .post("/services/cursos/registration")  // URL da API de cadastro de cursos
                    .body(StringBody("{ \"nome\": \"myHardCodedValue\" }")).asJson()  // Corpo da requisição
                    .check(status().is(200))  // Verificando se o status HTTP retornado é 200 (OK)
            );

    // Configuração da execução da simulação
    {
        setUp(
                scenario.injectOpen(atOnceUsers(1000))  // Inicia 1 usuário na simulação
        ).protocols(httpProtocolBuilder);  // Aplica o protocolo HTTP à simulação
    }
}
