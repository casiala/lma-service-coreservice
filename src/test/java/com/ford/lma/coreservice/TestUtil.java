package com.ford.lma.coreservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;

public class TestUtil {

    private static final String DEFAULT_AUDIENCE = "audience";

    public static ClientResponse get(WebClient webClient, String urlPath) {
        return webClient.get()
                .uri(urlPath)
                .exchange().block();
    }

    public static ClientResponse post(WebClient webClient, String urlPath, Object clazz) {
        return webClient.post()
                .uri(urlPath)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(clazz), clazz.getClass())
                .exchange().block();
    }

    public static List<String> getSensitiveActuatorEndpoints() {
        List<String> endpoints = new ArrayList<>();
        endpoints.add("/actuator/actuator");
        endpoints.add("/actuator/auditevents");
        endpoints.add("/actuator/autoconfig");
        endpoints.add("/actuator/beans");
        endpoints.add("/actuator/configprops");
        endpoints.add("/actuator/dump");
        endpoints.add("/actuator/env");
        endpoints.add("/actuator/flyway");
        endpoints.add("/actuator/loggers");
        endpoints.add("/actuator/liquibase");
        endpoints.add("/actuator/metrics");
        endpoints.add("/actuator/mappings");
        endpoints.add("/actuator/shutdown");
        endpoints.add("/actuator/trace");
        return endpoints;
    }

    public static ResultActions jsonGet(MockMvc mockMvc, String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(MockMvcResultHandlers.print());
    }

    public static ResultActions jsonPost(MockMvc mockMvc, ObjectMapper objectMapper, String url, Object entity) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity))
        )
                .andDo(MockMvcResultHandlers.print());
    }

    public static ResultActions jsonPut(MockMvc mockMvc, ObjectMapper objectMapper, String url, Object entity) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity))
        )
                .andDo(MockMvcResultHandlers.print());
    }

    public static ResultActions jsonDelete(MockMvc mockMvc, String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .delete(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print());
    }

    public static ResultActions jsonDeleteWithObject(MockMvc mockMvc, ObjectMapper objectMapper, String url, Object entity) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity))
        )
                .andDo(MockMvcResultHandlers.print());
    }
}
