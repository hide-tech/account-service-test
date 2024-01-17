package com.test.yazykov;

import com.test.yazykov.domain.IdType;
import com.test.yazykov.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.HashSet;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class TestApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void createTwoCustomersThenCreateAccountsThenDepositThenTransit() {
		//create two customers
        var signInSam = new SignIn("Sam", "111111111111", IdType.PASSPORT);
        var signInAlex = new SignIn("Alex", "222222222222", IdType.PASSPORT);

        var expectedSam = new CustomerDetails(1L, "Sam", "111111111111", IdType.PASSPORT, new HashSet<>());
        var expectedAlex = new CustomerDetails(2L, "Alex", "222222222222", IdType.PASSPORT, new HashSet<>());

        var realSam = webTestClient.post()
                .uri("/app/v1/customers")
                .bodyValue(signInSam)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(CustomerDetails.class).value(Assertions::assertNotNull)
                .returnResult().getResponseBody();

        var realAlex = webTestClient.post()
                .uri("/app/v1/customers")
                .bodyValue(signInAlex)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(CustomerDetails.class).value(Assertions::assertNotNull)
                .returnResult().getResponseBody();

        Assertions.assertEquals(expectedSam.name(), realSam.name());
        Assertions.assertEquals(expectedAlex.name(), realAlex.name());
		//create two accounts
		var accSam = webTestClient.post()
				.uri("/app/v1/customers/1/accounts")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(AccountDetails.class).value(Assertions::assertNotNull)
				.returnResult().getResponseBody();
		var accAlex = webTestClient.post()
				.uri("/app/v1/customers/2/accounts")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(AccountDetails.class).value(Assertions::assertNotNull)
				.returnResult().getResponseBody();

		Assertions.assertEquals(accSam.currency(), "BYN");
		Assertions.assertEquals(accAlex.value(), BigDecimal.ZERO);
		//deposit
		var deposit = new Deposit(2L, BigDecimal.TEN, "BYN");
		var accAdded = webTestClient.post()
				.uri("/app/v1/customers/2/accounts/2/deposit")
				.bodyValue(deposit)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(AccountDetails.class).value(Assertions::assertNotNull)
				.returnResult().getResponseBody();

		Assertions.assertEquals(new BigDecimal("10.00"), accAdded.value());
		//transfer

		var transfer = new PayrollDetail(2L, 1L, new BigDecimal("2.33"), "BYN");

		var accAfterTransfer = webTestClient.post()
				.uri("/app/v1/customers/2/accounts/2")
				.bodyValue(transfer)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(AccountDetails.class).value(Assertions::assertNotNull)
				.returnResult().getResponseBody();

		Assertions.assertEquals(accAfterTransfer.value(), new BigDecimal("7.67"));
    }

}
