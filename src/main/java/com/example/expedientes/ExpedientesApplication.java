package com.example.expedientes;

import com.permisosservicegrpc.grpc.PermisoRequest;
import com.permisosservicegrpc.grpc.PermisoResponse;
import com.permisosservicegrpc.grpc.permisosServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpedientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpedientesApplication.class, args);


    }
    
}
