package org.tt.springbootservice.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.tt.springbootservice.models.QuadraticEquationDao;
import org.tt.springbootservice.services.EquationClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class QuadraticEquationControllerTest {

	@Mock
	private EquationClient equationClient;

	@InjectMocks
	private QuadraticEquationController quadraticEquationController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCalculate_success() {
		double a = 3;
		double b = 4;
		double c = 1;
		String expectedSoapResponse = "<response><formula>3x^2 + 4x + 1 = 0</formula><D>0</D><x1>0</x1><x2>0</x2></response>";

		when(equationClient.solveEquation(new QuadraticEquationDao(a, b, c))).thenReturn(expectedSoapResponse);

		ResponseEntity<?> responseEntity = quadraticEquationController.calculate(a, b, c);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedSoapResponse, responseEntity.getBody());
	}

	@Test
	void testCalculate_invalidParameters() {
		double a = 0;
		double b = 0;
		double c = 0;
		String expectedErrorMessage = "Invalid input parameters";

		when(equationClient.solveEquation(new QuadraticEquationDao(a, b, c))).thenThrow(new IllegalArgumentException(expectedErrorMessage));

		ResponseEntity<?> responseEntity = quadraticEquationController.calculate(a, b, c);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals(expectedErrorMessage, responseEntity.getBody());
	}
}