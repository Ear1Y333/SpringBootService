package org.tt.springbootservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tt.springbootservice.models.QuadraticEquationDao;
import org.tt.springbootservice.services.EquationClient;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class QuadraticEquationController {

    private final EquationClient equationClient;
    /*
        Получение данных из ссылки считается плохой практикой,
        я бы использовал RequestBody в post-запросе,
        но по условию сказано делать именно так
     */
    @GetMapping("calc/")
    public ResponseEntity<?> calculate(@RequestParam double a,
                                       @RequestParam double b,
                                       @RequestParam double c) {
        String soapResponse = equationClient.solveEquation(new QuadraticEquationDao(a, b, c));
        return new ResponseEntity<>(soapResponse, HttpStatus.OK);
    }
}