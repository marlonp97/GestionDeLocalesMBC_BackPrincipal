package com.techcamp.mbc.controller;

import com.techcamp.mbc.model.EstadoPago;
import com.techcamp.mbc.service.EstadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para el manejo de los estados de los pagos
 * @author Marlon Pérez Ríos
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/v1/estadosPago")
public class EstadoPagoController {

    @Autowired
    private EstadoPagoService estadoPagoService;

    public EstadoPagoController(EstadoPagoService estadoPagoService) {

        this.estadoPagoService = estadoPagoService;

    }

    /**
     * Método para devolver todos los estados de los pagos
     * @return Respuesta HTTP con la lista de estados de los pagos
     */
    @GetMapping("/")
    public ResponseEntity<List<EstadoPago>> getAllEstadosPago() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(estadoPagoService.getAllEstadosPago(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}