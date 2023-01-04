package com.techcamp.mbc.service;

import com.techcamp.mbc.model.TipoPago;
import com.techcamp.mbc.repository.TipoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para el manejo de los tipos de pago
 * @author Marlon Pérez Ríos
 */
@Service
public class TipoPagoServiceImpl implements TipoPagoService {

    @Autowired
    private TipoPagoRepository tipoPagoRepository;

    public TipoPagoServiceImpl(TipoPagoRepository tipoPagoRepository) {

        this.tipoPagoRepository = tipoPagoRepository;

    }

    /**
     * Método para devolver todos los tipos de pago
     * @return Lista de tipos de pago
     */
    @Override
    public List<TipoPago> getAllTiposPago() throws Exception {

        try {

            return tipoPagoRepository.findAll();

        } catch (Exception e) {

            throw new Exception();

        }

    }

}