package com.techcamp.mbc.controller;

import com.techcamp.mbc.model.Archivo;
import com.techcamp.mbc.service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controlador para la carga y descarga de archivos
 * @author Marlon Pérez Ríos
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/v1/archivos")
public class ArchivoController {

    @Autowired
    ArchivoService archivoService;

    public ArchivoController(ArchivoService archivoService) {

        this.archivoService = archivoService;

    }

    /**
     * Método para subir archivo a carpeta local ubicada en la ruta del proyecto
     * @param archivo Archivo a subir
     * @return Respuesta HTTP con el archivo subido
     */
    @PostMapping("/subir/")
    public ResponseEntity<Archivo> subirArchivo(@RequestParam("soporte") MultipartFile archivo) {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(archivoService.subirArchivo(archivo), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para descargar archivo desde carpeta local ubicada en la ruta del proyecto
     * @param nombreArchivo Nombre del archivo
     * @return Respuesta HTTP de la descarga del archivo
     */
    @GetMapping("/bajar/{nombreArchivo}")
    public ResponseEntity<?> bajarArchivo(@PathVariable("nombreArchivo") String nombreArchivo) {

        try {

            Resource resource = archivoService.bajarArchivo(nombreArchivo);

            if(resource == null) {

                // HTTP 404 en caso de que no se encuentre el archivo
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

            String contentType = "application/octet-stream";
            String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

            // HTTP 200 en caso de que todo esté correcto
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(resource);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}