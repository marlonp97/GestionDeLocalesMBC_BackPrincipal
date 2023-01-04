package com.techcamp.mbc.controller;

import com.techcamp.mbc.dto.EmpresaPagoDTO;
import com.techcamp.mbc.model.Empresa;
import com.techcamp.mbc.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para el manejo de las empresas
 * @author Marlon Pérez Ríos
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {

        this.empresaService = empresaService;

    }

    /**
     * Método para devolver todas las empresas
     * @return Respuesta HTTP con la lista de empresas
     */
    @GetMapping("/")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(empresaService.getAllEmpresas(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver todas las empresas con formato DTO
     * @return Respuesta HTTP con la lista de empresas con formato DTO
     */
    @GetMapping("/pago/")
    public ResponseEntity<List<EmpresaPagoDTO>> getAllEmpresasPago() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(empresaService.getAllEmpresasPago(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}