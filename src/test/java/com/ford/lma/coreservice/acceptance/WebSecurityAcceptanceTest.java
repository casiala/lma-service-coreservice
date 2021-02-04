package com.ford.lma.coreservice.acceptance;

import com.ford.cloudnative.base.test.acceptance.AcceptanceTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

import static com.ford.lma.coreservice.TestUtil.get;
import static org.assertj.core.api.Assertions.assertThat;

public class WebSecurityAcceptanceTest {

	WebClient webClient;

	@Before
	public void setup() {
		webClient = AcceptanceTestUtil.webClientBuilder().build();
	}

	@Test
	public void testUnknownEndpointIsUnauthorized() {
		ClientResponse clientResponse = get(webClient, "/" + UUID.randomUUID().toString());
		assertThat(clientResponse.statusCode().is4xxClientError()).isTrue();
	}

}
