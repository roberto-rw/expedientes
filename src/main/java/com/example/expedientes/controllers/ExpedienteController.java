package com.example.expedientes.controllers;

import com.example.expedientes.services.ExpedienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ExpedienteController {

    @Autowired
    private ExpedienteService expedienteService;

    @PostMapping("expedientes/uploadFile")
    public ResponseEntity<String> receiveFile(@RequestParam("file") MultipartFile file){
        String message = expedienteService.saveFile(file);
        return new ResponseEntity<String>(message, HttpStatusCode.valueOf(200));
    }

    @GetMapping("expedientes/downloadFile/{fileName}")
    public ResponseEntity<Resource> sendFile(@PathVariable("fileName") String fileName){
        Resource archivo = expedienteService.getFile(fileName);
        HttpHeaders cabeceras = new HttpHeaders();
        cabeceras.add("Content-Disposition", "attachment; filename=" + fileName);

        Long lenght = null;
        try{
            lenght = archivo.contentLength();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Archivo Enviado");
        return ResponseEntity.ok()
                .headers(cabeceras)
                .contentLength(lenght)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(archivo);
    }

    @GetMapping("expedientes/prueba-gateway")
    public ResponseEntity<String> sendFile(){
        return new ResponseEntity<String>("Solicitud atendida", HttpStatusCode.valueOf(200));
    }
}
