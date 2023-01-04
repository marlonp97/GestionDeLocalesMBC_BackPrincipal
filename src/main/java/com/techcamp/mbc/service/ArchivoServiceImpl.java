package com.techcamp.mbc.service;

import com.techcamp.mbc.model.Archivo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servicio para la carga y descarga de archivos
 * @author Marlon Pérez Ríos
 */
@Service
public class ArchivoServiceImpl implements ArchivoService {

    private final Path root = Paths.get("uploads");

    /**
     * Método para subir archivo a carpeta local ubicada en la ruta del proyecto
     * @param archivo Archivo a subir
     * @return Archivo subido
     */
    @Override
    public Archivo subirArchivo(MultipartFile archivo) throws Exception {

        try (InputStream inputStream = archivo.getInputStream()) {

            String nombreArchivo = StringUtils.cleanPath(archivo.getOriginalFilename());

            // Los archivos se guardan en carpeta "uploads" en la carpeta raiz del proyecto
            Path directorioArchivos = Paths.get("uploads");

            if (!Files.exists(directorioArchivos)) {

                Files.createDirectories(directorioArchivos);

            }

            // Obtener fecha y tiempo actual
            DateTimeFormatter formatoTiempo = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            LocalDateTime tiempoActual = LocalDateTime.now();
            String tiempoActualFormateado = tiempoActual.format(formatoTiempo);

            // Asignar fecha y tiempo actual al principio del nombre del archivo
            String nombreCompletoArchivo = tiempoActualFormateado + " - " + nombreArchivo;
            Path rutaArchivo = directorioArchivos.resolve(nombreCompletoArchivo);
            Files.copy(inputStream, rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

            Archivo archivoSubido = new Archivo();
            archivoSubido.setSoporte(nombreCompletoArchivo);

            return archivoSubido;

        } catch (Exception e) {

            throw new Exception();

        }

    }

    @Override
    public Resource bajarArchivo(String nombreArchivo) throws Exception {

        try {

            // Obtener archivo de la carpeta "uploads"
            Path directorioArchivos = Paths.get("uploads");
            Path rutaArchivo = directorioArchivos.resolve(nombreArchivo);
            Resource resource = new UrlResource(rutaArchivo.toUri());

            if(resource.exists() || resource.isReadable()) {

                return resource;

            } else {

                return null;

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

}