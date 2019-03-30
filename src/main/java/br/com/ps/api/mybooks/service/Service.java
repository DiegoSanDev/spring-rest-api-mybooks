package br.com.ps.api.mybooks.service;

import java.util.Optional;

public interface Service<T> {

    T atualizar(T entity);

    Optional<T> buscarPorId(int id);

    T inserir(T entity);

}