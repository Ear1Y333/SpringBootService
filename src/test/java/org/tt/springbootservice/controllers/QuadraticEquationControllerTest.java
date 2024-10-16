package org.tt.springbootservice.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.tt.springbootservice.services.CustomSoapClient;
import org.tt.springbootservice.services.SoapRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class QuadraticEquationControllerTest {

	@Mock
	private CustomSoapClient customSoapClient;

	@InjectMocks
	private QuadraticEquationController quadraticEquationController;

	@Value("${soap.service.url}")
	private String soapServiceUrl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		soapServiceUrl = "http://localhost:7070/untitled1_war/solve";
	}

	@Test
	void testCalculate_success() {
		double a = 3;
		double b = 4;
		double c = 1;
		String soapRequest = SoapRequestBuilder.buildSolveEquationRequest(a, b, c);
		String expectedSoapResponse = "<response><formula>3x^2 + 4x + 1 = 0</formula><D>0</D><x1>0</x1><x2>0</x2></response>";

		when(customSoapClient.sendSoapRequest(soapServiceUrl, soapRequest)).thenReturn(expectedSoapResponse);

		ResponseEntity<?> responseEntity = quadraticEquationController.calculate(a, b, c);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedSoapResponse, responseEntity.getBody());
	}

	@Test
	void testCalculate_badRequest() {
		double a = 0;
		double b = 0;
		double c = 0;
		String expectedErrorMessage = "Invalid input parameters";

		when(customSoapClient.sendSoapRequest(soapServiceUrl, "<some-invalid-request>")).thenThrow(new IllegalArgumentException(expectedErrorMessage));

		ResponseEntity<?> responseEntity = quadraticEquationController.calculate(a, b, c);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
}
