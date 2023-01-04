package com.techcamp.mbc.service;

import com.techcamp.mbc.dto.LocalDTO;
import com.techcamp.mbc.dto.LocalPagoDTO;

import java.util.List;

/**
 * Interfaz del servicio para el manejo de los locales
 * @author Marlon Pérez Ríos
 */
public interface LocalService {

    /**
     * Método para devolver todos los locales
     * @return Lista de locales
     */
    List<LocalDTO> getAllLocales() throws Exception;

    /**
     * Método para devolver todos los locales con formato DTO
     * @return Lista de locales con formato DTO
     */
    List<LocalPagoDTO> getAllLocalesPago() throws Exception;

}