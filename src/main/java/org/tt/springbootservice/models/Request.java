package org.tt.springbootservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;

@Data
@JsonRootName("request")
public class Request {

    @JsonProperty("a")
    private double a;

    @JsonProperty("b")
    private double b;

    @JsonProperty("c")
    private double c;

    public Request(QuadraticEquationDao quadraticEquationDao) {
        this.a = quadraticEquationDao.getA();
        this.b = quadraticEquationDao.getB();
        this.c = quadraticEquationDao.getC();
    }

    public static String getXmlValue(QuadraticEquationDao quadraticEquationDao) {
        try {
            return new XmlMapper().writeValueAsString(new Request(quadraticEquationDao));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}