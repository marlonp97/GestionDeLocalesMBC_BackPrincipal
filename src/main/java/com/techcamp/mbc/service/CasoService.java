package com.techcamp.mbc.service;

import com.techcamp.mbc.dto.CasoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del servicio para el manejo de los casos de los pagos
 * @author Marlon Pérez Ríos
 */
public interface CasoService {

    /**
     * Método para devolver todos los casos
     * @return Lista de casos
     */
    List<CasoDTO> getAllCasos() throws Exception;

    /**
     * Método para crear un caso
     * @param casoDTO Caso
     * @return Caso creado
     */
    CasoDTO createCaso(CasoDTO casoDTO) throws Exception;

    /**
     * Método para devolver un caso al buscarlo por su ID
     * @param idCaso ID del caso
     * @return Caso
     */
    Optional<CasoDTO> getCasoById(Long idCaso) throws Exception;

    /**
     * Método para actualizar un caso al buscarlo por su ID
     * @param idCaso ID del caso
     * @param casoDTO Caso
     * @return Caso actualizado
     */
    CasoDTO updateCaso(Long idCaso, CasoDTO casoDTO) throws Exception;

}