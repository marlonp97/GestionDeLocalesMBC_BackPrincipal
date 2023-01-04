package com.techcamp.mbc.controller;

import com.techcamp.mbc.model.TipoPago;
import com.techcamp.mbc.service.TipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para el manejo de los tipos de pago
 * @author Marlon Pérez Ríos
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/v1/tiposPago")
public class TipoPagoController {

    @Autowired
    private TipoPagoService tipoPagoService;

    public TipoPagoController(TipoPagoService tipoPagoService) {

        this.tipoPagoService = tipoPagoService;

    }

    /**
     * Método para devolver todos los tipos de pago
     * @return Respuesta HTTP con la lista de tipos de pago
     */
    @GetMapping("/")
    public ResponseEntity<List<TipoPago>> getAllTiposPago() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(tipoPagoService.getAllTiposPago(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}