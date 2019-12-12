package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExemploResource {

    @Autowired
    ExemploService exemploService;

    @RequestMapping(value = "/notificar", method = RequestMethod.GET)
    public ResponseEntity<Integer> listar() throws Exception {
        Integer size = exemploService.run();
        return new ResponseEntity(size, HttpStatus.OK);
    }

}
