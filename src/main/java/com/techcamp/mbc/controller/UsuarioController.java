package com.techcamp.mbc.controller;

import com.techcamp.mbc.dto.UsuarioDTO;
import com.techcamp.mbc.dto.UsuarioPagoDTO;
import com.techcamp.mbc.model.UsuarioLogin;
import com.techcamp.mbc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para el manejo de los usuarios
 * @author Marlon Pérez Ríos
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;

    }

    /**
     * Método para devolver todos los usuarios
     * @return Respuesta HTTP con la lista de usuarios
     */
    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver todos los usuarios con formato DTO
     * @return Respuesta HTTP con la lista de usuarios con formato DTO
     */
    @GetMapping("/pago/")
    public ResponseEntity<List<UsuarioPagoDTO>> getAllUsuariosPago() {

        try {

            // HTTP 200 en caso de que todo esté correcto
            return new ResponseEntity<>(usuarioService.getAllUsuariosPago(), HttpStatus.OK);

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Método para devolver la información de un usuario al hacer login
     * @param usuarioLogin Usuario del login
     * @return Respuesta HTTP el usuario
     */
    @PostMapping("/login/")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioLogin usuarioLogin) {

        try {

            UsuarioDTO usuario = usuarioService.getUsuarioLogin(usuarioLogin);

            if(usuario != null) {

                // HTTP 200 en caso de que todo esté correcto
                return new ResponseEntity<>(usuario, HttpStatus.OK);

            } else {

                // HTTP 401 en caso de que no se encuentre el usuario
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            }

        } catch (Exception e) {

            // HTTP 400 en caso de que haya algún error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}