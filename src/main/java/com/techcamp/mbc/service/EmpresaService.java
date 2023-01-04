package com.techcamp.mbc.service;

import com.techcamp.mbc.dto.EmpresaPagoDTO;
import com.techcamp.mbc.model.Empresa;

import java.util.List;

/**
 * Interfaz del servicio para el manejo de las empresas
 * @author Marlon Pérez Ríos
 */
public interface EmpresaService {

    /**
     * Método para devolver todas las empresas
     * @return Lista de empresas
     */
    List<Empresa> getAllEmpresas() throws Exception;

    /**
     * Método para devolver todas las empresas con formato DTO
     * @return Lista de empresas con formato DTO
     */
    List<EmpresaPagoDTO> getAllEmpresasPago() throws Exception;

}