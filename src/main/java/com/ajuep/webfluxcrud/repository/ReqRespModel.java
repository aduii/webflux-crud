package com.ajuep.webfluxcrud.repository;

public class ReqRespModel<T> implements IReqRespModel<T> {
    private T data;
    private String message;

    public ReqRespModel() {
    }
    public ReqRespModel(T data, String message) {
        this.data = data;
        this.message = message;
    }
    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public String getMessage() {
        return this.message;
    }


}
