package org.tt.springbootservice.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomSoapClient {

    private final RestTemplate restTemplate;

    public CustomSoapClient() {
        this.restTemplate = new RestTemplate();
    }

    public String sendSoapRequest(String url, String xmlRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/xml");
        headers.set("SOAPAction", "solveEquation");

        HttpEntity<String> request = new HttpEntity<>(xmlRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
}
