package com.techcamp.mbc.service;

import com.techcamp.mbc.model.TipoPago;

import java.util.List;

/**
 * Interfaz del servicio para el manejo de los tipos de pago
 * @author Marlon Pérez Ríos
 */
public interface TipoPagoService {

    /**
     * Método para devolver todos los tipos de pago
     * @return Lista de tipos de pago
     */
    List<TipoPago> getAllTiposPago() throws Exception;

}