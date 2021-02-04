package com.ford.lma.coreservice;

import com.ford.cloudnative.base.app.web.exception.handler.ExceptionHandlerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ExceptionHandlerConfiguration.class)
@AutoConfigureWebTestClient(timeout = "30000")
public class WebSecurityConfigurationTest {

	@Autowired
	private WebTestClient webClient;


	/***********************************************************************************************
	 * ENDPOINTS: Swagger
	 ***********************************************************************************************/

	@Test
	public void should_allowSwaggerEndpoints_withoutAuthentication() {
		webClient.get().uri("/swagger-ui.html").exchange().expectStatus().is2xxSuccessful();
		webClient.get().uri("/v2/api-docs").exchange().expectStatus().is2xxSuccessful();
	}

	/***********************************************************************************************
	 * ENDPOINTS: Other
	 ***********************************************************************************************/

	@Test
	public void should_notAllowOtherEndpoints_withoutAuthentication() {
		webClient.get().uri("/other-does-not-exist").exchange().expectStatus().is4xxClientError();
	}

}
