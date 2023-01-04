package com.techcamp.mbc.service;

import com.techcamp.mbc.model.EstadoPago;
import com.techcamp.mbc.repository.EstadoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para el manejo de los estados de los pagos
 * @author Marlon Pérez Ríos
 */
@Service
public class EstadoPagoServiceImpl implements EstadoPagoService {

    @Autowired
    private EstadoPagoRepository estadoPagoRepository;

    public EstadoPagoServiceImpl(EstadoPagoRepository estadoPagoRepository) {

        this.estadoPagoRepository = estadoPagoRepository;

    }

    /**
     * Método para devolver todos los estados de los pagos
     * @return Lista de estados de los pagos
     */
    @Override
    public List<EstadoPago> getAllEstadosPago() throws Exception {

        try {

            return estadoPagoRepository.findAll();

        } catch (Exception e) {

            throw new Exception();

        }

    }

}