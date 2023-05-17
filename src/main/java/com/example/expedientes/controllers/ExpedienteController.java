package com.example.expedientes.controllers;

import com.example.expedientes.dto.RequestDTO;
import com.example.expedientes.services.ExpedienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/expedientes")
public class ExpedienteController {

    @Autowired
    private ExpedienteService expedienteService;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> receiveFile(@RequestParam("file") MultipartFile file){
        String message = expedienteService.saveFile(file);
        System.out.println("Archivo recibido");
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName}")
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

    @GetMapping("/prueba-gateway")
    public ResponseEntity<String> sendFile() {
        return new ResponseEntity<String>("Solicitud atendida", HttpStatus.OK);
    }

    @PostMapping(value = "/expedientes")
    public ResponseEntity<Resource> obtenerArchivo(@RequestBody RequestDTO permisoRequest) {
        String idPaciente = permisoRequest.getNssPaciente();
        String cedulaMedico = permisoRequest.getCedulaMedico();
        String nombreArchivo = permisoRequest.getNombreArchivo();
        return expedienteService.obtenerArchivo(idPaciente, cedulaMedico, nombreArchivo);
    }

    @GetMapping ("/prueba")
    public String prueba(){
        return "metodo de prueba funciona correctamente";
    }

}
