package org.tt.springbootservice.services;

public class SoapRequestBuilder {

    public static String buildSolveEquationRequest(double a, double b, double c) {
        return "<request><a>" + a + "</a><b>" + b + "</b><c>" + c +"</c></request>";
    }
}
