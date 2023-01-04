package com.techcamp.mbc.controller;

import com.itextpdf.text.Document;
import com.techcamp.mbc.dto.PagoDTO;
import com.techcamp.mbc.dto.PagoHistoricoDTO;
import com.techcamp.mbc.dto.PagoPendienteDTO;
import com.techcamp.mbc.exception.PendingPaymentsException;
import com.techcamp.mbc.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para el manejo de los pagos
 * @author Marlon Pérez Ríos
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    public PagoController(PagoService pagoService) {

        this.pagoService = pagoService;

    }

    /**
     * Método para devolver todos los pagos
     * @return Respuesta HTTP con la lista de pagos
     */
    @GetMapping("/")
    public ResponseEntity<List<PagoDTO>> getAllPagos() throws Exception {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(pagoService.getAllPagos(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para crear un pago
     * @param pagoDTO Pago
     * @return Respuesta HTTP con el pago creado
     */
    @PostMapping("/")
    public ResponseEntity<PagoDTO> createPago(@RequestBody PagoDTO pagoDTO) throws Exception {

        try {

            PagoDTO pago = pagoService.createPago(pagoDTO);

            if(pago != null) {

                // HTTP 201 en caso de que el pago sea creado
                return new ResponseEntity<>(pago, HttpStatus.CREATED);

            } else {

                // HTTP 404 en caso de que no se encuentre alguno de los parámetros
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @return Respuesta HTTP con el pago
     */
    @GetMapping("/{idPago}")
    public ResponseEntity<PagoDTO> getPagoById(@PathVariable("idPago") Long idPago) throws Exception {

        try {

            Optional<PagoDTO> pago = pagoService.getPagoById(idPago);

            if(pago.isPresent()) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(pago.get(), HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el pago
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para actualizar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @param pagoDTO Pago
     * @return Respuesta HTTP con el pago actualizado
     */
    @PutMapping("/{idPago}")
    public ResponseEntity<PagoDTO> updatePagoById(@PathVariable("idPago") Long idPago, @RequestBody PagoDTO pagoDTO) throws Exception {

        try {

            PagoDTO pago = pagoService.updatePago(idPago, pagoDTO);

            if(pago != null) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(pago, HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el pago o alguno de los parámetros
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para eliminar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @return Respuesta HTTP
     */
    @DeleteMapping("/{idPago}")
    public ResponseEntity deletePagoById(@PathVariable("idPago") Long idPago) {

        try {

            Boolean borrado = pagoService.deletePagoById(idPago);

            if(borrado) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el pago
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch(Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver todos los pagos finalizados de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Respuesta HTTP con la lista de pagos finalizados
     */
    @GetMapping("/historico/{idUsuario}")
    public ResponseEntity<List<PagoHistoricoDTO>> getPagosHistoricoByIdUsuario(@PathVariable("idUsuario") Long idUsuario) throws Exception {

        try {

            List<PagoHistoricoDTO> pago = pagoService.getPagosHistoricoByIdUsuario(idUsuario);

            if(pago != null) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(pago, HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el usuario
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver todos los pagos pendientes de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Respuesta HTTP con la lista de pagos pendientes
     */
    @GetMapping("/pendientes/{idUsuario}")
    public ResponseEntity<List<PagoPendienteDTO>> getPagosPendientesByIdUsuario(@PathVariable("idUsuario") Long idUsuario) throws Exception {

        try {

            List<PagoPendienteDTO> pago = pagoService.getPagosPendientesByIdUsuario(idUsuario);

            if(pago != null) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(pago, HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el usuario
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver el paz y salvo de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Respuesta HTTP con el paz y salvo
     */
    @GetMapping("/pazYSalvo/{idUsuario}")
    public ResponseEntity<Document> generarPazYSalvo(@PathVariable("idUsuario") Long idUsuario) {

        try {

            Document documento = pagoService.generarPazYSalvo(idUsuario);

            if(documento != null) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el usuario o la empresa
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (PendingPaymentsException e) {

            // HTTP 417 en caso de que el usuario no se encuentre a paz y salvo
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para aprobar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @param pagoDTO Pago
     * @return Respuesta HTTP con el pago
     */
    @PutMapping("/aprobar/{idPago}")
    public ResponseEntity<PagoDTO> aprobarPagoById(@PathVariable("idPago") Long idPago, @RequestBody PagoDTO pagoDTO) {

        try {

            PagoDTO pago = pagoService.aprobarPago(idPago, pagoDTO);

            if(pago != null) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(pago, HttpStatus.OK);

            } else {

                // HTTP 404 en caso de que no se encuentre el pago
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch(Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para crear pagos de forma masiva subiendo un archivo CSV
     * @param archivo Archivo a subir
     * @return Respuesta HTTP con la lista de pagos creados
     */
    @PostMapping("/masivo/")
    public ResponseEntity<List<PagoDTO>> createPagosCSV(@RequestParam("archivo") MultipartFile archivo) {

        try {

            if(!archivo.isEmpty()) {

                if(archivo.getContentType().equals("text/csv")) {

                    try {

                        // HTTP 200 en caso de que todo esté correcto
                        return new ResponseEntity<>(pagoService.createPagosCSV(archivo), HttpStatus.OK);

                    } catch (Exception e) {

                        // HTTP 400 en caso de que haya algún error
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

                    }

                } else {

                    // HTTP 400 en caso de que el archivo no sea de tipo CSV
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

                }

            } else {

                // HTTP 400 en caso de que el archivo esté vacío
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}