package com.techcamp.mbc.service;

import com.techcamp.mbc.dto.EmpresaPagoDTO;
import com.techcamp.mbc.mapper.EmpresaMapper;
import com.techcamp.mbc.model.Empresa;
import com.techcamp.mbc.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para el manejo de las empresas
 * @author Marlon Pérez Ríos
 */
@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaMapper empresaMapper;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {

        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;

    }

    /**
     * Método para devolver todas las empresas
     * @return Lista de empresas
     */
    @Override
    public List<Empresa> getAllEmpresas() throws Exception {

        try {

            return empresaRepository.findAll();

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver todas las empresas con formato DTO
     * @return Lista de empresas con formato DTO
     */
    @Override
    public List<EmpresaPagoDTO> getAllEmpresasPago() throws Exception {

        try {

            return empresaMapper.empresaToEmpresaPagoDTOList(empresaRepository.findAll());

        } catch (Exception e) {

            throw new Exception();

        }

    }

}