package com.fronchak.integrationtests.testcontainers;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fronchak.configs.TestConfigs;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	void shouldDisplaySwaggerUiPage() {
		String content = 
			given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
					.get()
					.then()
					.statusCode(200)
					.extract()
					.body()
					.asString();
		System.out.println(content);
		assertTrue(content.contains("Swagger UI"));
	}

}
