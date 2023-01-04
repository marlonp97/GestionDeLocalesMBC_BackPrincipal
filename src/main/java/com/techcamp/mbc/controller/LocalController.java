package com.techcamp.mbc.controller;

import com.techcamp.mbc.dto.LocalDTO;
import com.techcamp.mbc.dto.LocalPagoDTO;
import com.techcamp.mbc.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para el manejo de los locales
 * @author Marlon Pérez Ríos
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/v1/locales")
public class LocalController {

    @Autowired
    private LocalService localService;

    public LocalController(LocalService localService) {

        this.localService = localService;

    }

    /**
     * Método para devolver todos los locales
     * @return Respuesta HTTP con la lista de locales
     */
    @GetMapping("/")
    public ResponseEntity<List<LocalDTO>> getAllLocales() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(localService.getAllLocales(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver todos los locales con formato DTO
     * @return Respuesta HTTP con la lista de locales con formato DTO
     */
    @GetMapping("/pago/")
    public ResponseEntity<List<LocalPagoDTO>> getAllLocalesPago() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(localService.getAllLocalesPago(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}