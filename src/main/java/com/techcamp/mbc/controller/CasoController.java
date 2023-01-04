package com.techcamp.mbc.controller;

import com.techcamp.mbc.dto.CasoDTO;
import com.techcamp.mbc.exception.PaymentWithExistingCaseException;
import com.techcamp.mbc.service.CasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para el manejo de los casos de los pagos
 * @author Marlon Pérez Ríos
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/v1/casos")
public class CasoController {

    @Autowired
    private CasoService casoService;

    public CasoController(CasoService casoService) {

        this.casoService = casoService;

    }

    /**
     * Método para devolver todos los casos
     * @return Respuesta HTTP con la lista de casos
     */
    @GetMapping("/")
    public ResponseEntity<List<CasoDTO>> getAllCasos() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(casoService.getAllCasos(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para crear un caso
     * @param casoDTO Caso
     * @return Respuesta HTTP con el caso creado
     */
    @PostMapping("/")
    public ResponseEntity<CasoDTO> createCaso(@RequestBody CasoDTO casoDTO) {

        try {

            CasoDTO caso = casoService.createCaso(casoDTO);

            if(caso != null) {

                // HTTP 201 en caso de que el caso sea creado
                return new ResponseEntity<>(caso, HttpStatus.CREATED);

            } else {

                // HTTP 404 en caso de que no se encuentre alguno de los parámetros
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (PaymentWithExistingCaseException e) {

            // HTTP 417 en caso de que ya exista un caso asociado al pago
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver un caso al buscarlo por su ID
     * @param idCaso ID del caso
     * @return Respuesta HTTP con el caso
     */
    @GetMapping("/{idCaso}")
    public ResponseEntity<CasoDTO> getCasoById(@PathVariable("idCaso") Long idCaso) {

        try {

            Optional<CasoDTO> caso = casoService.getCasoById(idCaso);

            if(caso.isPresent()) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(caso.get(), HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el caso
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para actualizar un caso al buscarlo por su ID
     * @param idCaso ID del caso
     * @param casoDTO Caso
     * @return Respuesta HTTP con el caso actualizado
     */
    @PutMapping("/{idCaso}")
    public ResponseEntity<CasoDTO> updateCasoById(@PathVariable("idCaso") Long idCaso, @RequestBody CasoDTO casoDTO) {

        try {

            CasoDTO caso = casoService.updateCaso(idCaso, casoDTO);

            if(caso != null) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(caso, HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el caso o alguno de los parámetros
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}