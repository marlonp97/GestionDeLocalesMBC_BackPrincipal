package com.techcamp.mbc.service;

import com.techcamp.mbc.dto.UsuarioDTO;
import com.techcamp.mbc.dto.UsuarioPagoDTO;
import com.techcamp.mbc.model.UsuarioLogin;

import java.util.List;

/**
 * Interfaz del servicio para el manejo de los usuarios
 * @author Marlon Pérez Ríos
 */
public interface UsuarioService {

    /**
     * Método para devolver todos los usuarios
     * @return La lista de usuarios
     */
    List<UsuarioDTO> getAllUsuarios() throws Exception;

    /**
     * Método para devolver todos los usuarios con formato DTO
     * @return Lista de usuarios con formato DTO
     */
    List<UsuarioPagoDTO> getAllUsuariosPago() throws Exception;

    /**
     * Método para devolver la información de un usuario al hacer login
     * @param usuarioLogin Usuario del login
     * @return Usuario
     */
    UsuarioDTO getUsuarioLogin(UsuarioLogin usuarioLogin) throws Exception;

}