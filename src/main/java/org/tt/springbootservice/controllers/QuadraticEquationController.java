package org.tt.springbootservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tt.springbootservice.services.CustomSoapClient;
import org.tt.springbootservice.services.SoapRequestBuilder;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class QuadraticEquationController {

    private final CustomSoapClient customSoapClient;
    @Value("${soap.service.url}")
    private String soapServiceUrl;

    /*
        Получение данных из ссылки считается плохой практикой,
        я бы использовал RequestBody в post-запросе,
        но условие мне не дает это сделать)
     */
    @GetMapping("calc")
    public ResponseEntity<?> calculate(@RequestParam double a,
                                       @RequestParam double b,
                                       @RequestParam double c) {
        String soapRequest = SoapRequestBuilder.buildSolveEquationRequest(a, b, c);
        String soapResponse = customSoapClient.sendSoapRequest(soapServiceUrl, soapRequest);
        return new ResponseEntity<>(soapResponse, HttpStatus.OK);
    }
}
