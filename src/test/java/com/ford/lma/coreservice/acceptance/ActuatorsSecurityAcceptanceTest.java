package com.ford.lma.coreservice.acceptance;

import com.ford.cloudnative.base.test.acceptance.AcceptanceTestUtil;
import com.ford.lma.coreservice.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

import static com.ford.cloudnative.base.test.json.JsonUtil.jsonContent;
import static com.ford.lma.coreservice.TestUtil.get;
import static org.assertj.core.api.Assertions.assertThat;

public class ActuatorsSecurityAcceptanceTest {

	WebClient webClient;

	@Before
	public void setup() {
        webClient = AcceptanceTestUtil.webClientBuilder().build();
	}

    @Test
    public void testActuatorHealthEndpointIsAccessible() {
        ClientResponse clientResponse = get(webClient, "/actuator/health");
        assertThat(clientResponse.statusCode().is2xxSuccessful()).isTrue();

        JsonContent<?> jsonContent = jsonContent(Objects.requireNonNull(clientResponse.bodyToMono(String.class).block()));
        assertThat(jsonContent).hasJsonPathStringValue("$.status");
        assertThat(jsonContent).extractingJsonPathStringValue("$.status").isNotBlank();
    }


    @Test
    public void testActuatorInfoEndpointIsAccessible() {
        ClientResponse clientResponse = get(webClient, "/actuator/info");
        assertThat(clientResponse.statusCode().is2xxSuccessful()).isTrue();

        JsonContent<?> jsonContent = jsonContent(Objects.requireNonNull(clientResponse.bodyToMono(String.class).block()));
        assertThat(jsonContent).hasJsonPathStringValue("$.app.name");
        assertThat(jsonContent).extractingJsonPathStringValue("$.app.name").isNotBlank();
    }

    @Test
    public void testSensitiveActuatorEndpointsNotAccessible() {
        List<String> sensitiveActuatorEndpoints = TestUtil.getSensitiveActuatorEndpoints();
        for (String sensitiveEndpoint : sensitiveActuatorEndpoints) {
            ClientResponse clientResponse = get(webClient, sensitiveEndpoint);
            assertThat(clientResponse.statusCode().is4xxClientError()).isTrue();
        }
    }

}
