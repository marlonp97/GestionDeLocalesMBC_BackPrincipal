package com.techcamp.mbc.service;

import com.techcamp.mbc.dto.LocalDTO;
import com.techcamp.mbc.dto.LocalPagoDTO;
import com.techcamp.mbc.mapper.LocalMapper;
import com.techcamp.mbc.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para el manejo de los locales
 * @author Marlon Pérez Ríos
 */
@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalMapper localMapper;

    public LocalServiceImpl(LocalRepository localRepository, LocalMapper localMapper) {

        this.localRepository = localRepository;
        this.localMapper = localMapper;

    }

    /**
     * Método para devolver todos los locales
     * @return Lista de locales
     */
    @Override
    public List<LocalDTO> getAllLocales() throws Exception {

        try {

            return localMapper.localToLocalDTOList(localRepository.findAll());

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver todos los locales con formato DTO
     * @return Lista de locales con formato DTO
     */
    @Override
    public List<LocalPagoDTO> getAllLocalesPago() throws Exception {

        try {

            return localMapper.localToLocalPagoDTOList(localRepository.findAll());

        } catch (Exception e) {

            throw new Exception();

        }

    }

}