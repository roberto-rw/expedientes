package com.example.expedientes.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ExpedienteService {

    public String saveFile(MultipartFile file){
        try {
            file.transferTo(new File("D:\\Documentos HDD\\Sexto Semestre\\Sistemas Distribuidos\\Proyecto Final\\ArchivosServidor\\" + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Archivo recibido correctamente";
    }

    public Resource getFile(String name){
        Resource recurso = new FileSystemResource("D:\\Documentos HDD\\Sexto Semestre\\Sistemas Distribuidos\\Proyecto Final\\ArchivosServidor\\" + name);
        return recurso;
    }
}
