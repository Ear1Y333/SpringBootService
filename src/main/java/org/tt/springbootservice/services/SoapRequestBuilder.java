package org.tt.springbootservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tt.springbootservice.models.QuadraticEquationDao;

public class SoapRequestBuilder {

    public static String buildSolveEquationRequest(QuadraticEquationDao quadraticEquationDao) {
        return "<request><a>" + quadraticEquationDao.getA() + "</a><b>" + quadraticEquationDao.getB() + "</b><c>" + quadraticEquationDao.getC() +"</c></request>";
    }
}
