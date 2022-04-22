package com.desafioDevSuperior.desafio.capitulo1.client.services.exception;

public class DatabaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatabaseException(String msg){
        super(msg);
    }
}