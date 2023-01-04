package com.techcamp.mbc.service;

import com.techcamp.mbc.dto.CasoDTO;
import com.techcamp.mbc.exception.PaymentWithExistingCaseException;
import com.techcamp.mbc.mapper.CasoMapper;
import com.techcamp.mbc.model.Caso;
import com.techcamp.mbc.model.EstadoCaso;
import com.techcamp.mbc.model.Pago;
import com.techcamp.mbc.model.Usuario;
import com.techcamp.mbc.repository.CasoRepository;
import com.techcamp.mbc.repository.EstadoCasoRepository;
import com.techcamp.mbc.repository.PagoRepository;
import com.techcamp.mbc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para el manejo de los casos de los pagos
 * @author Marlon Pérez Ríos
 */
@Service
public class CasoServiceImpl implements CasoService {

    @Autowired
    private CasoRepository casoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoCasoRepository estadoCasoRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private CasoMapper casoMapper;

    public CasoServiceImpl(CasoRepository casoRepository, UsuarioRepository usuarioRepository,
                           EstadoCasoRepository estadoCasoRepository, PagoRepository pagoRepository,
                           CasoMapper casoMapper) {

        this.casoRepository = casoRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadoCasoRepository = estadoCasoRepository;
        this.pagoRepository = pagoRepository;
        this.casoMapper = casoMapper;

    }

    /**
     * Método para devolver todos los casos
     * @return Lista de casos
     */
    @Override
    public List<CasoDTO> getAllCasos() throws Exception {

        try {

            return casoMapper.casoToCasoDTOList(casoRepository.findAll());

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para crear un caso
     * @param casoDTO Caso
     * @return Caso creado
     */
    @Override
    public CasoDTO createCaso(CasoDTO casoDTO) throws PaymentWithExistingCaseException, Exception {

        try {

            Pago pagoDB = pagoRepository.findById(casoDTO.getPagoCaso().getIdPago()).orElse(null);

            if(pagoDB != null) {

                Caso casoDB = casoRepository.findByPago(pagoDB).orElse(null);

                if(casoDB == null) {

                    Usuario usuarioDB = usuarioRepository.findByCorreo(casoDTO.getUsuario()).orElse(null);
                    EstadoCaso estadoCasoDB = estadoCasoRepository.findByEstado(casoDTO.getEstadoCaso()).orElse(null);

                    if((usuarioDB != null) && (estadoCasoDB != null)) {

                        // Crear caso si el pago existe, si el pago no tiene ya un caso creado y si la información ingresada es correcta
                        Caso caso = new Caso();
                        caso.setDescripcion(casoDTO.getDescripcion());
                        caso.setSoporte(casoDTO.getSoporte());
                        caso.setUsuario(usuarioDB);
                        caso.setEstadoCaso(estadoCasoDB);
                        caso.setPago(pagoDB);

                        return casoMapper.casoToCasoDTO(casoRepository.save(caso));

                    } else {

                        return null;

                    }

                } else {

                    // Lanzar excepción si el pago ya tiene un caso creado
                    throw new PaymentWithExistingCaseException();

                }

            } else {

                return null;

            }

        } catch (PaymentWithExistingCaseException e) {

            throw new PaymentWithExistingCaseException();

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver un caso al buscarlo por su ID
     * @param idCaso ID del caso
     * @return Caso
     */
    @Override
    public Optional<CasoDTO> getCasoById(Long idCaso) throws Exception {

        try {

            return Optional.ofNullable(casoMapper.casoToCasoDTO(casoRepository.findById(idCaso).orElse(null)));

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para actualizar un caso al buscarlo por su ID
     * @param idCaso ID del caso
     * @param casoDTO Caso
     * @return Caso actualizado
     */
    @Override
    public CasoDTO updateCaso(Long idCaso, CasoDTO casoDTO) throws Exception {

        try {

            Caso casoDB = casoRepository.findById(idCaso).orElse(null);

            if(casoDB != null) {

                EstadoCaso estadoCasoDB = estadoCasoRepository.findByEstado(casoDTO.getEstadoCaso()).orElse(null);

                if(estadoCasoDB != null) {

                    // Actualizar caso si el caso existe y si la información ingresada es correcta
                    casoDB.setEstadoCaso(estadoCasoDB);

                    return casoMapper.casoToCasoDTO(casoRepository.save(casoDB));

                } else {

                    return null;

                }

            } else {

                return null;

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

}