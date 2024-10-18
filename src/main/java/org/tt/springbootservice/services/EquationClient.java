package org.tt.springbootservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tt.springbootservice.models.QuadraticEquationDao;
import org.tt.springbootservice.models.Request;

@Service
public class EquationClient {

    private static final Logger logger = LoggerFactory.getLogger(EquationClient.class);

    private final CustomSoapClient customSoapClient;

    @Value("${soap.service.url}")
    private String soapServiceUrl;

    public EquationClient(CustomSoapClient customSoapClient) {
        this.customSoapClient = customSoapClient;
    }

    public String solveEquation(QuadraticEquationDao quadraticEquationDao) {
        String soapRequest = Request.getXmlValue(quadraticEquationDao);

        logger.info("Sending SOAP request to URL: {}", soapServiceUrl);
        logger.info("SOAP request: {}", soapRequest);

        String soapResponse = customSoapClient.sendSoapRequest(soapServiceUrl, soapRequest);

        logger.info("Received SOAP response");
        logger.info("SOAP response: {}", soapResponse);

        return soapResponse;
    }
}