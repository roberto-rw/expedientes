package com.permisosservicegrpc.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.1)",
    comments = "Source: permisos.proto")
public class permisosServiceGrpc {

  private permisosServiceGrpc() {}

  public static final String SERVICE_NAME = "permisosService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.permisosservicegrpc.grpc.PermisoRequest,
      com.permisosservicegrpc.grpc.PermisoResponse> METHOD_GET_PERMISO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "permisosService", "getPermiso"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.permisosservicegrpc.grpc.PermisoRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.permisosservicegrpc.grpc.PermisoResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static permisosServiceStub newStub(io.grpc.Channel channel) {
    return new permisosServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static permisosServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new permisosServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static permisosServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new permisosServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class permisosServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getPermiso(com.permisosservicegrpc.grpc.PermisoRequest request,
        io.grpc.stub.StreamObserver<com.permisosservicegrpc.grpc.PermisoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_PERMISO, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_PERMISO,
            asyncUnaryCall(
              new MethodHandlers<
                com.permisosservicegrpc.grpc.PermisoRequest,
                com.permisosservicegrpc.grpc.PermisoResponse>(
                  this, METHODID_GET_PERMISO)))
          .build();
    }
  }

  /**
   */
  public static final class permisosServiceStub extends io.grpc.stub.AbstractStub<permisosServiceStub> {
    private permisosServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private permisosServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected permisosServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new permisosServiceStub(channel, callOptions);
    }

    /**
     */
    public void getPermiso(com.permisosservicegrpc.grpc.PermisoRequest request,
        io.grpc.stub.StreamObserver<com.permisosservicegrpc.grpc.PermisoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_PERMISO, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class permisosServiceBlockingStub extends io.grpc.stub.AbstractStub<permisosServiceBlockingStub> {
    private permisosServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private permisosServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected permisosServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new permisosServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.permisosservicegrpc.grpc.PermisoResponse getPermiso(com.permisosservicegrpc.grpc.PermisoRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_PERMISO, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class permisosServiceFutureStub extends io.grpc.stub.AbstractStub<permisosServiceFutureStub> {
    private permisosServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private permisosServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected permisosServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new permisosServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.permisosservicegrpc.grpc.PermisoResponse> getPermiso(
        com.permisosservicegrpc.grpc.PermisoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_PERMISO, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PERMISO = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final permisosServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(permisosServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PERMISO:
          serviceImpl.getPermiso((com.permisosservicegrpc.grpc.PermisoRequest) request,
              (io.grpc.stub.StreamObserver<com.permisosservicegrpc.grpc.PermisoResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_GET_PERMISO);
  }

}
