package com.techcamp.mbc.service;

import com.techcamp.mbc.dto.UsuarioDTO;
import com.techcamp.mbc.dto.UsuarioPagoDTO;
import com.techcamp.mbc.mapper.UsuarioMapper;
import com.techcamp.mbc.model.UsuarioLogin;
import com.techcamp.mbc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para el manejo de los usuarios
 * @author Marlon Pérez Ríos
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {

        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;

    }

    /**
     * Método para devolver todos los usuarios
     * @return La lista de usuarios
     */
    @Override
    public List<UsuarioDTO> getAllUsuarios() throws Exception {

        try {

            return usuarioMapper.usuarioToUsuarioDTOList(usuarioRepository.findAll());

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver todos los usuarios con formato DTO
     * @return Lista de usuarios con formato DTO
     */
    @Override
    public List<UsuarioPagoDTO> getAllUsuariosPago() throws Exception {

        try {

            return usuarioMapper.usuarioToUsuarioPagoDTOList(usuarioRepository.findAll());

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver la información de un usuario al hacer login
     * @param usuarioLogin Usuario del login
     * @return Usuario
     */
    @Override
    public UsuarioDTO getUsuarioLogin(UsuarioLogin usuarioLogin) throws Exception {

        try {

            return usuarioMapper.usuarioToUsuarioDTO(
                    usuarioRepository.findByCorreoAndClave(
                            usuarioLogin.getCorreo(), usuarioLogin.getClave()).orElse(null));

        } catch (Exception e) {

            throw new Exception();

        }

    }

}