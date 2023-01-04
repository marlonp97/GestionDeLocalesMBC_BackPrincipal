package com.techcamp.mbc.service;

import com.techcamp.mbc.model.EstadoPago;

import java.util.List;

/**
 * Interfaz del servicio para el manejo de los estados de los pagos
 * @author Marlon Pérez Ríos
 */
public interface EstadoPagoService {

    /**
     * Método para devolver todos los estados de los pagos
     * @return Lista de estados de los pagos
     */
    List<EstadoPago> getAllEstadosPago() throws Exception;

}