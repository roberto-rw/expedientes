package com.example.expedientes.services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.permisosservicegrpc.grpc.PermisoRequest;
import com.permisosservicegrpc.grpc.PermisoResponse;
import com.permisosservicegrpc.grpc.permisosServiceGrpc;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    public Resource getFile(String name) {
        Resource recurso = new FileSystemResource("D:\\Documentos HDD\\Sexto Semestre\\Sistemas Distribuidos\\Proyecto Final\\ArchivosServidor\\" + name);
        return recurso;
    }

    public ResponseEntity<Resource> obtenerArchivo(String idPaciente, String cedulaMedico, String nombreArchivo) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        permisosServiceGrpc.permisosServiceStub stub = permisosServiceGrpc.newStub(channel);

        PermisoRequest request = PermisoRequest.newBuilder()
                .setCedulaMedico(cedulaMedico)
                .setIdPaciente(idPaciente)
                .build();

        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean permisoValido = new AtomicBoolean(false);
        StreamObserver<PermisoResponse> responseObserver = new StreamObserver<PermisoResponse>() {
            @Override
            public void onNext(PermisoResponse response) {
                boolean permiso = response.getPermiso();
                System.out.println("Permiso: " + permiso);
                permisoValido.set(permiso);
            }

            @Override
            public void onError(Throwable thrwbl) {
                System.out.println("Ocurrio un error al obtener el permiso: " + thrwbl.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Operación terminada");
                channel.shutdown();
                latch.countDown();
            }
        };

        stub.getPermiso(request, responseObserver);

        try {
            latch.await();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        if (permisoValido.get()) {
            Resource recurso = new FileSystemResource("D:\\Documentos HDD\\Sexto Semestre\\Sistemas Distribuidos\\Proyecto Final\\ArchivosServidor\\" + nombreArchivo);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                    .body(recurso);
        } else {
            System.out.println("Permiso no condedido");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
