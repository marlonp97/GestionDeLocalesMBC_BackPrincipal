package com.techcamp.mbc.exception;

import com.techcamp.mbc.model.Archivo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * Excepción para cuando haya errores en la cargada o descargada de archivos
 * @author Marlon Pérez Ríos
 */
@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Archivo> handleMaxSizeException(MaxUploadSizeExceededException e) {

        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

    }

}