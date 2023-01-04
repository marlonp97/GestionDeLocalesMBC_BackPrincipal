package com.techcamp.mbc.service;

import com.techcamp.mbc.model.Archivo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interfaz del servicio para la carga y descarga de archivos
 * @author Marlon Pérez Ríos
 */
public interface ArchivoService {

    /**
     * Método para subir archivo a carpeta local ubicada en la ruta del proyecto
     * @param archivo Archivo a subir
     * @return Archivo subido
     */
    Archivo subirArchivo(MultipartFile archivo) throws Exception;

    /**
     * Método para descargar archivo desde carpeta local ubicada en la ruta del proyecto
     * @param nombreArchivo Nombre del archivo
     * @return Archivo
     */
    Resource bajarArchivo(String nombreArchivo) throws Exception;

}